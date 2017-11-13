package People

import Locations.Location

/**
 * Created by Mark Chimes on 2017/11/08.
 */
abstract class Person(private val name: String, private val location: Location) {

    fun name(): String {
        return name
    }

    fun currentLocation(): Location {
        return location
    }
}
