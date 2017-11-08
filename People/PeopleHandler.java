package People;

import Commands.CommandHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public List<String> getHelpText() {
        return new ArrayList<>(); // TODO
    }


    @Override
    protected String getDefaultName() {
        return "People";
    }

    @Override
    protected List<String> getDefaultHelpText() {
        return Arrays.asList("Shows a list of the survivors about which you know.");
    }
}
