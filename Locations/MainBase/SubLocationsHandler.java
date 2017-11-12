package Locations.MainBase;

import Locations.Location;
import Locations.LocationCommandTuple;
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

    public SubLocationsHandler(List<Location> sublocations) {
        for (Location sublocation : sublocations) {
            locationCommands.add(new LocationCommandTuple(sublocation));
        }

        setIteratorCommands(locationCommands);
    }
}
