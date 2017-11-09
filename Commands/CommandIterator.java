package Commands;

import java.util.List;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public class CommandIterator implements CommandOrMessageIterator {
    private final List<CommandTuple> items;
    private int iteratorIndex;

    public CommandIterator(List<CommandTuple> items) {
        this(items, 0);
    }

    public CommandIterator(List<CommandTuple> items, int index) {
        this.items = items;
        iteratorIndex = index;
    }

    public String currentText() {
        return currentCommand().actionString;
    }

    public CommandTuple currentCommand() {
        return items.get(iteratorIndex);
    }

    public int currentIndex() { return iteratorIndex; }

    @Override
    public void performPreviousInListCommand() {
        if (iteratorIndex > 0) {
            iteratorIndex--;
        } else {
            iteratorIndex = items.size() - 1;
        }
    }

    @Override
    public void performNextInListCommand() {
        if (iteratorIndex < items.size() - 1) {
            iteratorIndex++;
        } else {
            iteratorIndex = 0;
        }
    }
}