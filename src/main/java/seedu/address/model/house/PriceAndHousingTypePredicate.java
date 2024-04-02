package seedu.address.model.house;

import java.util.function.Predicate;

/**
 * Represents a predicate that checks if a house matches the given price and housing type criteria.
 */
public class PriceAndHousingTypePredicate implements Predicate<House> {
    private final Price price;
    private final HousingType housingType;

    /**
     * Constructs a PriceAndHousingTypePredicate with the given price and housing type criteria.
     *
     * @param price       The price criterion.
     * @param housingType The housing type criterion.
     */
    public PriceAndHousingTypePredicate(Price price, HousingType housingType) {
        this.price = price;
        this.housingType = housingType;
    }

    /**
     * Tests if the given house matches the price and housing type criteria.
     *
     * @param house The house to test.
     * @return True if the house matches the criteria, false otherwise.
     */
    @Override
    public boolean test(House house) {
        return house.getPrice().compareTo(price) <= 0 && house.getHousingType().equals(housingType);
    }
}
