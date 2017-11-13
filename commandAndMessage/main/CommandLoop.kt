package commandAndMessage.main

import MainMenu.MenuKeyPressHandler
import MainMenu.WelcomeMessenger
import commandAndMessage.command.CommandHandler
import commandAndMessage.message.MessageLogHandler

import java.awt.EventQueue
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import java.util.*
import java.util.concurrent.LinkedBlockingDeque
import java.util.function.Supplier
import java.util.logging.Logger

/**
 * Created by Mark Chimes on 2017/11/02.
 */
class CommandLoop(private val gui: BasicGui) {
    private var keyListener: CommandKeyListener? = null

    private var handler: CommandHandler? = null
    private var messageLogHandler: MessageLogHandler? = null

    private var isInCommandState = false
    private var isGameRunning: Boolean = false

    private val previousCommands = Stack<CommandHandler>()

    private val keyPresses = LinkedBlockingDeque<Int>()

    fun setup() {
        EventQueue.invokeLater { gui.isVisible = true }

        this.keyListener = CommandKeyListener()
        gui.addListener(keyListener!!)
        messageLogHandler = MessageLogHandler(WelcomeMessenger.welcomeMessage())
        setToMessageState()

        handler = MenuKeyPressHandler()
        LOGGER.info("To start, handler has next message? " + !handler!!.nextMessage.isEmpty())
        previousCommands.push(handler)
        isGameRunning = true
        outputTextToGui()
        runMainLoop()
    }

    private fun runMainLoop() {
        while (isGameRunning) {
            try {
                val nextKeyPress = keyPresses.take()
                performActionFor(nextKeyPress)
                outputTextToGui()
            } catch (e: InterruptedException) {
                LOGGER.severe({ e.message.toString() })
                isGameRunning = false
            }

        }
    }

    private inner class CommandKeyListener : KeyAdapter() {
        override fun keyPressed(e: KeyEvent?) {
            LOGGER.info("Got key press " + KeyEvent.getKeyText(e!!.keyCode))
            addActionToQueue(e.keyCode)
        }
    }

    private fun addActionToQueue(keyCode: Int) {
        keyPresses.offer(keyCode)
    }

    private fun performActionFor(keyCode: Int) {
        LOGGER.info("Performing CommandLoop action for: " + KeyEvent.getKeyText(keyCode))
        if (isInCommandState) {
            LOGGER.info("In command state.")
            performActionForCommand(keyCode)
        } else {
            LOGGER.info("Is not in command state.")
            performActionForCommandMessageLog(keyCode)
        }
    }

    private fun outputTextToGui() {
        if (isInCommandState) {
            LOGGER.info("In command state.")
            gui.setText(handler!!.currentText())
        } else {
            LOGGER.info("Is not in command state.")
            gui.setText(messageLogHandler!!.currentText())
        }
    }

    private fun performActionForCommand(keyCode: Int) {
        if (keyCode == KeyConstants.CONFIRM) {
            LOGGER.info("Case CONFIRM")
            handler!!.performConfirmCommand()

            val nextCommandOpt = handler!!.nextCommand()
            val nextMessage = handler!!.nextMessage
            val isClearingCommandStack = handler!!.isClearingCommandStack

            if (isClearingCommandStack) {
                previousCommands.clear()
            }

            if (nextCommandOpt.isPresent) {
                if (!isClearingCommandStack) {
                    previousCommands.push(handler)
                }
                setCurrentCommandHandler(nextCommandOpt.get())
                setToCommandState()
            }

            if (!nextMessage.isEmpty()) {
                addTexts(nextMessage)
            } else {
                LOGGER.info("Handler has no next message.")
            }
        } else if (keyCode == KeyConstants.PREVIOUS_ITEM) {
            LOGGER.info("performPreviousInListCommand")
            handler!!.performPreviousInListCommand()
        } else if (keyCode == KeyConstants.NEXT_ITEM) {
            LOGGER.info("performNextInListCommand")
            handler!!.performNextInListCommand()
        } else if (keyCode == KeyConstants.SWITCH_TEXT_COMMAND) {
            LOGGER.info("Case switchText")
            setToMessageState()
        } else if (keyCode == KeyConstants.PREVIOUS_MENU) {
            LOGGER.info("Case goToPreviousMenu")
            if (!previousCommands.isEmpty()) {
                setCurrentCommandHandler(previousCommands.pop())
                setToCommandState()
                LOGGER.info("Previous handler present. Switching to: " + handler!!.javaClass)
            } else {
                LOGGER.info("Command stack empty.")
            }
        } else if (keyCode == KeyConstants.HELP) {
            LOGGER.info("Case displayHelpForCurrentCommand")
            val helpText = handler!!.helpText
            if (!helpText.isEmpty()) {
                LOGGER.info("Switching to help text for: " + handler!!.javaClass)
                addTexts(helpText)
            }
        } else {
            LOGGER.info("Unknown command")
        }
    }

    private fun performActionForCommandMessageLog(keyCode: Int) {
        if (keyCode == KeyConstants.PREVIOUS_TEXT) {
            LOGGER.info("performPreviousInListCommand")
            messageLogHandler!!.performPreviousInListCommand()
        } else if (keyCode == KeyConstants.NEXT_TEXT) {
            LOGGER.info("performNextInListCommand")
            messageLogHandler!!.performNextInListCommand()
        } else if (keyCode == KeyConstants.SWITCH_TEXT_COMMAND || keyCode == KeyConstants.SKIP_TEXT
                || keyCode == KeyConstants.CONFIRM || keyCode == KeyConstants.HELP
                || keyCode == KeyConstants.PREVIOUS_MENU) {
            LOGGER.info("Case switchText or skipText(")
            setToCommandState()
        } else {
            LOGGER.info("Unknown command")
        }
    }

    private fun addTexts(newTexts: List<String>) {
        messageLogHandler!!.setToLastIndex()
        messageLogHandler!!.addTexts(newTexts)
        setToMessageState()
    }

    private fun setToMessageState() {
        LOGGER.info("Setting to message state.")
        isInCommandState = false
    }

    private fun setToCommandState() {
        LOGGER.info("Setting to command state.")
        isInCommandState = true
    }

    private fun setCurrentCommandHandler(handler: CommandHandler) {
        LOGGER.info("Setting current command handler to: " + handler.javaClass)
        this.handler = handler
    }

    companion object {
        private val LOGGER = Logger.getLogger(CommandLoop::class.java.name)
    }
}