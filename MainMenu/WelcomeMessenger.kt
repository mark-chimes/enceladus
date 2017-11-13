package MainMenu

import commandAndMessage.main.KeyConstants

import java.awt.event.KeyEvent
import java.text.MessageFormat
import java.util.ArrayList

/**
 * Created by Mark Chimes on 2017/11/05.
 */
object WelcomeMessenger {
    fun welcomeMessage(): ArrayList<String> {
        val welcomeMessage = ArrayList<String>()
        val previousText = KeyEvent.getKeyText(KeyConstants.PREVIOUS_TEXT)
        val nextText = KeyEvent.getKeyText(KeyConstants.NEXT_TEXT)
        val skipText = KeyEvent.getKeyText(KeyConstants.SKIP_TEXT)

        val previousItem = KeyEvent.getKeyText(KeyConstants.PREVIOUS_ITEM)
        val nextItem = KeyEvent.getKeyText(KeyConstants.NEXT_ITEM)
        val selectItem = KeyEvent.getKeyText(KeyConstants.CONFIRM)

        val previousMenu = KeyEvent.getKeyText(KeyConstants.PREVIOUS_MENU)
        val switchBetween = KeyEvent.getKeyText(KeyConstants.SWITCH_TEXT_COMMAND)
        val help = KeyEvent.getKeyText(KeyConstants.HELP)

        welcomeMessage.add(MessageFormat.format("Welcome to Enceladus! Press {0} and {1} to read, " + "and {2} to skip.", previousText, nextText, skipText))

        welcomeMessage.add(MessageFormat.format("Whilst in a commands menu, press {0} and {1} to change options, " + "and {2} to select.", previousItem, nextItem, selectItem))
        welcomeMessage.add(MessageFormat.format("Whilst browsing commands, you can also press " + "{0} to go to the previous menu.", previousMenu))
        welcomeMessage.add(MessageFormat.format("In many cases, you can get more information about a selected " + "command by pressing {0}.", help))
        welcomeMessage.add(MessageFormat.format("To switch between the log of messages, and currently " + "available commands, press {0}.", switchBetween))
        welcomeMessage.add("If this is your first time playing, please read the instructions.")
        return welcomeMessage
    }
}
