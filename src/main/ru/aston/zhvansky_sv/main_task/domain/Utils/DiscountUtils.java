package domain.Utils;


import domain.Client.Order;
import domain.Client.PersonType;
import domain.Client.User;

public final class DiscountUtils {

    private DiscountUtils() {
    }

    public static double getPriceWithDiscount(Order order, User user) {
        if (order == null || user == null) return -1;
        int countPills = order.getMedicines().size();
        double priceOfPills = order.getPriceOfPills();
        double discount = Math.min(countPills, 15);
        if (!user.getPersonType().equals(PersonType.DEFAULT) || !(user.getPersonType() == null)) {
            discount += 5;
        }
        return priceOfPills * discount / 100;
    }
}