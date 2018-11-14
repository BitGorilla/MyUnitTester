package Model;
import java.lang.reflect.InvocationTargetException;

/**
 * Finds class and checks if it is an instance of TestClass.java.
 *
 * @author Martin Sjölund
 * @version 1
 * @since 2018-11-14
 */
public class ClassFinder {

    private Class<?> aClass;

    /**
     * Constructor of class.
     *
     * @param className Name of class to find
     */
    public ClassFinder(String className){

        aClass = getClass(className);
    }

    /**
     * Fetches class by Java reflection.
     *
     * @param name Name of class to find.
     * @return Class by the name of the method parameter. Null if not found.
     */
    private Class<?> getClass(String name){
        try {

            return Class.forName(name);

        }
        catch(ClassNotFoundException | NoClassDefFoundError e) {
            return null;
        }
    }

    /**
     * Checks if aClass is an instance of TestClass.java.
     *
     * @return true if it is, false if not
     */
    private boolean isInstance(){
        Object obj;

        try{
            obj = aClass.getConstructor().newInstance();

            if (obj instanceof se.umu.cs.unittest.TestClass){
                return true;
            }
            else{
                return false;
            }

        }catch (NoSuchMethodException e){
            System.out.println("no constructor");
        }
        catch (InstantiationException e){
            e.printStackTrace();
        }
        catch (IllegalAccessException e){
            e.printStackTrace();
        }
        catch (InvocationTargetException e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * @return Class. Null if class does not exist of is not instance of
     * TestClass.java.
     */
    public Class returnClass(){
        if(aClass != null && isInstance()){
            return aClass;
        }
        else{
            return null;
        }
    }
}
