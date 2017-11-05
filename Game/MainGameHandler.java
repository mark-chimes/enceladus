package Game;

import Core.KeyPressHandler;
import Core.CommandHandler;
import Core.KeyConstants;
import Locations.LocationHandler;
import MainMenu.WelcomeMessenger;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public class MainGameHandler extends CommandHandler {
    Optional<KeyPressHandler> nextCommand = Optional.empty();

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
}
