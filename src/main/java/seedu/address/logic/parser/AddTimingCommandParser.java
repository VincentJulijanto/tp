package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddTimingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.RunTiming;

import static seedu.address.logic.parser.CliSyntax.*;

public class AddTimingCommandParser implements Parser<AddTimingCommand> {

    @Override
    public AddTimingCommand parse(String args) throws ParseException {

        ArgumentMultimap map = ArgumentTokenizer.tokenize(args,
                PREFIX_MIN,
                PREFIX_SEC);

        Index index = ParserUtil.parseIndex(map.getPreamble());

        int minutes = Integer.parseInt(map.getValue(PREFIX_MIN).get());
        double seconds = Double.parseDouble(map.getValue(PREFIX_SEC).get());

        if (minutes == 0 && seconds == 0) {
            throw new ParseException("Invalid timing: total time must be greater than 0");
        }

        RunTiming timing = new RunTiming(minutes, seconds);

        return new AddTimingCommand(index, timing);
    }
}
