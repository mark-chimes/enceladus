package Core;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public abstract class CommandHandler implements KeyPressHandler {
    private final static Logger LOGGER = Logger.getLogger(CommandHandler.class.getName());

    protected CommandOrMessageIterator iterator;
    protected Optional<CommandHandler> nextCommand = Optional.empty();
    protected List<String> nextMessage = new ArrayList<>();

    @Override
    public String currentText() {
        return iterator.currentText();
    }

    @Override
    public int currentIndex() {
        return iterator.currentIndex();
    }

    protected void setTextOrItemIterator(CommandOrMessageIterator iterator) {
        this.iterator = iterator;
    }

    public Optional<CommandHandler> nextCommand() {
        return nextCommand;
    }

    protected void setNextCommand(CommandHandler newCommand) {
        LOGGER.info("Setting next command to: " + newCommand.getClass());
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

    public abstract List<String> getHelpText();

    protected void setIteratorMessages(List<String> messages) {
        setTextOrItemIterator(new CommandIterator(messages));
    }

    public void performKeyPress(int keyCode) {
        if (keyCode == KeyConstants.CONFIRM) {
            performActionFor(currentText());
        } else {
            iterator.performKeyPress(keyCode);
        }
    }

    public abstract CommandHandler newHandlerFrom();

    protected abstract void performActionFor(String commandString);

}