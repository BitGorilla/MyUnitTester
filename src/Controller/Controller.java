package Controller;

import Model.Model;
import View.GUIBuilder;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Link between Model and View. Handles communication between the classes.
 *
 * @author Martin Sjölund
 * @version 1
 * @since 2018-11-14
 */
public class Controller {
    private GUIBuilder view;
    private Model model;
    private String className;
    private ActionListener buttonListener;

    /**
     * Constructor for the class.
     */
    public Controller(){
        buildView();
    }

    /**
     * Builds GUI.
     */
    private void buildView(){

        createActionListener();

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                view =new GUIBuilder("MyUnitTester", buttonListener);
                view.show();
            }});
    }

    /**
     * Runs the test program by initializing the Model class.
     */
    private void runProgram(){
        model = new Model(className);
        view.writeToTextArea(model.getTestResults());
    }

    /**
     * Creates an actionListener for the views buttons.
     */
    private void createActionListener(){
         buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("clear")){
                    view.clearTextArea();

                } else {
                    className = view.getClassName();
                    runProgram();
                }
            }
        };
    }
}
