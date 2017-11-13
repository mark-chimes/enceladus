package commandAndMessage.command

import commandAndMessage.command.CommandHandler

/**
 * Created by Mark Chimes on 2017/11/09.
 */
open class CommandTuple {
    val actionString: String
    val nextMessage: List<String>
    val helpText: List<String>
    val nextCommand: CommandHandler
    val shouldClearCommandStack: Boolean
    val executable: Runnable?

    constructor(actionString: String,
                nextMessage: List<String>,
                helpText: List<String>,
                nextCommand: CommandHandler,
                shouldClearCommandStack: Boolean) {
        this.actionString = actionString
        this.nextMessage = nextMessage
        this.helpText = helpText
        this.nextCommand = nextCommand
        this.shouldClearCommandStack = shouldClearCommandStack
        executable = null
    }

    constructor(actionString: String,
                nextMessage: List<String>,
                helpText: List<String>,
                nextCommand: CommandHandler,
                shouldClearCommandStack: Boolean,
                executable: Runnable) {
        this.actionString = actionString
        this.nextMessage = nextMessage
        this.helpText = helpText
        this.nextCommand = nextCommand
        this.shouldClearCommandStack = shouldClearCommandStack
        this.executable = executable
    }
}