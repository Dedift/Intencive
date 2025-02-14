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
import java.math.RoundingMode;
import java.util.ArrayList;

import static domain.Utils.DiscountUtils.*;

class DiscountUtilsTest {

    Order order;
    User user;

    @BeforeEach
    void setUp() {
        Pills pills = new Pills("Aspirin", new BigDecimal("10.80"), false, "acetylsalicylic acid", 20);
        Cream cream = new Cream("Diclofenac", new BigDecimal("5.23"), true, "diclofenac", 100);
        Powder powder = new Powder("TheraFlu", new BigDecimal("20.4"), false, "paracetamol", 10);
        Drops drops = new Drops("Artelac Splash", new BigDecimal("31.43"), false, "sodium hyaluronate", 10);

        this.user = new User(new ArrayList<>(), null, "Lu", "Zhvanskaya", 29, null, Gender.FEMALE, new BigDecimal("300.2"));
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
    void getPriceWithDiscount() {
        BigDecimal discount = BigDecimal.valueOf(Math.min(order.getMedicines().size(), MAX_COUNT_MEDICINE_FOR_SALE));
        discount = discount.add(user.getPersonType().ADDITIONAL_DISCOUNT);
        BigDecimal expectedPriceWithDiscount = (order.getPriceOfPills().multiply(discount)).divide(ONE_HUNDRED, RoundingMode.HALF_EVEN);
        Assertions.assertEquals(expectedPriceWithDiscount, DiscountUtils.getPriceWithDiscount(this.order, this.user));
    }
}