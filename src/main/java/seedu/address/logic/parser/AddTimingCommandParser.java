package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_MIN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEC;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddTimingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.RunTiming;

/**
 * Parses input arguments and creates an AddTimingCommand.
 */
public class AddTimingCommandParser implements Parser<AddTimingCommand> {

    @Override
    public AddTimingCommand parse(String args) throws ParseException {

        ArgumentMultimap map = ArgumentTokenizer.tokenize(args,
                PREFIX_MIN,
                PREFIX_SEC);

        // Check duplicate fields
        map.verifyNoDuplicatePrefixesFor(PREFIX_MIN, PREFIX_SEC);

        // Check missing required fields
        if (map.getValue(PREFIX_MIN).isEmpty() || map.getValue(PREFIX_SEC).isEmpty()) {
            throw new ParseException("Missing required fields: min/ sec/");
        }

        Index index;
        int minutes;
        double seconds;

        try {
            index = ParserUtil.parseIndex(map.getPreamble());
            minutes = Integer.parseInt(map.getValue(PREFIX_MIN).get());
            seconds = Double.parseDouble(map.getValue(PREFIX_SEC).get());
        } catch (Exception e) {
            throw new ParseException(
                    "Invalid command format: addtiming INDEX min/MINUTES sec/SECONDS");
        }

        // Prevent negative values
        if (minutes < 0) {
            throw new ParseException("Invalid minutes: must be a non-negative integer");
        }

        if (seconds < 0 || seconds >= 60) {
            throw new ParseException("Invalid seconds: must be between 0 and 59.99");
        }

        // Prevent zero timing
        if (minutes == 0 && seconds == 0) {
            throw new ParseException("Invalid timing: total time must be greater than 0");
        }

        RunTiming timing = new RunTiming(minutes, seconds);

        return new AddTimingCommand(index, timing);
    }
}
