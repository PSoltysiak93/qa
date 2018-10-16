package pl.jsystems.qajunit.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import pl.systems.qajunit.GamePlay;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;



public class ParamTest {
//    @Nested
//    @DisplayName("Parameterized test")
//    class NestedClass(



    @ParameterizedTest
    @DisplayName("First Parameterized test")
    @ValueSource(ints = {5, 15, 25})
    public void parameterizedTest(int number){
       assertTrue(number % 5 == 0);

    }

    @ParameterizedTest
    @DisplayName("Second Parameterized test")
    @ValueSource(strings = {"Hello", "Hello junit"})
    public void paramTest(String text){
    assertTrue(text.contains("Hello"));

    }

    @ParameterizedTest
    @DisplayName("Second Parameterized test")
    @CsvSource(delimiter = ',', value = {"Hello, 5", "HelloJunit 5, 15", "'Hello, Junit 5!', 25"})
    public void param2Test(String param1, int param2) {
        assertTrue( param1.contains("Hello"));
        assertTrue(param2 % 5 == 0);

    }
    @ParameterizedTest
    @CsvFileSource(resources = "/plik.csv", delimiter = ',')
    public void param3Test(String param1, int param2){
        assertTrue( param1.contains("Hello"));
        assertTrue(param2 % 5 == 0);
    }

    @Test
    public void exceptionTest() {

        assertThrows(IllegalArgumentException.class,
                () ->{
            GamePlay.play(0);
                }

        );
//        )



    }

}
