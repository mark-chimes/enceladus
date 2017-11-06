package MainMenu;

import Core.KeyPressHandler;
import Core.CommandHandler;
import Game.MainGameHandler;
import Game.NewGameMessenger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public class MenuKeyPressHandler extends CommandHandler {
    private final static Logger LOGGER = Logger.getLogger(CommandHandler.class.getName());

    public MenuKeyPressHandler() {
        setIteratorMessages(initialCommands());
    }

    @Override
    public void performActionFor(String actionString) {
        LOGGER.info("Performing action for: " + actionString);
        switch (actionString) {
            case "New Game" :
                setNextMessage(newGameMessage());
                setNextCommand(new MainGameHandler()); // TODO
                break;
                // TODO Figure out how to do this...
            case "Instructions":
                List<String> nextMessage = new ArrayList<>();
                nextMessage.add("Instructions forthcoming."); // TODO
                this.setNextMessage(nextMessage);
                this.clearNextCommand();
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
    public CommandHandler newHandlerFrom() {
        return new MenuKeyPressHandler();
    }

    @Override
    public List<String> getNextMessage() {
        return nextMessage;
    }

    private List<String> newGameMessage() {
        ArrayList<String> newGameMessage = new ArrayList<>();
        newGameMessage.addAll(NewGameMessenger.message());
        return newGameMessage;
    }

    @Override
    public List<String> getHelpText() {
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
        return helpText;
    }
}
