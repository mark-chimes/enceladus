package Core;

import java.util.List;

/**
 * Created by Mark Chimes on 2017/11/06.
 */
public class MessageLogHandler extends CommandOrTextHandler {
    MessageLogIterator iterator;

    public MessageLogHandler(List<String> messageLogs) {
        iterator = new MessageLogIterator(messageLogs);
        super.iterator = iterator;
    }

    @Override
    public CommandOrTextHandler newHandlerFrom() {
        return null; // TODO remove
    }

    public void addTexts(List<String> texts) {
        iterator.addTexts(texts);
    }

    public void setToLastIndex() {
        iterator.setToLastIndex();
    }

}