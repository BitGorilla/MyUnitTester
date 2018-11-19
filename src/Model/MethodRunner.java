package Model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Runs test methods and creates an ArrayList of test results.
 *
 * @author Martin Sjölund
 * @version 2
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
            Method setUp = getSetUpMethod(obj);
            Method tearDown = getTearDownMethod(obj);

            for (int i = 0; i < methodList.size(); i++){
                ArrayList<String> result = new ArrayList<>();
                Method me = methodList.get(i);

                result.add(me.getName()); /*Name of Method*/

                if (setUp != null) { /*If setup method exist*/
                    setUp.invoke(obj);
                }

                try {
                    result.add(me.invoke(obj).toString());
                    testResults.add(result);

                }catch (InvocationTargetException e){
                    result.add("false");
                    result.add(e.getCause().toString());
                    testResults.add(result);
                }

                if(tearDown != null) { /*if tearDown method exists*/
                    tearDown.invoke(obj);
                }
            }

        }catch (NoSuchMethodException | IllegalAccessException |
                InstantiationException | InvocationTargetException e) {
            e.getStackTrace();
        }
    }

    /**
     * Returns setUp method from an object of it exists.
     *
     * @param obj Object to search.
     * @return setUp method. Null if it does not exist.
     */
    private Method getSetUpMethod(Object obj){

        try{
            return obj.getClass().getMethod("setUp");

        }catch (NoSuchMethodException e){
            return null;
        }
    }

    /**
     * Returns tearDown method from an object of it exists.
     *
     * @param obj Object to search.
     * @return tearDown method. Null if it does not exist.
     */
    private Method getTearDownMethod(Object obj){

        try{
            return obj.getClass().getMethod("tearDown");

        }catch (NoSuchMethodException e){
            return null;
        }
    }

    /**
     * @return An ArrayList of test results.
     */
    public ArrayList getResults(){

        if (testResults.size() != 0){
            return testResults;
        }
        return null;
    }
}
