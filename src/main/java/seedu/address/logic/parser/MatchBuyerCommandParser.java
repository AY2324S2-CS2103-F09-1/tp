package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.MatchBuyerCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new MatchBuyerCommand object
 */
public class MatchBuyerCommandParser implements Parser<MatchBuyerCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MatchBuyerCommand
     * and returns a MatchBuyerCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public MatchBuyerCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MatchBuyerCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new MatchBuyerCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}
