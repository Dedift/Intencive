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

class FinancialUtilsTest {

    Order order;
    Pills pills;
    Cream cream;
    Powder powder;
    Drops drops;
    User user;

    @BeforeEach
    void setUp() {
        this.pills = new Pills("Aspirin", new BigDecimal("10.80"), false, "acetylsalicylic acid", 20);
        this.cream = new Cream("Diclofenac", new BigDecimal("5.23"), true, "diclofenac", 100);
        this.powder = new Powder("TheraFlu", new BigDecimal("20.4"), true, "paracetamol", 10);
        this.drops = new Drops("Artelac Splash", new BigDecimal("31.43"), false, "sodium hyaluronate", 10);

        this.user = new User(new ArrayList<>(), null, "Lu", "Zhvanskaya", 29, null, Gender.FEMALE, new BigDecimal("300.2"));
        user.setRecipes(new ArrayList<>() {{
            add(new Recipe(user, cream));
        }});

        this.order = FinancialUtils.createOrder(new ArrayList<>() {{
            add(pills);
            add(cream);
            add(powder);
            add(drops);
        }}, user).get();
    }

    @Test
    void createOrder() {
        Order order1 = FinancialUtils.createOrder(new ArrayList<>() {{
            add(pills);
            add(cream);
            add(powder);
            add(drops);
        }}, this.user).get();
        Order order2 = new Order((new ArrayList<>() {{
            add(pills);
            add(cream);
            add(drops);
        }}), this.user);
        Assertions.assertEquals(order1, order2);
    }

    @Test
    void payOrder() {
        Order order1 = FinancialUtils.createOrder(new ArrayList<>() {{
            add(pills);
            add(cream);
            add(powder);
            add(drops);
        }}, this.user).get();
        BigDecimal priceOfPills = order1.getPriceOfMedicines();
        BigDecimal countMedicine = BigDecimal.valueOf(order1.getMedicines().size());
        BigDecimal discount = user.getPersonType().ADDITIONAL_DISCOUNT.add(countMedicine);
        BigDecimal priceWithDiscount = (priceOfPills.multiply(discount)).divide(FinancialUtils.ONE_HUNDRED, RoundingMode.HALF_EVEN);
        BigDecimal expectedBalanceMoney = this.user.getMoney().subtract(priceWithDiscount);
        FinancialUtils.payOrder(order1, this.user);
        Assertions.assertEquals(expectedBalanceMoney, this.user.getMoney());
    }
}