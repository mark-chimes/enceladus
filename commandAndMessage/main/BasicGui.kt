package commandAndMessage.main

import javax.swing.*

import java.awt.*
import java.awt.event.KeyListener
import java.util.ArrayList

class BasicGui : JFrame() {
    private val commands: List<String>
    private val label: JLabel
    private val label2: JLabel

    init {
        commands = ArrayList()
        setupBasicLayout()
        label = JLabel()
        label2 = JLabel()

        label.text = "MyText"
        label2.text = "MyText"

        add(label)
        add(label2)

        label2.foreground = this.background

        label.grabFocus()
    }

    override fun setVisible(b: Boolean) {
        super.setVisible(b)
        label.grabFocus()
    }

    fun addListener(keyListener: KeyListener) {
        this.addKeyListener(keyListener)
        label.addKeyListener(keyListener)
        label2.addKeyListener(keyListener)
        label.grabFocus()
    }

    fun setText(text: String) {
        label.text = text
        label2.text = text
        if (label.hasFocus()) {
            label2.grabFocus()
        } else if (label2.hasFocus()) {
            label.grabFocus()
        } else {
            label.grabFocus()
        }
    }

    private fun setupBasicLayout() {
        title = "Enceladus"
        setSize(600, 400)
        setLocationRelativeTo(null)
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        layout = GridLayout(0, 1)
    }
}