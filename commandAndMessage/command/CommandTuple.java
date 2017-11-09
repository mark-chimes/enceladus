package commandAndMessage.command;

import commandAndMessage.command.CommandHandler;

import java.util.List;

/**
 * Created by Mark Chimes on 2017/11/09.
 */
public class CommandTuple {
    public final String actionString;
    public final List<String> nextMessage;
    public final List<String> helpText;
    public final CommandHandler nextCommand;
    public final boolean shouldClearCommandStack;
    public final Runnable executable;

    public CommandTuple(String actionString,
                        List<String> nextMessage,
                        List<String> helpText,
                        CommandHandler nextCommand, boolean shouldClearCommandStack) {
        this.actionString = actionString;
        this.nextMessage = nextMessage;
        this.helpText = helpText;
        this.nextCommand = nextCommand;
        this.shouldClearCommandStack = shouldClearCommandStack;
        this.executable = () -> { /* Do Nothing */ };
    }

    public CommandTuple(String actionString,
                        List<String> nextMessage,
                        List<String> helpText,
                        CommandHandler nextCommand,
                        boolean shouldClearCommandStack,
                        Runnable executable) {
        this.actionString = actionString;
        this.nextMessage = nextMessage;
        this.helpText = helpText;
        this.nextCommand = nextCommand;
        this.shouldClearCommandStack = shouldClearCommandStack;
        this.executable = executable;
    }
}