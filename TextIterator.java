import com.sun.corba.se.impl.oa.poa.ActiveObjectMap;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Optional;

/**
 * Created by Mark Chimes on 2017/11/02.
 */
public class TextIterator extends KeyAdapter{
    private final BasicGui gui;
    private final List<String> texts;
    private final Optional<ActionListener> oufOfBoundsListenerOpt;
    private int iteratorIndex;

    public TextIterator(BasicGui gui, List<String> texts) {
            this(gui, texts, 0);
        }
        public TextIterator(BasicGui gui, List<String> texts, int index) {

            this(gui,texts,index,Optional.empty());
        }

        public TextIterator(BasicGui gui, List<String> texts, int index, Optional<ActionListener> outOfBoundsListener) {
            this.gui = gui;
            this.texts = texts;
            iteratorIndex = index;
            this.oufOfBoundsListenerOpt = outOfBoundsListener;
        }

        public String currentText() {
            return texts.get(iteratorIndex);
        }
        public int currentIndex() {
            return iteratorIndex;
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
