package Locations;

import Locations.MainBase.SubLocationsHandler;
import commandAndMessage.command.CommandHandler;
import commandAndMessage.command.CommandTuple;
import commandAndMessage.command.NullCommandHandler;
import commandAndMessage.main.CommandLoop;
import commandAndMessage.main.KeyConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public abstract class Location {
    public abstract List<String> description();
    public abstract String name();
    public abstract List<String> nextMessage();
    private final static Logger LOGGER = Logger.getLogger(CommandLoop.class.getName());

    public final CommandHandler commandHandler() {
        return new LocationHandler(this);
    }

    public List<Location> getSublocations() {
        return new ArrayList<>();
    }

    public final CommandTuple subLocationCommandTuple() {
        CommandHandler sublocationHandler;
        List<String> nextText;

        if (getSublocations().isEmpty()) {
            sublocationHandler = new NullCommandHandler();
            nextText = Arrays.asList("This location has no sublocations.");
        } else {
            sublocationHandler = new SubLocationsHandler(this);
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

    public final CommandTuple getCommandTuple() {
        LOGGER.info("getCommandTuple for: " + this.getClass());
        return new CommandTuple(name(),
                nextMessage(),
                description(),
                commandHandler(),
                false);
    }
}
