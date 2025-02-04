package domain.Client;


import domain.Medicine.Medicine;

import java.util.ArrayList;
import java.util.Objects;

public class Order {

    public static final double ZERO = 0.0;
    private ArrayList<Medicine> medicines =  new ArrayList<>();
    private User user;

    public Order(ArrayList<Medicine> medicines, User user) {
        this.medicines = medicines;
        this.user = user;
    }

    public double getPriceOfPills(){
        double sum = ZERO;
        for (Medicine medicine : medicines){
            sum += medicine.getPrice();
        }
        return sum;
    }

    public User getUser() {
        return user;
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
        return Objects.equals(medicines, order.medicines);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(medicines);
    }
}