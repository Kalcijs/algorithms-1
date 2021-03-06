/*************************************************************************
 * Name:
 * Email:
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 * Description: An immutable data type for points in the plane.
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

  // compare points by slope
  public final Comparator<Point> SLOPE_ORDER = new SlopeOrderComparator();

  private final int x; // x coordinate
  private final int y; // y coordinate

  /** create the point (x, y).
   * 
   * @param x
   *          coordinate
   * @param y
   *          coordinate */
  public Point(int x, int y) {
    /* DO NOT MODIFY */
    this.x = x;
    this.y = y;
  }

  // plot this point to standard drawing
  public void draw() {
    /* DO NOT MODIFY */
    StdDraw.point(x, y);
  }

  // draw line between this point and that point to standard drawing
  public void drawTo(Point that) {
    /* DO NOT MODIFY */
    StdDraw.line(this.x, this.y, that.x, that.y);
  }

  /** slope between this point and that point.
   * 
   * @param that
   *          Point
   * @return slope value */
  public double slopeTo(Point that) {
    if (that == null) {
      throw new NullPointerException();
    }
    if (y == that.y) {
      // horizontal line - positive zero
      return +0.0;
    } else if (x == that.x) {
      // vertical line
      if (y < that.y) {
        // positive infinity
        return Double.POSITIVE_INFINITY;
      } else {
        // negative infinity
        return Double.NEGATIVE_INFINITY;
      }
    } else {
      // (y1 − y0) / (x1 − x0)
      return ((double) that.y - y) / ((double) that.x - x);
    }
  }

  /** Is this point lexicographically smaller than that one?.
   * comparing y-coordinates and breakingties by x-coordinates */
  public int compareTo(Point that) {
    if (that == null) {
      throw new NullPointerException();
    }
    if (y < that.y) {
      return -1;
    } else if (y == that.y) {
      if (x < that.x) {
        return -1;
      } else if (x == that.x) {
        return 0;
      } else {
        return 1;
      }
    } else {
      return 1;
    }
  }

  // return string representation of this point
  public String toString() {
    /* DO NOT MODIFY */
    return "(" + x + ", " + y + ")";
  }

  private class SlopeOrderComparator implements Comparator<Point> {
    @Override
    public int compare(Point point1, Point point2) {
      double comp = slopeTo(point1) - slopeTo(point2);
      if (comp > 0) {
        return 1;
      } else if (comp < 0) {
        return -1;
      } else {
        return 0;
      }
    }
  }

  // unit test
  public static void main(String[] args) {
    /* YOUR CODE HERE */
  }
}