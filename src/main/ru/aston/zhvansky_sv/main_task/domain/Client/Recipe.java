package domain.Client;


import domain.Medicine.Medicine;

import java.util.Objects;

public class Recipe {

    private User user;
    private Medicine medicine;

    public Recipe(User user, Medicine medicine) {
        this.user = user;
        this.medicine = medicine;
    }

    public User getUser() {
        return user;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Recipe recipe)) return false;
        return Objects.equals(medicine, recipe.medicine);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(medicine);
    }
}
