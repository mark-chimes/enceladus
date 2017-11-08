package Commands;

import java.util.List;

/**
 * Created by Mark Chimes on 2017/11/06.
 */
public class MessageLogHandler implements KeyPressHandler {
    MessageLogIterator iterator;

    public MessageLogHandler(List<String> messageLogs) {
        iterator = new MessageLogIterator(messageLogs);
    }

    public void addTexts(List<String> texts) {
        iterator.addTexts(texts);
    }

    public void setToLastIndex() {
        iterator.setToLastIndex();
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
        return iterator.currentIndex();
    }
}