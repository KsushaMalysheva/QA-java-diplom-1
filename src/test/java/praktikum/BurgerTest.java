package praktikum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    Burger burger = new Burger();

    @Mock
    Bun bun;
    Ingredient ingredient = mock(Ingredient.class);
    Ingredient ingredient2 = mock(Ingredient.class);
    Ingredient ingredient3 = mock(Ingredient.class);

    @Test  //только булки, без ингредиентов
    public void burgerWithoutIngredientsCheckTotalPrice() {
        // Burger burger = new Burger();
        when(bun.getPrice()).thenReturn(20f);
        burger.setBuns(bun);
        float actual = burger.getPrice();
        float expected = 40f;
        assertEquals("Итоговая цена бургера", expected, actual, 0);
    }

    @Test // булки + ингредиенты
    public void burgerWithIngredientsCheckTotalPrice() {
        when(bun.getPrice()).thenReturn(100f);
        when(ingredient.getPrice()).thenReturn(500f);
        burger.addIngredient(ingredient);
        burger.setBuns(bun);
        float actual = burger.getPrice();
        float expected = 700f;
        assertEquals("Итоговая цена бургера", expected, actual, 0);
    }

    @Test //удалим ингредиент
    public void burgerDeleteIngredient() {
        Mockito.when(bun.getPrice()).thenReturn(100f);
        Mockito.when(ingredient.getPrice()).thenReturn(10f);
        Mockito.when(ingredient2.getPrice()).thenReturn(50f);
        Mockito.when(ingredient3.getPrice()).thenReturn(100f);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient2);
        burger.addIngredient(ingredient3);
        burger.removeIngredient(1);
        burger.setBuns(bun);
        float actual = burger.getPrice();
        float expected = 310;
        assertEquals("Итоговая цена бургера", expected, actual, 0);
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
    public void moveIngredientReturnsCorrectPrice() {
        when(bun.getPrice()).thenReturn(100.50f);
        burger.setBuns(bun);
        when(ingredient.getPrice()).thenReturn(100f);
        when(ingredient2.getPrice()).thenReturn(99.99f);
        //when(ingredient3.getPrice()).thenReturn(200f); - UnnecessaryStubbingException
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient2);
        burger.addIngredient(ingredient3);
        burger.moveIngredient(0, 2);  // новый порядок теперь: 99.99, 200, 100
        burger.removeIngredient(1); // удалим элемент с индексом 1 (=200) чтобы потом увидеть цену и убедиться что данные были реально перемешаны
        float actual = burger.getPrice();
        float expected = 400.99f; // 100.50*2 + 99.99 + 100
        assertEquals("Итоговая цена бургера", expected, actual, 0);
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
        when(bun.getPrice()).thenReturn(price);
        when(ingredient.getPrice()).thenReturn(price);
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        float expectedPrice = price * 2 + price;
        float actualPrice = burger.getPrice();
        assertEquals(expectedPrice, actualPrice, 0.000002);
    }

    @Test
    public void getReceiptReturnsCorrectReceipt() {
        Burger burger = new Burger();
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);
        burger.setBuns(bun);
        when(ingredient.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient.getName()).thenReturn("dinosaur");
        when(ingredient.getPrice()).thenReturn(300f);
        burger.addIngredient(ingredient);
        String expectedReceipt = "(==== black bun ====)\r\n" + "= filling dinosaur =\r\n" + "(==== black bun ====)\r\n" + "\r\n" + "Price: 500,000000\r\n";
        String actualReceipt = burger.getReceipt();
        assertEquals(expectedReceipt, actualReceipt);
    }

    @Test // булка без имени, нет начинки и соуса
    public void getReceiptReturnsCorrectReceipt2() {
        burger.setBuns(bun);
        when(bun.getName()).thenReturn("");
        String actual = burger.getReceipt();
        String receipt = (String.format("(====  ====)%n" +
                "(====  ====)%n" +
                "%n" +
                "Price: 0,000000%n"));
        System.out.println(actual);
        assertEquals("Чек соответствует ожидаемому", receipt, actual);
    }

}