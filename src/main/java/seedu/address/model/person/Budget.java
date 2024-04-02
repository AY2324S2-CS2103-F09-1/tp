package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.house.Price;

/**
 * Represents a Buyer's budget in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidBudget(String)}
 */
public class Budget {

    public static final String MESSAGE_CONSTRAINTS = "Budget should be a positive number.";
    public static final String VALIDATION_REGEX = "\\d+(\\.\\d+)?";
    public final String value;

    /**
     * Constructs a {@code Budget}.
     *
     * @param budget A valid budget amount.
     */
    public Budget(String budget) {
        requireNonNull(budget);
        checkArgument(isValidBudget(budget), MESSAGE_CONSTRAINTS);
        value = budget;
    }

    /**
     * Returns true if a given string is a valid budget amount.
     */
    public static boolean isValidBudget(String test) {
        return test.matches(VALIDATION_REGEX) && Double.parseDouble(test) >= 0;
    }

    /**
     * Converts this budget to a Price.
     *
     * @return The Price equivalent of this budget.
     */
    public Price toPrice() {
        return new Price(value);
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
        if (!(other instanceof Budget)) {
            return false;
        }

        Budget otherBudget = (Budget) other;
        return value.equals(otherBudget.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
