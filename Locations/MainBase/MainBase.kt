package Locations.MainBase

import Locations.Location
import Locations.LocationHandler
import People.Person
import commandAndMessage.command.CommandHandler
import commandAndMessage.command.CommandTuple
import commandAndMessage.command.NullCommandHandler
import commandAndMessage.main.KeyConstants

import java.util.Arrays

/**
 * Created by Mark Chimes on 2017/11/08.
 */
class MainBase(allPeople: List<Person>, sublocations: List<Location>) : Location(allPeople, sublocations) {

    override fun description(): List<String> {
        return Arrays.asList("The main base.")
    }

    override fun name(): String {
        return "Main Base"
    }


    override fun nextMessage(): List<String> {
        return KeyConstants.EMPTY_LIST
    }

}