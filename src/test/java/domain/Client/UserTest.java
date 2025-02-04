package domain.Client;

import domain.Medicine.Cream;
import domain.Medicine.Drops;
import domain.Medicine.Pill;
import domain.Medicine.Powder;
import domain.Utils.DiscountUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    Pill pill;
    Cream cream;
    Powder powder;
    Drops drops;
    User user;

    @BeforeEach
    void setUp() {
        this.pill = new Pill("Aspirin", 10.80, false, "acetylsalicylic acid", 20);
        this.cream = new Cream("Diclofenac", 5.23, true, "diclofenac", 100);
        this.powder = new Powder("TheraFlu", 20.4, true, "paracetamol", 10);
        this.drops = new Drops("Artelac Splash", 31.43, false, "sodium hyaluronate", 10);
        this.user = new User(null, null, "Lu", "Zhvanskaya", 29, null, Gender.FEMALE, 300.2);
        user.setRecipes(new ArrayList<>() {{
            add(new Recipe(user, cream));
        }});
    }

    @Test
    void createOrder() {
        Order order1 = user.createOrder(new ArrayList<>() {{
            add(pill);
            add(cream);
            add(powder);
            add(drops);
        }});
        Order order2 = new Order((new ArrayList<>() {{
            add(pill);
            add(cream);
            add(drops);
        }}), this.user);
        Assertions.assertEquals(order1, order2);
    }

    @Test
    void payOrder() {
        Order order1 = user.createOrder(new ArrayList<>() {{
            add(pill);
            add(cream);
            add(powder);
            add(drops);
        }});
        double priceWithDiscount = DiscountUtils.getPriceWithDiscount(order1, this.user);
        double expectedBalanceMoney = this.user.getMoney() - priceWithDiscount;
        user.payOrder(order1);
        Assertions.assertEquals(expectedBalanceMoney, this.user.getMoney());
    }
}