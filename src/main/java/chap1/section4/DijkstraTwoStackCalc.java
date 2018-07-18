package chap1.section4;

import chap1.section1.demo.TheLinkStack;
import lib.StdIn;

public class DijkstraTwoStackCalc {
    public static void main(String... args) {
        calc_0();
    }

    private static void calc_0() {
        TheLinkStack.StackUsingLinkNode<String> opsStack = new TheLinkStack().new StackUsingLinkNode();
        TheLinkStack.StackUsingLinkNode<Double> valsStack = new TheLinkStack().new StackUsingLinkNode();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("(")) continue;
            else if (s.equals("+")) opsStack.push("+");
            else if (s.equals("-")) opsStack.push("-");
            else if (s.equals("*")) opsStack.push("*");
            else if (s.equals("/")) opsStack.push("/");
            else if (s.equals("sqrt")) opsStack.push("sqrt");
            else if (s.equals(")")) {
                double f = valsStack.pop();
                String op = opsStack.pop();
                if (op.equals("+")) f = valsStack.pop() + f;
                else if (op.equals("-")) f = valsStack.pop() - f;
                else if (op.equals("*")) f = valsStack.pop() * f;
                else if (op.equals("/")) f = valsStack.pop() / f;
                else if (op.equals("sqrt")) f = Math.sqrt(f);
                valsStack.push(f);
            } else valsStack.push(Double.parseDouble(s));
        }
        System.out.println(valsStack.pop());
    }
}
