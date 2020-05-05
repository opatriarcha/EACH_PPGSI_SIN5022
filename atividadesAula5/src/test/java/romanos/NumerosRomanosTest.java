package romanos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NumerosRomanosTest {

    @Test
    public void testExample(){
        int result = NumerosRomanos.convert("I");
        int expected = 1;
        assertEquals(expected,result);
    }

    @Test
    public void testEmptyString(){
        int result = NumerosRomanos.convert("");
        int expected = 0;
        assertEquals(expected,result);
    }

    @Test
    public void testInvalidStringFrequency(){
        assertThrows(Exception.class, () -> NumerosRomanos.convert("IIII") );
    }

    @Test
    public void testInvalidStringFrequency2(){
        assertThrows(Exception.class, () -> NumerosRomanos.convert("IVI") );
    }

    @Test
    public void testInvalidChar(){
        assertThrows(Exception.class, () -> NumerosRomanos.convert("A") );
    }

    /*
    @Test
    public void testInvalidCharAlternative(){
        int result = NumerosRomanos.convert("A");
        int expected = 0;
        assertEquals(expected,result);
    }
*/
}
