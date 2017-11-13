package Locations

import commandAndMessage.command.CommandTuple

class LocationCommandTuple(location: Location) : CommandTuple(location.name(), location.nextMessage(), location.description(), LocationHandler(location), false)
