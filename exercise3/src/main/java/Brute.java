/**
 * Created by Alex on 2015.02.27..
 */
public class Brute {

    private Point[] points;
    private boolean draw;

    protected Brute draw() {
        draw = true;
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        return this;
    }

    protected Brute load(String filename) {
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
        Merge.sort(points);
        return this;
    }

    protected void printCollinears() {
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k])) {
                        for (int l = k + 1; l < points.length; l++) {
                            if (points[i].slopeTo(points[k]) == points[i].slopeTo(points[l])) {
                                String p = points[i].toString();
                                String q = points[j].toString();
                                String r = points[k].toString();
                                String s = points[l].toString();
                                StdOut.println(p + " -> " + q + " -> " + r + " -> " + s);
                                if (draw) {
                                    points[i].drawTo(points[l]);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (draw) {
            StdDraw.show(0);
        }
    }

    public static void main(String[] args) {
        String filename = args[0];
        if (filename == null) throw new IllegalArgumentException("Missing filename");
        new Brute().load(filename).printCollinears();
    }

}
