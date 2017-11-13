package People

import commandAndMessage.command.CommandHandler
import commandAndMessage.command.CommandTuple
import commandAndMessage.command.NullCommandHandler
import commandAndMessage.main.KeyConstants

import java.util.ArrayList
import java.util.Arrays

/**
 * Created by Mark Chimes on 2017/11/05.
 */
class PeopleHandler(allPeople: List<Person>) : CommandHandler() {
    init {
        val commandTuples = ArrayList<CommandTuple>()

        for (person in allPeople) {
            commandTuples.add(CommandTuple(person.name(),
                    KeyConstants.EMPTY_LIST,
                    Arrays.asList("A person named " + person.name() + "."),
                    PersonHandler(person),
                    false
            ))
        }

        setIteratorCommands(commandTuples)
    }
}
