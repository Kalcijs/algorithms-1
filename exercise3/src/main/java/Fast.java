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
        //StdOut.print("\t");print(points);
        for (int i = 0; i < points.length-2; i++) {
            Point p = points[i];
            Point[] points2 = Arrays.copyOfRange(points, i+1, points.length);
            Arrays.sort(points2, p.SLOPE_ORDER);
            //StdOut.print("\t");print(points2);
            int num = 0;
            for (int j = 1; j < points2.length; j++) {
                if (p.slopeTo(points2[j-1]) == p.slopeTo(points2[j])) {
                    num++;
                    if (j+1 == points2.length && num > 1) {
                        print(p, points2, j-num, num);
                    }
                } else {
                    if (num > 1) {
                        print(p, points2, j-num-1, num);
                        num = 0;
                    }
                }
            }
        }
        if (draw) {
            StdDraw.show(0);
        }
    }

    private void print(Point[] p) {
        for (int i = 0; i < p.length; i++) {
            StdOut.print(p[i] + "\t");
        }
        StdOut.println();
    }

    private void print(Point p, Point[] points, int index, int count ) {
        StdOut.print(p);
        for (int i = 0; i <= count; i++) {
            StdOut.print(" -> " + points[index+i]);
        }
        if (draw) p.drawTo(points[index+count]);
        StdOut.println();
    }

    public static void main(String[] args) {
        String filename = args[0];
        if (filename == null) throw new IllegalArgumentException("Missing filename");
        new Fast().load(filename).printCollinears();
    }
}
