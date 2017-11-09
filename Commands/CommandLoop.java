package Commands;

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
public class CommandLoop {
    private final BasicGui gui;
    private CommandKeyListener keyListener;
    private final static Logger LOGGER = Logger.getLogger(CommandLoop.class.getName());

    private CommandHandler handler;
    private MessageLogHandler messageLogHandler;

    private boolean isInCommandState = false;
    private boolean isGameRunning;

    private final Stack<CommandHandler> previousCommands = new Stack<>();

    private LinkedBlockingDeque<Integer> keyPresses = new LinkedBlockingDeque<>();

    public CommandLoop(BasicGui gui) {
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
        LOGGER.info("To start, handler has next message? " + !handler.getNextMessage().isEmpty());
        previousCommands.push(handler);
        isGameRunning = true;
        outputTextToGui();
        runMainLoop();
    }

    private void runMainLoop() {
        while (isGameRunning) {
            try {
                int nextKeyPress = keyPresses.take();
                performActionFor(nextKeyPress);
                outputTextToGui();
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
        LOGGER.info("Performing CommandLoop action for: " + KeyEvent.getKeyText(keyCode));
        if (isInCommandState) {
            LOGGER.info("In command state.");
            performActionForCommand(keyCode);
        } else {
            LOGGER.info("Is not in command state.");
            performActionForCommandMessageLog(keyCode);
        }
    }

    private void outputTextToGui() {
        if (isInCommandState) {
            LOGGER.info("In command state.");
            gui.setText(handler.currentText());
        } else {
            LOGGER.info("Is not in command state.");
            gui.setText(messageLogHandler.currentText());
        }
    }

    private void performActionForCommand(int keyCode) {
        if (keyCode == KeyConstants.CONFIRM) {
            LOGGER.info("Case CONFIRM");
            handler.performConfirmCommand();

            Optional<CommandHandler> nextCommandOpt = handler.nextCommand();
            List<String> nextMessage = handler.getNextMessage();
            boolean isClearingCommandStack = handler.isClearingCommandStack();

            if (isClearingCommandStack) {
                previousCommands.clear();
            }

            if (nextCommandOpt.isPresent()) {
                if (!isClearingCommandStack) {
                    previousCommands.push(handler);
                }
                setCurrentCommandHandler(nextCommandOpt.get());
                setToCommandState();
            }

            if (!nextMessage.isEmpty()) {
                addTexts(nextMessage);
            } else {
                LOGGER.info("Handler has no next message.");
            }
        } else if (keyCode == KeyConstants.PREVIOUS_ITEM) {
            LOGGER.info("performPreviousInListCommand");
            handler.performPreviousInListCommand();
        } else if (keyCode == KeyConstants.NEXT_ITEM) {
            LOGGER.info("performNextInListCommand");
            handler.performNextInListCommand();
        } else if (keyCode == KeyConstants.SWITCH_TEXT_COMMAND) {
            LOGGER.info("Case switchText");
            setToMessageState();
        } else if (keyCode == KeyConstants.PREVIOUS_MENU) {
            LOGGER.info("Case goToPreviousMenu");
            if (!previousCommands.isEmpty()) {
                setCurrentCommandHandler(previousCommands.pop());
                setToCommandState();
                LOGGER.info("Previous handler present. Switching to: " + handler.getClass());
            } else {
                LOGGER.info("Command stack empty.");
            }
        } else if (keyCode == KeyConstants.HELP) {
            LOGGER.info("Case displayHelpForCurrentCommand");
            List<String> helpText = handler.getHelpText();
            if (!helpText.isEmpty()) {
                LOGGER.info("Switching to help text for: " + handler.getClass());
                addTexts(helpText);
            }
        } else {
            LOGGER.info("Unknown command");
        }
    }

    private void performActionForCommandMessageLog(int keyCode) {
        if (keyCode == KeyConstants.PREVIOUS_TEXT) {
            LOGGER.info("performPreviousInListCommand");
            messageLogHandler.performPreviousInListCommand();
        } else if (keyCode == KeyConstants.NEXT_TEXT) {
            LOGGER.info("performNextInListCommand");
            messageLogHandler.performNextInListCommand();
        } else if (keyCode == KeyConstants.SWITCH_TEXT_COMMAND || keyCode == KeyConstants.SKIP_TEXT
                || keyCode == KeyConstants.CONFIRM) {
            LOGGER.info("Case switchText or skipText(");
            setToCommandState();
        } else {
            LOGGER.info("Unknown command");
        }
    }

    private void addTexts(List<String> newTexts) {
        messageLogHandler.setToLastIndex();
        messageLogHandler.addTexts(newTexts);
        setToMessageState();
    }

    private void setToMessageState() {
        LOGGER.info("Setting to message state.");
        isInCommandState = false;
    }

    private void setToCommandState() {
        LOGGER.info("Setting to command state.");
        isInCommandState = true;
    }

    private void setCurrentCommandHandler(CommandHandler handler) {
        LOGGER.info("Setting current command handler to: " + handler.getClass());
        this.handler = handler;
    }
}