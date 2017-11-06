package Game;

import java.util.ArrayList;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public class NewGameMessenger {
    public static ArrayList<String> message() {
        ArrayList<String> welcomeMessage = new ArrayList<>();
        welcomeMessage.add("Starting a new game");
        return welcomeMessage;
    }
}
