package commandAndMessage.message

import commandAndMessage.command.CommandOrMessageIterator

/**
 * Created by Mark Chimes on 2017/11/06.
 */
class MessageLogHandler(messageLogs: MutableList<String>) : CommandOrMessageIterator {
    internal var iterator: MessageLogIterator

    init {
        iterator = MessageLogIterator(messageLogs)
    }

    fun addTexts(texts: List<String>) {
        iterator.addTexts(texts)
    }

    fun setToLastIndex() {
        iterator.setToLastIndex()
    }

    override fun currentText(): String {
        return iterator.currentText()
    }

    override fun currentIndex(): Int {
        return iterator.currentIndex()
    }

    override fun performPreviousInListCommand() {
        iterator.performPreviousInListCommand()
    }

    override fun performNextInListCommand() {
        iterator.performNextInListCommand()
    }
}