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
    private final JTextArea textArea;
    private final JTextField textField;
    private final List<ActionListener> listeners = new ArrayList<>();
    private CustomTextKeyListener keyListener;
    private final JLabel label;
    private final JLabel label2;
    private int listIndex = 0;



    public BasicGui() {
//        setupBasicLayout();
//        textArea = new JTextArea("Welcome to Enceladus!");
//        textArea.setToolTipText("Display");
//        JScrollPane scroll = new JScrollPane(textArea);
//        add(scroll);
//        commands = new ArrayList<>();
//        textField = new JTextField();
//        textField.setToolTipText("Command");
//        setupTextArea();
//        createCommandableTextField();


            /****/
            textArea = new JTextArea();
            textField = new JTextField();

            commands = new ArrayList<>();
            setupBasicLayout();
            label= new JLabel();
            label2= new JLabel();

            label.setText("MyText");
            label2.setText("MyText");

            add(label);
            add(label2);

            label.grabFocus();
            this.addKeyListener(new LabelKeyListener());
            this.getRootPane().addKeyListener(new LabelKeyListener());
            label.addKeyListener(new LabelKeyListener());
            label2.addKeyListener(new LabelKeyListener());

            label.grabFocus();


            /****/

        }

    public final void addListener(ActionListener actionListener) {
        listeners.add(actionListener);
    }

    private final void setupBasicLayout() {
        setTitle("Enceladus");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(0,1));
    }

    public final void setCommands(List<String> newCommands) {
        commands = new ArrayList(newCommands);
        setToFirstCommandOrBlank();
    }

    private final void setupTextArea() {
        textArea.setEditable(false);
    }

    public final void addToDisplayArea(String newText) {
        textArea.append("\n"  + newText);

    }

    private final void setToFirstCommandOrBlank() {
        if (commands.isEmpty()) {
            textField.setText("");
        } else {
            textField.setText(commands.get(0));
        }
    }

    private final void createCommandableTextField() {
        setToFirstCommandOrBlank();
        textField.setEditable(false);
        add(textField);
        keyListener = new CustomTextKeyListener();
        textField.addKeyListener(keyListener);
    }

    public void resetCommandIndex() {
        keyListener.resetListIndex();
    }

    private class CustomTextKeyListener implements KeyListener {
        private int listIndex = 0;

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_UP:
                    if (listIndex > 0) {
                        listIndex--;
                    } else {
                        listIndex = commands.size() - 1;
                    }
                    textField.setText(commands.get(listIndex));
                    break;
                case KeyEvent.VK_DOWN:
                    if (listIndex < commands.size() - 1) {
                        listIndex++;
                    } else {
                        listIndex = 0;
                    }
                    textField.setText(commands.get(listIndex));
                    break;
                case KeyEvent.VK_ENTER:
                    String command = textField.getText();
                    addToDisplayArea(command);
                    textArea.grabFocus();
                    int len = textArea.getDocument().getLength();
                    textArea.setCaretPosition(len);
                    for (ActionListener listener : listeners) {
                        listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, command));
                    }
            }
        }

        public void resetListIndex() {
            listIndex = 0;
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // DO NOTHING
        }

        @Override
        public void keyTyped(KeyEvent e) {
            // DO NOTHING
        }
    }

    private class LabelKeyListener implements KeyListener {
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_UP:

                    if (listIndex > 0) {
                        listIndex--;
                    } else {
                        listIndex = commands.size() - 1;
                    }

                    readOnScreen();
                    break;
                case KeyEvent.VK_DOWN:
                    if (listIndex < commands.size() - 1) {
                        listIndex++;
                    } else {
                        listIndex = 0;
                    }
s
                    readOnScreen();
                    break;
                case KeyEvent.VK_ENTER:
                    String command = textField.getText();
                    addToDisplayArea(command);
                    textArea.grabFocus();
                    int len = textArea.getDocument().getLength();
                    textArea.setCaretPosition(len);
                    for (ActionListener listener : listeners) {
                        listener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, command));
                    }
            }
        }

        private void readOnScreen() {
            label.setText(commands.get(listIndex));
            label2.setText(commands.get(listIndex));
            if (label.hasFocus()) {
                label2.grabFocus();
            } else {
                label.grabFocus();
            }
        }

        public void resetListIndex() {
            listIndex = 0;
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // DO NOTHING
        }

        @Override
        public void keyTyped(KeyEvent e) {
            // DO NOTHING
        }
    }
}