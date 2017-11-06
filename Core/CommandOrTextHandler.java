package Core;

import javax.swing.text.html.Option;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public abstract class CommandOrTextHandler implements KeyPressHandler {
    private final static Logger LOGGER = Logger.getLogger(CommandOrTextHandler.class.getName());

    protected CommandOrTextIterator iterator;
    protected Optional<CommandOrTextHandler> nextCommand = Optional.empty();
    protected List<String> nextMessage = new ArrayList<>();

    @Override
    public void performKeyPress(int keyCode) {
        iterator.performKeyPress(keyCode);
    }

    @Override
    public String currentText() {
        return iterator.currentText();
    }

    @Override
    public int currentIndex() {
        return iterator.currentIndex();
    }

    protected void setTextOrItemIterator(CommandOrTextIterator iterator) {
        this.iterator = iterator;
    }

    public Optional<CommandOrTextHandler> nextCommand() {
        return nextCommand;
    }

    protected void setNextCommand(CommandOrTextHandler newCommand) {
        nextCommand = Optional.ofNullable(newCommand);
    }

    protected void clearNextCommand() {
        nextCommand = Optional.empty();
    }

    public List<String> getNextMessage() {
        return nextMessage;
    }

    protected void setNextMessage(List<String> nextMessage) {
        this.nextMessage = nextMessage;
    }

    public abstract CommandOrTextHandler newHandlerFrom();

    public Optional<List<String>> getHelpText() {
        return Optional.empty();
    }
}
