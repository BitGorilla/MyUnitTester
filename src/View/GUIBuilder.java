package View;

import Controller.ButtonController;
import Controller.ClearController;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Builds GUI for testing classes that implements TestClass.
 *
 * @author Martin Sjölund
 * @version 1
 * @since 2018-11-14
 */
public class GUIBuilder {

    private JFrame frame;
    private JButton runBtn;
    private JButton clearBtn;
    private JTextField textField;
    private JTextArea textArea;

    /**
     * Constructor of class.
     * Creates a JFrame and adds JPanels' to it.
     *
     * @param title Sets the title of a JFrame.
     */
    public GUIBuilder(String title){

        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,500);
        frame.setResizable(false);

        JPanel middlePanel = buildMiddlePanel();
        JPanel bottomPanel = buildBottomPanel();
        JPanel topPanel = buildTopPanel();
        frame.getRootPane().setDefaultButton(runBtn);

        frame.getContentPane().add(BorderLayout.NORTH, topPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);
        frame.getContentPane().add(BorderLayout.CENTER, middlePanel);

    }

    /**
     * Creates a JPanel to be positioned at the top of the JFrame.
     * Adds a describing JLabel, a JTextField and a JButton to run program.
     * The JButton uses ButtonController as action listener.
     *
     * @return A JPanel containing a JLabel, JTextField and JTextArea.
     */
    private JPanel buildTopPanel(){
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JPanel labelPanel = new JPanel();
        JPanel runPanel = new JPanel();
        runPanel.setLayout(new FlowLayout());

        Border padding = BorderFactory.createEmptyBorder(10, 10,
                                                 10, 10);
        topPanel.setBorder(padding);

        JLabel label = new JLabel("What class do you want to test?");
        textField = new JTextField(25);
        runBtn = new JButton("Run tests");
        runBtn.addActionListener(new ButtonController(textField, textArea));
        labelPanel.add(label);
        runPanel.add(textField);
        runPanel.add(runBtn);

        topPanel.add(labelPanel);
        topPanel.add(runPanel);

        return topPanel;
    }

    /**
     * Builds a JPanel to be positioned at the bottom of the JFrame.
     * Adds a JButton used for clearing the JTextArea of the GUI.
     * The JButton uses ClearController as action listener.
     *
     * @return A JPanel containing a JButton.
     */
    private JPanel buildBottomPanel(){

        JPanel bottomPanel = new JPanel();

        clearBtn = new JButton("Clear");
        clearBtn.addActionListener(new ClearController(textArea));
        bottomPanel.add(clearBtn);



        return bottomPanel;
    }

    /**
     * Builds a JPanel to be positioned at the center of the JFrame.
     * Adds a JTextArea for output text.
     *
     * @return A JPanel containing a JTextArea.
     */
    private JPanel buildMiddlePanel(){

        JPanel middlePanel = new JPanel();
        textArea = new JTextArea(20,50);
        textArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(textArea);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.
                                            HORIZONTAL_SCROLLBAR_NEVER);
        middlePanel.add(scroll, BorderLayout.CENTER);

        return middlePanel;
    }

    /**
     * Sets JFrame to visable.
     */
    public void show(){
        frame.setVisible(true);
    }
}
