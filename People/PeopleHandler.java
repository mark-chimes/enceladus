package People;

import Commands.CommandHandler;
import Commands.CommandTuple;
import Commands.NullCommandHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public class PeopleHandler extends CommandHandler {
    private final List<CommandTuple> commandTuples = Arrays.asList(
            new CommandTuple("Mark",
                    EMPTY_LIST,
                    Arrays.asList("A person named Mark"),
                    new NullCommandHandler(),
                    false
            ),
            new CommandTuple("Lilley",
                    EMPTY_LIST,
                    Arrays.asList("A person named Lilley."),
                    new NullCommandHandler(),
                    false
            )
    );

    public PeopleHandler() {
        setIteratorCommands(commandTuples);
    }
}
