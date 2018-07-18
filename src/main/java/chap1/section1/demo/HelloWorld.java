package chap1.section1.demo;

import com.sun.org.apache.xml.internal.resolver.readers.ExtendedXMLCatalogReader;

public class HelloWorld {
    public static void main(String... args) {
//        System.out.println(foo("hello"));
//        System.out.println(bar("world"));
        System.out.println(testEx());
    }

    private static int testEx() {
        try {
            throwEx();
        } catch (Exception e) {
            throw e;
        }
        return 0;
    }

    private static void throwEx() {
        throw new RuntimeException("I am testing");
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
