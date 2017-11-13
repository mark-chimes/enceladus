package Locations

import People.Person

import java.util.ArrayList

/**
 * Created by Mark Chimes on 2017/11/05.
 */
abstract class Location(protected var allPeople: List<Person>, val sublocations: List<Location>) {

    abstract fun description(): List<String>
    abstract fun name(): String
    abstract fun nextMessage(): List<String>

    val peopleRecursive: List<Person>
        get() {
            val peopleHere = ArrayList<Person>()

            if (sublocations.isEmpty()) {
                for (person in allPeople) {
                    if (person.currentLocation() == this) {
                        peopleHere.add(person)
                    }
                }

                return peopleHere
            }

            for (subLocation in sublocations) {
                peopleHere.addAll(subLocation.peopleRecursive)
            }

            return peopleHere
        }
}
