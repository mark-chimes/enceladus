import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;

/**
 * Created by Mark Chimes on 2017/11/02.
 */
public class GameLoop {
    private final BasicGui gui;
    private TextIterator textIterator;
    private DownListener downListener;

    public GameLoop(BasicGui gui) {
        this.gui = gui;
    }

    public void setup() {
        EventQueue.invokeLater(() -> {
            gui.setVisible(true);
        });
        downListener = new DownListener();
        addGuiTextList(welcomeMessage(), false);
    }

    public ArrayList<String> welcomeMessage() {
        ArrayList<String> welcomeMessage = new ArrayList<>();
        welcomeMessage.add("Welcome to Enceladus! Press left and right to read, and down to skip to the menu.");
        welcomeMessage.add("If this is your first time playing, please read the instructions.");
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

    public void changeGuiTextList(java.util.List<String> texts, boolean isLooping) {
        gui.removeKeyListener(textIterator);
        addGuiTextList(texts, isLooping);
    }

    private void addGuiTextList(java.util.List<String> texts, boolean isLooping) {
        textIterator = new TextIterator(gui, texts, isLooping);
        gui.setText(texts.get(0));
        gui.addListener(textIterator);
        gui.addListener(downListener); // This is wrong - should be stored with the text iterator?
    }

    private class DownListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_DOWN:
                    changeGuiTextList(initialCommands(), true);
                    break;
            }
        }
    }
}
