package commandAndMessage.command

/**
 * Created by Mark Chimes on 2017/11/05.
 */
class CommandIterator @JvmOverloads constructor(private val items: List<CommandTuple>, private var iteratorIndex: Int = 0) : CommandOrMessageIterator {

    override fun currentText(): String {
        return currentCommand().actionString
    }

    fun currentCommand(): CommandTuple {
        return items[iteratorIndex]
    }

    override fun currentIndex(): Int {
        return iteratorIndex
    }

    override fun performPreviousInListCommand() {
        if (iteratorIndex > 0) {
            iteratorIndex--
        } else {
            iteratorIndex = items.size - 1
        }
    }

    override fun performNextInListCommand() {
        if (iteratorIndex < items.size - 1) {
            iteratorIndex++
        } else {
            iteratorIndex = 0
        }
    }
}