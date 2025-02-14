package domain.Client;

import java.math.BigDecimal;

/**
 * Represents the type of user, which determines additional discounts they may receive.
 * Each person type has an associated additional discount value.
 */
public enum PersonType {
    RETIREE(new BigDecimal("5")),
    DISABLED(new BigDecimal("5")),
    VETERAN(new BigDecimal("5")),
    DEFAULT(new BigDecimal("0"));

    /**
     * The additional discount percentage associated with the person type.
     */
    public final BigDecimal ADDITIONAL_DISCOUNT;

    /**
     * Constructs a PersonType enum with the specified additional discount.
     *
     * @param additionalDiscount The additional discount percentage.
     */
    PersonType(BigDecimal additionalDiscount) {
        ADDITIONAL_DISCOUNT = additionalDiscount;
    }
}
