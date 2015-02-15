import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PercolationTest {

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void test() {
    Percolation p = new Percolation(8);
    assertFalse(p.isOpen(1, 3));
    p.open(1, 3);
    assertTrue(p.isOpen(1, 3));
    assertFalse(p.percolates());
    p.open(2, 3);
    p.open(3, 3);
    p.open(4, 3);
    p.open(5, 3);
    p.open(6, 3);
    assertFalse(p.percolates());
    p.open(7, 3);
    assertFalse(p.percolates());
    p.open(8, 3);
    // p.print();
    // System.out.println(p.percolates());
    assertTrue(p.percolates());
  }

  @Test
  public void test1() {
    Percolation p = new Percolation(1);
    p.open(1, 1);
    // p.print();
    // System.out.println(p.percolates());
    assertTrue(p.isOpen(1, 1));
    assertTrue(p.isFull(1, 1));
    assertTrue(p.percolates());
  }

  @Test
  public void test1no() {
    Percolation p = new Percolation(1);
    // p.print();
    // System.out.println(p.percolates());
    assertFalse(p.isOpen(1, 1));
    assertFalse(p.isFull(1, 1));
    assertFalse(p.percolates());
  }

  @Test
  public void test2() {
    Percolation p = new Percolation(2);
    p.open(1, 1);
    p.open(1, 2);
    p.open(2, 2);
    // p.print();
    // System.out.println(p.percolates());
    assertTrue(p.isOpen(1, 1));
    assertTrue(p.isFull(1, 1));
    assertTrue(p.isOpen(2, 2));
    assertTrue(p.isFull(2, 2));
    assertTrue(p.percolates());
  }

  @Test
  public void test2no() {
    Percolation p = new Percolation(2);
    p.open(1, 1);
    p.open(2, 2);
    // p.print();
    // System.out.println(p.percolates());
    assertTrue(p.isOpen(1, 1));
    assertTrue(p.isFull(1, 1));
    assertTrue(p.isOpen(2, 2));
    assertFalse(p.isFull(2, 2));
    assertFalse(p.percolates());
  }
}
