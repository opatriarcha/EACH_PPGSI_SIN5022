package sillypascal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IdentifierTest {

    //C1,C2,C3
    @Test
    public void testIdentificadorValidoTamanhoMedio(){
        boolean valido = Identifier.validaIdentificador("abc");
        Assertions.assertTrue(valido);
        //ou assertEquals(true,valido);
    }

    //C4.1
    @Test
    public void testIdentificadorInvalidoVazio(){
        boolean valido = Identifier.validaIdentificador("");
        Assertions.assertFalse(valido);
        //ou assertEquals(false,valido);
    }

    @Test
    public void testIdentificadorInvalidoNulo(){
        boolean valido = Identifier.validaIdentificador(null);
        Assertions.assertFalse(valido);
        //ou assertEquals(false,valido);
    }

}
