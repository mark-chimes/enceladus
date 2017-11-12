package Locations;

import commandAndMessage.command.CommandTuple;

public class LocationCommandTuple extends CommandTuple {

    public LocationCommandTuple(Location location) {
        super(location.name(),
                location.nextMessage(),
                location.description(),
                new LocationHandler(location),
                false);
    }
}
