package Locations

import commandAndMessage.command.CommandHandler
import commandAndMessage.command.CommandTuple

import java.util.ArrayList

/**
 * Created by Mark Chimes on 2017/11/05.
 */
class LocationsListHandler(locations: List<Location>) : CommandHandler() {
    init {
        val commandTuples = ArrayList<CommandTuple>()

        for (location in locations) {
            commandTuples.add(LocationCommandTuple(location))
        }
        setIteratorCommands(commandTuples)
    }
}
