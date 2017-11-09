package Game;

import commandAndMessage.command.CommandHandler;
import commandAndMessage.command.CommandTuple;
import commandAndMessage.command.NullCommandHandler;
import Locations.LocationsHandler;
import People.PeopleHandler;

import java.util.Arrays;
import java.util.List;


/**
 * Created by Mark Chimes on 2017/11/05.
 */
public class MainGameHandler extends CommandHandler {
    private final List<CommandTuple> commandTuples = Arrays.asList(
            new CommandTuple("View People",
                    EMPTY_LIST,
                    Arrays.asList("A list of all your survivors."),
                    new PeopleHandler(),
                    false
            ),
            new CommandTuple("View Locations",
                    EMPTY_LIST,
                    Arrays.asList("A list of all the locations you have discovered."),
                    new LocationsHandler(),
                    false
            ),
            new CommandTuple("Exit",
                    EMPTY_LIST,
                    Arrays.asList("Quit the game, for good."),
                    new NullCommandHandler(),
                    false,
                    () -> System.exit(0)
            )
    );

    public MainGameHandler() {
        setIteratorCommands(commandTuples);
    }
}
