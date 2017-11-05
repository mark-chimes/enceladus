package MainMenu;

import Core.CommandOrTextHandler;
import Core.KeyPressHandler;
import Core.CommandHandler;
import Core.KeyConstants;
import Game.NewGameMessenger;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public class MenuKeyPressHandler extends CommandHandler {
    Optional<KeyPressHandler> nextCommand = Optional.empty();

    public MenuKeyPressHandler() {
        setIteratorMessages(initialCommands());
    }

    @Override
    public void performActionFor(String actionString) {
        switch (actionString) {
            case "New Game" :
                setNextCommand(new NewGameMessenger());
                break;
            case "Instructions":
                setNextCommand(new WelcomeMessenger());
                break;
            case "Exit":
                System.exit(0);
        }
    }

    private final ArrayList<String> initialCommands() {
        ArrayList<String> initialCommands = new ArrayList<>();
        initialCommands.add("New Game");
        initialCommands.add("Load Game");
        initialCommands.add("Instructions");
        initialCommands.add("Options");
        initialCommands.add("Exit");
        return initialCommands;
    }

    @Override
    public CommandOrTextHandler newHandlerFrom() {
        return new MenuKeyPressHandler();
    }
}
