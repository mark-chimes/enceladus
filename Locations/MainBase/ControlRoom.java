package Locations.MainBase;

import Locations.Location;
import People.Mark;
import commandAndMessage.main.KeyConstants;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mark Chimes on 2017/11/09.
 */
public class ControlRoom extends Location {
    public ControlRoom() {
        super.peopleHere = Arrays.asList(new Mark());
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
