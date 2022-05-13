package praktikum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    Bun bun;

    @Mock
    Ingredient ingredient;

    Burger burger = new Burger();

    @Test
    public void setBunsCheck() {
        burger.setBuns(burger.bun);
        assertNotNull(burger);
    }

    @Test
    public void addIngredientCheck() {
        burger.addIngredient(ingredient);
        assertNotNull(burger.ingredients);
    }

    @Test
    public void addIngredientReturnsCorrectIngredientSize() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        int expectedSize = 1;
        int actualSize = burger.ingredients.size();
        assertEquals(expectedSize, actualSize);
    }
    @Test
    public void removeIngredientReturnsCorrectIngredientSize() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        int expectedSize = 0;
        int actualSize = burger.ingredients.size();
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void removeIngredientCheck() {
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        List<Ingredient> expected = new ArrayList<>();
        assertEquals(expected, burger.ingredients);
    }

    @Test
    public void moveIngredientReturnsCorrectIngredient() {
        Burger burger = new Burger();
        burger.addIngredient(new Ingredient(IngredientType.SAUCE, "sour cream", 200));
        burger.addIngredient(new Ingredient(IngredientType.FILLING, "sausage", 300));
        burger.moveIngredient(0, 1);
        String expectedName = "sour cream";
        String actualName = burger.ingredients.get(1).name;
        assertEquals(expectedName, actualName);
    }

    @Test
    public void moveIngredientCheck() {
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient);
        burger.moveIngredient(0, 1);
        assertNotNull(burger.ingredients.get(1));
    }


    @Test
    public void getPriceReturnsCorrectPrice() {
        Burger burger = new Burger();
        float price = 100;
        Mockito.when(bun.getPrice()).thenReturn(price);
        Mockito.when(ingredient.getPrice()).thenReturn(price);
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        float expectedPrice = price * 2 + price;
        float actualPrice = burger.getPrice();
        assertEquals(expectedPrice, actualPrice, 0.000002);
    }

    @Test
    public void getReceiptReturnsCorrectReceipt() {
        Burger burger = new Burger();
        Mockito.when(bun.getName()).thenReturn("black bun");
        Mockito.when(bun.getPrice()).thenReturn(100f);
        burger.setBuns(bun);
        Mockito.when(ingredient.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(ingredient.getName()).thenReturn("dinosaur");
        Mockito.when(ingredient.getPrice()).thenReturn(300f);
        burger.addIngredient(ingredient);
        String expectedReceipt = "(==== black bun ====)\r\n" + "= filling dinosaur =\r\n" + "(==== black bun ====)\r\n" + "\r\n" + "Price: 500,000000\r\n";
        String actualReceipt = burger.getReceipt();
        assertEquals(expectedReceipt, actualReceipt);
    }
}