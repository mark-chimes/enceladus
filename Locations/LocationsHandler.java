package Locations;

import Locations.MainBase.MainBaseHandler;
import commandAndMessage.command.CommandHandler;
import commandAndMessage.command.CommandTuple;
import commandAndMessage.command.NullCommandHandler;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public class LocationsHandler extends CommandHandler {
    private final List<CommandTuple> locationCommands = Arrays.asList(
            new CommandTuple( "Main Base",
                    EMPTY_LIST,
                    Arrays.asList("The main base from which you operate."),
                    new MainBaseHandler(),
                    false
            ),
            new CommandTuple("The Wild Beyond",
                    EMPTY_LIST,
                    Arrays.asList("The dangerous wilds that lie outside the base."),
                    new NullCommandHandler(),
                    false
            )
    );

    public LocationsHandler() {
        setIteratorCommands(locationCommands);
    }

}
