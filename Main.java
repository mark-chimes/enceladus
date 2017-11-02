/**
 * Created by Mark Chimes on 2017/10/31.
 */
public class Main {

    public static void main(String[] args) {
        BasicGui gui = new BasicGui();
        GameLoop handler = new GameLoop(gui);
        handler.setup();
    }
}
