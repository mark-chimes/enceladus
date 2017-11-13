package commandAndMessage.command

/**
 * Created by Mark Chimes on 2017/11/05.
 */
interface CommandOrMessageIterator {
    fun currentText(): String
    fun currentIndex(): Int
    fun performPreviousInListCommand()
    fun performNextInListCommand()
}
