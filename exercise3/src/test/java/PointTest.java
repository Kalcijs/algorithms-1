import org.junit.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class PointTest {

    private static final double DELTA = 1e-15;

    /**
     * Testing p1.slopeTo(p2) (y1 − y0) / (x1 − x0)
     * @throws Exception
     */
    @Test
    public void testSlopeTo() throws Exception {
        /* positive infinite slope, where p and q have coordinates in [0, 500) */
        assertEquals(Double.POSITIVE_INFINITY, new Point(203, 493).slopeTo(new Point(203, 443)), DELTA);
        /* negative infinite slope, where p and q have coordinates in [0, 500) */
        assertEquals(Double.NEGATIVE_INFINITY, new Point(203, 333).slopeTo(new Point(203, 443)), DELTA);
        /* positive infinite slope, where p and q have coordinates in [0, 32768) */
        assertEquals(Double.POSITIVE_INFINITY, new Point(29265, 14284).slopeTo(new Point(29265, 2939)), DELTA);
        /* positive infinite slope, where p and q have coordinates in [0, 32768) */
        assertEquals(Double.NEGATIVE_INFINITY, new Point(29265, 10345).slopeTo(new Point(29265, 14284)), DELTA);
        /* negative infinite slope, where p and q have coordinates in [0, 500) */
        assertEquals(Double.NEGATIVE_INFINITY, new Point(128, 294).slopeTo(new Point(128, 494)), DELTA);
        /* negative infinite slope, where p and q have coordinates in [0, 32768) */
        assertEquals(Double.NEGATIVE_INFINITY, new Point(29115, 26798).slopeTo(new Point(29115, 26799)), DELTA);
        /* negative infinite slope, where p and q have coordinates in [0, 500) */
        assertEquals(0.0, new Point(128, 294).slopeTo(new Point(128, 294)), DELTA);
        /* negative infinite slope, where p and q have coordinates in [0, 32768) */
        assertEquals(0.0, new Point(29115, 26798).slopeTo(new Point(29115, 26798)), DELTA);
        /* symmetric infinity for random points p and q with coordinates in [0, 500) */
        {
            Point p = new Point(162, 272);
            Point q = new Point(162, 451);
            assertEquals(p.slopeTo(q), -q.slopeTo(p) , DELTA);
        }
        /* symmetric for random points p and q with coordinates in [0, 500) */
        {
            Point p = new Point(122, 272);
            Point q = new Point(162, 451);
            assertEquals(p.slopeTo(q), q.slopeTo(p) , DELTA);
        }

    }

    @Test
    public void testCompareTo() throws Exception {
        try{
            new Point(123, 234).compareTo(null);
            fail();
        } catch(NullPointerException e) {
            // OK
        }

    }


    @Test
    public void testSlopeOrder() throws Exception {
        // sign of compare(), where p, q, and r have coordinates in [0, 500)
        {
            Point p = new Point(288, 293);
            Point q = new Point(135, 34);
            Point r = new Point(288, 124);
            assertEquals(-1, p.SLOPE_ORDER.compare(q, r));
            assertEquals(1.6928104575163399, p.slopeTo(q), DELTA);
            assertEquals(Double.POSITIVE_INFINITY, p.slopeTo(r), DELTA);
        }
        // sign of compare(), where p, q, and r have coordinates in [0, 32768)
        {
            Point p = new Point(13996, 28588);
            Point q = new Point(27649, 32164);
            Point r = new Point(13996, 3572);
            assertEquals(-1, p.SLOPE_ORDER.compare(q, r));
            assertEquals(0.26192045704240824, p.slopeTo(q), DELTA);
            assertEquals(Double.POSITIVE_INFINITY, p.slopeTo(r), DELTA);
        }
        // sign of compare(), where p, q, and r have coordinates in [0, 10)
        {
            Point p = new Point(6, 8);
            Point q = new Point(6, 5);
            Point r = new Point(0, 0);
            assertEquals(1, p.SLOPE_ORDER.compare(q, r));
            assertEquals(Double.POSITIVE_INFINITY, p.slopeTo(q), DELTA);
            assertEquals(1.3333333333333333, p.slopeTo(r), DELTA);
        }
        {
            Point p = new Point(6, 8);
            Point q = new Point(6, 5);
            try {
                p.SLOPE_ORDER.compare(q, null);
                fail();
            } catch (NullPointerException e) {
                // OK
            }

            try {
                p.SLOPE_ORDER.compare(null, q);
                fail();
            } catch (NullPointerException e) {
                // OK
            }

            try {
                p.SLOPE_ORDER.compare(null, null);
                fail();
            } catch (NullPointerException e) {
                // OK
            }
        }
    }
}