package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOCK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOUSING_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_POSTALCODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STREET;
import static seedu.address.logic.parser.CliSyntax.PREFIX_UNITNUMBER;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.State;
import seedu.address.model.house.House;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Seller;

/**
 * Adds a house to the seller.
 */
public class DeleteHouseCommand extends Command {

    public static final String COMMAND_WORD = "deleteHouse";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a house assigned to a Seller. Indicate N/A "
            + "for nonexistent fields. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_HOUSING_TYPE + "HOUSING_TYPE "
            + PREFIX_STREET + "STREET "
            + PREFIX_BLOCK + "BLOCK "
            + PREFIX_LEVEL + "LEVEL "
            + PREFIX_UNITNUMBER + "UNIT NUMBER "
            + PREFIX_POSTALCODE + "POSTAL CODE "
            + PREFIX_PRICE + "PRICE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_HOUSING_TYPE + "Condominium "
            + PREFIX_STREET + "Clementi Ave 2 "
            + PREFIX_BLOCK + "N/A "
            + PREFIX_LEVEL + "02 "
            + PREFIX_UNITNUMBER + "25 "
            + PREFIX_POSTALCODE + "578578 "
            + PREFIX_PRICE + "99999 ";

    public static final String MESSAGE_SUCCESS = "House deleted!";
    public static final String MESSAGE_MISSING_HOUSE = "This house does not exist in EstateEase";

    public static final String MESSAGE_WRONG_HOUSE = "This house does not belong to this seller!";

    public static final String MESSAGE_INVALID_SELLER = "This Seller does not exist in EstateEase";

    private final House houseToDelete;

    private final Name nameToCheck;

    /**
     * Creates a DeleteHouseCommand to delete the specified {@code house}
     */
    public DeleteHouseCommand(House house, Name name) {
        requireNonNull(house);
        requireNonNull(name);
        houseToDelete = house;
        nameToCheck = name;
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

        if (!model.hasHouse(houseToDelete)) {
            throw new CommandException(MESSAGE_MISSING_HOUSE);
        }

        if (!model.hasPerson(nameToCheck)) {
            throw new CommandException(MESSAGE_INVALID_SELLER);
        }

        Person sellerToDeleteFrom = model.findPersonByName(nameToCheck);

        if (!(sellerToDeleteFrom instanceof Seller)) {
            throw new CommandException(MESSAGE_INVALID_SELLER);
        }

        if (!((Seller) sellerToDeleteFrom).hasHouse(houseToDelete)) {
            throw new CommandException(MESSAGE_WRONG_HOUSE);
        }

        model.deleteHouse(houseToDelete, sellerToDeleteFrom);
        model.setState(State.PERSON_LIST);
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteHouseCommand)) {
            return false;
        }

        DeleteHouseCommand otherDeleteCommand = (DeleteHouseCommand) other;
        return houseToDelete.equals(otherDeleteCommand.houseToDelete);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("houseToDelete", houseToDelete)
                .toString();
    }
}
