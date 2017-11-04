import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by Mark Chimes on 2017/11/02.
 */
public class GameLoop {
    private final BasicGui gui;
    private TextIterator textIterator;

    public GameLoop(BasicGui gui) {
        this.gui = gui;
    }

    public void setup() {
        EventQueue.invokeLater(() -> {
            gui.setVisible(true);
        });
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
    }
}
