package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BunMockTest {

    @Mock
    private Bun bun;


    @Test
    public void getPrice() {
        Bun bun = new Bun("black bun", 100);
        float actual = bun.getPrice();
        assertEquals(100, actual, 0);

    }
    @Test
    public void getName() {
        Bun bun = new Bun("black bun", 100);
        String actual = bun.getName();
        assertEquals("black bun", actual);

    }

    @Test
    public void checkGetNametest() {
        bun.getName();
        Mockito.verify(bun).getName();
    }

    @Test
    public void checkGetPricetest() {
        bun.getPrice();
        Mockito.verify(bun).getPrice();
    }
}

