import java.util.Arrays;

/**
 * Created by Alex on 2015.02.27..
 */
public class Fast {

    private Point[] points;
    private boolean draw;

    protected Fast draw() {
        draw = true;
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        return this;
    }

    protected Fast load(String filename) {
        In in = new In(filename);
        points = new Point[in.readInt()];
        if (draw) {
            StdDraw.setPenRadius(0.01);
        }
        int i = 0;
        while (!in.isEmpty()) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
            if (draw) points[i].draw();
            i++;
        }
        if (draw) {
            // display to screen all at once
            StdDraw.show(0);
            // reset the pen radius
            StdDraw.setPenRadius();
        }
        Arrays.sort(points);
        return this;
    }

    protected void printCollinears() {
        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
            //StdOut.print(String.format("%15s", p));
            Point[] points2 = Arrays.copyOfRange(points, i+1, points.length);
            Arrays.sort(points2, p.SLOPE_ORDER);
            //StdOut.print("\t[");
            boolean[] match = new boolean[points2.length];
            double slopePrevious = Double.MIN_VALUE;
            boolean found = false;
            for (int j = 0; j < points2.length; j++) {
                double slope = p.slopeTo(points2[j]);
                if (slopePrevious == slope) {
                    match[j] = true;
                    if (!found) {
                        match[j-1] = true;
                        found = true;
                    }
                } else {
                    slopePrevious = slope;
                    if (found) break;
                }
                //StdOut.print(String.format("%.3f", p.slopeTo(points2[j])));
                //StdOut.print(String.format("%16s", points2[j]));
                //StdOut.print("    ");
            }
            if (found) {
                StdOut.print(p);
                for (int j = 0; j < points2.length; j++) {
                    if (match[j]) {
                        StdOut.print(" -> " + points2[j]);
                        if (draw) p.drawTo(points2[j]);
                    }
                }
                StdOut.println();
            }
            //StdOut.println("]");
        }
        if (draw) {
            StdDraw.show(0);
        }
    }

    public static void main(String[] args) {
        String filename = args[0];
        if (filename == null) throw new IllegalArgumentException("Missing filename");
        new Fast().load(filename).printCollinears();
    }
}
