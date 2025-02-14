package domain.Medicine;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents a drops medicine, which is a specific type of medicine.
 * Inherits from the abstract class Medicine and adds a volume field.
 */
public class Drops extends Medicine {

    private Integer volume;

    /**
     * Constructs a new Drops instance.
     *
     * @param name            The name of the drops.
     * @param price           The price of the drops.
     * @param needRecipe      Indicates whether a prescription is required for these drops.
     * @param activeSubstance The active substance in the drops.
     * @param volume          The volume of the drops in milliliters (ml).
     */
    public Drops(String name, BigDecimal price, Boolean needRecipe, String activeSubstance, Integer volume) {
        super(name, price, needRecipe, activeSubstance);
        this.volume = volume;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            log.debug("Comparing Drops with itself: true");
            return true;
        }
        if (!(o instanceof Drops drops)) {
            log.debug("Comparing Drops with a different type: false");
            return false;
        }
        boolean isEqual = super.equals(o) && volume == drops.volume;
        log.debug("Comparing Drops with another Drops. Result: {}", isEqual);
        return isEqual;
    }

    @Override
    public int hashCode() {
        int hashCode = Objects.hash(super.hashCode(), volume);
        log.debug("Calculating hash code for Drops: {}", hashCode);
        return hashCode;
    }

    @Override
    public String toString() {
        return "Drops{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", needRecipe=" + needRecipe +
                ", activeSubstance='" + activeSubstance + '\'' +
                ", volume=" + volume +
                '}';
    }
}