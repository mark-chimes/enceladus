package commandAndMessage.command;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public interface CommandOrMessageIterator {
    String currentText();
    int currentIndex();
    void performPreviousInListCommand();
    void performNextInListCommand();
}
