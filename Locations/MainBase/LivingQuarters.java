package Locations.MainBase;

import Locations.Location;
import commandAndMessage.command.CommandHandler;
import commandAndMessage.command.NullCommandHandler;
import commandAndMessage.main.KeyConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mark Chimes on 2017/11/09.
 */
public class LivingQuarters extends Location {
    @Override
    public List<String> description() {
        return Arrays.asList("Description forthcoming");
    }

    @Override
    public String name() {
        return "Living Quarters";
    }

    @Override
    public List<String> nextMessage() {
        return KeyConstants.EMPTY_LIST;
    }

}
