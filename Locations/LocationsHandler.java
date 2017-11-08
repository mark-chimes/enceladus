package Locations;

import Commands.CommandHandler;
import Locations.MainBase.MainBase;
import Locations.MainBase.MainBaseHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public class LocationsHandler extends CommandHandler {
    Location mainBase;

    public LocationsHandler() {
        setIteratorMessages(commands());
        mainBase = new MainBase();
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
    public List<String> getHelpText() {
        switch (currentText()) {
            case "Main Base":
                return mainBase.description();
        }
        return new ArrayList<>(); // TODO
    }
}
