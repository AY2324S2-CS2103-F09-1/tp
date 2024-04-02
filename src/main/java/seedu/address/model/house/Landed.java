package seedu.address.model.house;

import seedu.address.commons.util.ToStringBuilder;


/**
 * Represents a landed house.
 */
public class Landed implements House {

    public final PostalCode postalCode;
    public final Street street;
    public final UnitNumber unitNumber;
    public final Price price;

    /**
     * Constructs a Landed house.
     *
     * @param unitNumber The unit number of the house.
     * @param postalCode The postal code of the house.
     * @param street     The street of the house.
     * @param price      The price of the house.
     */
    public Landed(UnitNumber unitNumber, PostalCode postalCode, Street street, Price price) {
        this.postalCode = postalCode;
        this.street = street;
        this.unitNumber = unitNumber;
        this.price = price;
    }

    /**
     * Retrieves the unit number of the landed house.
     *
     * @return The unit number of the landed house.
     */
    @Override
    public UnitNumber getUnitNumber() {
        return this.unitNumber;
    }

    /**
     * Retrieves the postal code of the landed house.
     *
     * @return The postal code of the landed house.
     */
    @Override
    public PostalCode getPostalCode() {
        return this.postalCode;
    }

    /**
     * Retrieves the street of the landed house.
     *
     * @return The street of the landed house.
     */
    @Override
    public Street getStreet() {
        return this.street;
    }

    /**
     * Retrieves the price of the landed house.
     *
     * @return The price of the landed house.
     */
    @Override
    public Price getPrice() {
        return price;
    }

    /**
     * Retrieves the housing type of the landed house.
     *
     * @return The housing type of the landed house.
     */
    @Override
    public HousingType getHousingType() {
        return new HousingType("Landed");
    }

    /**
     * Checks if this house is equal to another object.
     *
     * @param other The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Landed)) {
            return false;
        }

        House otherLanded = (House) other;
        return this.toString().equals(otherLanded.toString());
    }

    /**
     * Returns a string representation of the landed house.
     *
     * @return A string representation of the landed house.
     */
    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this);
        // For now, it just appends the super class's toString method.
        return "Landed House: " + builder.toString() + ", " + new ToStringBuilder(this)
                .add("Unit Number", unitNumber)
                .add("Street", street)
                .add("Postal Code", postalCode)
                .add("Price", price)
                .toString();
    }
}


