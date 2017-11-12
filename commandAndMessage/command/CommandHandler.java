package commandAndMessage.command;

import com.sun.javafx.UnmodifiableArrayList;

import java.util.List;
import java.util.Optional;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public abstract class CommandHandler implements CommandOrMessageIterator {
    protected CommandIterator iterator;

    public String currentText() {
        return iterator.currentText();
    }

    public int currentIndex() {
        return iterator.currentIndex();
    }

    protected void setTextOrItemIterator(CommandIterator iterator) {
        this.iterator = iterator;
    }

    public Optional<CommandHandler> nextCommand() {
        CommandHandler handler = currentCommand().nextCommand;

        if (handler instanceof NullCommandHandler) {
            return Optional.empty();
        } else {
            return Optional.of(handler);
        }
    }

    public List<String> getNextMessage() {
        return currentCommand().nextMessage;
    }

    public List<String> getHelpText() {
        return currentCommand().helpText;
    }

    protected void setIteratorCommands(List<CommandTuple> commands) {
        setTextOrItemIterator(new CommandIterator(commands));
    }

    public final CommandTuple currentCommand() {
        return iterator.currentCommand();
    }

    public void performPreviousInListCommand() {
        iterator.performPreviousInListCommand();
    }

    public void performNextInListCommand() {
        iterator.performNextInListCommand();
    }

    public boolean isClearingCommandStack() {
        return currentCommand().shouldClearCommandStack;
    }

    public void performConfirmCommand() {
        currentCommand().executable.run();
    };
}