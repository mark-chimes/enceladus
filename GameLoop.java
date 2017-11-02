import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Mark Chimes on 2017/11/02.
 */
public class GameLoop {
    private final BasicGui gui;
    private final CommandDisplay commandDisplay;

    public GameLoop(BasicGui gui) {
        this.gui = gui;
        this.commandDisplay = new CommandDisplay(gui);
        commandDisplay.newTextsList(setupCommands());
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
}
