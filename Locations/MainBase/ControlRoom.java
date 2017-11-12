package Locations.MainBase;

import Locations.Location;
import People.Mark;
import People.Person;
import commandAndMessage.main.KeyConstants;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mark Chimes on 2017/11/09.
 */
public class ControlRoom extends Location {
    public ControlRoom(List<Person> allPeople, List<Location> sublocations) {
        super(allPeople, sublocations);
    }

    @Override
    public List<String> description() {
        return Arrays.asList("Description forthcoming");
    }

    @Override
    public String name() {
        return "Control Room";
    }

    @Override
    public List<String> nextMessage() {
        return KeyConstants.EMPTY_LIST;
    }

}
