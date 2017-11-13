package Game

import Locations.Location
import Locations.LocationsListHandler
import Locations.MainBase.ControlRoom
import Locations.MainBase.LivingQuarters
import Locations.MainBase.MainBase
import Locations.WildsBeyond.WildsBeyond
import People.Lilley
import People.Mark
import People.Person
import com.sun.javafx.UnmodifiableArrayList
import commandAndMessage.command.CommandHandler
import commandAndMessage.command.CommandTuple
import commandAndMessage.command.NullCommandHandler
import People.PeopleHandler
import commandAndMessage.main.KeyConstants

import java.util.ArrayList
import java.util.Arrays


/**
 * Created by Mark Chimes on 2017/11/05.
 */
class MainGameHandler : CommandHandler() {
    private val emptyList = UnmodifiableArrayList<Location>(arrayOfNulls(0), 0)

    private val allPeople: MutableList<Person>
    private val allLocations: List<Location>


    private fun commandTuples(): List<CommandTuple> {
        return Arrays.asList(
                CommandTuple("View People",
                        KeyConstants.EMPTY_LIST,
                        Arrays.asList("A list of all your survivors."),
                        PeopleHandler(allPeople),
                        false
                ),
                CommandTuple("View Locations",
                        KeyConstants.EMPTY_LIST,
                        Arrays.asList("A list of all the locations you have discovered."),
                        LocationsListHandler(allLocations),
                        false
                ),
                CommandTuple("Exit",
                        KeyConstants.EMPTY_LIST,
                        Arrays.asList("Continues a game that was previously played."),
                        NullCommandHandler(),
                        false,
                        Runnable { this.exitGame() }
                )
        )
    }

    private fun exitGame() {
        System.exit(0)
    }

    init {

        allPeople = ArrayList()

        val controlRoom = ControlRoom(allPeople, emptyList)
        val livingQuarters = LivingQuarters(allPeople, emptyList)

        allLocations = buildLocationsTree(allPeople, controlRoom, livingQuarters)
        setUpPeople(allPeople, controlRoom, livingQuarters)

        setIteratorCommands(commandTuples())

    }

    fun setUpPeople(possiblyEmptyPeopleList: MutableList<Person>, vararg locations: Location) {
        val mark = Mark(locations[0])
        val lilley = Lilley(locations[1])

        possiblyEmptyPeopleList.add(mark)
        possiblyEmptyPeopleList.add(lilley)
    }

    fun buildLocationsTree(possiblyEmptyPeopleList: List<Person>,
                           vararg startLocations: Location): List<Location> {
        val mainBase = MainBase(possiblyEmptyPeopleList, Arrays.asList(*startLocations))
        val wildsBeyond = WildsBeyond(possiblyEmptyPeopleList, emptyList)

        return Arrays.asList(mainBase, wildsBeyond)
    }
}
