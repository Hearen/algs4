package chap1.section1.demo;

public class QA_1 {
    public static void main(String... args) {
        // https://stackoverflow.com/questions/39076639/what-happens-to-a-declared-uninitialized-variable-in-java
        System.out.println(Math.abs(Integer.MIN_VALUE));
        int a = 0;
        System.out.println(a);
        // https://stackoverflow.com/questions/46886372/difference-between-1-0-and-1-0-0-0-in-java?rq=1
//        System.out.println(1 / 0);
        System.out.println(1 / 0.0);

        // (a / b) * b + a % b = a;
        //
        System.out.println(-14 % 3); // -2;
        System.out.println(14 % -3); // 2;
        System.out.println(Math.floorMod(-14, 3)); // 1;
        System.out.println(Math.floorMod(14, -3)); // -1;
    }
}
