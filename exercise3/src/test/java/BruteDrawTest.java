import org.junit.Ignore;
import org.junit.Test;

public class BruteDrawTest {

    @Ignore
    @Test
    public void test6() throws Exception {
        Brute.main(new String[]{file("input6.txt")});
        //new Brute().draw().load(file("input6.txt")).printCollinears();
    }

    @Ignore
    @Test
    public void test8() throws Exception {
        Brute.main(new String[]{file("input8.txt")});
        //new Brute().draw().load(file("input8.txt")).printCollinears();
    }

    public static void main(String[] args) {
        try {
            new BruteDrawTest().test8();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String file(String file) {
        return this.getClass().getResource(file).getFile();
    }


}