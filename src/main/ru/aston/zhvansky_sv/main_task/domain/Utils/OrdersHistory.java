package domain.Utils;


import domain.Client.Order;

import java.util.ArrayList;
import java.util.Comparator;

public final class OrdersHistory {

    private static ArrayList<Order> allOrders = new ArrayList<>();

    private OrdersHistory() {
    }

    public static ArrayList<Order> getSortedOrders() {
        ArrayList<Order> ordersForSort = allOrders;
        ordersForSort.sort(Comparator.comparing(o -> o.getUser().getSurName()));
        return ordersForSort;
    }

    public static ArrayList<Order> getAllOrders() {
        return allOrders;
    }

}
