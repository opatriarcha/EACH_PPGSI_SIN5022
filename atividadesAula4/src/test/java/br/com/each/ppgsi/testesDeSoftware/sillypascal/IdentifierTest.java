package br.com.each.ppgsi.testesDeSoftware.sillypascal;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 *
 * @author orlando
 */
public class IdentifierTest {        

    //ID1
    @Test
    public void nullIdentifierShouldReturnFalse(){        
        Assertions.assertFalse(Identifier.validaIdentificador(null));        
    }
    
    //ID2
    @Test
    public void emptyIdentifierShouldReturnFalse(){        
        Assertions.assertFalse(Identifier.validaIdentificador(""));        
    }
    
    //ID3
    @Test
    public void moreThanSixLenghtShouldReturnFalse(){        
        Assertions.assertFalse(Identifier.validaIdentificador("abcdefgh"));        
    }
    
    //ID4
    @Test
    public void startWithDigitShouldReturnFalse(){
        Assertions.assertFalse(Identifier.validaIdentificador("6abcd"));
    }

    //ID5
    @Test
    public void aCharacterNonAlphabeticShouldReturnFalse(){
        Assertions.assertFalse(Identifier.validaIdentificador("a$bcde"));
    }

    //ID6
    @Test
    public void aDigitInTheIdentifierShouldReturnFalse(){
        Assertions.assertFalse(Identifier.validaIdentificador("a4bcde"));
    }

    //ID7
    @Test
    public void aFullCorrectIdentifierShouldReturnTrue(){
        Assertions.assertTrue(Identifier.validaIdentificador("abcde"));
    }
}
