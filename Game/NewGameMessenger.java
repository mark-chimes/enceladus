package Game;

import Core.KeyConstants;
import Core.MessageHandler;
import MainMenu.MenuCommandHandler;

import java.awt.event.KeyEvent;
import java.text.MessageFormat;
import java.util.ArrayList;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public class NewGameMessenger extends MessageHandler {
    public NewGameMessenger() {
        setIteratorMessages(welcomeMessage());
    }

    @Override
    public void performActionFor(int keyCode) {
        super.performActionFor(keyCode);
        if (keyCode == KeyConstants.SKIP_TEXT) {
            setNextCommand(new MainGameHandler());
        }
    }

    public ArrayList<String> welcomeMessage() {
        ArrayList<String> welcomeMessage = new ArrayList<>();
        welcomeMessage.add("Starting a new game.");
        return welcomeMessage;
    }

}
