package MainMenu

import commandAndMessage.command.CommandHandler
import commandAndMessage.command.CommandTuple
import commandAndMessage.command.NullCommandHandler
import Game.MainGameHandler
import commandAndMessage.main.KeyConstants

import java.util.Arrays

/**
 * Created by Mark Chimes on 2017/11/05.
 */
class MenuKeyPressHandler : CommandHandler() {
    private val commandTuples = Arrays.asList(
            CommandTuple("New Game",
                    Arrays.asList("Starting a new game."),
                    Arrays.asList("Starts a game completely from the beginning."),
                    MainGameHandler(),
                    true
            ),
            CommandTuple("Instructions",
                    Arrays.asList("Instructions forthcoming"),
                    Arrays.asList("Provides basic instructions for play."),
                    NullCommandHandler(),
                    false
            ),
            CommandTuple("Load game",
                    KeyConstants.EMPTY_LIST,
                    Arrays.asList("Continues a game that was previously played."),
                    NullCommandHandler(),
                    false
            ),
            CommandTuple("Options",
                    KeyConstants.EMPTY_LIST,
                    Arrays.asList("Set game-play options such as controls."),
                    NullCommandHandler(),
                    false
            ),
            CommandTuple("Exit",
                    KeyConstants.EMPTY_LIST,
                    Arrays.asList("Continues a game that was previously played."),
                    NullCommandHandler(),
                    false,
                    Runnable { this.exitGame() }
            )
    )

    fun exitGame() {
        System.exit(0)
    }

    init {
        setIteratorCommands(commandTuples)
    }
}
