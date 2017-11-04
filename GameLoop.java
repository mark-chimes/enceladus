import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by Mark Chimes on 2017/11/02.
 */
public class GameLoop {
    private final BasicGui gui;
    private TextIterator textIterator;
    Optional<ActionListener> outOfBoundsListener = Optional.empty();

    public GameLoop(BasicGui gui) {
        this.gui = gui;
        textIterator = new TextIterator(gui, setupCommands());
        gui.addListener(textIterator);
    }

    public void setup() {
        EventQueue.invokeLater(() -> {
            gui.setVisible(true);
            gui.setText("Welcome to Enceladus!");
        });
    }

    private final ArrayList<String> setupCommands() {
        ArrayList<String> initialCommands = new ArrayList<>();

        initialCommands.add("Random command");
        initialCommands.add("This is a super long piece of text in order to try to see what the command box does with it");
        initialCommands.add("Exit");

        return initialCommands;
    }

    private void changeGuiTextList(java.util.List<String> texts, int index) {
        gui.removeKeyListener(textIterator);
        textIterator = new TextIterator(gui, texts, index);
        gui.addListener(textIterator);
    }
}
