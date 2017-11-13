package Locations.WildsBeyond

import Locations.Location
import Locations.LocationHandler
import People.Person
import commandAndMessage.command.CommandHandler
import commandAndMessage.main.KeyConstants

import java.util.Arrays

/**
 * Created by Mark Chimes on 2017/11/08.
 */
class WildsBeyond(allPeople: List<Person>, sublocations: List<Location>) : Location(allPeople, sublocations) {

    override fun description(): List<String> {
        return Arrays.asList("The main base.")
    }

    override fun name(): String {
        return "Wilds Beyond"
    }

    override fun nextMessage(): List<String> {
        return KeyConstants.EMPTY_LIST
    }
}