package Locations

import Locations.MainBase.SubLocationsHandler
import People.PeopleHandler
import commandAndMessage.command.CommandHandler
import commandAndMessage.command.CommandTuple
import commandAndMessage.command.NullCommandHandler
import commandAndMessage.main.KeyConstants

import java.util.Arrays

/**
 * Created by Mark Chimes on 2017/11/05.
 */
class LocationHandler(private val location: Location) : CommandHandler() {

    init {
        setIteratorCommands(commandTuples())
    }

    private fun commandTuples(): List<CommandTuple> {
        return Arrays.asList(
                CommandTuple("Description",
                        KeyConstants.EMPTY_LIST,
                        Arrays.asList("A description of this location."),
                        NullCommandHandler(),
                        false
                ),
                CommandTuple("Status Summary",
                        KeyConstants.EMPTY_LIST,
                        Arrays.asList("A summary of the status of this location," + "and what has happened here recently."),
                        NullCommandHandler(),
                        false
                ),
                CommandTuple("Overview",
                        KeyConstants.EMPTY_LIST,
                        Arrays.asList("An overview of this location."),
                        NullCommandHandler(),
                        false
                ),
                peopleCommandTuple(),
                subLocationCommandTuple()
        )
    }

    private fun peopleCommandTuple(): CommandTuple {
        val peopleCommandHandler: CommandHandler
        val nextText: List<String>

        if (location.peopleRecursive.isEmpty()) {
            peopleCommandHandler = NullCommandHandler()
            nextText = Arrays.asList("There is no-one currently at this location.")
        } else {
            peopleCommandHandler = PeopleHandler(location.peopleRecursive)
            nextText = KeyConstants.EMPTY_LIST
        }

        return CommandTuple("People here",
                nextText,
                Arrays.asList("A list of all the survivors currently in this location."),
                peopleCommandHandler,
                false
        )
    }

    private fun subLocationCommandTuple(): CommandTuple {
        val sublocationHandler: CommandHandler
        val nextText: List<String>

        if (location.sublocations.isEmpty()) {
            sublocationHandler = NullCommandHandler()
            nextText = Arrays.asList("This location has no sublocations.")
        } else {
            sublocationHandler = SubLocationsHandler(location.sublocations)
            nextText = KeyConstants.EMPTY_LIST
        }

        return CommandTuple("Sub-locations",
                nextText,
                Arrays.asList("A list of sub-locations within this location, " + "from which you can get a more fine-grained view."),
                sublocationHandler,
                false
        )
    }
}

