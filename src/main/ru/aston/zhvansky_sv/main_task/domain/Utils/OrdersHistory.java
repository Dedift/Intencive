package domain.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Client.Order;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Utility class for managing and retrieving order history.
 * Provides functionality to add orders, retrieve all orders, and retrieve orders sorted by user surname.
 */
public final class OrdersHistory {

    private static final Logger logger = LoggerFactory.getLogger(OrdersHistory.class);
    private static final List<Order> allOrders = new ArrayList<>();

    /**
     * Private constructor to prevent instantiation of the class.
     * This class is intended to be used statically.
     */
    private OrdersHistory() {
    }

    /**
     * Adds an order to the order history.
     * If the provided order is null, a warning is logged, and the order is not added.
     *
     * @param order The order to be added to the history.
     */
    public static void addOrder(Order order) {
        if (order == null) {
            logger.warn("Attempted to add a null order to the history.");
            return;
        }
        allOrders.add(order);
        logger.info("Order added to history: {}", order);
    }

    /**
     * Retrieves a list of all orders sorted by the surname of the user who placed the order.
     * The original list of orders remains unchanged.
     *
     * @return A sorted list of orders by user surname.
     */
    public static List<Order> getSortedOrders() {
        logger.debug("Sorting orders by user surname.");
        return allOrders.stream()
                .sorted(Comparator.comparing(
                        o -> o.getUser().getSurName()
                ))
                .peek(order -> logger.debug("Processed order: {}", order))
                .toList();
    }

    /**
     * Retrieves a list of all orders in the history.
     *
     * @return A list of all orders.
     */
    public static List<Order> getAllOrders() {
        logger.info("Returning all orders. Total orders: {}", allOrders.size());
        return allOrders;
    }
}