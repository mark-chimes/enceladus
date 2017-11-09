package MainMenu;

import Commands.CommandHandler;
import Game.MainGameHandler;
import Game.NewGameMessenger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public class MenuKeyPressHandler extends CommandHandler {
    private final static Logger LOGGER = Logger.getLogger(CommandHandler.class.getName());

    public MenuKeyPressHandler() {
        setIteratorMessages(initialCommands());
        setNextMessage(newGameMessage());
        setNextCommand(new MainGameHandler());
        shouldClearCommandStack();
    }

    @Override
    public void performConfirmCommand() {
        String actionString = currentText();
        switch (actionString) {
            case "New Game" :
                CommandHandler mainGameHandler = new MainGameHandler();
                setNextMessage(mainGameHandler.getMessageDisplayedByThisCommand());
                setNextCommand(mainGameHandler);
                LOGGER.info("shouldClearCommandStack");
                shouldClearCommandStack();
                break;
            case "Instructions":
                List<String> nextMessage = new ArrayList<>();
                nextMessage.add("Instructions forthcoming."); // TODO
                this.setNextMessage(nextMessage);
                this.clearNextCommand();
                LOGGER.info("shouldNotClearCommandStack");
                shouldNotClearCommandStack();
                break;
            case "Exit":
                System.exit(0);
        }
    }

    @Override
    public boolean isClearingCommandStack() {
        return true;
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
                CommandHandler mainGameHandler = new MainGameHandler();
                return mainGameHandler.getDefaultHelpText();
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

    @Override
    protected String getDefaultName() {
        throw new NotImplementedException();
    }

    @Override
    public List<String> getDefaultHelpText() {
        throw new NotImplementedException();
    }
}
