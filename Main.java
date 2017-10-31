import java.awt.*;

/**
 * Created by Mark Chimes on 2017/10/31.
 */
public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            BasicGui gui = new BasicGui();
            gui.setVisible(true);
        });
    }
}
