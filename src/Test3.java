import se.umu.cs.unittest.TestClass;

public class Test3 implements TestClass {

    private MyString myString;

    public Test3(){
    }

    public void setUp(){ myString = new MyString("Hello world!"); }

    public void tearDown(){ myString=null; }

    //test should succeed
    public boolean testInitalization(){ return myString.getString().equals(
                                        "Hello world!");}

    //test should succeed
    public boolean testEmptyString(){
        myString.emptyString();
        return myString.getString().equals("");
    }

    //test should fail
    public boolean testFailByException(){
        myString=null;
        myString.setString("Goodbye!");
        return true;
    }

    //test should fail
    public boolean testFailing(){
        return myString.getString().equals("Goodbye");
    }


}
