package Core;

import MainMenu.MenuKeyPressHandler;
import MainMenu.WelcomeMessenger;

import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Logger;

/**
 * Created by Mark Chimes on 2017/11/02.
 */
public class GameLoop {
    private final BasicGui gui;
    private CommandKeyListener keyListener;
    private final static Logger LOGGER = Logger.getLogger(GameLoop.class.getName());

    private CommandOrMessageHandler handler;
    private boolean isInCommandState;
    private boolean isGameRunning;
    private MessageLogHandler messageLogHandler;

    private Stack<CommandOrMessageHandler> previousCommands = new Stack<>();

    private LinkedBlockingDeque<Integer> keyPresses = new LinkedBlockingDeque<>();

    public GameLoop(BasicGui gui) {
        this.gui = gui;
    }

    public void setup() {
        EventQueue.invokeLater(() -> {
            gui.setVisible(true);
        });
        this.keyListener = new CommandKeyListener();
        gui.addListener(keyListener);
        messageLogHandler = new MessageLogHandler(WelcomeMessenger.welcomeMessage());
        setCommandHandlerToMessageLog();
        previousCommands.push(new MenuKeyPressHandler());
        isGameRunning = true;
        runMainLoop();
    }

    private void runMainLoop() {
        while (isGameRunning) {
            try {
                int nextKeyPress = keyPresses.take();
                performActionFor(nextKeyPress);
            } catch (InterruptedException e) {
                LOGGER.severe(e::getMessage);
                isGameRunning = false;
            }
        }
    }

    private class CommandKeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            LOGGER.info("Got key press " + KeyEvent.getKeyText(e.getKeyCode()));
            addActionToQueue(e.getKeyCode());
        }
    }

    private void addActionToQueue(int keyCode) {
        keyPresses.offer(keyCode);
    }

    private void performActionFor(int keyCode) {
        if (keyCode == KeyConstants.SWITCH_TEXT_COMMAND) {
            switchText();
        } else if (keyCode == KeyConstants.SKIP_TEXT && !isInCommandState)  {
            skipText();
        }else if (keyCode == KeyConstants.PREVIOUS_MENU) {
            goToPreviousMenu();
        } else if (keyCode == KeyConstants.HELP) {
            displayHelpForCurrentCommand();
        } else {
            performMiscKeyPress(keyCode);
        }
        gui.setText(handler.currentText());
    }

    private void switchText() {
        if (isInCommandState) {
            LOGGER.info("In command state. Pushing handler: " + handler.getClass());
            previousCommands.push(handler);
            setCommandHandlerToMessageLog();
        } else {
            LOGGER.info("Is not in command state.");
            setCommandHandlerToLastCommand();
        }
    }

    private void skipText() {
        LOGGER.info("Is not in command state.");
        setCommandHandlerToLastCommand();
    }

    private void goToPreviousMenu() {
        if (!previousCommands.isEmpty()) {
            setCurrentCommandHandler(previousCommands.pop());
            LOGGER.info("Previous handler present. Switching to: " + handler.getClass());
        } else {
            LOGGER.info("Command stack empty.");
        }
    }

    private void displayHelpForCurrentCommand() {
        Optional<List<String>> helpText = handler.getHelpText();
        if (helpText.isPresent()) {
            LOGGER.info("Pushing " + handler.getClass() + " to stack.");
            previousCommands.push(((CommandHandler) handler).newHandlerFrom());
            LOGGER.info("Switching to help text for: " + handler.getClass());
            addTextsAndDisplay(helpText.get());
        }
    }

    private void performMiscKeyPress(int keyCode) {
        handler.performKeyPress(keyCode);
        List<String> nextMessage = handler.getNextMessage();
        Optional<CommandOrMessageHandler> nextCommandOpt = handler.nextCommand();

        if (nextCommandOpt.isPresent()) {
            CommandOrMessageHandler nextCommand = nextCommandOpt.get();
            LOGGER.info("Handler has next command: " + nextCommand.getClass());
            LOGGER.info("Pushing " + handler.getClass() + " to stack.");
            previousCommands.push(((CommandHandler) handler).newHandlerFrom());
            LOGGER.info("Setting to " + nextCommand.getClass());
            setCurrentCommandHandler(handler.nextCommand().get());
        } else {
            LOGGER.info("Handler has NO next command.");
        }
        if (nextMessage.isEmpty()) {
            LOGGER.info("Handler has no next message.");
        } else {
            LOGGER.info("Handler has next message so pushing " + handler.getClass() + " to queue.");
            previousCommands.push(((CommandHandler) handler).newHandlerFrom());
            LOGGER.info("Handler has next message so adding message to queue.");
            addTextsAndDisplay(nextMessage);
        }
    }

    private void addTextsAndDisplay(List<String> newTexts) {
        messageLogHandler.setToLastIndex();
        messageLogHandler.addTexts(newTexts);
        setCommandHandlerToMessageLog();
    }

    private void setCommandHandlerToMessageLog() {
        LOGGER.info("Setting current command handler message log handler.");
        isInCommandState = false;
        this.handler = messageLogHandler;
        gui.setText(handler.currentText());
    }

    private void setCommandHandlerToLastCommand() {
        if (!previousCommands.isEmpty()) {
            isInCommandState = true;
            this.handler = previousCommands.pop();
            LOGGER.info("Setting current command handler to: " + handler.getClass());
            gui.setText(handler.currentText());
        } else {
            LOGGER.warning("Command stack empty.");
        }
    }

    private void setCurrentCommandHandler(CommandOrMessageHandler handler) {
        LOGGER.info("Setting current command handler to: " + handler.getClass());
        isInCommandState = true;
        this.handler = handler;
        gui.setText(handler.currentText());
    }
}