package Core;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public interface CommandOrTextIterator extends KeyPressHandler {
    public abstract String currentText();
    public abstract int currentIndex();
}
