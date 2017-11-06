package Core;

/**
 * Created by Mark Chimes on 2017/11/06.
 */
public class MessageLogHandler extends CommandOrTextHandler {
    public MessageLogHandler(MessageLogIterator messageLogs) {
        super();
        setTextOrItemIterator(messageLogs);
    }

    @Override
    public CommandOrTextHandler newHandlerFrom() {
        return null; // TODO remove
    }
}