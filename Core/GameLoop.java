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

    private CommandOrTextHandler handler;
    private boolean isInCommandState;
    private boolean isGameRunning;
    private MessageLogIterator messageLogIterator;
    private Stack<CommandOrTextHandler> previousCommands = new Stack<>();

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
        messageLogIterator = new WelcomeMessenger();
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
            if (isInCommandState) {
                LOGGER.info("In command state. Pushing handler: " + handler.getClass());
                previousCommands.push(handler);
                setCommandHandlerToMessageLog();
            } else {
                LOGGER.info("Is not in command state.");
                setCommandHandlerToLastCommand();
            }
        } else if (keyCode == KeyConstants.SKIP_TEXT && !isInCommandState)  {
            LOGGER.info("Is not in command state.");
            setCommandHandlerToLastCommand();
        }else if (keyCode == KeyConstants.PREVIOUS_MENU) {
            if (!previousCommands.isEmpty()) {
                setCurrentCommandHandler(previousCommands.pop());
                LOGGER.info("Previous handler present. Switching to: " + handler.getClass());
            } else {
                LOGGER.info("Command stack empty.");
            }
        } else if (keyCode == KeyConstants.HELP) {
            Optional<List<String>> helpText = handler.getHelpText();
            if (helpText.isPresent()) {
                LOGGER.info("Pushing " + handler.getClass() + " to stack.");
                previousCommands.push(handler.newHandlerFrom());
                LOGGER.info("Switching to help text for: " + handler.getClass());
                addTextsAndDisplay(helpText.get());
            }
        } else {
            handler.performKeyPress(keyCode);
            List<String> nextMessage = handler.getNextMessage();
            Optional<CommandOrTextHandler> nextCommandOpt = handler.nextCommand();

            if (nextCommandOpt.isPresent()) {
                CommandOrTextHandler nextCommand = nextCommandOpt.get();
                LOGGER.info("Handler has next command: " + nextCommand.getClass());
                LOGGER.info("Pushing " + handler.getClass() + " to stack.");
                previousCommands.push(handler.newHandlerFrom());
                LOGGER.info("Setting to " + nextCommand.getClass());
                setCurrentCommandHandler(handler.nextCommand().get());
            } else {
                LOGGER.info("Handler has NO next command.");
            }
            if (nextMessage.isEmpty()) {
                LOGGER.info("Handler has no next message.");
            } else {
                LOGGER.info("Handler has next message so pushing " + handler.getClass() + " to queue.");
                previousCommands.push(handler.newHandlerFrom());
                LOGGER.info("Handler has next message so adding message to queue.");
                addTextsAndDisplay(nextMessage);
            }
        }
        gui.setText(handler.currentText());
    }

    private void addTextsAndDisplay(List<String> newTexts) {
        messageLogIterator.setToLastIndex();
        messageLogIterator.addTexts(newTexts);
        setCommandHandlerToMessageLog();
    }

    private void setCommandHandlerToMessageLog() {
        LOGGER.info("Setting current command handler message log handler.");
        isInCommandState = false;
        this.handler = new MessageLogHandler(messageLogIterator);
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

    private void setCurrentCommandHandler(CommandOrTextHandler handler) {
        LOGGER.info("Setting current command handler to: " + handler.getClass());
        isInCommandState = true;
        this.handler = handler;
        gui.setText(handler.currentText());
    }
}