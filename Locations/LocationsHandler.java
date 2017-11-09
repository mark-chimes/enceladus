package Locations;

import Locations.MainBase.LivingQuarters;
import Locations.MainBase.MainBase;
import Locations.MainBase.MainBaseHandler;
import commandAndMessage.command.CommandHandler;
import commandAndMessage.command.CommandTuple;
import commandAndMessage.command.NullCommandHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public class LocationsHandler extends CommandHandler {
    List<Location> locations = Arrays.asList(new MainBase(), new LivingQuarters());

    public LocationsHandler() {
        List<CommandTuple> commandTuples = new ArrayList<>();

        for (Location location : locations) {
            commandTuples.add(new CommandTuple(location.name(),
                    EMPTY_LIST,
                    location.description(),
                    new NullCommandHandler(),
                    false)
            );
        }
        setIteratorCommands(commandTuples);
    }

}
