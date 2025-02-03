package domain.Medicine;

import java.util.Objects;

public class Cream extends Medicine{

    int volume;

    public Cream(String name, double price, boolean needRecipe, String activeSubstance, int volume) {
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
        if (!(o instanceof Cream cream)) return false;
        return volume == cream.volume;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(volume);
    }
}