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

    private CommandHandler handler;
    private MessageLogHandler messageLogHandler;

    private boolean isInCommandState = false;
    private boolean isGameRunning;

    private Stack<CommandHandler> previousCommands = new Stack<>();

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
        setToMessageState();

        handler = new MenuKeyPressHandler();
        previousCommands.push(handler);
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
        LOGGER.info("Performing action for: " + KeyEvent.getKeyText(keyCode));
        if (keyCode == KeyConstants.SWITCH_TEXT_COMMAND) {
            LOGGER.info("Case switchText");
            switchText();
        } else if (keyCode == KeyConstants.SKIP_TEXT)  {
            LOGGER.info("Case skipText");
            skipText();
        }else if (keyCode == KeyConstants.PREVIOUS_MENU) {
            LOGGER.info("Case goToPreviousMenu");
            goToPreviousMenu();
        } else if (keyCode == KeyConstants.HELP) {
            LOGGER.info("Case displayHelpForCurrentCommand");
            displayHelpForCurrentCommand();
        } else {
            LOGGER.info("Case performMiscKeyPress");
            performMiscKeyPress(keyCode);
        }

        if (isInCommandState) {
            gui.setText(handler.currentText());
        } else {
            gui.setText(messageLogHandler.currentText());
        }
    }

    private void switchText() {
        if (isInCommandState) {
            LOGGER.info("In command state.");
            setToMessageState();
        } else {
            LOGGER.info("Is not in command state.");
            setToCommandState();
        }
    }

    private void skipText() {
        if (!isInCommandState) {
            LOGGER.info("Is not in command state.");
            setToCommandState();
        }
    }

    private void goToPreviousMenu() {
        if (isInCommandState) {
            if (!previousCommands.isEmpty()) {
                setCurrentCommandHandler(previousCommands.pop());
                LOGGER.info("Previous handler present. Switching to: " + handler.getClass());
            } else {
                LOGGER.info("Command stack empty.");
            }
        } else {
            LOGGER.info("Not in command state, so setting to command state.");
            setToCommandState();
        }

    }

    private void displayHelpForCurrentCommand() {
        if (isInCommandState) {
            LOGGER.info("In command state.");
            List<String> helpText = handler.getHelpText();
            if (!helpText.isEmpty()) {
                LOGGER.info("Pushing " + handler.getClass() + " to stack.");
                previousCommands.push(handler.newHandlerFrom());
                LOGGER.info("Switching to help text for: " + handler.getClass());
                addTextsAndDisplay(helpText);
            }
        } else {
            LOGGER.info("NOT In command state so not displaying help.");
        }
    }

    private void performMiscKeyPress(int keyCode) {
        LOGGER.info("Performing misc key press for: " + KeyEvent.getKeyText(keyCode));
        if (isInCommandState) {
            LOGGER.info("In command state");
            handler.performKeyPress(keyCode);
            List<String> nextMessage = handler.getNextMessage();
            Optional<CommandHandler> nextCommandOpt = handler.nextCommand();
            CommandHandler commandHandler = handler;
            if (nextCommandOpt.isPresent()) {
                CommandHandler nextCommand = nextCommandOpt.get();
                LOGGER.info("Handler has next command: " + nextCommand.getClass());
                LOGGER.info("Pushing " + handler.getClass() + " to stack.");
                previousCommands.push(commandHandler.newHandlerFrom());
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
        } else {
            LOGGER.info("Not in command state");
            messageLogHandler.performKeyPress(keyCode);
        }
    }

    private void addTextsAndDisplay(List<String> newTexts) {
        messageLogHandler.setToLastIndex();
        messageLogHandler.addTexts(newTexts);
        setToMessageState();
    }

    private void setToMessageState() {
        LOGGER.info("Setting to message state.");
        isInCommandState = false;
        gui.setText(messageLogHandler.currentText());
    }

    private void setToCommandState() {
        LOGGER.info("Setting to command state.");
        isInCommandState = true;
        gui.setText(handler.currentText());
    }

    private void setCommandHandlerToLastCommand() {
        if (!previousCommands.isEmpty()) {
            setToCommandState();
            this.handler = previousCommands.pop();
            LOGGER.info("Setting current command handler to: " + handler.getClass());
            gui.setText(handler.currentText());
        } else {
            LOGGER.warning("Command stack empty.");
        }
    }

    private void setCurrentCommandHandler(CommandHandler handler) {
        LOGGER.info("Setting current command handler to: " + handler.getClass());
        setToCommandState();
        this.handler = handler;
        gui.setText(handler.currentText());
    }
}