import View.GUIBuilder;
import javax.swing.*;

public class MyUnitTester {

    public static void main(String args[]){

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                GUIBuilder gui=new GUIBuilder("MyUnitTester");
                gui.show();
            }});
    }
}
