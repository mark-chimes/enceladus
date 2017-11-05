package Core;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public interface KeyPressHandler {
    void performKeyPress(int keyCode);
    String currentText();
    int currentIndex();
}
