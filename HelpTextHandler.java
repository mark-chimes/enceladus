import Core.CommandHandler;
import Core.CommandOrTextHandler;
import Core.KeyConstants;
import Core.TextHandler;
import MainMenu.MenuKeyPressHandler;

import java.util.List;
import java.util.Optional;
import java.util.Stack;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public class HelpTextHandler extends TextHandler {
    private final CommandOrTextHandler previousCommand;
    private final List<String> helpText;
    public Stack<CommandOrTextHandler> previousHandlers;

    public HelpTextHandler(List<String> helptext, CommandOrTextHandler previousCommand,
                           Stack<CommandOrTextHandler> previousHandlers) {
        this.previousCommand = previousCommand;
        this.helpText = helptext;
        this.previousHandlers = previousHandlers;
        setIteratorMessages(helpText);
    }

    @Override
    public CommandOrTextHandler newHandlerFrom() {
        return new HelpTextHandler(helpText, previousCommand, previousHandlers);
    }

    @Override
    public void performKeyPress(int keyCode) {
        super.performKeyPress(keyCode);
        if (keyCode == KeyConstants.SKIP_TEXT) {
            previousHandlers.pop();
            setNextCommand(previousCommand);
        }
    }
}
