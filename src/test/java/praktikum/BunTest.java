package praktikum;

import org.junit.Test;
import com.github.javafaker.Faker;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class BunTest {
    Faker faker = Faker.instance();
    Random random = new Random();

    final private String name = faker.name().toString();
    final private float price = random.nextFloat();

    Bun bun = new Bun(name, price);

    @Test
    public void getNameReturnsCorrectName() {
        Bun bun = new Bun("Name", 10);
        String expectedName = "Name";
        String actualName = bun.getName();
        assertEquals(expectedName, actualName);
    }

    @Test
    public void getPriceReturnsCorrectPrice() {
        Bun bun = new Bun("Name", 10);
        float expectedPrice = 10;
        float actualPrice = bun.getPrice();
        assertEquals(expectedPrice, actualPrice, 2);
    }

    @Test
    public void getNameCheck() {
        String actual = bun.getName();
        assertEquals(name, actual);
    }

    @Test
    public void getPriceCheck() {
        float actual = bun.getPrice();
        assertEquals(price, actual, 0);
    }
}

