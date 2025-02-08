package domain.Client;

import domain.Medicine.Cream;
import domain.Medicine.Drops;
import domain.Medicine.Pills;
import domain.Medicine.Powder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class OrderTest {

    Pills pills;
    Cream cream;
    Powder powder;
    Drops drops;
    Order order;

    @BeforeEach
    void setUp() {
        this.pills = new Pills("Aspirin", 10.80, false, "acetylsalicylic acid", 20);
        this.cream = new Cream("Diclofenac", 5.23, true, "diclofenac", 100);
        this.powder = new Powder("TheraFlu", 20.4, false, "paracetamol", 10);
        this.drops = new Drops("Artelac Splash", 31.43, false, "sodium hyaluronate", 10);

        User user = new User(null, null, "Lu", "Zhvanskaya", 29, null, Gender.FEMALE, 300.2);
        user.setRecipes(new ArrayList<>() {{
            add(new Recipe(user, cream));
        }});

        this.order = user.createOrder(new ArrayList<>() {{
            add(pills);
            add(cream);
            add(powder);
            add(drops);
        }});
    }

    @Test
    void getPriceOfPills() {
        double expectedPrice = pills.getPrice() + cream.getPrice() + powder.getPrice() + drops.getPrice();
        Assertions.assertEquals(expectedPrice, order.getPriceOfPills());
    }
}