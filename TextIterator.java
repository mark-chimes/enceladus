import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * Created by Mark Chimes on 2017/11/02.
 */
public class TextIterator extends TextOrItemIterator {
    private final BasicGui gui;
    private final List<String> texts;
    private int iteratorIndex;

    public TextIterator(BasicGui gui, List<String> texts) {
        this.gui = gui;
        this.texts = texts;
    }

    public String currentText() {
        return texts.get(iteratorIndex);
    }

    public int currentIndex() { return iteratorIndex; }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyConstants.PREVIOUS_TEXT:
                if (iteratorIndex > 0) {
                    iteratorIndex--;
                }
                break;
            case KeyConstants.NEXT_TEXT:
                if (iteratorIndex < texts.size() - 1) {
                    iteratorIndex++;
                }
                break;
        }
        gui.setText(texts.get(iteratorIndex));
    }
}
