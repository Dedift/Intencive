package domain.Utils;

import domain.Client.*;
import domain.Medicine.Cream;
import domain.Medicine.Drops;
import domain.Medicine.Pills;
import domain.Medicine.Powder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;

class OrdersHistoryTest {

    Order firstOrder;
    Order secondOrder;


    @BeforeEach
    void setUp() {
        Pills pills = new Pills("Aspirin", new BigDecimal("10.80"), false, "acetylsalicylic acid", 20);
        Cream cream = new Cream("Diclofenac", new BigDecimal("5.23"), true, "diclofenac", 100);
        Powder powder = new Powder("TheraFlu", new BigDecimal("20.4"), false, "paracetamol", 10);
        Drops drops = new Drops("Artelac Splash", new BigDecimal("31.43"), false, "sodium hyaluronate", 10);

        User firstUser = new User(new ArrayList<>(), null, "Lu", "Zhvanskaya", 29, null, Gender.FEMALE, new BigDecimal("300.2"));
        User secondUser = new User(new ArrayList<>(), null, "Alina", "Plush", 19, PersonType.DEFAULT, Gender.FEMALE, new BigDecimal("300.2"));
        firstUser.setRecipes(new ArrayList<>() {{
            add(new Recipe(firstUser, cream));
        }});
        secondUser.setRecipes(new ArrayList<>() {{
            add(new Recipe(secondUser, cream));
        }});

        this.firstOrder = firstUser.createOrder(new ArrayList<>() {{
            add(pills);
            add(cream);
            add(powder);
            add(drops);
        }});

        this.secondOrder = secondUser.createOrder(new ArrayList<>() {{
            add(pills);
            add(cream);
            add(powder);
            add(drops);
        }});
    }

    @Test
    void getSortedOrders() {

        ArrayList<Order> ordersForSort = new ArrayList<>() {{
            add(firstOrder);
            add(secondOrder);
        }};
        ordersForSort.sort(Comparator.comparing(o -> o.getUser().getSurName()));
        Assertions.assertEquals(secondOrder.getUser().getSurName(), ordersForSort.get(0).getUser().getSurName());
    }
}