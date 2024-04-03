package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.AddHouseCommand.MESSAGE_DUPLICATE_HOUSE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOCK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOUSING_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSTALCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STREET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNITNUMBER;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.State;
import seedu.address.model.house.House;
import seedu.address.model.person.Seller;


/**
 * Adds a seller to the address book.
 */
public class AddSellerCommand extends Command {

    public static final String COMMAND_WORD = "addSeller";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a seller to EstateEase. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_HOUSING_TYPE + "HOUSING_TYPE "
            + PREFIX_STREET + "STREET "
            + PREFIX_BLOCK + "BLOCK "
            + PREFIX_LEVEL + "LEVEL "
            + PREFIX_UNITNUMBER + "UNIT NUMBER "
            + PREFIX_POSTALCODE + "POSTAL CODE "
            + PREFIX_PRICE + "PRICE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_HOUSING_TYPE + "Hdb "
            + PREFIX_STREET + "Clementi Ave 2 "
            + PREFIX_BLOCK + "311 "
            + PREFIX_LEVEL + "02 "
            + PREFIX_UNITNUMBER + "25 "
            + PREFIX_POSTALCODE + "578578 "
            + PREFIX_PRICE + "999999999";

    public static final String MESSAGE_SUCCESS = "New seller added= %1$s";
    public static final String MESSAGE_DUPLICATE_SELLER = "This person already exists in EstateEase";

    private final Seller sellerToAdd;

    /**
     * Creates an AddSellerCommand to add the specified {@code Seller}
     */
    public AddSellerCommand(Seller seller) {
        requireNonNull(seller);
        sellerToAdd = seller;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(sellerToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_SELLER);
        }

        model.addPerson(sellerToAdd);

        House houseToAdd = sellerToAdd.getHouses().get(0);

        if (model.hasHouse(houseToAdd)) {
            model.deletePersonFromPersons(sellerToAdd);
            throw new CommandException(MESSAGE_DUPLICATE_HOUSE);
        }

        model.addHouseToHouses(houseToAdd);
        model.setState(State.PERSON_LIST);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(sellerToAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddSellerCommand)) {
            return false;
        }

        AddSellerCommand otherAddCommand = (AddSellerCommand) other;
        return sellerToAdd.equals(otherAddCommand.sellerToAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("sellerToAdd", sellerToAdd)
                .toString();
    }
}
