package Core;

import java.awt.event.KeyEvent;
import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Mark Chimes on 2017/11/06.
 */
public class MessageLogIterator implements CommandOrTextIterator {
    private final List<String> texts;
    private int iteratorIndex;
    private final static Logger LOGGER = Logger.getLogger(CommandOrTextHandler.class.getName());

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
            return MessageFormat.format("End of text. Press {0} to continue.", skip);
        }
    }

    public int currentIndex() { return iteratorIndex; }

    public void performKeyPress(int action) {
        LOGGER.info("Got key press " + KeyEvent.getKeyText(action));

        switch (action) {
            case KeyConstants.PREVIOUS_TEXT:
                LOGGER.info("Going to previous text. Iterator at: " + iteratorIndex);
                if (iteratorIndex > 0) {
                    iteratorIndex--;
                }
                break;
            case KeyConstants.NEXT_TEXT:
                LOGGER.info("Going to next text. Iterator at: " + iteratorIndex);
                if (iteratorIndex < texts.size()) {
                    iteratorIndex++;
                }
                break;
        }
    }
}
