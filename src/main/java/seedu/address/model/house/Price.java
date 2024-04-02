package seedu.address.model.house;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a House's Price amount.
 * Guarantees: immutable; is valid as declared in {@link #isValidPrice(String)}
 */
public class Price implements Comparable<Price> {
    public static final String MESSAGE_CONSTRAINTS = "Price should be a positive number.";
    public static final String VALIDATION_REGEX = "\\d+(\\.\\d+)?";
    public final String value;

    /**
     * Constructs a {@code Price}.
     *
     * @param price A valid price amount.
     */
    public Price(String price) {
        requireNonNull(price);
        checkArgument(isValidPrice(price), MESSAGE_CONSTRAINTS);
        value = price;
    }

    /**
     * Returns true if a given String is a valid price amount.
     */
    public static boolean isValidPrice(String test) {
        return test.matches(VALIDATION_REGEX) && Double.parseDouble(test) >= 0;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Price)) {
            return false;
        }

        Price otherPrice = (Price) other;
        return value.equals(otherPrice.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public int compareTo(Price other) {
        double thisValue = Double.parseDouble(this.value);
        double otherValue = Double.parseDouble(other.value);
        return Double.compare(thisValue, otherValue);
    }
}
