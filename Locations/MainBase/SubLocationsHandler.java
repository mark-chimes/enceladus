package Locations.MainBase;

import Locations.Location;
import commandAndMessage.command.CommandHandler;
import commandAndMessage.command.CommandTuple;
import commandAndMessage.command.NullCommandHandler;
import commandAndMessage.main.KeyConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mark Chimes on 2017/11/09.
 */
public class SubLocationsHandler extends CommandHandler {
    private final List<CommandTuple> locationCommands = new ArrayList<>();

    public SubLocationsHandler(Location location) {
        final List<Location> sublocations = location.getSublocations();

        for (Location sublocation : sublocations) {
            locationCommands.add(sublocation.getCommandTuple());
        }

        setIteratorCommands(locationCommands);
    }
}
