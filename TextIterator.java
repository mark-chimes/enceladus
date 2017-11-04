import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * Created by Mark Chimes on 2017/11/02.
 */
public class TextIterator extends KeyAdapter{
    private final BasicGui gui;
    private final List<String> texts;
    private final boolean isLooping;
    private int iteratorIndex;

    public TextIterator(BasicGui gui, List<String> texts) {
        this(gui, texts, 0);
    }

    public TextIterator(BasicGui gui, List<String> texts, boolean isLooping) {
        this(gui, texts, 0, isLooping);
    }

    public TextIterator(BasicGui gui, List<String> texts, int index) {
        this(gui,texts,index, true);
    }

    public TextIterator(BasicGui gui, List<String> texts, int index, boolean isLooping) {
        this.gui = gui;
        this.texts = texts;
        iteratorIndex = index;
        this.isLooping = isLooping;
    }

    public String currentText() {
        return texts.get(iteratorIndex);
    }

    public int currentIndex() { return iteratorIndex; }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (iteratorIndex > 0) {
                    iteratorIndex--;
                } else if(isLooping){
                    iteratorIndex = texts.size() - 1;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (iteratorIndex < texts.size() - 1) {
                    iteratorIndex++;
                } else if(isLooping) {
                    iteratorIndex = 0;
                }
                break;
        }
        gui.setText(texts.get(iteratorIndex));
    }
}
