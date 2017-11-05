package Locations;

import Core.CommandHandler;
import Core.KeyConstants;

import java.util.ArrayList;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public class LocationHandler extends CommandHandler {
    public LocationHandler() {
        setIteratorMessages(commands());
    }

    public void performActionFor(String actionString) {
        switch (actionString) {
            case "Main Base":
                break;
            case "The Wild Beyond":
                break;
        }
    }

    private final ArrayList<String>  commands() {
        ArrayList<String> initialCommands = new ArrayList<>();
        initialCommands.add("Main Base");
        initialCommands.add("The Wild Beyond");
        return initialCommands;
    }
}
