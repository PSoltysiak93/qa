package pl.jsystems.qajunit.test;

import org.junit.jupiter.api.*;

import javax.xml.bind.SchemaOutputResolver;
import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("To jest nasza klasa testowa")
public class JunitTest {

    @BeforeEach
    void before () {
        System.out.println("==========Before each");
        testowyString = "testowyString";
    }

    @AfterEach
    void afterEach(){

        System.out.println("===========After Each");
        testowyString = "testowyString";
    }

    @BeforeAll
    static  void beforeAll(){

        System.out.println("===========Before All");


    }

    @AfterAll
    static void afterAll(){
        System.out.println("=========After All");

    }
    @RepeatedTest(5)
    @Test
    @DisplayName("To jest nasz pierwszy test")
    public void firstTest() {

        System.out.println(0.2 * 0.2);
        assertTrue(new BigDecimal("0.2").multiply(new BigDecimal("0.2")).doubleValue() == 0.04);
//        assertTrue((0.2 * 0.2) == 0.04);


    }


    String testowyString = "testowyString";

    @Test
    public void testStringa() {
        assertEquals("testowyString", testowyString);
        assertThat(testowyString, containsString("Strin"));
        assertThat(testowyString, equalTo("testowyString"));
        assertThat(testowyString, endsWith("ing"));

    }

    @Test
    public void testStringa2() {
        assertAll(() -> {

                    assertEquals("testowyString", testowyString);
                    assertThat(testowyString, containsString("StrinDDD"));
                    assertThat(testowyString, equalTo("testowyString"));
                    assertThat(testowyString, endsWith("inSSSgS"));

                }

        );

    }
}
