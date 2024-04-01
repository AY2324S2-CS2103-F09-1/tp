package seedu.address.model.person.exceptions;

/**
 * Signals that the operation will result in duplicate Persons (Persons are considered duplicates if they have the same
 * identity).
 */
public class InvalidSellerException extends RuntimeException {
    public InvalidSellerException() {
        super("Person must be a Seller to add a house");
    }
}
