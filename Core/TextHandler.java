package Core;

import java.awt.event.KeyEvent;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

/**
 * Created by Mark Chimes on 2017/11/05.
 */

public abstract class TextHandler extends CommandOrTextHandler {
    private Optional<CommandOrTextHandler> nextCommand = Optional.empty();

    protected void setIteratorMessages(List<String> messages) {
        String skip = KeyEvent.getKeyText(KeyConstants.SKIP_TEXT);
        messages.add(MessageFormat.format("End of text. Press {0} to continue.", skip));
        setTextOrItemIterator(new TextIterator(messages));
    }

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
        return 0;
    }

    @Override
    public Optional<CommandOrTextHandler> nextCommand() {
        return nextCommand;
    }

    protected void setNextCommand(CommandOrTextHandler newCommand) {
        nextCommand = Optional.of(newCommand);
    }
}