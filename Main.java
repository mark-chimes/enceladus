import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark Chimes on 2017/10/31.
 */
public class Main {
    private static final ArrayList<String> initialCommands = new ArrayList<>();

    public static void main(String[] args) {
        BasicGui gui = new BasicGui();

        setupTextArea(gui);
        setupCommands(gui);

        ActionListener listener = e -> {
            String command = e.getActionCommand().toLowerCase();
            switch(command) {
                case "exit":
                    List<String> newCommands = new ArrayList<>();
                    newCommands.add("No");
                    newCommands.add("Yes");
                    gui.addToDisplayArea("Are you sure you want to exit?");
                    gui.setCommands(newCommands);
                    break;
                case "yes":
                    System.exit(0);
                    break;
                case "no":
                    gui.addToDisplayArea("Not exiting.");
                    gui.setCommands(initialCommands);
                    gui.resetCommandIndex();
            }
        };

        EventQueue.invokeLater(() -> {
            gui.setVisible(true);
        });

        gui.addListener(listener);
    }


    private static final void setupTextArea(BasicGui gui) {
        gui.addToDisplayArea("Test line.");
    }

    private static final void setupCommands(BasicGui gui) {
        initialCommands.add("Random command");
        initialCommands.add("Exit");
        initialCommands.add("This is a super long piece of text in order to try to see what the command box does with it");
        gui.setCommands(initialCommands);
    }

}
