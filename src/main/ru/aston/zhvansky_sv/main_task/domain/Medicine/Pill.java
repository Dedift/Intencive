package domain.Medicine;

import java.util.Objects;

public class Pill extends Medicine{

    private int numberPillsInPackage;

    public Pill(String name, double price, boolean needRecipe, String activeSubstance, int numberPillsInPackage) {
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
        if (!(o instanceof Pill pill)) return false;
        return numberPillsInPackage == pill.numberPillsInPackage;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numberPillsInPackage);
    }
}