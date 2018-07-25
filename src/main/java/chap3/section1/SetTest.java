package chap3.section1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SetTest {

    public static void main(String... args) {
        LinkSet<String, Integer> linkset = new LinkSet<>();
        String s = "SEARCHEXAMPLE";
        for (int i = 0; i < s.length(); ++i) {
            linkset.put(String.valueOf(s.charAt(i)), i);
        }
        Iterator<String> iterator = linkset.keys();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
//        for (String ss: linkset.keys()) {
//
//        }
//        List<String> list = new ArrayList<>();
//        for (String s1 : list) {
//
//        }
    }
}
