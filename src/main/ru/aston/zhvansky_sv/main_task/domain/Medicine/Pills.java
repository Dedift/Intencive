package domain.Medicine;

import java.util.Objects;

/**
 * Represents a pills medicine, which is a specific type of medicine.
 * Inherits from the abstract class Medicine and adds a numberPillsInPackage field.
 */
public class Pills extends Medicine{

    private int numberPillsInPackage;

    /**
     * Constructs a new Pills instance.
     *
     * @param name                  The name of the pills.
     * @param price                 The price of the pills.
     * @param needRecipe            Indicates whether a prescription is required for these pills.
     * @param activeSubstance       The active substance in the pills.
     * @param numberPillsInPackage  The number of pills per pack.
     */
    public Pills(String name, double price, boolean needRecipe, String activeSubstance, int numberPillsInPackage) {
        super(name, price, needRecipe, activeSubstance);
        this.numberPillsInPackage = numberPillsInPackage;
    }

    public int getNumberPillsInPackage() {
        return numberPillsInPackage;
    }

    public void setNumberPillsInPackage(int numberPillsInPackage) {
        this.numberPillsInPackage = numberPillsInPackage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            log.debug("Comparing Pill with itself: true");
            return true;
        }
        if (!(o instanceof Pills pills)) {
            log.debug("Comparing Pill with a different type: false");
            return false;
        }
        boolean isEqual = super.equals(o) && numberPillsInPackage == pills.numberPillsInPackage;
        log.debug("Comparing Pill with another Pill. Result: {}", isEqual);
        return isEqual;
    }

    @Override
    public int hashCode() {
        int hashCode = Objects.hash(super.hashCode(), numberPillsInPackage);
        log.debug("Calculating hash code for Pill: {}", hashCode);
        return hashCode;
    }

    @Override
    public String toString() {
        return "Pill{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", needRecipe=" + needRecipe +
                ", activeSubstance='" + activeSubstance + '\'' +
                ", numberPillsInPackage=" + numberPillsInPackage +
                '}';
    }
}