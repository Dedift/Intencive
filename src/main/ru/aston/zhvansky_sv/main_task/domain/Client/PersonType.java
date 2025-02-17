package domain.Client;

/**
 * Represents the type of user, which determines additional discounts they may receive.
 * Each person type has an associated additional discount value.
 */
public enum PersonType {
    RETIREE(5),
    DISABLED(5),
    VETERAN(5),
    DEFAULT(0);

    /**
     * The additional discount percentage associated with the person type.
     */
    public final int ADDITIONAL_DISCOUNT;

    /**
     * Constructs a PersonType enum with the specified additional discount.
     *
     * @param additionalDiscount The additional discount percentage.
     */
    PersonType(int additionalDiscount) {
        ADDITIONAL_DISCOUNT = additionalDiscount;
    }
}
