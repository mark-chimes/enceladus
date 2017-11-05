package Core;

import java.util.Optional;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public abstract class CommandOrTextHandler implements KeyPressHandler {
    protected CommandOrTextIterator iterator;
    protected Optional<CommandOrTextHandler> nextCommand = Optional.empty();
    protected Optional<CommandOrTextHandler> previousCommand = Optional.empty();

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
        nextCommand = Optional.of(newCommand);
    }
    protected void setPreviousCommand(CommandOrTextHandler newCommand) {
        previousCommand = Optional.of(newCommand);
    }
    public abstract CommandOrTextHandler newHandlerFrom();
}
