package People;

import commandAndMessage.command.CommandHandler;
import commandAndMessage.command.CommandTuple;
import commandAndMessage.command.NullCommandHandler;
import commandAndMessage.main.KeyConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public class PeopleHandler extends CommandHandler {
    public PeopleHandler(List<Person> allPeople) {
        List<CommandTuple> commandTuples = new ArrayList<>();

        for (Person person : allPeople) {
            commandTuples.add(new CommandTuple(person.name(),
                    KeyConstants.EMPTY_LIST,
                    Arrays.asList("A person named " +  person.name() + "."),
                    new PersonHandler(person),
                    false
            ));
        }

        setIteratorCommands(commandTuples);
    }
}
