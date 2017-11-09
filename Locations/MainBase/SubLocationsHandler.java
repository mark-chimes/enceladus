package Locations.MainBase;

import commandAndMessage.command.CommandHandler;
import commandAndMessage.command.CommandTuple;
import commandAndMessage.command.NullCommandHandler;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Mark Chimes on 2017/11/09.
 */
public class SubLocationsHandler extends CommandHandler {
    private final List<CommandTuple> locationCommands = Arrays.asList(
        new CommandTuple( "Control Room",
            EMPTY_LIST,
            Arrays.asList("The control room contains all of the complex structures " +
                    "which keep the base running."),
            new NullCommandHandler(),
            false
        ),
        new CommandTuple("Living Quarters",
            EMPTY_LIST,
            Arrays.asList("The living quarters is the section containing all of the " +
                    "incidental structures necessary for continued human survival."),
            new NullCommandHandler(),
            false
        )
    );

    public SubLocationsHandler() {
        setIteratorCommands(locationCommands);
    }
}
