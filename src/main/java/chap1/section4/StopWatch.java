package chap1.section4;

public class StopWatch {
    private long start;
    public StopWatch() {
        start = System.nanoTime();
    }

    public long elapsedMillis() {
        return (System.nanoTime() - start) / 1_000_000;
    }

    @Override
    public String toString() {
        return String.format("time cost: %d ms", elapsedMillis());
    }
}
