package domain.Client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Medicine.Medicine;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents an order made by a user.
 * Contains a list of medicines and the user who placed the order.
 */
public class Order {

    private static final Logger log = LoggerFactory.getLogger(Order.class);
    private List<Medicine> medicines;
    private User user;

    /**
     * Constructs a new Order instance.
     *
     * @param medicines The list of medicines in the order.
     * @param user      The user who placed the order.
     */
    public Order(List<Medicine> medicines, User user) {
        this.medicines = medicines;
        this.user = user;
        log.debug("Created new Order: user={}, number of medicines={}", user, medicines.size());
    }

    /**
     * Calculates the total price of all medicines in the order.
     *
     * @return The total price of the medicines in the order.
     */
    public BigDecimal getPriceOfPills() {
        if (medicines == null) {
            log.debug("Medicines list is null, returning 0.0");
            return BigDecimal.ZERO;
        }

        Optional<BigDecimal> sum = medicines.stream()
                .map(Medicine::getPrice)
                .peek(price -> log.debug("Processing medicine with price: {}", price))
                        .reduce(BigDecimal::add);

        log.debug("Calculated total price of medicines in order: {}", sum);
        return sum.orElse(BigDecimal.ZERO);
    }

    public User getUser() {
        log.debug("Getting user of order: {}", user);
        return user;
    }

    public List<Medicine> getMedicines() {
        log.debug("Getting list of medicines in order. Number of medicines: {}", medicines.size());
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
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