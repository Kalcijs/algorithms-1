import java.util.Arrays;

/** Created by Alex on 2015.02.27.. */
public class Fast {

  private Point[] points;
  private boolean draw;

  private Fast draw() {
    draw = true;
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    StdDraw.show(0);
    return this;
  }

  private Fast load(String filename) {
    In in = new In(filename);
    points = new Point[in.readInt()];
    if (draw) {
      StdDraw.setPenRadius(0.01);
    }
    int index = 0;
    while (!in.isEmpty()) {
      int x0 = in.readInt();
      int y0 = in.readInt();
      points[index] = new Point(x0, y0);
      if (draw) {
        points[index].draw();
      }
      index++;
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

  private void printCollinears() {
    for (int i = 0; i < points.length - 2; i++) {
      Point p0 = points[i];
      Point[] points2 = Arrays.copyOfRange(points, i + 1, points.length);
      Arrays.sort(points2, p0.SLOPE_ORDER);
      int num = 0;
      for (int j = 1; j < points2.length; j++) {
        if (p0.slopeTo(points2[j - 1]) == p0.slopeTo(points2[j])) {
          num++;
          if (j + 1 == points2.length && num > 1) {
            print(p0, points2, j - num, num);
          }
        } else {
          if (num > 1) {
            print(p0, points2, j - num - 1, num);
            num = 0;
          }
        }
      }
    }
    if (draw) {
      StdDraw.show(0);
    }
  }

  private void print(Point p0, Point[] points, int index, int count) {
    StdOut.print(p0);
    for (int i = 0; i <= count; i++) {
      StdOut.print(" -> " + points[index + i]);
    }
    if (draw) {
      p0.drawTo(points[index + count]);
    }
    StdOut.println();
  }

  /** Run fast find.
   * 
   * @param args
   *          input arguments */
  public static void main(String[] args) {
    String filename = args[0];
    if (filename == null) {
      throw new IllegalArgumentException("Missing filename");
    }
    new Fast().draw().load(filename).printCollinears();
  }
}
