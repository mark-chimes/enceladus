package Locations;

import Commands.CommandHandler;
import Commands.CommandTuple;
import Commands.NullCommandHandler;
import Locations.MainBase.MainBase;
import Locations.MainBase.MainBaseHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public class LocationsHandler extends CommandHandler {
    Location mainBase = new MainBase();;

    private final List<CommandTuple> commandTuples = Arrays.asList(
            new CommandTuple( "Main Base",
                    EMPTY_LIST,
                    Arrays.asList("The main base from which you operate."),
                    new NullCommandHandler(),
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
        setIteratorCommands(commandTuples);
    }

}
