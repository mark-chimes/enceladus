package commandAndMessage.command

import com.sun.javafx.UnmodifiableArrayList
import java.util.Optional

/**
 * Created by Mark Chimes on 2017/11/05.
 */
abstract class CommandHandler : CommandOrMessageIterator {
    lateinit var iterator: CommandIterator

    override fun currentText(): String {
        return iterator.currentText()
    }

    override fun currentIndex(): Int {
        return iterator.currentIndex()
    }

    protected fun setTextOrItemIterator(iterator: CommandIterator) {
        this.iterator = iterator
    }

    fun nextCommand(): Optional<CommandHandler> {
        val handler = currentCommand().nextCommand

        return if (handler is NullCommandHandler) {
            Optional.empty()
        } else {
            Optional.of(handler)
        }
    }

    val nextMessage: List<String>
        get() = currentCommand().nextMessage

    open val helpText: List<String>
        get() = currentCommand().helpText

    protected fun setIteratorCommands(commands: List<CommandTuple>) {
        setTextOrItemIterator(CommandIterator(commands))
    }

    fun currentCommand(): CommandTuple {
        return iterator.currentCommand()
    }

    override fun performPreviousInListCommand() {
        iterator.performPreviousInListCommand()
    }

    override fun performNextInListCommand() {
        iterator.performNextInListCommand()
    }

    val isClearingCommandStack: Boolean
        get() = currentCommand().shouldClearCommandStack

    fun performConfirmCommand() {
        currentCommand().executable?.run()
    }
}