package praktikum;

import org.junit.Test;

import static org.junit.Assert.*;

public class IngredientTest {

    private static final Ingredient ingredient = new Ingredient(
            IngredientType.FILLING, "meat", 100.0f
    );

    @Test
    public void getPrice() {
        assertEquals(100.0f, ingredient.getPrice(), 0.0000001);
    }

    @Test
    public void getName() {
        assertEquals("meat", ingredient.getName());
    }

    @Test
    public void getType() {
        assertEquals(IngredientType.FILLING, ingredient.getType());
    }
}