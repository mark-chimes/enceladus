package People;

import commandAndMessage.command.CommandHandler;
import commandAndMessage.command.CommandTuple;
import commandAndMessage.command.NullCommandHandler;
import commandAndMessage.main.KeyConstants;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Mark Chimes on 2017/11/09.
 */
public class PersonHandler extends CommandHandler {
    private final Person person;

    public PersonHandler(Person person) {
        this.person = person;

        List<CommandTuple> commands = Arrays.asList(
                new CommandTuple( "Description",
                        KeyConstants.EMPTY_LIST,
                        Arrays.asList("Displays a basic description for " + person.name() + "."),
                        new NullCommandHandler(),
                        false
                ),
                new CommandTuple("Location",
                        KeyConstants.EMPTY_LIST,
                        Arrays.asList("Displays where " + person.name() + " is currently situated."),
                        new NullCommandHandler(),
                        false
                )
        );
        super.setIteratorCommands(commands);
    }
}
