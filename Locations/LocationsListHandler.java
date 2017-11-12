package Locations;

import commandAndMessage.command.CommandHandler;
import commandAndMessage.command.CommandTuple;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public class LocationsListHandler extends CommandHandler {
    public LocationsListHandler(List<Location> locations) {
        List<CommandTuple> commandTuples = new ArrayList<>();

        for (Location location : locations) {
            commandTuples.add(new LocationCommandTuple(location));
        }
        setIteratorCommands(commandTuples);
    }
}
