package domain.Medicine;

import java.util.Objects;

/**
 * Represents a cream medicine, which is a specific type of medicine.
 * Inherits from the abstract class Medicine and adds a volume field.
 */
public class Cream extends Medicine{

    private int volume;

    /**
     * Constructs a new Cream instance.
     *
     * @param name            The name of the cream.
     * @param price           The price of the cream.
     * @param needRecipe      Indicates whether a prescription is required for this cream.
     * @param activeSubstance The active substance in the cream.
     * @param volume          The volume of the cream in milliliters (ml).
     */
    public Cream(String name, double price, boolean needRecipe, String activeSubstance, int volume) {
        super(name, price, needRecipe, activeSubstance);
        this.volume = volume;
        log.debug("Created new Cream: name={}, price={}, needRecipe={}, activeSubstance={}, volume={}",
                name, price, needRecipe, activeSubstance, volume);
    }

    public int getVolume() {
        log.debug("Getting volume of cream: {} ml", volume);
        return volume;
    }

    public void setVolume(int volume) {
        log.debug("Setting volume of cream. Old volume: {} ml, New volume: {} ml", this.volume, volume);
        this.volume = volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            log.debug("Comparing Cream with itself: true");
            return true;
        }
        if (!(o instanceof Cream cream)) {
            log.debug("Comparing Cream with a different type: false");
            return false;
        }
        boolean isEqual = super.equals(o) && volume == cream.volume;
        log.debug("Comparing Cream with another Cream. Result: {}", isEqual);
        return isEqual;
    }

    @Override
    public int hashCode() {
        int hashCode = Objects.hash(super.hashCode(), volume);
        log.debug("Calculating hash code for Cream: {}", hashCode);
        return hashCode;
    }

    @Override
    public String toString() {
        return "Cream{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", needRecipe=" + needRecipe +
                ", activeSubstance='" + activeSubstance + '\'' +
                ", volume=" + volume +
                '}';
    }
}