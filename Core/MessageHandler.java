package Core;

import Core.ItemOrMessageHandler;
import Core.TextIterator;

import java.util.List;
import java.util.Optional;

/**
 * Created by Mark Chimes on 2017/11/05.
 */

public abstract class MessageHandler extends ItemOrMessageHandler {
    private Optional<ItemOrMessageHandler> nextCommand = Optional.empty();

    protected void setIteratorMessages(List<String> messages) {
        setTextOrItemIterator(new TextIterator(messages));
    }

    @Override
    public void performActionFor(int keyCode) {
        iterator.performActionFor(keyCode);
    }

    @Override
    public String currentText() {
        return iterator.currentText();
    }

    @Override
    public int currentIndex() {
        return 0;
    }

    @Override
    public Optional<ItemOrMessageHandler> nextCommand() {
        return nextCommand;
    }

    protected void setNextCommand(ItemOrMessageHandler newCommand) {
        nextCommand = Optional.of(newCommand);
    }
}