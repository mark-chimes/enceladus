package Commands;

import java.awt.event.KeyEvent;
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
    protected boolean isClearingCommandStack = false;

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
        LOGGER.info("Setting next commaand to: " + newCommand.getClass());
        nextCommand = Optional.ofNullable(newCommand);
    }

    protected void clearNextCommand() {
        nextCommand = Optional.empty();
    }

    protected void shouldClearCommandStack() {
        isClearingCommandStack = true;
    }

    protected void shouldNotClearCommandStack() {
        isClearingCommandStack = false;
    }

    public List<String> getNextMessage() {
        return nextMessage;
    }

    protected void setNextMessage(List<String> nextMessage) {
        LOGGER.info("Set next message got called!");
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

    protected abstract void performActionFor(String commandString);

    protected abstract String getDefaultName();

    public abstract List<String> getDefaultHelpText();

    public List<String> getMessageDisplayedByThisCommand() {
        return new ArrayList<>();
    }

    public boolean isClearingCommandStack() {
        return isClearingCommandStack;
    }
}