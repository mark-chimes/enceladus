package Locations.MainBase

import Locations.Location
import People.Person
import commandAndMessage.main.KeyConstants

import java.util.Arrays

/**
 * Created by Mark Chimes on 2017/11/09.
 */
class LivingQuarters(allPeople: List<Person>, sublocations: List<Location>) : Location(allPeople, sublocations) {

    override fun description(): List<String> {
        return Arrays.asList("Description forthcoming")
    }

    override fun name(): String {
        return "Living Quarters"
    }

    override fun nextMessage(): List<String> {
        return KeyConstants.EMPTY_LIST
    }


}
