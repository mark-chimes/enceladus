package Game;

import Core.KeyConstants;
import Core.TextHandler;

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

    public ArrayList<String> welcomeMessage() {
        ArrayList<String> welcomeMessage = new ArrayList<>();
        welcomeMessage.add("Starting a new game.");
        return welcomeMessage;
    }

}
