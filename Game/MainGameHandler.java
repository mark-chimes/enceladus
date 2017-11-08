package Game;

import Commands.CommandHandler;
import Locations.LocationsHandler;
import People.PeopleHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Mark Chimes on 2017/11/05.
 */
public class MainGameHandler extends CommandHandler {
    public static final String VIEW_PEOPLE = "View People";
    public static final String VIEW_LOCATIONS = "View Locations";
    public static final String EXIT = "Exit";

    public MainGameHandler() {
        setIteratorMessages(commands());
    }

    @Override
    public void performActionFor(String actionString) {
        switch (actionString) {
            case VIEW_PEOPLE:
                setNextCommand(new PeopleHandler());
                break;
            case VIEW_LOCATIONS:
                setNextCommand(new LocationsHandler());
                break;
            case EXIT:
                System.exit(0);
                break;
        }
    }

    @Override
    protected String getDefaultName() {
        return "New Game";
    }

    @Override
    public List<String> getDefaultHelpText() {
        return Arrays.asList("Starts a game completely from the beginning.");
    }

    private final ArrayList<String> commands() {
        ArrayList<String> commands = new ArrayList<>();
        commands.add(VIEW_PEOPLE);
        commands.add(VIEW_LOCATIONS);
        commands.add("TODO: Save Game");
        commands.add(EXIT);
        return commands;
    }

    @Override
    public List<String> getHelpText() {
        return new ArrayList<>(); // TODO
    }

    @Override
    public List<String> getMessageDisplayedByThisCommand() {
        return Arrays.asList("Starting a new game.");
    }
}
