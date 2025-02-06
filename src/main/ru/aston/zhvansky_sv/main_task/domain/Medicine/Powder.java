package domain.Medicine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * Represents a powder medicine, which is a specific type of medicine.
 * Inherits from the abstract class Medicine and adds a numberSachetsInPackage field.
 */
public class Powder extends Medicine{

    private static final Logger log = LoggerFactory.getLogger(Powder.class);
    private int numberSachetsInPackage;

    /**
     * Constructs a new Powder instance.
     *
     * @param name                      The name of the powder.
     * @param price                     The price of the powder.
     * @param needRecipe                Indicates whether a prescription is required for these powder.
     * @param activeSubstance           The active substance in the powder.
     * @param numberSachetsInPackage    The number of sachets per pack.
     */
    public Powder(String name, double price, boolean needRecipe, String activeSubstance, int numberSachetsInPackage) {
        super(name, price, needRecipe, activeSubstance);
        this.numberSachetsInPackage = numberSachetsInPackage;
    }

    public int getNumberSachetsInPackage() {
        return numberSachetsInPackage;
    }

    public void setNumberSachetsInPackage(int numberSachetsInPackage) {
        this.numberSachetsInPackage = numberSachetsInPackage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            log.debug("Comparing Powder with itself: true");
            return true;
        }
        if (!(o instanceof Powder powder)) {
            log.debug("Comparing Powder with a different type: false");
            return false;
        }
        boolean isEqual = super.equals(o) && numberSachetsInPackage == powder.numberSachetsInPackage;
        log.debug("Comparing Powder with another Powder. Result: {}", isEqual);
        return isEqual;
    }

    @Override
    public int hashCode() {
        int hashCode = Objects.hash(super.hashCode(), numberSachetsInPackage);
        log.debug("Calculating hash code for Powder: {}", hashCode);
        return hashCode;
    }

    @Override
    public String toString() {
        return "Powder{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", needRecipe=" + needRecipe +
                ", activeSubstance='" + activeSubstance + '\'' +
                ", numberSetsInPackage=" + numberSachetsInPackage +
                '}';
    }
}
