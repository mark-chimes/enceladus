package Locations.MainBase;

import Locations.Location;
import commandAndMessage.main.KeyConstants;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Mark Chimes on 2017/11/09.
 */
public class ControlRoom extends Location {
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
