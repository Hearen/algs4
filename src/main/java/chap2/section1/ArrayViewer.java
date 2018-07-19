package chap2.section1;

import java.awt.*;

import lib.StdDraw;

public class ArrayViewer {
    public static void drawRealtimeArray(Comparable[] arr, int max, int l, int r, Color theColor) {
        for (int i = 0; i < arr.length; ++i) {
            double x = i * 1.0 / arr.length;
            double y = (Integer) arr[i] / 2.0 / max;
            double rw = 0.4 / arr.length;
            double rh = (Integer) arr[i] / 2.0 / max;
            StdDraw.setPenColor(Color.GRAY);
            if (i >= l && i <= r) StdDraw.setPenColor(theColor);
            StdDraw.filledRectangle(x, y, rw, rh);
        }
    }
}
