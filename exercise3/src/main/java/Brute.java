/** Created by Alex on 2015.02.27.. */
public class Brute {

  private Point[] points;
  private boolean draw;

  private Brute draw() {
    draw = true;
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    StdDraw.show(0);
    return this;
  }

  private Brute load(String filename) {
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
    Merge.sort(points);
    return this;
  }

  private void printCollinears() {
    for (int i = 0; i < points.length; i++) {
      for (int j = i + 1; j < points.length; j++) {
        for (int k = j + 1; k < points.length; k++) {
          if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k])) {
            for (int l = k + 1; l < points.length; l++) {
              if (points[i].slopeTo(points[k]) == points[i].slopeTo(points[l])) {
                print(i, j, k, l);
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

  private void print(int p1, int p2, int p3, int p4) {
    StdOut.println(points[p1] + " -> " + points[p2] + " -> " + points[p3] + " -> " + points[p4]);
    if (draw) {
      points[p1].drawTo(points[p4]);
    }
  }

  /** Run the Brute find.
   * 
   * @param args
   *          input arguments */
  public static void main(String[] args) {
    String filename = args[0];
    if (filename == null) {
      throw new IllegalArgumentException("Missing filename");
    }
    new Brute().draw().load(filename).printCollinears();
  }

}
