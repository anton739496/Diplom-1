package praktikum;

import lombok.AllArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
@AllArgsConstructor
public class BunTest {

    private static final Database DATABASE = new Database();

    private final Bun bun;

    private final String bunName;

    private final double bunPrice;

    @Parameters
    public static Collection<Object[]> params() {
        return DATABASE.availableBuns().stream()
                .map(bun -> new Object[]{bun, bun.getName(), bun.getPrice()})
                .limit(2)
                .collect(Collectors.toUnmodifiableList());
    }

    @Test
    public void getNameShouldReturnValidName() {
        //when-then
        assertEquals(bunName, bun.getName());
    }

    @Test
    public void getNameShouldReturnValidPrice() {
        //when-then
        assertEquals(bunPrice, bun.getPrice(), 0.00000001);
    }
}