package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.State;
import seedu.address.model.house.House;
import seedu.address.model.house.HousingType;
import seedu.address.model.house.PriceAndHousingTypePredicate;
import seedu.address.model.person.Budget;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.FullNameEqualsKeywordPredicate;

/**
 * Matches a specific buyer's budget and housing type to sellers' house price and
 * housing type when given the buyer's name.
 * Keyword matching is case-insensitive.
 */
public class MatchBuyerCommand extends Command {

    public static final String COMMAND_WORD = "matchBuyer";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Matches a specific buyer's budget and housing type "
            + "to sellers' house price and housing type when given the buyer's name.\n"
            + "Parameters: full name\n"
            + "Example: " + COMMAND_WORD + " alice chan";

    private final FullNameEqualsKeywordPredicate fullNamePredicate;

    public MatchBuyerCommand(FullNameEqualsKeywordPredicate fullNamePredicate) {
        this.fullNamePredicate = fullNamePredicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(fullNamePredicate);
        if (model.getFilteredPersonList().isEmpty()) {
            return new CommandResult(Messages.MESSAGE_BUYER_NOT_FOUND);
        } else {
            Buyer targetBuyer = (Buyer) model.getFilteredPersonList().get(0);
            Budget budget = targetBuyer.getBudget();
            HousingType housingType = targetBuyer.getPreferredHousingType();
            PriceAndHousingTypePredicate predicate = new PriceAndHousingTypePredicate(budget.toPrice(), housingType);

            model.updateFilteredSellerList(predicate);

            ObservableList<House> filteredSellerList = model.getAllFilteredHouseList(predicate);
            model.setState(State.MATCH_RESULTS);
            model.showMatchResults(model.getFilteredSellerList());

            return new CommandResult(
                    String.format(Messages.MESSAGE_HOUSE_LISTED_OVERVIEW, filteredSellerList.size()));
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MatchBuyerCommand)) {
            return false;
        }

        MatchBuyerCommand otherMatchBuyerCommand = (MatchBuyerCommand) other;
        return fullNamePredicate.equals(otherMatchBuyerCommand.fullNamePredicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("fullNamePredicate", fullNamePredicate)
                .toString();
    }
}
