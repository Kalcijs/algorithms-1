public class PercolationStats {

  private int[] openSites;
  private double[] mean;
  /**
   * perform T independent experiments on an N-by-N grid
   * 
   * @param N
   * @param T
   */
  public PercolationStats(int N, int T) {
    if (N <= 0) {
      throw new IllegalArgumentException("N must be > 0");
    }
    if (T <= 0) {
      throw new IllegalArgumentException("T must be > 0");
    }
    openSites = new int[T];
    mean = new double[T];
    double n2 = N * N;
    for (int i = 0; i < T; i++) {
      Percolation percolation = new Percolation(N);
      do {
        int x = 0, y = 0;
        do {
          x = StdRandom.uniform(N) + 1;
          y = StdRandom.uniform(N) + 1;
        } while (percolation.isOpen(x, y));
        percolation.open(x, y);
        openSites[i]++;
      } while (!percolation.percolates());
      mean[i] = openSites[i] / n2;
    }

  }
  /**
   * sample mean of percolation threshold
   * 
   * @return
   */
  public double mean() {
    return StdStats.mean(mean);
  }
  /**
   * sample standard deviation of percolation threshold
   * 
   * @return
   */
  public double stddev() {
    return StdStats.stddev(mean);
  }
  /**
   * low endpoint of 95% confidence interval
   * 
   * @return
   */
  public double confidenceLo() {
    return mean() - (1.96 * stddev() / Math.sqrt(openSites.length));
  }
  /**
   * high endpoint of 95% confidence interval
   * 
   * @return
   */
  public double confidenceHi() {
    return mean() + (1.96 * stddev() / Math.sqrt(openSites.length));
  }

  public static void main(String[] args) {
    int N = Integer.parseInt(args[0]);
    int T = Integer.parseInt(args[1]);
    PercolationStats stats = new PercolationStats(N, T);
    StdOut.println("mean                    = " + stats.mean());
    StdOut.println("stddev                  = " + stats.stddev());
    StdOut.println("95% confidence interval = " + stats.confidenceLo() + ", "
        + stats.confidenceHi());

  }
}