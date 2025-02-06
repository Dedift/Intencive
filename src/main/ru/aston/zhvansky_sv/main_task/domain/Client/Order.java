package domain.Client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Medicine.Medicine;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents an order made by a user.
 * Contains a list of medicines and the user who placed the order.
 */
public class Order {

    public static final double ZERO = 0.0;
    private static final Logger log = LoggerFactory.getLogger(Order.class);
    private ArrayList<Medicine> medicines = new ArrayList<>();
    private User user;

    /**
     * Constructs a new Order instance.
     *
     * @param medicines The list of medicines in the order.
     * @param user      The user who placed the order.
     */
    public Order(ArrayList<Medicine> medicines, User user) {
        this.medicines = medicines;
        this.user = user;
        log.debug("Created new Order: user={}, number of medicines={}", user, medicines.size());
    }

    /**
     * Calculates the total price of all medicines in the order.
     *
     * @return The total price of the medicines in the order.
     */
    public double getPriceOfPills() {
        double sum = ZERO;
        for (Medicine medicine : medicines) {
            sum += medicine.getPrice();
        }
        log.debug("Calculated total price of medicines in order: {}", sum);
        return sum;
    }

    public User getUser() {
        log.debug("Getting user of order: {}", user);
        return user;
    }

    public ArrayList<Medicine> getMedicines() {
        log.debug("Getting list of medicines in order. Number of medicines: {}", medicines.size());
        return medicines;
    }

    public void setMedicines(ArrayList<Medicine> medicines) {
        log.debug("Setting list of medicines in order. Old size: {}, New size: {}", this.medicines.size(), medicines.size());
        this.medicines = medicines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            log.debug("Comparing Order with itself: true");
            return true;
        }
        if (!(o instanceof Order order)) {
            log.debug("Comparing Order with a different type: false");
            return false;
        }
        boolean isEqual = Objects.equals(medicines, order.medicines);
        log.debug("Comparing Order with another Order. Result: {}", isEqual);
        return isEqual;
    }

    @Override
    public int hashCode() {
        int hashCode = Objects.hashCode(medicines);
        log.debug("Calculating hash code for Order: {}", hashCode);
        return hashCode;
    }

    @Override
    public String toString() {
        return "Order{" +
                "medicines=" + medicines +
                ", user=" + user +
                '}';
    }
}