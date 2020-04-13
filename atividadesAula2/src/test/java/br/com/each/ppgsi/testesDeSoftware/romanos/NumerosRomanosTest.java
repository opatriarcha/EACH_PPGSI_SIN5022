package br.com.each.ppgsi.testesDeSoftware.romanos;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NumerosRomanosTest {

    /**
     * • O programa numerais romanos recebe uma string como parâmetro
            contendo um número romano e então o coverte para um número
            inteiro. Em numerais romanos as letras representam:
            – I = 1, V = 5, X = 10, L = 50, C = 100, D = 500, M = 1000
       • Letras podem ser combinadas para formar números. As letras
            devem ser ordenadas do maior para o menor valor. Por exemplo,
            CCXVI seria 216. Para alguns números é preciso fazer a subtração de
            valores seguindo a notação. Por exemplo: IX = 9 (10 – 1), XL = 40
                (50-10), XIV = 14 (10+5-1)
     */
    
    private static Map<Character, Integer> map;

    static {
        map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
    }
    
    @Test
    public void testExample(){
        int expected = NumerosRomanos.convert("XXXX");
        int result = 1;
        assertEquals(expected,result);
    }
    
    
    //IDs 1 a 8
    @Test
    public void testEntradasValidasUnitariamente(){        
        assertEquals(1 ,NumerosRomanos.convert("I"));
        assertEquals(5 ,NumerosRomanos.convert("V"));
        assertEquals(10 ,NumerosRomanos.convert("X"));
        assertEquals(50 ,NumerosRomanos.convert("L"));
        assertEquals(100 ,NumerosRomanos.convert("C"));
        assertEquals(500 ,NumerosRomanos.convert("D"));
        assertEquals(1000 ,NumerosRomanos.convert("M"));
    }
    
    //ID 8
    @Test
    public void testEntradasInvalidasunitariamente(){        
        assertEquals(1 ,NumerosRomanos.convert("Z"));        
    }
    
    //IDs 9 a 15
    @Test
    public void testEntradasValidasCompostas(){        
        assertEquals(2 ,NumerosRomanos.convert("II"));
        assertEquals(3 ,NumerosRomanos.convert("III"));
        assertEquals(4 ,NumerosRomanos.convert("IV"));
        assertEquals(6 ,NumerosRomanos.convert("VI"));
        assertEquals(7 ,NumerosRomanos.convert("VII"));
        assertEquals(8 ,NumerosRomanos.convert("VIII"));
        assertEquals(9 ,NumerosRomanos.convert("IX"));
    }
    
    //IDs 16 a 21
    @Test
    public void testEntradasInvalidasCompostas(){        
        assertThrows(RuntimeException.class ,() -> NumerosRomanos.convert("XXXX"));
        assertThrows(RuntimeException.class ,() -> NumerosRomanos.convert("IIII"));
        assertThrows(RuntimeException.class ,() -> NumerosRomanos.convert("VIIII"));
        assertThrows(RuntimeException.class ,() -> NumerosRomanos.convert("LXXXX"));
        assertThrows(RuntimeException.class ,() -> NumerosRomanos.convert("CCCC"));        
        assertThrows(RuntimeException.class ,() -> NumerosRomanos.convert("DCCCC"));
    }
    
    //ID 22  |" X"       |ERRO                |  V1          |
    @Test
    public void testEspacoAntesDoCaractere(){        
        assertThrows(RuntimeException.class ,() -> NumerosRomanos.convert(" X"));        
    }
        
    //ID 23  | B         |ERRO                |  V1          |
    @Test
    public void testForaDoDominio(){        
        assertThrows(RuntimeException.class ,() -> NumerosRomanos.convert("B"));        
    }
    
    //ID 24  | vazia     |ERRO                |  V1          |
    @Test
    public void testStringVaziaComoEntrada(){        
        assertThrows(RuntimeException.class ,() -> NumerosRomanos.convert(""));        
    }
    
    //ID 25  | " "     |ERRO                |  V1          |
    @Test
    public void testEspaçoVaziComoEntrada(){        
        assertThrows(RuntimeException.class ,() -> NumerosRomanos.convert(" "));        
    }
}
