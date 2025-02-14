package domain.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Client.Order;
import domain.Client.User;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * A utility class for calculating discounts on orders.
 * Provides a method for calculating the total price of an order, including discounts.
 */
public final class DiscountUtils {

    private static final Logger logger = LoggerFactory.getLogger(DiscountUtils.class);

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
    private DiscountUtils() {
    }

    /**
     * Calculates the total price of the order, including discounts.
     * The discount depends on the number of medicines in the order and the type of user.
     *
     * @param order The order for which the discount is calculated.
     * @param user  The user who places the order.
     * @return The total price of the order, including the discount. Returns -1 if the input data is incorrect.
     */
    public static BigDecimal getPriceWithDiscount(Order order, User user) {
        logger.debug("Calculating discount for order: {}, user: {}", order, user);
        if (Objects.isNull(order) || Objects.isNull(user) || order.getMedicines().isEmpty()) {
            logger.warn("Invalid input: order or user is null, or order has no medicines");
            return BigDecimal.ONE.negate();
        }
        int countPills = order.getMedicines().size();
        BigDecimal priceOfPills = order.getPriceOfPills();
        logger.debug("Count of pills: {}, Price of pills: {}", countPills, priceOfPills);
        if (priceOfPills.compareTo(BigDecimal.ZERO) == 0) {
            logger.warn("Invalid price of pills: {}", priceOfPills);
            return BigDecimal.ONE.negate();
        }
        BigDecimal discount = calculateDiscount(countPills, user);
        logger.debug("Total discount: {}", discount);
        BigDecimal finalPrice = (priceOfPills.multiply(discount)).divide(ONE_HUNDRED, RoundingMode.HALF_EVEN);
        logger.info("Final price with discount: {}", finalPrice);
        return finalPrice;
    }

    private static BigDecimal calculateDiscount(int countPills, User user) {
        BigDecimal discount = BigDecimal.valueOf(Math.min(countPills, MAX_COUNT_MEDICINE_FOR_SALE));
        logger.debug("Initial discount: {}", discount);
        discount = discount.add(user.getPersonType().ADDITIONAL_DISCOUNT);
        logger.debug("Additional discount applied. New discount: {}", discount);
        return discount;
    }
}