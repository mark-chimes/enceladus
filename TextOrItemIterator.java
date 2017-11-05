import java.awt.event.KeyAdapter;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public abstract class TextOrItemIterator implements CommandHandler{
    public abstract String currentText();
    public abstract int currentIndex();
}
