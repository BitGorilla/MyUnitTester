import se.umu.cs.unittest.TestClass;

public class Test4 implements TestClass {

    private MyString myString;

    public Test4(){
        myString = new MyString("Hello world!");
    }

    //test should succeed
    public boolean testInitalization(){
        myString = new MyString("Hello world!");
        return myString.getString().equals("Hello world!");
    }


    //test should succeed
    public boolean testEmptyString(){
        myString = new MyString("Hello world!");
        myString.emptyString();
        return myString.getString().equals("");
    }

    //test should fail
    public boolean testFailByException(){
        myString = new MyString("Hello world!");
        myString=null;
        myString.setString("Goodbye!");
        return true;
    }

    //test should fail
    public boolean testFailing(){
        myString = new MyString("Hello world!");
        return myString.getString().equals("Goodbye");
    }


}
