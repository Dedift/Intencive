package domain.Client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.Medicine.Medicine;

import java.util.Objects;

/**
 * Represents a prescription for a specific medicine issued to a user.
 * Contains the user and the medicine prescribed.
 */
public class Recipe {

    private static final Logger log = LoggerFactory.getLogger(Recipe.class);
    private User user;
    private Medicine medicine;

    /**
     * Constructs a new Recipe instance.
     *
     * @param user      The user to whom the prescription is issued.
     * @param medicine  The medicine prescribed.
     */
    public Recipe(User user, Medicine medicine) {
        this.user = user;
        this.medicine = medicine;
        log.debug("Created new Recipe: user={}, medicine={}", user, medicine);
    }

    public User getUser() {
        log.debug("Getting user of recipe: {}", user);
        return user;
    }

    public Medicine getMedicine() {
        log.debug("Getting medicine of recipe: {}", medicine);
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        log.debug("Setting medicine of recipe. Old medicine: {}, New medicine: {}", this.medicine, medicine);
        this.medicine = medicine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            log.debug("Comparing Recipe with itself: true");
            return true;
        }
        if (!(o instanceof Recipe recipe)) {
            log.debug("Comparing Recipe with a different type: false");
            return false;
        }
        boolean isEqual = Objects.equals(medicine, recipe.medicine);
        log.debug("Comparing Recipe with another Recipe. Result: {}", isEqual);
        return isEqual;
    }

    @Override
    public int hashCode() {
        int hashCode = Objects.hashCode(medicine);
        log.debug("Calculating hash code for Recipe: {}", hashCode);
        return hashCode;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "user=" + user +
                ", medicine=" + medicine +
                '}';
    }
}
