package Locations.MainBase;

import Commands.CommandHandler;
import Commands.CommandTuple;
import Commands.NullCommandHandler;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public class MainBaseHandler extends CommandHandler{
    private final List<CommandTuple> commandTuples = Arrays.asList(
            new CommandTuple("Description",
                    EMPTY_LIST,
                    Arrays.asList("A description of this location."),
                    new NullCommandHandler(),
                    false
            ),
            new CommandTuple("Status Summary",
                    EMPTY_LIST,
                    Arrays.asList("A summary of the status of this location," +
                            "and what has happened here recently."),
                    new NullCommandHandler(),
                    false
            ),
            new CommandTuple("Overview",
                    EMPTY_LIST,
                    Arrays.asList("An overview of this location."),
                    new NullCommandHandler(),
                    false
            ),
            new CommandTuple("People",
                    EMPTY_LIST,
                    Arrays.asList("A list of all the survivors currently in this location."),
                    new NullCommandHandler(),
                    false
            ),
            new CommandTuple("Sub-locations",
                    EMPTY_LIST,
                    Arrays.asList("A list of sub-locations within this location, " +
                            "from which you can get a more fine-grained view."),
                    new NullCommandHandler(),
                    false
            )
    );

    public MainBaseHandler() {
        setIteratorCommands(commandTuples);
    }
}

