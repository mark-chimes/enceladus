import Core.BasicGui;
import Core.CommandOrTextHandler;
import Core.KeyConstants;
import MainMenu.WelcomeMessenger;

import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.logging.Logger;

/**
 * Created by Mark Chimes on 2017/11/02.
 */
public class GameLoop {
    private final BasicGui gui;
    private CommandKeyListener keyListener;
    private final static Logger LOGGER = Logger.getLogger(GameLoop.class.getName());

    private CommandOrTextHandler handler;
    private Stack<CommandOrTextHandler> previousHandlers = new Stack<>();

    public GameLoop(BasicGui gui) {
        this.gui = gui;
    }

    public void setup() {
        EventQueue.invokeLater(() -> {
            gui.setVisible(true);
        });
        this.keyListener = new CommandKeyListener();
        gui.addListener(keyListener);
        setCurrentCommandHandler(new WelcomeMessenger());
    }

    private class CommandKeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            LOGGER.info("Got key press " + KeyEvent.getKeyText(e.getKeyCode()));
            performActionFor(e.getKeyCode());
        }
    }

    private void performActionFor(int keyCode) {
        if (keyCode == KeyConstants.PREVIOUS_MENU) {
            if (!previousHandlers.isEmpty()) {
                handler = previousHandlers.pop();
                LOGGER.info("Previous handler present. Switching to: " + handler.getClass());
            }
        } else if (keyCode == KeyConstants.HELP) {
            Optional<List<String>> helpText = handler.getHelpText();
            if (helpText.isPresent()) {
                LOGGER.info("Pushing " + handler.getClass() + " to stack.");
                previousHandlers.push(handler.newHandlerFrom());
                LOGGER.info("Switching to help text for: " + handler.getClass());
                setCurrentCommandHandler(new HelpTextHandler(helpText.get(), handler, previousHandlers));
            }
        } else {
            handler.performKeyPress(keyCode);
            if (handler.nextCommand().isPresent()) {
                CommandOrTextHandler nextCommand = handler.nextCommand().get();
                LOGGER.info("Handler has next command. Switching to: " + nextCommand.getClass());
                if (!handler.getClass().equals(HelpTextHandler.class)) {
                    LOGGER.info("Pushing " + handler.getClass() + " to stack.");
                    previousHandlers.push(handler.newHandlerFrom());
                }
                setCurrentCommandHandler(handler.nextCommand().get());
            }
        }
        gui.setText(handler.currentText());
    }

    private void setCurrentCommandHandler(CommandOrTextHandler handler) {
        LOGGER.info("Setting current command handler to: " + handler.getClass());
        this.handler = handler;
        gui.setText(handler.currentText());
    }
}