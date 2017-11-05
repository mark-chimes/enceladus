package Core;

import java.util.List;

/**
 * Created by Mark Chimes on 2017/11/02.
 */
public class TextIterator implements CommandOrTextIterator {
    private final List<String> texts;
    private int iteratorIndex;

    public TextIterator(List<String> texts) {
        this.texts = texts;
    }

    public String currentText() {
        return texts.get(iteratorIndex);
    }

    public int currentIndex() { return iteratorIndex; }

    public void performKeyPress(int action) {
        switch (action) {
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
    }
}
