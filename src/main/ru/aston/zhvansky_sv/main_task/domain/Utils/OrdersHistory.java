package domain.Utils;


import domain.Client.Order;

import java.util.ArrayList;
import java.util.Comparator;

public final class OrdersHistory {

    public static ArrayList<Order> allOrders = new ArrayList<>();

    private OrdersHistory() {
    }

    public static ArrayList<Order> getSortedOrders() {
        allOrders.sort(Comparator.comparing(o -> o.getUser().getSurName()));
        return allOrders;
    }

    public static ArrayList<Order> getAllOrders() {
        return allOrders;
    }

}
