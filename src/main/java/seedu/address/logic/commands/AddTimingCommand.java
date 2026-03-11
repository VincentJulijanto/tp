package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.RunTiming;

public class AddTimingCommand extends Command {

    public static final String COMMAND_WORD = "addtiming";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a 2.4km run timing to the athlete identified by the index number.\n"
            + "Parameters: INDEX min/MINUTES sec/SECONDS\n"
            + "Example: " + COMMAND_WORD + " 1 min/10 sec/30";

    private final Index index;
    private final RunTiming timing;

    public AddTimingCommand(Index index, RunTiming timing) {
        this.index = index;
        this.timing = timing;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {

        requireNonNull(model);

        List<Person> athletes = model.getFilteredPersonList();

        if (index.getZeroBased() >= athletes.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person athlete = athletes.get(index.getZeroBased());

        athlete.addRunTiming(timing);

        String resultMessage = "Added timing for "
                + athlete.getName()
                + ": "
                + timing;

        return new CommandResult(resultMessage);
    }
}
