public class MyString {

    String str = new String();

    public MyString(String s){
        str = s;
    }

    public String getString(){
        return str;
    }

    public void emptyString(){
        str = "";
    }

    public void setString(String s){
        str = s;
    }
}
