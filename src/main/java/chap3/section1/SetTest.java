package chap3.section1;

public class SetTest {

    public static void main(String... args) {
        OrderedLinkSet<String, Integer> linkset = new OrderedLinkSet<>();
        String s = "SEARCHEXAMPLE";
        for (int i = 0; i < s.length(); ++i) {
            linkset.put(String.valueOf(s.charAt(i)), i);
        }
        for (String s1 : linkset.keys()) {
            System.out.println(s1 + ": " + linkset.get(s1));
        }
    }
}
