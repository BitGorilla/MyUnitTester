package Model;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Finds Methods beginning with the word "test". Returns them as an ArrayList
 * of methods.
 *
 * @author Martin Sjölund
 * @version 2
 * @since 2018-11-14
 */
public class MethodFinder {

    private ArrayList<Method> methodList;

    /**
     * Constructor of class.
     *
     * @param aClass class to search methods in.
     */
    public MethodFinder(Class aClass) {

        if (aClass != null) {
            methodList = findTestMethods(aClass.getMethods());
        }
        else{
            System.out.println("No class");
        }
    }

    /**
     * Searches class for methods beginning with "test".
     *
     * @param methods Array with all the methods in a class.
     * @return An ArrayList of methods beginning with "test".
     */
    private ArrayList findTestMethods(Method[] methods){
        methodList = new ArrayList<>();

        if(methods.length != 0) {
            for (int i = 0; i < methods.length; i++) {
                if (methods[i].getName().startsWith("test")){
                    methodList.add(methods[i]);
                }
            }
        }
        return methodList;
    }

    /**
     * @return The ArrayList of methods beginning with "test".
     */
    public ArrayList getMethodList(){
        return methodList;
    }
}
