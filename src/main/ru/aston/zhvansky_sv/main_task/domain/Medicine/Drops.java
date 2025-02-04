package domain.Medicine;

import java.util.Objects;

public class Drops extends Medicine {

    private int volume;

    public Drops(String name, double price, boolean needRecipe, String activeSubstance, int volume) {
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
        if (!(o instanceof Drops drops)) return false;
        return volume == drops.volume;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(volume);
    }
}