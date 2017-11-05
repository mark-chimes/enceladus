/**
 * Created by Mark Chimes on 2017/11/05.
 */
public interface CommandHandler {
    void performActionFor(int keyCode);
    String currentText();
    int currentIndex();
}
