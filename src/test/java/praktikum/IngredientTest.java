package praktikum;

import com.github.javafaker.Faker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Random;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class IngredientTest {

    private IngredientType getRandomIngredientType() {
        IngredientType[] values = IngredientType.values();
        return values[random.nextInt(values.length)];
    }

    Faker faker = Faker.instance();
    Random random = new Random();

    final private IngredientType type = getRandomIngredientType();
    final private String name = faker.name().toString();
    final private float price = random.nextFloat();

    Ingredient ingredient = new Ingredient(type, name, price);

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

    @Test
    public void getNameReturnsCorrectName() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 100);
        String expectedName = "hot sauce";
        String actualName = ingredient.getName();
        assertEquals(expectedName, actualName);
    }

    @Test
    public void getPriceReturnsCorrectPrice() {

        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 100);
        float expectedPrice = 100;
        float actualPrice = ingredient.getPrice();
        assertEquals(expectedPrice, actualPrice, 0);
    }

    @Test
    public void getTypeReturnsCorrectType() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 100);
        IngredientType expectedType = IngredientType.SAUCE;
        IngredientType actualType = ingredient.getType();
        assertEquals(expectedType, actualType);
    }
}