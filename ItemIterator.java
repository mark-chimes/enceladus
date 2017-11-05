import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public class ItemIterator extends KeyAdapter {
    private final BasicGui gui;
    private final List<String> items;
    private int iteratorIndex;

    public ItemIterator(BasicGui gui, List<String> items) {
        this(gui, items, 0);
    }

    public ItemIterator(BasicGui gui, List<String> items, int index) {
        this.gui = gui;
        this.items = items;
        iteratorIndex = index;
    }

    public String currentItem() {
        return items.get(iteratorIndex);
    }

    public int currentIndex() { return iteratorIndex; }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
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
        gui.setText(items.get(iteratorIndex));
    }
}