import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class TestMain {

    @Test
    public void testProcess() {
        String expected = ""+
                "name:     Pie       seen: 3 times\n" +
                "=============       =============\n" +
                "Price:   2.99       seen: 3 times\n" +
                "-------------       -------------\n\n";

        ArrayList<String> arr = new ArrayList<>();
        arr.add("2.99");
        arr.add("2.99");
        arr.add("2.99");

        String actual = Main.process(arr, "Pie");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testGetList() {
        int expected = 5;
        String s = "ggggg";
        List<String> list = Main.getList("g", s);

        int actual = list.size();

        Assert.assertEquals(actual, expected);
        Assert.assertTrue(list.contains("g"));
    }

    @Test
    public void testPad() {
        String expected = "     Pie";

        String actual = Main.pad("Pie");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testPad2() {
        String expected = "88888888";

        String actual = Main.pad("88888888");

        Assert.assertEquals(expected, actual);
    }
}
