package Locations;

import Locations.MainBase.SubLocationsHandler;
import People.PeopleHandler;
import commandAndMessage.command.CommandHandler;
import commandAndMessage.command.CommandTuple;
import commandAndMessage.command.NullCommandHandler;
import commandAndMessage.main.KeyConstants;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public class LocationHandler extends CommandHandler{
    private final Location location;

    public LocationHandler(Location location) {
        this.location = location;
        setIteratorCommands(commandTuples());
    }

    private final List<CommandTuple> commandTuples() {
        return Arrays.asList(
                new CommandTuple("Description",
                        KeyConstants.EMPTY_LIST,
                        Arrays.asList("A description of this location."),
                        new NullCommandHandler(),
                        false
                ),
                new CommandTuple("Status Summary",
                        KeyConstants.EMPTY_LIST,
                        Arrays.asList("A summary of the status of this location," +
                                "and what has happened here recently."),
                        new NullCommandHandler(),
                        false
                ),
                new CommandTuple("Overview",
                        KeyConstants.EMPTY_LIST,
                        Arrays.asList("An overview of this location."),
                        new NullCommandHandler(),
                        false
                ),
                new CommandTuple("People",
                        KeyConstants.EMPTY_LIST,
                        Arrays.asList("A list of all the survivors currently in this location."),
                        new PeopleHandler(),
                        false
                ),
                location.subLocationCommandTuple()
        );
    }
}

