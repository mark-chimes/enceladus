import java.util.List;
import java.util.Optional;

/**
 * Created by Mark Chimes on 2017/11/05.
 */
public abstract class ItemOrMessageHandler implements CommandHandler {
    protected TextOrItemIterator iterator;
    protected Optional<ItemOrMessageHandler> nextCommand = Optional.empty();

    @Override
    public void performActionFor(int keyCode) {
        iterator.performActionFor(keyCode);
    }

    @Override
    public String currentText() {
        return iterator.currentText();
    }

    @Override
    public int currentIndex() {
        return iterator.currentIndex();
    }

    protected void setTextOrItemIterator(TextOrItemIterator iterator) {
        this.iterator = iterator;
    }

    public Optional<ItemOrMessageHandler> nextCommand() {
        return nextCommand;
    }

    protected void setNextCommand(ItemOrMessageHandler newCommand) {
        nextCommand = Optional.of(newCommand);
    }

}
