package Game;

import Core.CommandOrTextHandler;
import Core.KeyConstants;
import Core.MessageLogIterator;
import Core.TextHandler;

import java.awt.event.KeyEvent;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public class NewGameMessenger extends MessageLogIterator {
    public NewGameMessenger() {
        super(new ArrayList<>());
        super.addTexts(welcomeMessage());
    }

    public ArrayList<String> welcomeMessage() {
        ArrayList<String> welcomeMessage = new ArrayList<>();
        String skip = KeyEvent.getKeyText(KeyConstants.SKIP_TEXT);
        welcomeMessage.add(MessageFormat.format("Starting a new game. Press {0} to begin.",skip));
        return welcomeMessage;
    }
}
