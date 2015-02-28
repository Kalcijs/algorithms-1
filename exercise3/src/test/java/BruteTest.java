
import org.junit.*;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;

import static org.junit.Assert.*;

@Ignore
public class BruteTest {
    private static final String EOL = System.getProperty("line.separator");

    @Rule
    public final StandardOutputStreamLog log = new StandardOutputStreamLog();

    @Test
    public void test6() throws Exception {
        String expected = "(14000, 10000) -> (18000, 10000) -> (19000, 10000) -> (21000, 10000)" + EOL +
                "(14000, 10000) -> (18000, 10000) -> (19000, 10000) -> (32000, 10000)" + EOL +
                "(14000, 10000) -> (18000, 10000) -> (21000, 10000) -> (32000, 10000)" + EOL +
                "(14000, 10000) -> (19000, 10000) -> (21000, 10000) -> (32000, 10000)" + EOL +
                "(18000, 10000) -> (19000, 10000) -> (21000, 10000) -> (32000, 10000)" + EOL;
        new Brute().load(file("input6.txt")).printCollinears();
        assertEquals(expected, log.getLog());
    }

    @Test
    public void test8() throws Exception {
        String expected = "(10000, 0) -> (7000, 3000) -> (3000, 7000) -> (0, 10000)" + EOL +
                "(3000, 4000) -> (6000, 7000) -> (14000, 15000) -> (20000, 21000)" + EOL;
        new Brute().load(file("input8.txt")).printCollinears();
        assertEquals(expected, log.getLog());
    }

    private String file(String file) {
        return this.getClass().getResource(file).getFile();
    }

}