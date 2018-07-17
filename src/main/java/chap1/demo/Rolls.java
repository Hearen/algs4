package chap1.demo;

import lib.StdRandom;

public class Rolls {

    public static void main(String... args) {
        testCoinFlip();
    }

    private static void testRolls() {
        final int N = 10_000;
        final int SIDES = 6;
        Counter[] rolls = new Counter[SIDES];
        for (int i = 0; i < SIDES; ++i) {
            // https://stackoverflow.com/questions/70324/java-inner-class-and-static-nested-class#answer-34551507
            rolls[i] = new Rolls().new Counter((i + 1) + "'s");
        }

        for (int i = 0; i < N; ++i) {
            rolls[StdRandom.uniform(SIDES)].inc();
        }

        for (Counter roll : rolls) {
            System.out.println(roll);
        }
    }

    private static void testCoinFlip() {
        final int N = 10_000;
        Counter heads = new Rolls().new Counter("heads");
        Counter tails = new Rolls().new Counter("tails");
        for (int i = 0; i < N; ++i) {
            if (StdRandom.bernoulli(0.5)) heads.inc();
            else tails.inc();
        }
        if (heads.tally() == tails.tally())
            System.out.println("Tie");
        else
            System.out.println(max(heads, tails) + " wins!");
    }

    private static Counter max(Counter a, Counter b) {
        return a.tally() > b.tally() ? a : b;
    }

    class Counter {
        String id;
        int count;

        public Counter(String theId) {
            this.id = theId;
            this.count = 0;
        }

        public void inc() {
            count++;
        }

        public int tally() {
            return count;
        }

        @Override
        public String toString() {
            return String.format("{id: %s, count: %d}", id, count);
        }
    }
}
