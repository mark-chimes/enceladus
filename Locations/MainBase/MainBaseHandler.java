package Locations.MainBase;

import Commands.CommandHandler;
import Locations.Location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public class MainBaseHandler extends CommandHandler{

    public MainBaseHandler() {
        setIteratorMessages(commands());
    }

    public void performActionFor(String actionString) {
        switch (actionString) {
            case "Description":
                break;
            case "Summary":
                break;
            case "Overview":
                break;
            case "People":
                break;
            case "Sub-locations":
                break;
        }
    }

    private final ArrayList<String> commands() {
        ArrayList<String> initialCommands = new ArrayList<>();
        initialCommands.add("Description");
        initialCommands.add("Summary");
        initialCommands.add("Overview");
        initialCommands.add("People");
        initialCommands.add("Sub-locations");

        return initialCommands;
    }

    @Override
    public List<String> getHelpText() {
        return new ArrayList<>(); // TODO
    }

    @Override
    protected String getDefaultName() {
        return "Main Base";
    }

    @Override
    public List<String> getDefaultHelpText() {
        return Arrays.asList("The main base", "Description forthcoming");
    }
}

