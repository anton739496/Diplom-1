package praktikum;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class BurgerTest {

    private static final Database DATABASE = new Database();

    @Test
    public void setBuns() {
        //given
        List<Bun> buns = DATABASE.availableBuns();
        Burger burger = new Burger();

        //when
        burger.setBuns(buns.get(0));

        //then
        assertEquals(buns.get(0), burger.bun);
    }

    @Test
    public void addIngredient() {
        //given
        List<Ingredient> ingredients = DATABASE.availableIngredients();
        Burger burger = new Burger();

        //when
        burger.addIngredient(ingredients.get(0));

        //then
        assertEquals(ingredients.get(0), burger.ingredients.get(0));
    }

    @Test
    public void removeIngredient() {
        //given
        List<Ingredient> ingredients = DATABASE.availableIngredients();
        Burger burger = new Burger();
        ingredients.forEach(burger::addIngredient);


        assertEquals(ingredients.size(), burger.ingredients.size());
        assertTrue(burger.ingredients.contains(ingredients.get(0)));

        //when
        burger.removeIngredient(0);

        //then
        assertEquals(ingredients.size() - 1, burger.ingredients.size());
        assertFalse(burger.ingredients.contains(ingredients.get(0)));
    }

    @Test
    public void moveIngredient() {
        //given
        List<Ingredient> ingredients = DATABASE.availableIngredients();
        Burger burger = new Burger();
        ingredients.forEach(burger::addIngredient);

        assertEquals(ingredients.get(0), burger.ingredients.get(0));

        //when
        burger.moveIngredient(0, 2);

        //then
        assertEquals(ingredients.get(0), burger.ingredients.get(2));
    }

    @Test
    public void getPrice() {
        //given
        Ingredient ingredient1 = Mockito.mock(Ingredient.class);
        Ingredient ingredient2 = Mockito.mock(Ingredient.class);
        Ingredient ingredient3 = Mockito.mock(Ingredient.class);

        Mockito.when(ingredient1.getPrice()).thenReturn(1.0f);
        Mockito.when(ingredient2.getPrice()).thenReturn(2.0f);
        Mockito.when(ingredient3.getPrice()).thenReturn(3.0f);

        Bun bun1 = Mockito.mock(Bun.class);
        Mockito.when(bun1.getPrice()).thenReturn(10.0f);
        Burger burger = new Burger();
        double expectedPrice = ingredient1.getPrice() + ingredient2.getPrice()
                + ingredient3.getPrice() + bun1.getPrice() * 2;

        //when
        burger.setBuns(bun1);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.addIngredient(ingredient3);

        //then
        assertEquals(expectedPrice, burger.getPrice(), 0.0000001);
    }

    @Test
    public void getReceipt() throws IOException {
        //given
        List<Ingredient> ingredients = DATABASE.availableIngredients();
        Bun bun = DATABASE.availableBuns().get(0);
        Burger burger = new Burger();

        //when
        burger.setBuns(bun);
        ingredients.forEach(burger::addIngredient);

        //then
        String receipt = Files.readString(Paths.get("src/test/resources/receipt1.txt"));
        assertEquals(receipt, burger.getReceipt());
    }
}