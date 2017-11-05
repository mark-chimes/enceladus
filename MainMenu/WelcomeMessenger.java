package MainMenu;

import Core.KeyConstants;
import Core.MessageHandler;

import java.awt.event.KeyEvent;
import java.text.MessageFormat;
import java.util.ArrayList;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public class WelcomeMessenger extends MessageHandler {
    public WelcomeMessenger() {
        setIteratorMessages(welcomeMessage());
    }

    @Override
    public void performActionFor(int keyCode) {
        super.performActionFor(keyCode);
        if (keyCode == KeyConstants.SKIP_TEXT) {
            setNextCommand(new MenuCommandHandler());
        }
    }

    public ArrayList<String> welcomeMessage() {
        ArrayList<String> welcomeMessage = new ArrayList<>();
        String previousText = KeyEvent.getKeyText(KeyConstants.PREVIOUS_TEXT);
        String nextText = KeyEvent.getKeyText(KeyConstants.NEXT_TEXT);
        String skipText = KeyEvent.getKeyText(KeyConstants.SKIP_TEXT);

        String previousItem = KeyEvent.getKeyText(KeyConstants.PREVIOUS_ITEM);
        String nextItem = KeyEvent.getKeyText(KeyConstants.NEXT_ITEM);
        String selectItem = KeyEvent.getKeyText(KeyConstants.CONFIRM);

        String previousMenu = KeyEvent.getKeyText(KeyConstants.PREVIOUS_MENU);

        welcomeMessage.add(MessageFormat.format("Welcome to Enceladus! Press {0} and {1} to read, " +
                "and {2} to skip.", previousText, nextText, skipText));
        welcomeMessage.add(MessageFormat.format("Whilst in a menu, press {0} and {1} to change options, " +
                "and {2} to select.", previousItem, nextItem, selectItem));
        welcomeMessage.add(MessageFormat.format("You can also press {0} to go to the previous menu.",
                previousMenu));
        welcomeMessage.add("If this is your first time playing, please read the instructions.");
        return welcomeMessage;
    }
}