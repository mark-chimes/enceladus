import Commands.BasicGui;
import Commands.CommandLoop;

/**
 * Created by Mark Chimes on 2017/10/31.
 */
public class Main {
    public static void main(String[] args) {
        BasicGui gui = new BasicGui();
        CommandLoop handler = new CommandLoop(gui);
        handler.setup();
    }
}
