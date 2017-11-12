package Locations;

import Locations.MainBase.MainBase;
import Locations.WildsBeyond.WildsBeyond;
import commandAndMessage.command.CommandHandler;
import commandAndMessage.command.CommandTuple;
import commandAndMessage.main.CommandLoop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public class LocationsListHandler extends CommandHandler {
    List<Location> locations = Arrays.asList(new MainBase());

    public LocationsListHandler() {
        List<CommandTuple> commandTuples = new ArrayList<>();

        for (Location location : locations) {
            commandTuples.add(new LocationCommandTuple(location));
        }
        setIteratorCommands(commandTuples);
    }
}
