import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class BasicGui extends JFrame {

    public BasicGui() {
        setTitle("Enceladus");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(0,1));

        JTextArea textArea = new JTextArea("Welcome to Enceladus!\n");
        textArea.setEditable(false);
        textArea.append("This is a new line\n");
        JScrollPane scroll = new JScrollPane(textArea);
        add(scroll);

        List<String> options = new ArrayList<>();
        options.add("Hello");
        options.add("Hi");
        options.add("Howzit");
        options.add("This is a super long piece of text in order to try to see what the button does with it");


        JTextField textField = new JTextField(options.get(0));
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
                        System.out.println("Up");
                        textField.setText(options.get(listIndex));
                        break;
                    case KeyEvent.VK_DOWN:
                        if (listIndex < options.size() -1) {
                            listIndex ++;
                        } else {
                            listIndex = options.size() - 1;
                        }
                        System.out.println("Down");
                        textField.setText(options.get(listIndex));
                        break;
                    case KeyEvent.VK_ENTER:
                        System.out.println("Submitting action");
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