import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class BasicGui extends JFrame {
    private List<String> commands;
    private final JTextArea textArea;
    private final JTextField textField;

    public BasicGui() {
        setupBasicLayout();
        textArea = new JTextArea("Welcome to Enceladus!\n");
        JScrollPane scroll = new JScrollPane(textArea);
        add(scroll);
        commands = new ArrayList<>();
        textField = new JTextField();
        setupTextArea();
        setupCommands();
        createCommandableTextField();
    }

    private final void setupBasicLayout() {
        setTitle("Enceladus");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(0,1));
    }

    private final void setupCommands() {
        ArrayList<String> initialCommands = new ArrayList<>();

        initialCommands.add("Hello");
        initialCommands.add("Hi");
        initialCommands.add("Howzit");
        initialCommands.add("This is a super long piece of text in order to try to see what the button does with it");
        setCommands(initialCommands);
    }

    public final void setCommands(List<String> newCommands) {
        commands = new ArrayList(newCommands);
        textField.setText(commands.get(0)); // TODO
    }

    private final void setupTextArea() {
        textArea.setEditable(false);
        textArea.append("This is a new line\n");
    }

    private final void createCommandableTextField() {
        textField.setText(commands.get(0));
        textField.setEditable(false);
        add(textField);
        textField.addKeyListener(new KeyListener() {
            int listIndex = 0;

            @Override
            public void keyTyped(KeyEvent e) {
                // DO NOTHING
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch( keyCode ) {
                    case KeyEvent.VK_UP:
                        if (listIndex > 0) {
                            listIndex --;
                        }
                        textField.setText(commands.get(listIndex));
                        break;
                    case KeyEvent.VK_DOWN:
                        if (listIndex < commands.size() -1) {
                            listIndex ++;
                        } else {
                            listIndex = commands.size() - 1;
                        }
                        textField.setText(commands.get(listIndex));
                        break;
                    case KeyEvent.VK_ENTER:
                        textArea.append(textField.getText() + "\n");
                        textArea.grabFocus();
                        int len = textArea.getDocument().getLength();
                        textArea.setCaretPosition(len);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                // DO NOTHING
            }
        });
    }
}