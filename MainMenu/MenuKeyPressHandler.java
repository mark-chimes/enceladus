package MainMenu;

import commandAndMessage.command.CommandHandler;
import commandAndMessage.command.CommandTuple;
import commandAndMessage.command.NullCommandHandler;
import Game.MainGameHandler;
import commandAndMessage.main.KeyConstants;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public class MenuKeyPressHandler extends CommandHandler {
    private final List<CommandTuple> commandTuples = Arrays.asList(
        new CommandTuple("New Game",
                Arrays.asList("Starting a new game."),
                Arrays.asList("Starts a game completely from the beginning."),
                new MainGameHandler(),
                true
        ),
        new CommandTuple("Instructions",
                Arrays.asList("Instructions forthcoming"),
                Arrays.asList("Provides basic instructions for play."),
                new NullCommandHandler(),
                false
        ),
        new CommandTuple("Load game",
                KeyConstants.EMPTY_LIST,
                Arrays.asList("Continues a game that was previously played."),
                new NullCommandHandler(),
                false
        ),
        new CommandTuple("Options",
                KeyConstants.EMPTY_LIST,
                Arrays.asList("Set game-play options such as controls."),
                new NullCommandHandler(),
                false
        ),
        new CommandTuple("Exit",
                KeyConstants.EMPTY_LIST,
                Arrays.asList("Continues a game that was previously played."),
                new NullCommandHandler(),
                false,
                this::exitGame
        )
    );

    public void exitGame() {
        System.exit(0);
    }

    public MenuKeyPressHandler() {
        setIteratorCommands(commandTuples);
    }
}
