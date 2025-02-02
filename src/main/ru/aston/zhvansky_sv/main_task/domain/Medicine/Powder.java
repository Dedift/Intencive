package domain.Medicine;

import java.util.Objects;

public class Powder extends Medicine{

    int numberSetsInPackage;

    public Powder(String name, double price, boolean needRecipe, String activeSubstance, int numberSetsInPackage) {
        super(name, price, needRecipe, activeSubstance);
        this.numberSetsInPackage = numberSetsInPackage;
    }

    public int getNumberSetsInPackage() {
        return numberSetsInPackage;
    }

    public void setNumberSetsInPackage(int numberSetsInPackage) {
        this.numberSetsInPackage = numberSetsInPackage;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Powder powder)) return false;
        return numberSetsInPackage == powder.numberSetsInPackage;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numberSetsInPackage);
    }
}