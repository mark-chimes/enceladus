import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Mark Chimes on 2017/11/02.
 */
public class CommandDisplay {
    private final BasicGui gui;

    Optional<TextIterator> textIterator = Optional.empty();

    public CommandDisplay(BasicGui gui) {
        this.gui = gui;
    }

    public void newTextsList(List<String> texts) {
        newTextsList(texts, -1);
    }

    private void newTextsList(List<String> texts, int index) {
        textIterator.ifPresent(gui::removeKeyListener);
        textIterator = Optional.of(new TextIterator(gui, texts, index));
        gui.addListener(textIterator.get());
    }


    private final class TextIterator extends KeyAdapter {
        private final BasicGui gui;
        private final List<String> texts;
        int iteratorIndex;

        public TextIterator(BasicGui gui, List<String> texts) {
            this(gui, texts, 0);
        }

        public TextIterator(BasicGui gui, List<String> texts, int index) {
            this.gui = gui;
            this.texts = texts;
            iteratorIndex = index;
        }

        public String currentText() {
            return texts.get(iteratorIndex);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    if (iteratorIndex > 0) {
                        iteratorIndex--;
                    } else {
                        iteratorIndex = texts.size() - 1;
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (iteratorIndex < texts.size() - 1) {
                        iteratorIndex++;
                    } else {
                        iteratorIndex = 0;
                    }
                    break;
            }
            gui.setText(texts.get(iteratorIndex));
        }
    }
}
