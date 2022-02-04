import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

//unit tests for Samochod class
class TestSamochod {

    @ParameterizedTest
    @ValueSource(ints = {100})
    void creationTest(int pojZbiornika) {
        Samochod samochod = new Samochod("1", pojZbiornika);
        assertAll(
                () -> assertNotNull(samochod),
                () -> assertTrue(0 <= samochod.getPaliwo() && samochod.getPaliwo() <= pojZbiornika)
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {1})
    void tankowanieTest(int paliwo) {
        Samochod samochod = new Samochod("1", 100);
        int prev = samochod.getPaliwo();
        int expected = prev + paliwo;
        samochod.tankowanie(paliwo);
        int actual = samochod.getPaliwo();
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(ints = {100})
    void pojZbiornikaTest(int pojZbiornika) {
        Samochod samochod = new Samochod("1", pojZbiornika);
        samochod.tankowanie(Integer.MAX_VALUE - samochod.getPaliwo());
        int actual = samochod.getPaliwo();
        assertEquals(pojZbiornika, actual);
    }

    @Test
    void samochodTest() {
        Samochod[] cars = {new Samochod("11Y", 20), new Samochod("12a34", 15)};
        //while (true)
        for (Samochod s : cars) {
            s.start();
        }
    }
}

