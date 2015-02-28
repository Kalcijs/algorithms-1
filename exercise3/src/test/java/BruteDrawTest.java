import org.junit.Test;

public class BruteDrawTest {

    @Test
    public void test6() throws Exception {
        new Brute().draw().load(file("input6.txt")).printCollinears();
    }

    @Test
    public void test8() throws Exception {
        new Brute().draw().load(file("input8.txt")).printCollinears();
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