package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOCK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BUDGET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOUSING_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSTALCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STREET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNITNUMBER;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditBuyerDescriptorBuilder;
import seedu.address.testutil.EditSellerDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";

    public static final String VALID_NAME_KHOONSUN = "Yek KhoonSun";
    public static final String VALID_NAME_BEN = "Ben Ten";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_PHONE_BEN = "33333333";

    public static final String VALID_PHONE_KHOONSUN = "44444444";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_EMAIL_BEN = "ben@example.com";

    public static final String VALID_EMAIL_KHOONSUN = "khoonsun@example.com";
    public static final String VALID_HOUSING_TYPE_AMY = "Hdb";
    public static final String VALID_HOUSING_TYPE_BOB = "Condominium";
    public static final String VALID_HOUSING_TYPE_BEN = "Condominium";
    public static final String VALID_HOUSING_TYPE_KHOONSUN = "Landed";
    public static final String VALID_BUDGET_AMY = "999000";
    public static final String VALID_BUDGET_BOB = "1000000";
    public static final String VALID_BUDGET_BEN = "123000";
    public static final String VALID_TAG_BUYER = "Buyer";
    public static final String VALID_TAG_SELLER = "Seller";
    public static final String VALID_BLOCK_AMY = "99B";
    public static final String VALID_BLOCK_BOB = "99A";
    public static final String VALID_LEVEL_AMY = "10";
    public static final String VALID_LEVEL_BOB = "11";
    public static final String VALID_POSTAL_CODE_AMY = "654321";
    public static final String VALID_POSTAL_CODE_BOB = "654321";
    public static final String VALID_STREET_AMY = "Orchard Road";
    public static final String VALID_STREET_BOB = "Toa Payoh Road";
    public static final String VALID_UNIT_NUMBER_AMY = "38";
    public static final String VALID_UNIT_NUMBER_BOB = "38";
    public static final String VALID_PRICE_AMY = "3000000";
    public static final String VALID_PRICE_BOB = "4000000";
    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String TAG_DESC_BUYER = " " + PREFIX_TAG + VALID_TAG_BUYER;
    public static final String TAG_DESC_SELLER = " " + PREFIX_TAG + VALID_TAG_SELLER;

    public static final String HOUSING_TYPE_DESC_AMY = " " + PREFIX_HOUSING_TYPE + VALID_HOUSING_TYPE_AMY;
    public static final String HOUSING_TYPE_DESC_BOB = " " + PREFIX_HOUSING_TYPE + VALID_HOUSING_TYPE_BOB;
    public static final String BLOCK_DESC_AMY = " " + PREFIX_BLOCK + VALID_BLOCK_AMY;
    public static final String BLOCK_DESC_BOB = " " + PREFIX_BLOCK + VALID_BLOCK_BOB;
    public static final String LEVEL_DESC_AMY = " " + PREFIX_LEVEL + VALID_LEVEL_AMY;
    public static final String LEVEL_DESC_BOB = " " + PREFIX_LEVEL + VALID_LEVEL_BOB;
    public static final String POSTAL_CODE_DESC_AMY = " " + PREFIX_POSTALCODE + VALID_POSTAL_CODE_AMY;
    public static final String POSTAL_CODE_DESC_BOB = " " + PREFIX_POSTALCODE + VALID_POSTAL_CODE_BOB;
    public static final String STREET_DESC_AMY = " " + PREFIX_STREET + VALID_STREET_AMY;
    public static final String STREET_DESC_BOB = " " + PREFIX_STREET + VALID_STREET_BOB;
    public static final String UNIT_NUMBER_DESC_AMY = " " + PREFIX_UNITNUMBER + VALID_UNIT_NUMBER_AMY;
    public static final String UNIT_NUMBER_DESC_BOB = " " + PREFIX_UNITNUMBER + VALID_UNIT_NUMBER_BOB;
    public static final String PRICE_DESC_AMY = " " + PREFIX_PRICE + VALID_PRICE_AMY;
    public static final String PRICE_DESC_BOB = " " + PREFIX_PRICE + VALID_PRICE_AMY;
    public static final String VALID_BUDGET_DESC_AMY = " " + PREFIX_BUDGET + VALID_BUDGET_AMY;
    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_HOUSING_TYPE_DESC = " " + PREFIX_HOUSING_TYPE;
    public static final String INVALID_BUDGET_DESC = " " + PREFIX_BUDGET; // "" not allowed in budget
    public static final String INVALID_PRICE_DESC = " " + PREFIX_PRICE; // "" not allowed in price
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditSellerCommand.EditSellerDescriptor DESC_SELLER_AMY;
    public static final EditSellerCommand.EditSellerDescriptor DESC_SELLER_BOB;

    public static final EditBuyerCommand.EditBuyerDescriptor DESC_BUYER_AMY;
    public static final EditBuyerCommand.EditBuyerDescriptor DESC_BUYER_BOB;


    static {
        DESC_SELLER_AMY = new EditSellerDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY)
                .withTags(VALID_TAG_SELLER).build();
        DESC_SELLER_BOB = new EditSellerDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withTags(VALID_TAG_SELLER).build();
        DESC_BUYER_AMY = new EditBuyerDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY)
                .withTags(VALID_TAG_BUYER).build();
        DESC_BUYER_BOB = new EditBuyerDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withTags(VALID_TAG_BUYER).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - EstateEase, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s EstateEase.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

}
