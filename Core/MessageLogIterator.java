package Core;

import java.awt.event.KeyEvent;
import java.text.MessageFormat;
import java.util.List;

/**
 * Created by Mark Chimes on 2017/11/06.
 */
public class MessageLogIterator implements CommandOrMessageIterator {
    private final List<String> texts;
    private int iteratorIndex;

    public MessageLogIterator(List<String> texts) {
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
            String previous = KeyEvent.getKeyText(KeyConstants.PREVIOUS_MENU);
            String switchText = KeyEvent.getKeyText(KeyConstants.SWITCH_TEXT_COMMAND);
            return MessageFormat.format("End of text. Press {0}, {1}, or {2} to continue.",
                    skip, previous, switchText);

        }
    }

    public void setToLastIndex() {
        iteratorIndex = texts.size();
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
