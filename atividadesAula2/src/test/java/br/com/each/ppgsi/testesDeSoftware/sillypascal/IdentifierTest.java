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
    
    //meus testes
    //ID 1	abc	válido	C1, C2, C3
    @Test
    public void testIdentificadorValido(){        
        Assertions.assertTrue(Identifier.validaIdentificador("abc"));        
    }
    
    //ID 2   |""       |invalido            |C4.1                    |
    @Test
    public void testIdentificadorInvalidoVazio1(){        
        Assertions.assertFalse(Identifier.validaIdentificador(""));        
    }
    
    //ID 3   |abcdefgh |inválido            |C4.2                    |
    @Test
    public void testIdentificadorInvalidoMaiorQuePermitido(){        
        Assertions.assertFalse(Identifier.validaIdentificador("abcdefgh"));        
    }
    
    //ID 4   |1abc     |invalido            |C5                      |
    @Test
    public void testIdentificadorInvalidoIniciandoComNumero(){        
        Assertions.assertFalse(Identifier.validaIdentificador("1abc"));        
    }
    
    //ID 5   | ab-cd   |invalido            |C6                      |
    @Test
    public void testIdentificadorInvalidoCaracterEspecial(){        
        Assertions.assertFalse(Identifier.validaIdentificador("ab-cd"));        
    }
    
    //ID 6   |a        |válido              |V1.1, v2.2    |
    @Test
    public void testIdentificadorValidoTamanho1(){        
        Assertions.assertTrue(Identifier.validaIdentificador("a"));        
    }
    
    //ID 7   |abcdef   |valido              |V1.2          |
    @Test
    public void testIdentificadorValidoTamanho6(){        
        Assertions.assertTrue(Identifier.validaIdentificador("abcdef"));        
    }
    
    //ID 8   |""       |invalido            |V3.1          |
    @Test
    public void testIdentificadorinValidoTamanhoVazio(){        
        Assertions.assertFalse(Identifier.validaIdentificador(""));        
    }
    
    //ID 9   |abcdefgh |invalido            |V3.2          |
    @Test
    public void testIdentificadorInvalidoTamanho8(){        
        Assertions.assertFalse(Identifier.validaIdentificador("abcdefgh"));        
    }
    
    //ID 10  |Abc      |valido              |V4.1, V2.1    |
    @Test
    public void testIdentificadorValidoAbc(){        
        Assertions.assertTrue(Identifier.validaIdentificador("Abc"));        
    }
    
    //ID 11  |Zcv      |valido              |V4.3          |
    @Test
    public void testIdentificadorValidoZcv(){        
        Assertions.assertTrue(Identifier.validaIdentificador("Zcv"));        
    }
    
    //ID 12  |zcv      |valido              |V4.4          |
    @Test
    public void testIdentificadorValido_zcv(){        
        Assertions.assertTrue(Identifier.validaIdentificador("zcv"));        
    }
    
    //ID 13  |0abc     |invalido            |V4.1          |
    @Test
    public void testIdentificadorInvalido_0abc(){        
        Assertions.assertFalse(Identifier.validaIdentificador("0abc"));        
    }
    
    //ID 14  |9abc     |invalido            |V4.2          |
    @Test
    public void testIdentificadorInvalido_9abc(){        
        Assertions.assertFalse(Identifier.validaIdentificador("9abc"));        
    }
}
