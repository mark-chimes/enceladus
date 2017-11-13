package People

import commandAndMessage.command.CommandHandler
import commandAndMessage.command.CommandTuple
import commandAndMessage.command.NullCommandHandler
import commandAndMessage.main.KeyConstants

import java.util.Arrays

/**
 * Created by Mark Chimes on 2017/11/09.
 */
class PersonHandler(private val person: Person) : CommandHandler() {

    init {

        val commands = Arrays.asList(
                CommandTuple("Description",
                        KeyConstants.EMPTY_LIST,
                        Arrays.asList("Displays a basic description for " + person.name() + "."),
                        NullCommandHandler(),
                        false
                ),
                CommandTuple("Location",
                        KeyConstants.EMPTY_LIST,
                        Arrays.asList("Displays where " + person.name() + " is currently situated."),
                        NullCommandHandler(),
                        false
                )
        )
        super.setIteratorCommands(commands)
    }
}
