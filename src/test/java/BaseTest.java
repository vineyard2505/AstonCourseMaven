import org.example.Main;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BaseTest {
    Main factorTest = new Main();

    @Test
    public void factZeroTest(){
        Assert.assertEquals(factorTest.calculateFactorial(0),1);
    }

    @Test
    public void factNumberTest(){
        Assert.assertEquals(factorTest.calculateFactorial(5),120);
    }

    @Test
    public void factNegativeTest(){
        try {
            factorTest.calculateFactorial(-1);
            Assert.fail("Факториал отрицательного числа не возможен");
        } catch (IllegalArgumentException error){

        }
    }
}
