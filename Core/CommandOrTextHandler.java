package Core;

import java.util.List;
import java.util.Optional;
import java.util.Stack;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public abstract class CommandOrTextHandler implements KeyPressHandler {
    protected CommandOrTextIterator iterator;
    protected Optional<CommandOrTextHandler> nextCommand = Optional.empty();

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
    public abstract CommandOrTextHandler newHandlerFrom();

    public Optional<List<String>> getHelpText() {
        return Optional.empty();
    }
}
