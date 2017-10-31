import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Mark Chimes on 2017/10/31.
 */
public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            BasicGui gui = new BasicGui();

            setupCommands(gui);

            gui.setVisible(true);
        });
    }


    private static final void setupCommands(BasicGui gui) {
        ArrayList<String> initialCommands = new ArrayList<>();

        initialCommands.add("Hello");
        initialCommands.add("Hi");
        initialCommands.add("Howzit");
        initialCommands.add("This is a super long piece of text in order to try to see what the button does with it");
        gui.setCommands(initialCommands);
    }

}
