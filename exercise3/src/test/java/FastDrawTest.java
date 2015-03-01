
import org.junit.Ignore;
import org.junit.Test;

public class FastDrawTest {

    @Ignore
    @Test
    public void test6compareWithBrute() throws Exception {
        StdOut.println("BRUTE");
        Brute.main(new String[]{file("input6.txt")});
        //new Brute().load(file("input6.txt")).printCollinears();
        StdOut.println("FAST");
        Fast.main(new String[]{file("input6.txt")});
        //new Fast().load(file("input6.txt")).printCollinears();
    }

    @Ignore
    @Test
    public void test8compareWithBrute() throws Exception {
        StdOut.println("BRUTE");
        Brute.main(new String[]{file("input8.txt")});
        //new Brute().load(file("input8.txt")).printCollinears();
        StdOut.println("FAST");
        Fast.main(new String[]{file("input8.txt")});
        //new Fast().load(file("input8.txt")).printCollinears();
    }

    @Ignore
    @Test
    public void test6() throws Exception {
        Fast.main(new String[]{file("input6.txt")});
        //new Fast().draw().load(file("input6.txt")).printCollinears();
    }

    @Ignore
    @Test
    public void test8() throws Exception {
        Fast.main(new String[]{file("input8.txt")});
        //new Fast().draw().load(file("input8.txt")).printCollinears();
    }

    public static void main(String[] args) {
        try {
            new FastDrawTest().test8();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String file(String file) {
        return this.getClass().getResource(file).getFile();
    }
}