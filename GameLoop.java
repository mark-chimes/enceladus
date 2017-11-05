import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.MessageFormat;
import java.util.*;

/**
 * Created by Mark Chimes on 2017/11/02.
 */
public class GameLoop {
    private final BasicGui gui;
    private ArrayList<CommandKeyListener> currentListeners;

    public GameLoop(BasicGui gui) {
        this.gui = gui;
    }

    public void setup() {
        EventQueue.invokeLater(() -> {
            gui.setVisible(true);
        });
        currentListeners = setToWelcome(gui);
    }

    public final void setCurrentListeners(ArrayList<CommandKeyListener> listeners) {
        gui.removeListeners(currentListeners);
        this.currentListeners = listeners;
        gui.addListeners(listeners);
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

    private final ArrayList<String> initialCommands() {
        ArrayList<String> initialCommands = new ArrayList<>();
        initialCommands.add("New Game");
        initialCommands.add("Load Game");
        initialCommands.add("Instructions");
        initialCommands.add("Options");
        initialCommands.add("Exit");
        return initialCommands;
    }

    private final ArrayList<String> thanksForPlaying() {
        ArrayList<String> thanksForPlaying = new ArrayList<>();
        thanksForPlaying.add("Thanks for playing!");
        return thanksForPlaying;
    }

    private ArrayList<CommandKeyListener> setToWelcome(BasicGui gui) {
        ArrayList<String> texts = welcomeMessage();
        gui.setText(texts.get(0));

        ArrayList<CommandKeyListener> commandKeys = new ArrayList<>();
        commandKeys.add(new TextIterator(gui, texts));
        commandKeys.add(new SkipTextListener());
        gui.addListeners(commandKeys);

        return commandKeys;
    }

    public ArrayList<CommandKeyListener> setToMenuCommands(BasicGui gui) {
        ArrayList<String> items = initialCommands();
        gui.setText(items.get(0));

        TextOrItemIterator itemIterator = new ItemIterator(gui, items);

        ArrayList<CommandKeyListener> commandKeys = new ArrayList<>();
        commandKeys.add(itemIterator);
        commandKeys.add(new ConfirmListener(itemIterator));

        return commandKeys;
    }

    private ArrayList<CommandKeyListener> setToExit(BasicGui gui) {
        ArrayList<String> texts = thanksForPlaying();
        gui.setText(texts.get(0));

        ArrayList<CommandKeyListener> commandKeys = new ArrayList<>();
        commandKeys.add(new TextIterator(gui, texts));
        commandKeys.add(new ExitOnAnything());
        gui.addListeners(commandKeys);

        return commandKeys;
    }

    private class SkipTextListener extends CommandKeyListener {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyConstants.SKIP_TEXT:
                    setCurrentListeners(setToMenuCommands(gui));
                    break;
            }
        }
    }

    private class ExitOnAnything extends CommandKeyListener {
        @Override
        public void keyPressed(KeyEvent e) {
            System.exit(0);
        }
    }

    private class ConfirmListener extends CommandKeyListener {
        private final TextOrItemIterator iterator;

        public ConfirmListener(TextOrItemIterator iterator) {
            this.iterator = iterator;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyConstants.CONFIRM:
                    int selectedIndex = iterator.currentIndex();
                    String selectedText = iterator.currentText();
                    if (selectedText.equals("Instructions")) { // TODO
                        setCurrentListeners(setToWelcome(gui));
                    } else if (selectedText.equals("Exit")) { // TODO
                        setCurrentListeners(setToExit(gui));
                    } else {
                        System.out.println("Unknown selection");
                    }
                    break;
            }
        }
    }
}