package Core;

import java.util.List;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public class CommandIterator implements CommandOrMessageIterator {
    private final List<String> items;
    private int iteratorIndex;

    public CommandIterator(List<String> items) {
        this(items, 0);
    }

    public CommandIterator(List<String> items, int index) {
        this.items = items;
        iteratorIndex = index;
    }

    public String currentText() {
        return items.get(iteratorIndex);
    }

    public int currentIndex() { return iteratorIndex; }

    public void performKeyPress(int action) {
        switch (action) {
            case KeyConstants.PREVIOUS_ITEM:
                if (iteratorIndex > 0) {
                    iteratorIndex--;
                } else {
                    iteratorIndex = items.size() - 1;
                }
                break;
            case KeyConstants.NEXT_ITEM:
                if (iteratorIndex < items.size() - 1) {
                    iteratorIndex++;
                } else {
                    iteratorIndex = 0;
                }
                break;
        }
    }
}