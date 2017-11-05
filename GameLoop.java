import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.MessageFormat;
import java.util.*;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Mark Chimes on 2017/11/02.
 */
public class GameLoop {
    private final BasicGui gui;
    private CommandKeyListener keyListener;
    private final static Logger LOGGER = Logger.getLogger(GameLoop.class.getName());

    private CommandHandler handler;

    public GameLoop(BasicGui gui) {
        this.gui = gui;
    }

    public void setup() {
        EventQueue.invokeLater(() -> {
            gui.setVisible(true);
        });
        this.keyListener = new CommandKeyListener();
        gui.addListener(keyListener);
        setCurrentCommandHandler(new WelcomeMessenger());
    }

    private class CommandKeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            performActionFor(e.getKeyCode());
        }
    }

    private void performActionFor(int keyCode) {
        LOGGER.info("Performing action for: " + KeyEvent.getKeyText(keyCode));
        handler.performActionFor(keyCode);
        gui.setText(handler.currentText());
    }

    private void setCurrentCommandHandler(CommandHandler handler) {
        LOGGER.info("Setting current command handler to: " + handler.getClass());
        this.handler = handler;
        gui.setText(handler.currentText());
    }

    private abstract class Messenger implements CommandHandler {
        private TextIterator iterator;

        protected void setIteratorMessages(List<String> messages) {
            iterator = new TextIterator(messages);
        }

        @Override
        public void performActionFor(int keyCode) {
            iterator.performActionFor(keyCode);
        }

        @Override
        public String currentText() {
            return iterator.currentText();
        }

        @Override
        public int currentIndex() {
            return 0;
        }
    }

    private class WelcomeMessenger extends Messenger {
        public WelcomeMessenger() {
            super();
            setIteratorMessages(welcomeMessage());
        }

        @Override
        public void performActionFor(int keyCode) {
            super.performActionFor(keyCode);
            if (keyCode == KeyConstants.SKIP_TEXT) {
                setCurrentCommandHandler(new MenuCommandHandler());
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
            welcomeMessage.add(MessageFormat.format("End of text. Press {0} to continue.", selectItem));
            return welcomeMessage;
        }
    }

    private abstract class ItemHandler implements CommandHandler {
        private ItemIterator iterator;

        @Override
        public void performActionFor(int keyCode) {
            iterator.performActionFor(keyCode);
        }

        protected void setIteratorMessages(List<String> messages) {
            iterator = new ItemIterator(messages);
        }

        @Override
        public String currentText() {
            return iterator.currentText();
        }

        @Override
        public int currentIndex() {
            return iterator.currentIndex();
        }
    }

    private class MenuCommandHandler extends ItemHandler {
        public MenuCommandHandler() {
            setIteratorMessages(initialCommands());
        }

        @Override
        public void performActionFor(int keyCode) {
            super.performActionFor(keyCode);
            if (keyCode == KeyConstants.CONFIRM) {
                switch (currentText()) {
                    case "Instructions":
                        setCurrentCommandHandler(new WelcomeMessenger());
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

}