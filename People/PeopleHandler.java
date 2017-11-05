package People;

import Core.CommandHandler;
import Core.CommandOrTextHandler;
import Locations.LocationsHandler;

import java.util.ArrayList;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public class PeopleHandler extends CommandHandler {
    public PeopleHandler() {
        setIteratorMessages(commands());
    }

    public void performActionFor(String actionString) {
        switch (actionString) {
            case "Mark":
                break;
            case "Lilley":
                break;
        }
    }

    private final ArrayList<String> commands() {
        ArrayList<String> initialCommands = new ArrayList<>();

        initialCommands.add("Mark");
        initialCommands.add("Lilley");
        return initialCommands;
    }

    @Override
    public CommandOrTextHandler newHandlerFrom() {
        return new PeopleHandler();
    }
}
