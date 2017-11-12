package Game;

import Locations.Location;
import Locations.LocationsListHandler;
import Locations.MainBase.ControlRoom;
import Locations.MainBase.LivingQuarters;
import Locations.WildsBeyond.WildsBeyond;
import People.Lilley;
import People.Mark;
import People.Person;
import com.sun.javafx.UnmodifiableArrayList;
import commandAndMessage.command.CommandHandler;
import commandAndMessage.command.CommandTuple;
import commandAndMessage.command.NullCommandHandler;
import People.PeopleHandler;
import commandAndMessage.main.KeyConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Mark Chimes on 2017/11/05.
 */
public class MainGameHandler extends CommandHandler {
    private final List<Location> emptyList = new UnmodifiableArrayList<>(new Location[0], 0);

    private List<Person> allPeople;
    private List<Location> allLocations;


    private final List<CommandTuple> commandTuples() {
        return Arrays.asList(
                new CommandTuple("View People",
                        KeyConstants.EMPTY_LIST,
                        Arrays.asList("A list of all your survivors."),
                        new PeopleHandler(allPeople),
                        false
                ),
                new CommandTuple("View Locations",
                        KeyConstants.EMPTY_LIST,
                        Arrays.asList("A list of all the locations you have discovered."),
                        new LocationsListHandler(allLocations),
                        false
                ),
                new CommandTuple("Exit",
                        KeyConstants.EMPTY_LIST,
                        Arrays.asList("Quit the game entirely."),
                        new NullCommandHandler(),
                        false,
                        () -> System.exit(0)
                )
        );
    }

    public MainGameHandler() {

        allPeople = new ArrayList<>();

        Location controlRoom = new ControlRoom(allPeople, emptyList);
        Location livingQuarters = new LivingQuarters(allPeople, emptyList);

        allLocations = buildLocationsTree(allPeople, controlRoom, livingQuarters);
        setUpPeople(allPeople, controlRoom, livingQuarters);

        setIteratorCommands(commandTuples());

    }

    public void setUpPeople(List<Person> possiblyEmptyPeopleList, Location... locations) {
        Person mark = new Mark(locations[0]);
        Person lilley = new Lilley(locations[1]);

        possiblyEmptyPeopleList.add(mark);
        possiblyEmptyPeopleList.add(lilley);
    }

    public List<Location> buildLocationsTree(List<Person> possiblyEmptyPeopleList,
                                             Location... startLocations) {
        Location mainBase = new LivingQuarters(possiblyEmptyPeopleList, Arrays.asList(startLocations));
        Location wildsBeyond = new WildsBeyond(possiblyEmptyPeopleList, emptyList);

        return Arrays.asList(mainBase, wildsBeyond);
    }
}
