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
                peopleCommandTuple(),
                subLocationCommandTuple()
        );
    }

    private final CommandTuple peopleCommandTuple() {
        CommandHandler peopleCommandHandler;
        List<String> nextText;

        if (location.getPeopleRecursive().isEmpty()) {
            peopleCommandHandler = new NullCommandHandler();
            nextText = Arrays.asList("There is no-one currently at this location.");
        } else {
            peopleCommandHandler = new PeopleHandler(location.getPeopleRecursive());
            nextText = KeyConstants.EMPTY_LIST;
        }

        return new CommandTuple("People here",
                nextText,
                Arrays.asList("A list of all the survivors currently in this location."),
                peopleCommandHandler,
                false
        );
    }

    private final CommandTuple subLocationCommandTuple() {
        CommandHandler sublocationHandler;
        List<String> nextText;

        if (location.getSublocations().isEmpty()) {
            sublocationHandler = new NullCommandHandler();
            nextText = Arrays.asList("This location has no sublocations.");
        } else {
            sublocationHandler = new SubLocationsHandler(location.getSublocations());
            nextText = KeyConstants.EMPTY_LIST;
        }

        return new CommandTuple("Sub-locations",
                nextText,
                Arrays.asList("A list of sub-locations within this location, " +
                        "from which you can get a more fine-grained view."),
                sublocationHandler,
                false
        );
    }
}

