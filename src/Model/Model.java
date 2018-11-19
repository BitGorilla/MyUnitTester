package Model;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Runs classes for testing test classes. Interprets test results.
 *
 * @author Martin Sjölund
 * @version 1
 * @since 2018-11-14
 */
public class Model {

    private String newline = System.getProperty("line.separator");
    private StringBuilder testResultString = new StringBuilder();

    /**
     * Constructor of class.
     *
     * @param className Name of class to find.
     */
    public Model(String className){
        ClassFinder clFinder = new ClassFinder(className);
        Class<?> cl = clFinder.returnClass();

        if (cl != null) { /*If class exists*/

            if(isInstance(cl)) { /*If Class is instance of TestClass.java*/

                MethodFinder finder = new MethodFinder(cl);
                MethodRunner runner = new MethodRunner(finder.getMethodList(), cl);

                if(runner.getResults() != null) {
                    createTestResultString(runner.getResults());
                }else{
                    writeErrorMsg("No test methods in class");
                }
            }else{
                writeErrorMsg("Class is not instance of TestClass.java");
            }
        }else{
            writeErrorMsg("Class not found");
        }
    }

    /**
     * Checks if aClass is an instance of TestClass.java.
     *
     * @return true if it is, false if not
     */
    private boolean isInstance(Class<?> aClass){
        Object obj;

        try{
            obj = aClass.getConstructor().newInstance();

            return (obj instanceof se.umu.cs.unittest.TestClass);

        }catch (NoSuchMethodException | InstantiationException |
                IllegalAccessException e){
            writeErrorMsg("Cannot create instance of Class");
        }
        catch (InvocationTargetException e){
            writeErrorMsg("Target class throws "+e.getCause().toString());
        }
        return false;
    }

    /**
     * Writes results from class test to testResultString.
     *
     * @param result contains ArrayLists' of results from class test.
     */
    private void createTestResultString(ArrayList<ArrayList> result){
        int successes = 0;
        int fails = 0;
        int exceptions = 0;

        for (int i = 0; i < result.size(); i++){
            String s;

            if (result.get(i).get(1).equals("true")) {
                successes++;
                s = "SUCCESS";

            } else if (result.get(i).size() == 3){
                fails++;
                s = "FAIL Generated a "+" "+result.get(i).get(2);

            } else {
                fails++;
                exceptions++;
                s = "FAIL";
            }
            testResultString.append(result.get(i).get(0) + ": "+s+newline);
        }

        addSummary(successes, fails, exceptions);
    }

    /**
     * Adds a summary of the test results to testResultString.
     *
     * @param successes number of successful tests.
     * @param fails number of failed tests.
     * @param exceptions number of tests that threw an exception.
     */
    private void addSummary(int successes, int fails, int exceptions) {
        testResultString.append(newline);
        testResultString.append(String.valueOf(successes)+" test succeded"
                                +newline);
        testResultString.append(String.valueOf(fails)+" test failed"+newline);
        testResultString.append(String.valueOf(exceptions)+" test failed " +
                                "because of an exception"+newline);
    }

    /**
     * Writes error message defined by msg.
     *
     * @param msg Error message.
     */
    private void writeErrorMsg(String msg){
        testResultString.append(msg+newline);
    }

    /**
     *
     * @return test results as a String.
     */
    public String getTestResults(){
        testResultString.append("////////////////////////////////////"
                +newline);
        return testResultString.toString();
    }
}
