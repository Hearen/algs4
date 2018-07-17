package chap1.demo;

import lib.Counter;
import lib.Interval1D;
import lib.Interval2D;
import lib.Point2D;
import lib.StdRandom;

public class AreaCountGraph {

    public static void main(String... args) {
        double xl = .2;
        double xh = .5;
        double yl = .5;
        double yh = .6;
        Interval1D xInteval1D = new Interval1D(xl, xh);
        Interval1D yInterval1D = new Interval1D(yl, yh);
        Interval2D box = new Interval2D(xInteval1D, yInterval1D);
        box.draw();
        final int N = 100_000;
        Counter c = new Counter("hits");
        for (int i = 0; i < N; ++i) {
            double x = StdRandom.uniform();
            double y = StdRandom.uniform();
            Point2D point = new Point2D(x, y);
            if (box.contains(point)) c.increment();
            else point.draw();
        }
        System.out.println(c.tally());
        System.out.println(box.area());
    }
}
