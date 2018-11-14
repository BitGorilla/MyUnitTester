package Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener responsible for clearing textArea of text.
 *
 * @author Martin Sjölund
 * @version 1
 * @since 2018-11-14
 */
public class ClearController implements ActionListener {

    private JTextArea textArea;

    /**
     * Constructor of class.
     *
     * @param textArea A reference to a JTextArea.
     */
    public ClearController(JTextArea textArea){
        this.textArea = textArea;
    }

    /**
     * Clears textArea of text.
     *
     * @param e An ActionEvent.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        textArea.setText("");
    }
}
