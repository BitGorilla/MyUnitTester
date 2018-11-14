package Controller;

import Model.ClassFinder;
import Model.MethodFinder;
import Model.MethodRunner;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Action listener for JButton from test GUI. Manages classes for testing of
 * test class.
 * Writes to GUI.
 *
 * @author Martin Sjölund
 * @version 1
 * @since 2018-11-14
 */
public class ButtonController implements ActionListener {

    private String newline = System.getProperty("line.separator");
    private JTextField textField;
    private JTextArea textArea;

    /**
     * Constructor of class.
     *
     * @param textField reference to JTextField which should contain name
     *                  of a class.
     * @param textArea reference to JTextArea to which output will be written.
     */
    public ButtonController(JTextField textField, JTextArea textArea){
        this.textField = textField;
        this.textArea = textArea;
    }


    /**
     * Creates classes for testing of test class.
     *
     * @param e An ActionEvent.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        ClassFinder clFinder = new ClassFinder(textField.getText());
        Class<?> cl = clFinder.returnClass();

        if(cl != null) {
            MethodFinder finder = new MethodFinder(cl);
            MethodRunner runner = new MethodRunner(finder.getMethodList(), cl);
            writeToTextArea(runner.getResults());
        }else{
            textArea.append("////////////////////////////////////"+newline);
            writeErrorMsg("Class not found or does not implement TestClass" +
                          ".java");
        }

    }

    /**
     * Writes results from class test to textArea.
     *
     * @param result contains ArrayLists' of results from class test.
     */
    private void writeToTextArea(ArrayList<ArrayList> result){

        int successes = 0;
        int fails = 0;
        int exceptions = 0;

        textArea.append("////////////////////////////////////"+newline);
        for (int i = 0; i < result.size(); i++){
            String s;

            if (result.get(i).get(1).equals("true")) {
                successes++;
                s = "SUCCESS";
            }
            else if(result.get(i).size() == 3){
                fails++;
                s = "FAIL Generated a "+" "+result.get(i).get(2);
            }
            else{
                fails++;
                exceptions++;
                s = "FAIL";
            }

            textArea.append(result.get(i).get(0) + ": "+s+newline);

        }

        textArea.append(newline);
        textArea.append(String.valueOf(successes)+" test succeded"+newline);
        textArea.append(String.valueOf(fails)+" test failed"+newline);
        textArea.append(String.valueOf(exceptions)+" test failed because of " +
                                        "an exception"+newline);

    }

    /**
     * Writes error message defined by msg.
     *
     * @param msg Error message.
     */
    private void writeErrorMsg(String msg){
        textArea.append(msg+newline);
    }
}
