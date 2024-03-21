package seedu.address.model.person;

import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a buyer in the address book.
 */
public class Buyer extends Person {

    /**
     * Constructs a new Buyer instance without specifying a house. Default constructor.
     *
     * @param name        The name of the buyer.
     * @param phone       The phone number of the buyer.
     * @param email       The email address of the buyer.
     * @param housingType The type of housing the buyer wants.
     * @param tags        The tags associated with the buyer.
     */
    public Buyer(Name name, Phone phone, Email email, String housingType, Set<Tag> tags) {
        super(name, phone, email, housingType, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", super.getName())
                .add("phone", super.getPhone())
                .add("email", super.getEmail())
                .add("housing type", super.getHousingType())
                .add("tags", super.getTags())
                .toString();
    }
}
