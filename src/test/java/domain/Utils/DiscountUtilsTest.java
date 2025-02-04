package domain.Utils;

import domain.Client.*;
import domain.Medicine.Cream;
import domain.Medicine.Drops;
import domain.Medicine.Pill;
import domain.Medicine.Powder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static domain.Utils.DiscountUtils.*;

class DiscountUtilsTest {

    Order order;
    User  user;

    @BeforeEach
    void setUp() {
        Pill pill = new Pill("Aspirin", 10.80, false, "acetylsalicylic acid", 20);
        Cream cream = new Cream("Diclofenac", 5.23, true, "diclofenac", 100);
        Powder powder = new Powder("TheraFlu", 20.4, false, "paracetamol", 10);
        Drops drops = new Drops("Artelac Splash", 31.43, false, "sodium hyaluronate", 10);

        this.user = new User(null, null, "Lu", "Zhvanskaya", 29, null, Gender.FEMALE, 300.2);
        user.setRecipes(new ArrayList<>() {{
            add(new Recipe(user, cream));
        }});

        this.order = user.createOrder(new ArrayList<>() {{
            add(pill);
            add(cream);
            add(powder);
            add(drops);
        }});
    }

    @Test
    void getPriceWithDiscount() {
        double discount = Math.min(order.getMedicines().size(), MAX_COUNT_MEDICINE_FOR_SALE);
        if (!user.getPersonType().equals(PersonType.DEFAULT) || !(user.getPersonType() == null)) {
            discount += ADDITIONAL_DISCOUNT;
        }
        double expectedPriceWithDiscount = order.getPriceOfPills() * discount / ONE_HUNDRED;
        Assertions.assertEquals(expectedPriceWithDiscount, DiscountUtils.getPriceWithDiscount(this.order, this.user));
    }
}