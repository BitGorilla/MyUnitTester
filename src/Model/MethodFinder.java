package Model;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Finds Methods beginning with the word "test". Returns them as an ArrayList
 * of methods.
 *
 * @author Martin Sjölund
 * @version 1
 * @since 2018-11-14
 */
public class MethodFinder {

    private Class<?> myClass;
    private ArrayList<Method> methodList;

    /**
     * Constructor of class.
     *
     * @param aClass
     */
    public MethodFinder(Class aClass) {

        myClass = aClass;

        if (myClass != null) {
            methodList = findTestMethods(myClass.getMethods());
        }
        else{
            System.out.println("no class");
        }
    }

    /**
     * Searches class for methods beginning with "test".
     *
     * @param methods Array with all the methods in a class.
     * @return An ArrayList of methods beginning with "test".
     */
    private ArrayList findTestMethods(Method[] methods){
        methodList = new ArrayList<Method>();

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
     *
     * @return The ArrayList of methods beginning with "test".
     */
    public ArrayList getMethodList(){
        return methodList;
    }
}
