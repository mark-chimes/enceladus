import java.util.List;
import java.util.Optional;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public abstract class ItemHandler extends ItemOrMessageHandler {
    protected void setIteratorMessages(List<String> messages) {
       setTextOrItemIterator(new ItemIterator(messages));
    }
}