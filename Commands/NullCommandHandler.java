package Commands;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark Chimes on 2017/11/09.
 */
public class NullCommandHandler extends CommandHandler {
    @Override
    public List<String> getHelpText() {
        return EMPTY_LIST;
    }
}
