
import org.junit.*;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class BruteTest {
    private static final String EOL = System.getProperty("line.separator");
    private static volatile PrintStream originalSysOut;
    private static volatile ByteArrayOutputStream output;
    @BeforeClass
    public static void setUpClass() {
        originalSysOut = System.out;
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }
    @Before
    public void setUp() {
        output.reset();
    }
    @AfterClass
    public static void tearDownClass() {
        System.setOut(originalSysOut);
    }

    @Test
    public void test6() throws Exception {
        //println(file("input6.txt"));
        String expected = "(14000, 10000) -> (18000, 10000) -> (19000, 10000) -> (21000, 10000)" + EOL +
                "(14000, 10000) -> (18000, 10000) -> (19000, 10000) -> (32000, 10000)" + EOL +
                "(14000, 10000) -> (18000, 10000) -> (21000, 10000) -> (32000, 10000)" + EOL +
                "(14000, 10000) -> (19000, 10000) -> (21000, 10000) -> (32000, 10000)" + EOL +
                "(18000, 10000) -> (19000, 10000) -> (21000, 10000) -> (32000, 10000)" + EOL;
        new Brute().load(file("input6.txt")).printCollinears();
        assertEquals(expected, output.toString());
    }

    @Test
    public void test8() throws Exception {
        //println(file("input8.txt"));
        String expected = "(10000, 0) -> (7000, 3000) -> (3000, 7000) -> (0, 10000)" + EOL +
                "(3000, 4000) -> (6000, 7000) -> (14000, 15000) -> (20000, 21000)" + EOL;
        new Brute().load(file("input8.txt")).printCollinears();
        assertEquals(expected, output.toString());
    }

    private String file(String file) {
        return this.getClass().getResource(file).getFile();
    }

    private void println(String msg) {
        originalSysOut.println(msg);
    }

}