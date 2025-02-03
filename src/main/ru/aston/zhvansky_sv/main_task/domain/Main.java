package domain;

import domain.Client.Gender;
import domain.Client.Order;
import domain.Client.Recipe;
import domain.Client.User;
import domain.Medicine.Cream;
import domain.Medicine.Drops;
import domain.Medicine.Pill;
import domain.Medicine.Powder;
import domain.Utils.OrdersHistory;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Pill aspirin = new Pill("Aspirin", 10.80, false, "acetylsalicylic acid", 20);
        Cream diclofenac = new Cream("Diclofenac", 5.23, true, "diclofenac", 100);
        Powder theraFlu = new Powder("TheraFlu", 20.4, true, "paracetamol", 10);
        Drops artelacSplash = new Drops("Artelac Splash", 31.43, false, "sodium hyaluronate", 10);

        User user = new User(null, null, "Lu", "Zhvanskaya", 29, null, Gender.FEMALE, 300.2);
        user.setRecipes(new ArrayList<>(){{
        add(new Recipe(user, diclofenac));}});

        Order order = user.createOrder(new ArrayList<>() {{
            add(aspirin);
            add(theraFlu);
            add(artelacSplash);
            add(diclofenac);
        }});

        user.payOrder(order);
        OrdersHistory.getSortedOrders();
    }
}
