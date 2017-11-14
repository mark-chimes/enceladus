package Locations.MainBase

import Locations.Location
import Locations.LocationCommandTuple
import commandAndMessage.command.CommandHandler
import commandAndMessage.command.CommandTuple
import commandAndMessage.command.NullCommandHandler
import commandAndMessage.main.KeyConstants

import java.util.ArrayList
import java.util.Arrays

/**
 * Created by Mark Chimes on 2017/11/09.
 */
class SubLocationsHandler(sublocations: List<Location>) : CommandHandler() {
    private val locationCommands = ArrayList<CommandTuple>()

    init {
        sublocations.mapTo(locationCommands) { LocationCommandTuple(it) }
        setIteratorCommands(locationCommands)
    }
}
