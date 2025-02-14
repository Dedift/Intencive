package domain.Client;

import domain.Medicine.Cream;
import domain.Medicine.Drops;
import domain.Medicine.Pills;
import domain.Medicine.Powder;
import domain.Utils.FinancialUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

class OrderTest {

    Pills pills;
    Cream cream;
    Powder powder;
    Drops drops;
    Order order;

    @BeforeEach
    void setUp() {
        this.pills = new Pills("Aspirin", new BigDecimal("10.80"), false, "acetylsalicylic acid", 20);
        this.cream = new Cream("Diclofenac", new BigDecimal("5.23"), true, "diclofenac", 100);
        this.powder = new Powder("TheraFlu", new BigDecimal("20.4"), false, "paracetamol", 10);
        this.drops = new Drops("Artelac Splash", new BigDecimal("31.43"), false, "sodium hyaluronate", 10);

        User user = new User(new ArrayList<>(), null, "Lu", "Zhvanskaya", 29, null, Gender.FEMALE, new BigDecimal("300.2"));
        user.setRecipes(new ArrayList<>() {{
            add(new Recipe(user, cream));
        }});

        this.order = FinancialUtils.createOrder(new ArrayList<>() {{
            add(pills);
            add(cream);
            add(powder);
            add(drops);
        }}, user);
    }

    @Test
    void getPriceOfMedicines() {
        BigDecimal expectedPrice = pills.getPrice().add(cream.getPrice()).add(powder.getPrice()).add(drops.getPrice());
        Assertions.assertEquals(expectedPrice, order.getPriceOfMedicines());
    }
}