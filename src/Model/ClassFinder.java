package Model;

/**
 * Finds class and checks if it is an instance of TestClass.java.
 *
 * @author Martin Sjölund
 * @version 2
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
     *
     * @return Class. Null if class does not exist of is not instance of
     * TestClass.java.
     */
    public Class returnClass(){
        if(aClass != null){
            return aClass;
        }
        else{
            return null;
        }
    }


}
