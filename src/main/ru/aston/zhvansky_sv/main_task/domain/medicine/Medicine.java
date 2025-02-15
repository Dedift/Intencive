package domain.medicine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Abstract class representing a generic medicine.
 * This class serves as the base for specific types of medicines and provides common properties
 * such as name, price, prescription requirement, and active substance.
 */
public abstract class Medicine {

    protected static final Logger log = LoggerFactory.getLogger(Medicine.class);
    protected String name;
    protected BigDecimal price;
    protected Boolean needRecipe;
    protected String activeSubstance;

    /**
     * Constructor used by child classes.
     *
     * @param name            The name of the medicine.
     * @param price           The price of the medicine.
     * @param needRecipe      Indicates whether a prescription is required for this medicine.
     * @param activeSubstance The active substance in the medicine.
     */
    public Medicine(String name, BigDecimal price, Boolean needRecipe, String activeSubstance) {
        this.name = name;
        this.price = price;
        this.needRecipe = needRecipe;
        this.activeSubstance = activeSubstance;
        log.debug("Created new Medicine: name={}, price={}, needRecipe={}, activeSubstance={}",
                name, price, needRecipe, activeSubstance);
    }

    public String getName() {
        log.debug("Getting name of medicine: {}", name);
        return name;
    }

    public void setName(String name) {
        log.debug("Setting name of medicine. Old name: {}, New name: {}", this.name, name);
        this.name = name;
    }

    public BigDecimal getPrice() {
        log.debug("Getting price of medicine: {}", price);
        return price;
    }

    public void setPrice(BigDecimal price) {
        log.debug("Setting price of medicine. Old price: {}, New price: {}", this.price, price);
        this.price = price;
    }

    public boolean isNeedRecipe() {
        log.debug("Checking if medicine requires a prescription: {}", needRecipe);
        return needRecipe;
    }

    public void setNeedRecipe(boolean needRecipe) {
        log.debug("Setting prescription requirement for medicine. Old value: {}, New value: {}",
                this.needRecipe, needRecipe);
        this.needRecipe = needRecipe;
    }

    public String getActiveSubstance() {
        log.debug("Getting active substance of medicine: {}", activeSubstance);
        return activeSubstance;
    }

    public void setActiveSubstance(String activeSubstance) {
        log.debug("Setting active substance of medicine. Old value: {}, New value: {}",
                this.activeSubstance, activeSubstance);
        this.activeSubstance = activeSubstance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            log.debug("Comparing Medicine with itself: true");
            return true;
        }
        if (!(o instanceof Medicine medicine)) {
            log.debug("Comparing Medicine with a different type: false");
            return false;
        }
        boolean isEqual = Objects.equals(price, medicine.price) && needRecipe == medicine.needRecipe && Objects.equals(name, medicine.name) && Objects.equals(activeSubstance, medicine.activeSubstance);
        log.debug("Comparing Medicine with another Medicine. Result: {}", isEqual);
        return isEqual;
    }

    @Override
    public int hashCode() {
        int hashCode = Objects.hash(name, price, needRecipe, activeSubstance);
        log.debug("Calculating hash code for Medicine: {}", hashCode);
        return hashCode;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", needRecipe=" + needRecipe +
                ", activeSubstance='" + activeSubstance + '\'' +
                '}';
    }
}