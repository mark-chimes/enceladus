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
    private TextIterator textIterator;
    private SkipTextListener skipListener;

    public GameLoop(BasicGui gui) {
        this.gui = gui;
    }

    public void setup() {
        EventQueue.invokeLater(() -> {
            gui.setVisible(true);
        });
        skipListener = new SkipTextListener();
        ArrayList<String> texts = welcomeMessage();
        textIterator = new TextIterator(gui, texts);
        gui.setText(texts.get(0));
        gui.addListener(textIterator);
        gui.addListener(skipListener);
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

    public void changeGuiTextList(java.util.List<String> texts) {

        addGuiTextList(texts);
    }

    private void addGuiTextList(java.util.List<String> texts) {
        textIterator = new TextIterator(gui, texts);
        gui.setText(texts.get(0));
        gui.addListener(textIterator);
    }

    private class SkipTextListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyConstants.SKIP_TEXT:
                    System.out.println("Got skip code");
                    gui.removeKeyListener(textIterator);
                    ArrayList<String> items = initialCommands();
                    ItemIterator optionsIterator = new ItemIterator(gui, items);
                    gui.addListener(optionsIterator);
                    gui.setText(items.get(0));
                    break;
            }
        }
    }
}