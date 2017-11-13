package commandAndMessage.message

import commandAndMessage.main.KeyConstants
import commandAndMessage.command.CommandOrMessageIterator

import java.awt.event.KeyEvent
import java.text.MessageFormat

/**
 * Created by Mark Chimes on 2017/11/06.
 */
class MessageLogIterator(private val texts: MutableList<String>) : CommandOrMessageIterator {
    private var iteratorIndex: Int = 0

    fun addTexts(newTexts: List<String>) {
        texts.addAll(newTexts)
    }

    override fun currentText(): String {
        if (iteratorIndex < texts.size) {
            return texts[iteratorIndex]
        } else {
            val skip = KeyEvent.getKeyText(KeyConstants.SKIP_TEXT)
            val previous = KeyEvent.getKeyText(KeyConstants.PREVIOUS_MENU)
            val switchText = KeyEvent.getKeyText(KeyConstants.SWITCH_TEXT_COMMAND)
            return MessageFormat.format("End of text. Press {0}, {1}, or {2} to continue.",
                    skip, previous, switchText)

        }
    }

    fun setToLastIndex() {
        iteratorIndex = texts.size
    }

    override fun currentIndex(): Int {
        return iteratorIndex
    }

    override fun performPreviousInListCommand() {
        if (iteratorIndex > 0) {
            iteratorIndex--
        }
    }

    override fun performNextInListCommand() {
        if (iteratorIndex < texts.size) {
            iteratorIndex++
        }
    }
}
