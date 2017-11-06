package Locations;

import Core.CommandHandler;
import Core.CommandOrTextHandler;
import Locations.MainBase.MainBaseHandler;

import java.util.ArrayList;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public class LocationsHandler extends CommandHandler {
    public LocationsHandler() {
        setIteratorMessages(commands());
    }

    public void performActionFor(String actionString) {
        switch (actionString) {
            case "Main Base":
                setNextCommand(new MainBaseHandler());
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

    @Override
    public CommandOrTextHandler newHandlerFrom() {
        return new LocationsHandler();
    }
}
