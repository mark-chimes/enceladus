package Core;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public abstract class CommandOrMessageHandler implements KeyPressHandler {
    protected CommandOrMessageIterator iterator;
    protected Optional<CommandOrMessageHandler> nextCommand = Optional.empty();
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

    protected void setTextOrItemIterator(CommandOrMessageIterator iterator) {
        this.iterator = iterator;
    }

    public Optional<CommandOrMessageHandler> nextCommand() {
        return nextCommand;
    }

    protected void setNextCommand(CommandOrMessageHandler newCommand) {
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

    public Optional<List<String>> getHelpText() {
        return Optional.empty();
    }


}
