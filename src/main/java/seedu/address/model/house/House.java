package seedu.address.model.house;


/**
 * Represents a House.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public interface House {
    public static final String MESSAGE_CONSTRAINTS = "Housing types can only be Hdb, Condominium or Landed.";
    public static final String[] VALIDATION_REGEX = {"Hdb", "Condominium", "Landed"};

    /**
     * Retrieves the unit number of the house.
     *
     * @return The unit number of the house.
     */
    UnitNumber getUnitNumber();

    /**
     * Retrieves the postal code of the house.
     *
     * @return The postal code of the house.
     */
    PostalCode getPostalCode();

    /**
     * Retrieves the street of the house.
     *
     * @return The street of the house.
     */
    Street getStreet();

    /**
     * Retrieves the price of the house.
     *
     * @return The price of the house.
     */
    Price getPrice();

    /**
     * Checks if the given name is a valid housing type.
     *
     * @param name The name to check.
     * @return True if the name is a valid housing type, false otherwise.
     */
    public static boolean isValidName(String name) {
        for (String element : VALIDATION_REGEX) {
            if (element.equals(name)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Checks if this house is equal to another object.
     *
     * @param other The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    public boolean equals(Object other);

    /**
     * Returns a string representation of the house.
     *
     * @return A string representation of the house.
     */
    @Override
    public String toString();

    /**
     * Retrieves the housing type of the house.
     *
     * @return The housing type of the house.
     */
    HousingType getHousingType();
}
