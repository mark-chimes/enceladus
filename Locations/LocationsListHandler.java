package Locations;

import Locations.MainBase.MainBase;
import Locations.WildsBeyond.WildsBeyond;
import commandAndMessage.command.CommandHandler;
import commandAndMessage.command.CommandTuple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public class LocationsListHandler extends CommandHandler {
    List<Location> locations = Arrays.asList(new MainBase());

    public LocationsListHandler() {
        List<CommandTuple> commandTuples = new ArrayList<>();

        for (Location location : locations) {
            commandTuples.add(location.getCommandTuple());
        }
        setIteratorCommands(commandTuples);
    }

}
