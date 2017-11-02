import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark Chimes on 2017/10/31.
 */
public class Main {
    private static final ArrayList<String> initialCommands = new ArrayList<>();

    public static void main(String[] args) {
        BasicGui gui = new BasicGui();
        final List<Integer> commandsIt = new ArrayList<>();
        commandsIt.add(0);

        setupCommands();


        KeyAdapter listener = new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (commandsIt.get(0) < initialCommands.size() - 1) {
                    commandsIt.set(0, commandsIt.get(0) + 1 );
                } else {
                    commandsIt.set(0, 0);
                }
                gui.setText(initialCommands.get(commandsIt.get(0)));
            }
        };

        EventQueue.invokeLater(() -> {
            gui.setVisible(true);
            gui.setText("Welcome to Enceladus!");
        });



        gui.addListener(listener);


    }

    private static final void setupCommands() {
        initialCommands.add("Random command");
        initialCommands.add("Exit");
        initialCommands.add("This is a super long piece of text in order to try to see what the command box does with it");
    }

}
