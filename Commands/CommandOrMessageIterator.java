package Commands;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public interface CommandOrMessageIterator extends KeyPressHandler {
    String currentText();
    int currentIndex();
}
