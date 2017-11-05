package MainMenu;

import Core.CommandHandler;
import Core.ItemHandler;
import Core.KeyConstants;
import MainMenu.WelcomeMessenger;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public class MenuCommandHandler extends ItemHandler {
    Optional<CommandHandler> nextCommand = Optional.empty();

    public MenuCommandHandler() {
        setIteratorMessages(initialCommands());
    }

    @Override
    public void performActionFor(int keyCode) {
        super.performActionFor(keyCode);
        if (keyCode == KeyConstants.CONFIRM) {
            switch (currentText()) {
                case "Instructions":
                    setNextCommand(new WelcomeMessenger());
                    break;
                case "Exit":
                    System.exit(0);
            }
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
}
