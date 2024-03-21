package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOCK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOUSING_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSTALCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STREET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNITNUMBER;

import java.util.Set;

import seedu.address.logic.commands.AddBuyerCommand;
import seedu.address.logic.commands.AddSellerCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.house.House;
import seedu.address.model.house.NonLanded;
import seedu.address.model.person.Person;
import seedu.address.model.person.Seller;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code buyer}.
     */
    public static String getAddBuyerCommand(Person buyer) {
        return AddBuyerCommand.COMMAND_WORD + " " + getPersonDetails(buyer);
    }

    /**
     * Returns an add seller command string for adding the {@code seller}.
     */
    public static String getAddSellerCommand(Seller seller) {
        return AddSellerCommand.COMMAND_WORD + " " + getSellerDetails(seller);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Person person) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + person.getName().fullName + " ");
        sb.append(PREFIX_PHONE + person.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + person.getEmail().value + " ");
        sb.append(PREFIX_HOUSING_TYPE + person.getHousingType() + " ");
        person.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of the command string for the given {@code seller}'s details, including houses.
     */
    public static String getSellerDetails(Seller seller) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + seller.getName().fullName + " ");
        sb.append(PREFIX_PHONE + seller.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + seller.getEmail().value + " ");
        sb.append(PREFIX_HOUSING_TYPE + seller.getHousingType() + " ");
        // Append house details
        for (House house : seller.getHouses()) {
            sb.append(PREFIX_STREET + house.getStreet().value + " ");
            if (house instanceof NonLanded) {
                NonLanded nonLanded = (NonLanded) house;
                if (nonLanded.getBlock() != null) {
                    sb.append(PREFIX_BLOCK + nonLanded.getBlock().value + " ");
                }
                if (nonLanded.getLevel() != null) {
                    sb.append(PREFIX_LEVEL + nonLanded.getLevel().value + " ");
                }
            }
            sb.append(PREFIX_UNITNUMBER + house.getUnitNumber().value + " ");
            sb.append(PREFIX_POSTALCODE + house.getPostalCode().value + " ");

        }
        seller.getTags().forEach(s -> sb.append(PREFIX_TAG + s.tagName + " "));
        return sb.toString().trim();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getHousingType().ifPresent(housingType -> sb.append(PREFIX_HOUSING_TYPE).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
