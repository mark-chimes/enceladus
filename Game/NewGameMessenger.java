package Game;

import Core.CommandOrTextHandler;
import Core.KeyConstants;
import Core.KeyPressHandler;
import Core.TextHandler;

import java.awt.event.KeyEvent;
import java.text.MessageFormat;
import java.util.ArrayList;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public class NewGameMessenger extends TextHandler {
    public NewGameMessenger() {
        setIteratorMessages(welcomeMessage());
    }

    @Override
    public void performKeyPress(int keyCode) {
        super.performKeyPress(keyCode);
        if (keyCode == KeyConstants.SKIP_TEXT) {
            setNextCommand(new MainGameHandler());
        }
    }

    @Override
    public CommandOrTextHandler newHandlerFrom() {
        return new NewGameMessenger();
    }

    public ArrayList<String> welcomeMessage() {
        ArrayList<String> welcomeMessage = new ArrayList<>();
        String skip = KeyEvent.getKeyText(KeyConstants.SKIP_TEXT);
        welcomeMessage.add(MessageFormat.format("Starting a new game. Press {0} to begin.",skip));
        return welcomeMessage;
    }

}
