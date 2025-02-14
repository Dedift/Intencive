package domain.Utils;

import domain.Client.Recipe;
import domain.Medicine.Medicine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Client.Order;
import domain.Client.User;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A utility class for financial operations.
 * Provides a method for create order, calculating the total price of an order, including discounts, pay order.
 */
public final class FinancialUtils {

    private static final Logger log = LoggerFactory.getLogger(FinancialUtils.class);

    /**
     * The maximum number of medicines for which a discount can be applied.
     */
    public static final int MAX_COUNT_MEDICINE_FOR_SALE = 15;

    /**
     * A constant for calculating percentages.
     */
    public static final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100);

    /**
     * Private constructor to prevent instantiation of the class.
     * This class is intended to be used statically.
     */
    private FinancialUtils() {
    }

    /**
     * Creates a new order for the user only with legal medicine.
     *
     * @param medicines The list of medicines to be included in the order.
     * @param user The user who places the order.
     * @return          The created order.
     */
    public static Order createOrder(ArrayList<Medicine> medicines, User user) {
        if (user == null) {
            log.error("Attempted to create an order from null user.");
            throw new IllegalArgumentException("User cannot be null.");
        }
        if(Objects.nonNull(medicines)) {
            List<Medicine> legalMedicines = medicines.stream()
                    .filter(medicine -> !medicine.isNeedRecipe() || user.getRecipes().contains(new Recipe(user, medicine)))
                    .toList();
            Order order = new Order(legalMedicines, user);
            OrdersHistory.addOrder(order);
            log.debug("Created new Order for User: name={}, surName={}, number of medicines={}",
                    user.getName(), user.getSurName(), legalMedicines.size());
            return order;
        } else {
            Order order = new Order(new ArrayList<>(), user);
            OrdersHistory.addOrder(order);
            log.debug("Created empty Order for User: name={}, surName={}", user.getName(), user.getSurName());
            return order;
        }
    }

    /**
     * Pays for the specified order using the user's money.
     *
     * @param order The order to be paid.
     * @param user The user who pays for this order.
     */
    public static void payOrder(Order order, User user) {
        if (order == null) {
            log.error("Attempted to pay for a null order.");
            throw new IllegalArgumentException("Order cannot be null.");
        } else if (user == null) {
            log.error("Attempted to pay an order from null user.");
            throw new IllegalArgumentException("User cannot be null.");
        }
        BigDecimal priceWithDiscount = FinancialUtils.getPriceWithDiscount(order, user);
        if (priceWithDiscount.compareTo(BigDecimal.ZERO) < 0) {
            log.error("Invalid price for order: {}", order);
            throw new IllegalArgumentException("Invalid price for order.");
        }

        if (user.getMoney().compareTo(priceWithDiscount) < 0) {
            log.error("User does not have enough money to pay for the order: name={}, surName={}, required={}, available={}",
                    user.getName(), user.getSurName(), priceWithDiscount, user.getMoney());
            throw new IllegalStateException("Not enough money to pay for the order.");
        }
        user.setMoney(user.getMoney().subtract(priceWithDiscount));
        log.debug("User paid for Order: name={}, surName={}, amount paid={}, remaining money={}",
                user.getName(), user.getSurName(), priceWithDiscount, user.getMoney());
    }

    /**
     * Calculates the total price of the order, including discounts.
     * The discount depends on the number of medicines in the order and the type of user.
     *
     * @param order The order for which the discount is calculated.
     * @param user  The user who places the order.
     * @return The total price of the order, including the discount. Returns -1 if the input data is incorrect.
     */
    private static BigDecimal getPriceWithDiscount(Order order, User user) {
        log.debug("Calculating discount for order: {}, user: {}", order, user);
        if (Objects.isNull(order) || Objects.isNull(user) || order.getMedicines().isEmpty()) {
            log.warn("Invalid input: order or user is null, or order has no medicines");
            return BigDecimal.ONE.negate();
        }
        int countMedicines = order.getMedicines().size();
        BigDecimal priceOfMedicines = order.getPriceOfMedicines();
        log.debug("Count of medicines: {}, Price of medicines: {}", countMedicines, priceOfMedicines);
        if (priceOfMedicines.compareTo(BigDecimal.ZERO) == 0) {
            log.warn("Invalid price of medicines: {}", priceOfMedicines);
            return BigDecimal.ONE.negate();
        }
        BigDecimal discount = calculateDiscount(countMedicines, user);
        log.debug("Total discount: {}", discount);
        BigDecimal finalPrice = (priceOfMedicines.multiply(discount)).divide(ONE_HUNDRED, RoundingMode.HALF_EVEN);
        log.info("Final price with discount: {}", finalPrice);
        return finalPrice;
    }

    /**
     * Calculates discount of the order.
     * The discount depends on the number of medicines in the order and the type of user.
     *
     * @param countMedicines The count of medicines in the order.
     * @param user  The user who places the order.
     * @return Discount of the order.
     */
    private static BigDecimal calculateDiscount(int countMedicines, User user) {
        BigDecimal discount = BigDecimal.valueOf(Math.min(countMedicines, MAX_COUNT_MEDICINE_FOR_SALE));
        log.debug("Initial discount: {}", discount);
        discount = discount.add(user.getPersonType().ADDITIONAL_DISCOUNT);
        log.debug("Additional discount applied. New discount: {}", discount);
        return discount;
    }
}