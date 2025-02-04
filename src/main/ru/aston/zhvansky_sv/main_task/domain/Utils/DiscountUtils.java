package domain.Utils;


import domain.Client.Order;
import domain.Client.PersonType;
import domain.Client.User;

import java.util.Objects;

public final class DiscountUtils {

    public static final int MAX_COUNT_MEDICINE_FOR_SALE = 15;
    public static final int ADDITIONAL_DISCOUNT = 5;
    public static final int ONE_HUNDRED = 100;

    private DiscountUtils() {
    }

    public static double getPriceWithDiscount(Order order, User user) {
        if (Objects.isNull(order) || Objects.isNull(user) || order.getMedicines().isEmpty()) return -1;
        int countPills = order.getMedicines().size();
        double priceOfPills = order.getPriceOfPills();
        double discount = Math.min(countPills, MAX_COUNT_MEDICINE_FOR_SALE);
        if (!user.getPersonType().equals(PersonType.DEFAULT) || !(user.getPersonType() == null)) {
            discount += ADDITIONAL_DISCOUNT;
        }
        return priceOfPills * discount / ONE_HUNDRED;
    }
}