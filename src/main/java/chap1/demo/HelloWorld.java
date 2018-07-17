package chap1.demo;

public class HelloWorld {
    public static void main(String... args) {
        System.out.println(foo("hello"));
        System.out.println(bar("world"));
    }
    public static String foo (String someStr)
    {
        String newStr = someStr + 3 + "]";
        return newStr;
    }

    public static String bar (String someStr)
    {
        String newStr = someStr + "3" + "]";
        return newStr;
    }
}
