package praktikum;

import com.github.javafaker.Faker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Random;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class IngredientTest {
    Faker faker = Faker.instance();
    Random random = new Random();
    final private IngredientType type = getRandomIngredientType();
    final private String name = faker.name().toString();
    final private float price = random.nextFloat();
    Ingredient ingredient = new Ingredient(type, name, price);

    private IngredientType getRandomIngredientType() {
        IngredientType[] values = IngredientType.values();
        return values[random.nextInt(values.length)];
    }

    @Test
    public void getNameCheck() {
        String actual = ingredient.getName();
        assertEquals(name, actual);
    }

    @Test
    public void getPriceCheck() {
        float actual = ingredient.getPrice();
        assertEquals(price, actual, 0);
    }

    @Test
    public void getTypeCheck() {
        IngredientType actual = ingredient.getType();
        assertEquals(type, actual);
    }
}