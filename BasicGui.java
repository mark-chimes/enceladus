import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class BasicGui extends JFrame {
    private List<String> commands;
    private final JLabel label;
    private final JLabel label2;

    public BasicGui() {
            commands = new ArrayList<>();
            setupBasicLayout();
            label= new JLabel();
            label2= new JLabel();

            label.setText("MyText");
            label2.setText("MyText");

            add(label);
            add(label2);

            label.grabFocus();
            label.grabFocus();
        }

    public final void addListener(KeyListener keyListener) {
        this.addKeyListener(keyListener);
        this.getRootPane().addKeyListener(keyListener);
        label.addKeyListener(keyListener);
        label2.addKeyListener(keyListener);
    }

    public final void removeListener(KeyListener keyListener) {
        this.removeKeyListener(keyListener);
        this.getRootPane().removeKeyListener(keyListener);
        label.removeKeyListener(keyListener);
        label2.removeKeyListener(keyListener);
    }

    public final void removeListeners(List<? extends KeyListener> keyListeners) {
        keyListeners.forEach(l -> removeListener(l));
    }

    public final void setText(String text) {
        label.setText(text);
        label2.setText(text);
        if (label.hasFocus()) {
            label2.grabFocus();
        } else {
            label.grabFocus();
        }
    }

    private final void setupBasicLayout() {
        setTitle("Enceladus");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(0,1));
    }
}