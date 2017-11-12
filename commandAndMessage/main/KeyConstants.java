package commandAndMessage.main;

import com.sun.javafx.UnmodifiableArrayList;

import java.awt.event.KeyEvent;
import java.util.List;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public class KeyConstants {
    public static final int PREVIOUS_TEXT = KeyEvent.VK_UP;
    public static final int NEXT_TEXT = KeyEvent.VK_DOWN;
    public static final int PREVIOUS_ITEM = KeyEvent.VK_UP;
    public static final int NEXT_ITEM = KeyEvent.VK_DOWN;

    public static final int PREVIOUS_MENU = KeyEvent.VK_BACK_SPACE;
    public static final int CONFIRM = KeyEvent.VK_ENTER;

    public static final int SKIP_TEXT = KeyEvent.VK_ESCAPE;

    public static final int HELP = KeyEvent.VK_F1;

    public static final int SWITCH_TEXT_COMMAND = KeyEvent.VK_BACK_SLASH;

    public static final List<String> EMPTY_LIST = new UnmodifiableArrayList(new String[0], 0);

}
