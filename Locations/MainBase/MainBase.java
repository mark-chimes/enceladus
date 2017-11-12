package Locations.MainBase;

import Locations.Location;
import Locations.LocationHandler;
import People.Person;
import commandAndMessage.command.CommandHandler;
import commandAndMessage.command.CommandTuple;
import commandAndMessage.command.NullCommandHandler;
import commandAndMessage.main.KeyConstants;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Mark Chimes on 2017/11/08.
 */
public class MainBase extends Location {

    public MainBase(List<Person> allPeople, List<Location> sublocations) {
        super(allPeople, sublocations);
    }

    @Override
    public List<String> description() {
        return Arrays.asList("The main base.");
    }

    @Override
    public String name() {
        return "Main Base";
    }


    @Override
    public List<String> nextMessage() {
        return KeyConstants.EMPTY_LIST;
    }

}