package Core;

import java.util.List;

/**
 * Created by Mark Chimes on 2017/11/06.
 */
public class MessageLogHandler extends CommandOrMessageHandler {
    MessageLogIterator iterator;

    public MessageLogHandler(List<String> messageLogs) {
        iterator = new MessageLogIterator(messageLogs);
        super.iterator = iterator;
    }

    public void addTexts(List<String> texts) {
        iterator.addTexts(texts);
    }

    public void setToLastIndex() {
        iterator.setToLastIndex();
    }

}