package commandAndMessage.command

import commandAndMessage.command.CommandHandler
import commandAndMessage.main.KeyConstants

/**
 * Created by Mark Chimes on 2017/11/09.
 */
class NullCommandHandler : CommandHandler() {
    override val helpText: List<String>
        get() = KeyConstants.EMPTY_LIST
}
