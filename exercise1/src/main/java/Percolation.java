public class Percolation {

  private static final byte OPEN = 1;

  private int n, top, bottom;
  private byte s[];
  private WeightedQuickUnionUF u;

  /**
   * create N-by-N grid, with all sites blocked
   * 
   * @param n
   */
  public Percolation(int n) {
    if (n < 1) {
      throw new IllegalArgumentException();
    }
    this.n = n;
    this.top = 0;
    this.bottom = n * n + 1;
    this.s = new byte[bottom - 1];
    u = new WeightedQuickUnionUF(bottom + 1);
  }

  /**
   * open site (row i, column j) if it is not open already
   * 
   * @param i
   * @param j
   */
  public void open(int i, int j) {
    checkBounds(i, j);
    int p = getN(i, j);
    s[p - 1] = OPEN;
    if (i > 1 && s[getId(i - 1, j)] == OPEN) {
      u.union(p, getN(i - 1, j));
    }
    if (i < n && s[getId(i + 1, j)] == OPEN) {
      u.union(p, getN(i + 1, j));
    }
    if (j > 1 && s[getId(i, j - 1)] == OPEN) {
      u.union(p, getN(i, j - 1));
    }
    if (j < n && s[getId(i, j + 1)] == OPEN) {
      u.union(p, getN(i, j + 1));
    }
    if (i == 1) {
      u.union(top, p);
    }
    if (i == n) {
      u.union(bottom, p);
    }
  }

  /**
   * is site (row i, column j) open?
   * 
   * @param i
   * @param j
   * @return
   */
  public boolean isOpen(int i, int j) {
    checkBounds(i, j);
    return s[getId(i, j)] == OPEN;
  }

  /**
   * is site (row i, column j) full?
   * 
   * @param i
   * @param j
   * @return
   */
  public boolean isFull(int i, int j) {
    checkBounds(i, j);
    return u.connected(getN(i, j), top);
  }

  /**
   * does the system percolate?
   * 
   * @return
   */
  public boolean percolates() {
    return u.connected(top, bottom);
  }

  // private void print() {
  // for (int i = 1; i <= n; i++) {
  // for (int j = 1; j <= n; j++) {
  // System.out.print(" " + s[getId(i, j)]);
  // }
  // System.out.println();
  // }
  // }

  private int getId(int i, int j) {
    return (i - 1) * n + (j - 1);
  }

  private int getN(int i, int j) {
    return getId(i, j) + 1;
  }

  private void checkBounds(int i, int j) {
    if (i <= 0 || i > n) {
      throw new IndexOutOfBoundsException();
    }
    if (j <= 0 || j > n) {
      throw new IndexOutOfBoundsException();
    }
  }

  public static void main(String[] args) {
  }
}