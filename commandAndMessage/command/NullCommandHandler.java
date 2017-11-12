package commandAndMessage.command;

import commandAndMessage.command.CommandHandler;
import commandAndMessage.main.KeyConstants;

import java.util.List;

/**
 * Created by Mark Chimes on 2017/11/09.
 */
public class NullCommandHandler extends CommandHandler {
    @Override
    public List<String> getHelpText() {
        return KeyConstants.EMPTY_LIST;
    }
}
