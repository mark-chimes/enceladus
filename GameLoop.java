import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.MessageFormat;
import java.util.*;

/**
 * Created by Mark Chimes on 2017/11/02.
 */
public class GameLoop {
    private final BasicGui gui;
    private TextOrItemIterator textIterator;
    
    public GameLoop(BasicGui gui) {
        this.gui = gui;
    }

    public void setup() {
        EventQueue.invokeLater(() -> {
            gui.setVisible(true);
        });
        textIterator = setToWelcome(gui);
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

    private TextOrItemIterator setToWelcome(BasicGui gui) {
        ArrayList<String> texts = welcomeMessage();
        TextIterator textIterator = new TextIterator(gui, texts);
        gui.setText(texts.get(0));
        gui.addListener(textIterator);
        gui.addListener(new SkipTextListener());
        return textIterator;
    }

    private TextOrItemIterator setToMenuCommands(BasicGui gui) {
        ArrayList<String> items = initialCommands();
        gui.setText(items.get(0));
        ItemIterator itemIterator = new ItemIterator(gui, items);
        gui.addListener(itemIterator);
        gui.addListener(new ConfirmListener());
        return itemIterator;
    }

    private TextOrItemIterator setToExit(BasicGui gui) {
        ArrayList<String> texts = thanksForPlaying();
        TextIterator textIterator = new TextIterator(gui, texts);
        gui.setText(texts.get(0));
        gui.addListener(textIterator);
        gui.addListener(new ExitOnAnything());
        return textIterator;
    }

    private class SkipTextListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyConstants.SKIP_TEXT:
                    gui.removeListener(textIterator);
                    gui.removeListener(this);
                    textIterator = setToMenuCommands(gui);
                    break;
            }
        }
    }

    private class ExitOnAnything extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            System.exit(0);
        }
    }

    private class ConfirmListener extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyConstants.CONFIRM:
                int selectedIndex = textIterator.currentIndex();
                String selectedText = textIterator.currentText();
                if (selectedText.equals("Instructions")) { // TODO
                    gui.removeListener(textIterator);
                    gui.removeListener(this);
                    textIterator = setToWelcome(gui);
                } else if (selectedText.equals("Exit")) { // TODO
                    gui.removeListener(textIterator);
                    gui.removeListener(this);
                    textIterator = setToExit(gui);
                } else {
                    System.out.println("Unknown selection");
                }
                break;
            }
        }
    }
}