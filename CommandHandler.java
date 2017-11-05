import java.util.Optional;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public interface CommandHandler {
    public void performActionFor(int keyCode);
    public String currentText();
    public int currentIndex();
}
