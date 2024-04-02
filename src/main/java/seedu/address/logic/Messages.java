package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Person;
import seedu.address.model.person.Seller;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";
    public static final String MESSAGE_HOUSE_LISTED_OVERVIEW = "%1$d house(s) listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
                "Multiple values specified for the following single-valued field(s): ";
    public static final String MESSAGE_BUYER_NOT_FOUND = "The specified buyer was not found.";
    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code person} for display to the user, differentiating between Buyer and Seller.
     */
    public static String format(Person person) {
        final StringBuilder builder = new StringBuilder();
        builder.append(person.getName())
                .append("; Phone= ")
                .append(person.getPhone())
                .append("; Email= ")
                .append(person.getEmail())
                .append("; Tags= ");
        person.getTags().forEach(tag -> builder.append("[").append(tag.tagName).append("] "));

        if (person instanceof Buyer) {
            Buyer buyer = (Buyer) person;
            builder.append("; Preferred Housing Type= ")
                    .append(buyer.getPreferredHousingType())
                    .append("; Budget= ")
                    .append(buyer.getBudget());
        } else if (person instanceof Seller) {
            Seller seller = (Seller) person;
            builder.append("; Number of Houses= ")
                    .append(seller.getHouses().size());
        }
        return builder.toString();
    }
}
