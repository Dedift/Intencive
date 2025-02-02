package domain.Client;


import domain.Medicine.Medicine;

import java.util.ArrayList;
import java.util.Objects;

public class Order {

    private ArrayList<Medicine> medicines;
    private User user;

    public Order(ArrayList<Medicine> medicines, User user) {
        this.medicines = medicines;
        this.user = user;
    }

    public double getPriceOfPills(){
        double sum = 0.0;
        for (Medicine medicine : medicines){
            sum += medicine.getPrice();
        }
        return sum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(ArrayList<Medicine> medicines) {
        this.medicines = medicines;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Order order)) return false;
        return Objects.equals(medicines, order.medicines) && Objects.equals(user, order.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicines, user);
    }
}