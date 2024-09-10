import org.example.Main;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BaseTest {

    @Test
    public void factZeroTest(){
        assertEquals(1, Main.calculateFactorial(0));
    }

    @Test
    public void factNumberTest(){
        assertEquals(120, Main.calculateFactorial(5));
    }

    @Test
    public void factNegativeTest(){
        assertThrows(IllegalArgumentException.class, () -> Main.calculateFactorial(-1));
    }
}
