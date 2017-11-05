package Game;

import Core.CommandHandler;
import Core.ItemHandler;
import Core.KeyConstants;
import MainMenu.WelcomeMessenger;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public class MainGameHandler extends ItemHandler {
    Optional<CommandHandler> nextCommand = Optional.empty();

    public MainGameHandler() {
        setIteratorMessages(commands());
    }

    @Override
    public void performActionFor(int keyCode) {
        super.performActionFor(keyCode);
        if (keyCode == KeyConstants.CONFIRM) {
            switch (currentText()) {
                case "View People":
                    break;
                case "View Locations":
                    setNextCommand(new WelcomeMessenger());
                    break;
                case "Exit":
                    System.exit(0);
            }
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
