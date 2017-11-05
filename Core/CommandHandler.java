package Core;

import java.util.List;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public abstract class CommandHandler extends CommandOrTextHandler {
    protected void setIteratorMessages(List<String> messages) {
        setTextOrItemIterator(new CommandIterator(messages));
    }

    public void performKeyPress(int keyCode) {
        super.performKeyPress(keyCode);
        if (keyCode == KeyConstants.CONFIRM) {
            performActionFor(currentText());
        }
    }

    protected abstract void performActionFor(String commandString);
}