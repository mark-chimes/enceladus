package commandAndMessage.main

import com.sun.javafx.UnmodifiableArrayList

import java.awt.event.KeyEvent

/**
 * Created by Mark Chimes on 2017/11/05.
 */
object KeyConstants {
    val PREVIOUS_TEXT = KeyEvent.VK_UP
    val NEXT_TEXT = KeyEvent.VK_DOWN
    val PREVIOUS_ITEM = KeyEvent.VK_UP
    val NEXT_ITEM = KeyEvent.VK_DOWN

    val PREVIOUS_MENU = KeyEvent.VK_BACK_SPACE
    val CONFIRM = KeyEvent.VK_ENTER

    val SKIP_TEXT = KeyEvent.VK_ESCAPE

    val HELP = KeyEvent.VK_F1

    val SWITCH_TEXT_COMMAND = KeyEvent.VK_BACK_SLASH

    val EMPTY_LIST: List<String> = UnmodifiableArrayList(arrayOfNulls(0), 0)

}
