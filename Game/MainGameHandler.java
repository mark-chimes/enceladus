package Game;

import Core.CommandOrTextHandler;
import Core.CommandHandler;
import Locations.LocationHandler;

import java.util.ArrayList;


/**
 * Created by Mark Chimes on 2017/11/05.
 */
public class MainGameHandler extends CommandHandler {
    public MainGameHandler() {
        setIteratorMessages(commands());
    }

    @Override
    public void performActionFor(String actionString) {
        switch (actionString) {
            case "View People":
                break;
            case "View Locations":
                setNextCommand(new LocationHandler());
                break;
            case "Exit":
                System.exit(0);
                break;
        }
    }

    private final ArrayList<String> commands() {
        ArrayList<String> commands = new ArrayList<>();
        commands.add("View People");
        commands.add("View Locations");
        commands.add("TODO: Save Game");
        commands.add("Exit");
        return commands;
    }

    @Override
    public CommandOrTextHandler newHandlerFrom() {
        return new MainGameHandler();
    }
}
