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

    public void setUser(User user) {
        this.user = user;
    }

    public Medicine getPill() {
        return medicine;
    }

    public void setPill(Medicine medicine) {
        this.medicine = medicine;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Recipe recipe)) return false;
        return Objects.equals(user, recipe.user) && Objects.equals(medicine, recipe.medicine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, medicine);
    }
}
