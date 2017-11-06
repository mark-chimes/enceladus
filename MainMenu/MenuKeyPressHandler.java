package MainMenu;

import Core.CommandOrTextHandler;
import Core.KeyPressHandler;
import Core.CommandHandler;
import Game.MainGameHandler;
import Game.NewGameMessenger;

import java.util.ArrayList;
import java.util.List;
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
                setNextMessage(newGameMessage());
                setNextCommand(new MainGameHandler()); // TODO
                break;
                // TODO Figure out how to do this...
//            case "Instructions":
//                setNextCommand(new WelcomeMessenger());
//                break;
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

    @Override
    public List<String> getNextMessage() {
        return nextMessage;
    }

    private List<String> newGameMessage() {
        ArrayList<String> newGameMessage = new ArrayList<>();
        newGameMessage.add("Starting a new game.");
        return newGameMessage;
    }

    @Override
    public Optional<List<String>> getHelpText() {
        List<String> helpText = new ArrayList<>();
        switch(currentText()) {
            case "New Game" :
                helpText.add("Starts a game completely from the beginning.");
                break;
            case "Load Game" :
                helpText.add("Continues a game that was previously played.");
                break;
            case "Instructions" :
                helpText.add("Provides basic instructions for play.");
                break;
            case "Options" :
                helpText.add("Set game-play options such as controls.");
                break;
            case "Exit" :
                helpText.add("Quits the game completely.");
                break;
        }
        return Optional.of(helpText);
    }
}
