package Core;

import java.awt.event.KeyEvent;
import java.text.MessageFormat;
import java.util.List;

/**
 * Created by Mark Chimes on 2017/11/06.
 */
public class MessageLogHandler implements CommandOrTextIterator {
    private final List<String> texts;
    private int iteratorIndex;

    public MessageLogHandler(List<String> texts) {
        this.texts = texts;
    }

    public void addTexts(List<String> newTexts) {
        texts.addAll(newTexts);
    }

    public String currentText() {
        if (iteratorIndex < texts.size()) {
            return texts.get(iteratorIndex);
        } else {
            String skip = KeyEvent.getKeyText(KeyConstants.SKIP_TEXT);
            return MessageFormat.format("End of text. Press {0} to continue.", skip);
        }
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
                if (iteratorIndex < texts.size()) {
                    iteratorIndex++;
                }
                break;
        }
    }
}
