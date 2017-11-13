import commandAndMessage.main.BasicGui
import commandAndMessage.main.CommandLoop

/**
 * Created by Mark Chimes on 2017/10/31.
 */
object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        val gui = BasicGui()
        val handler = CommandLoop(gui)
        handler.setup()
    }
}
