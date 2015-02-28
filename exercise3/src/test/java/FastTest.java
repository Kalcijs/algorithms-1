
import org.junit.Test;

import static org.junit.Assert.*;

public class FastTest {

    @Test
    public void test6() throws Exception {
        StdOut.println("BRUTE");
        new Brute().load(file("input8.txt")).printCollinears();
        StdOut.println("FAST");
        new Fast().load(file("input8.txt")).printCollinears();
    }

    private String file(String file) {
        return this.getClass().getResource(file).getFile();
    }
}