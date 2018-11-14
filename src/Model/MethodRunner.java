package Model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Runs test methods and creates an ArrayList of test results.
 *
 * @author Martin Sjölund
 * @version 1
 * @since 2018-11-14
 */
public class MethodRunner {

    private ArrayList<Method> methodList;
    private ArrayList<ArrayList> testResults;
    private Class<?> aClass;

    /**
     * Constructor of class.
     *
     * @param methods List of methods to run.
     * @param aClass The class of which the methods originates.
     */
    public MethodRunner(ArrayList<Method> methods, Class aClass){
        methodList = methods;
        this.aClass = aClass;
        runTests();
    }

    /**
     * Runs test methods and saves the results in the ArrayList testResult.
     */
    private void runTests(){
        testResults = new ArrayList<>();

        try {

            Object obj = aClass.getConstructor().newInstance();
            Method setUp;
            Method tearDown;

            try{
                setUp = obj.getClass().getMethod("setUp");

            }catch (NoSuchMethodException e){
                setUp = null;
            }

            try{
                tearDown = obj.getClass().getMethod("tearDown");

            }catch (NoSuchMethodException e){
                tearDown = null;
            }

            for (int i = 0; i < methodList.size(); i++){
                ArrayList<String> result = new ArrayList<>();
                Method me = methodList.get(i);

                //If setUp method exists
                if (setUp != null) {
                    setUp.invoke(obj);
                }

                try {

                    result.add(me.getName());
                    result.add(me.invoke(obj).toString());
                    testResults.add(result);

                }catch (InvocationTargetException e){
                    result.add("false");
                    result.add(e.getCause().toString());
                    testResults.add(result);
                }

                //if tearDown method exists
                if(tearDown != null) {
                    tearDown.invoke(obj);
                }
            }

        }catch (NoSuchMethodException e){
            e.getStackTrace();
        }
        catch (IllegalAccessException e){
            e.getStackTrace();
        }
        catch (InstantiationException e){
            e.getStackTrace();
        }
        catch (InvocationTargetException e){
            e.getStackTrace();
        }
    }

    /**
     *
     * @return An ArrayList of test results.
     */
    public ArrayList getResults(){

        return testResults;
    }
}
