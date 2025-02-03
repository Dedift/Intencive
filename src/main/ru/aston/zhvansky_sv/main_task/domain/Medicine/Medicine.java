package domain.Medicine;

public abstract class Medicine {

    private String name;
    private double price;
    private boolean needRecipe;
    private String activeSubstance;

    public Medicine(String name, double price, boolean needRecipe, String activeSubstance) {
        this.name = name;
        this.price = price;
        this.needRecipe = needRecipe;
        this.activeSubstance = activeSubstance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isNeedRecipe() {
        return needRecipe;
    }

    public void setNeedRecipe(boolean needRecipe) {
        this.needRecipe = needRecipe;
    }

    public String getActiveSubstance() {
        return activeSubstance;
    }

    public void setActiveSubstance(String activeSubstance) {
        this.activeSubstance = activeSubstance;
    }
}