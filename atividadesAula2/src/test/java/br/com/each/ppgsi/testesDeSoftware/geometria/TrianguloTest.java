package br.com.each.ppgsi.testesDeSoftware.geometria;

import br.com.each.ppgsi.testesDeSoftware.geometria.LadoInvalidoException;
import br.com.each.ppgsi.testesDeSoftware.geometria.Triangulo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrianguloTest {

    private static final String MSG_INSIDE_EXCEPTION = "lado invalido";
    private static final String MSG_ISOSCELES = "ISOSCELES";
    private static final String MSG_EQUILATERO = "EQUILATERO";
    private static final String MSG_ESCALENO = "ESCALENO";
    private static final String MSG_NAO_FORMA_TRIANGULO = "NAO FORMA TRIANGULO";


    //C1,C2,C3,C4
    @Test
    public void testEquilatero() throws LadoInvalidoException{
        int LA = 5;
        int LB = 5;
        int LC = 5;
        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals("EQUILATERO",result);
    }

    //C1,C2,C3,C5.1
    @Test
    public void testIsosceles1() throws LadoInvalidoException{
        int LA = 5;
        int LB = 5;
        int LC = 6;
        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals("ISOSCELES",result);
    }

    //C1,C2,C3,C5.2
    @Test
    public void testIsosceles2() throws LadoInvalidoException{
        int LA = 6;
        int LB = 5;
        int LC = 5;
        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals("ISOSCELES",result);
    }

    //C1,C2,C3,C5.3
    @Test
    public void testIsosceles3() throws LadoInvalidoException{
        int LA = 6;
        int LB = 5;
        int LC = 6;
        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals("ISOSCELES",result);
    }

    //C1,C2,C3,C6
    @Test
    public void testEscaleno() throws LadoInvalidoException{
        int LA = 4;
        int LB = 5;
        int LC = 6;
        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals("ESCALENO",result);
    }

    //C7
    @Test
    public void testLAInvalido() throws LadoInvalidoException{
        int LA = -2;
        int LB = 4;
        int LC = 5;
        assertThrows(LadoInvalidoException.class, () -> Triangulo.classificaTriangulo(LA, LB, LC));
    }

    //C8
    @Test
    public void testLBInvalido() throws LadoInvalidoException{
        int LA = 4;
        int LB = -2;
        int LC = 5;
        assertThrows(LadoInvalidoException.class, () -> Triangulo.classificaTriangulo(LA, LB, LC));
    }

    //C9
    @Test
    public void testLCInvalido() throws LadoInvalidoException{
        int LA = 4;
        int LB = 5;
        int LC = -2;
        assertThrows(LadoInvalidoException.class, () -> Triangulo.classificaTriangulo(LA, LB, LC));
    }

    //C10.1
    @Test
    public void testNaoTriangulo1() throws LadoInvalidoException{
        int LA = 10;
        int LB = 2;
        int LC = 3;
        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals("NAO FORMA TRIANGULO",result);
    }

    //C10.2
    @Test
    public void testNaoTriangulo2() throws LadoInvalidoException{
        int LA = 2;
        int LB = 10;
        int LC = 3;
        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals("NAO FORMA TRIANGULO",result);
    }

    //C10.3
    @Test
    public void testNaoTriangulo3() throws LadoInvalidoException{
        int LA = 3;
        int LB = 2;
        int LC = 10;
        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals("NAO FORMA TRIANGULO",result);
    }

    //meus testes
    //ID1
    @Test
    public void testFormaEquilatero() throws LadoInvalidoException{
        int LA = 5;
        int LB = 5;
        int LC = 5;
        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals("EQUILATERO",result);
    }

    //ID2
    @Test
    public void testFormaIsoscelesCMaior() throws LadoInvalidoException{
        int LA = 5;
        int LB = 5;
        int LC = 6;
        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals("ISOSCELES",result);
    }

    //ID3
    @Test
    public void testFormaIsoscelesAMaior() throws LadoInvalidoException{
        int LA = 6;
        int LB = 5;
        int LC = 5;
        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals("ISOSCELES",result);
    }

    //ID4
    @Test
    public void testFormaIsoscelesBMaior() throws LadoInvalidoException{
        int LA = 5;
        int LB = 6;
        int LC = 5;
        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals("ISOSCELES",result);
    }

    //ID5
    @Test
    public void testFormaEscaleno() throws LadoInvalidoException{
        int LA = 4;
        int LB = 5;
        int LC = 6;
        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals("ESCALENO",result);
    }

    //ID6 |6 | -2,4,5   |ERRO – valor inválido |C7                     |
    @Test
    public void testValorNegativoLadoAGeraErro() throws LadoInvalidoException{
        int LA = -2;
        int LB = 4;
        int LC = 5;        

        Exception exception = assertThrows(LadoInvalidoException.class, () -> {
            Triangulo.classificaTriangulo(LA, LB, LC);
        });


        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(MSG_INSIDE_EXCEPTION));
    }

    //ID |7 | 4,-2,5   |ERRO – valor inválido |C8                     |
    @Test
    public void testValorNegativoLadoBGeraErro() throws LadoInvalidoException{
        int LA = 4;
        int LB = -2;
        int LC = 5;        

        Exception exception = assertThrows(LadoInvalidoException.class, () -> {
            Triangulo.classificaTriangulo(LA, LB, LC);
        });


        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(MSG_INSIDE_EXCEPTION));
    }

    //ID |8 | 4,5,-2   |ERRO – valor inválido |C9                     |
    @Test
    public void testValorNegativoLadoCGeraErro() throws LadoInvalidoException{
        int LA = 4;
        int LB = 5;
        int LC = -2;        

        Exception exception = assertThrows(LadoInvalidoException.class, () -> {
            Triangulo.classificaTriangulo(LA, LB, LC);
        });


        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(MSG_INSIDE_EXCEPTION));
    }


    //ID |9 | 10, 2, 3 |Não forma triângulo   |C10.1                  |
    @Test
    public void testNaoFormaTrianguloAMaior() throws LadoInvalidoException{
        int LA = 10;
        int LB = 2;
        int LC = 3;
        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals("NAO FORMA TRIANGULO",result);
    }

    //ID 10| 2, 10, 3 |Não forma triângulo   |C10.2                  |
    @Test
    public void testNaoFormaTrianguloBMaior() throws LadoInvalidoException{
        int LA = 2;
        int LB = 10;
        int LC = 3;
        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals("NAO FORMA TRIANGULO",result);
    }

    //ID 11|2, 3, 10  |Não forma triângulo   |C10.3                  |
    @Test
    public void testNaoFormaTrianguloCMaior() throws LadoInvalidoException{
        int LA = 2;
        int LB = 2;
        int LC = 10;
        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals("NAO FORMA TRIANGULO",result);
    }   

    //Inicio testes de análise de Valor Limite
    //ID 12| 0, 3, 4 |ERRO – valor inválido |v5            |
    @Test
    public void testValorZeroLadoALancaException() throws LadoInvalidoException{
        int LA = 0;
        int LB = 3;
        int LC = 4;

        Exception exception = assertThrows(LadoInvalidoException.class, () -> {
            Triangulo.classificaTriangulo(LA, LB, LC);
        });

        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(MSG_INSIDE_EXCEPTION));
    }
    
    //ID 13| -1, 3, 4|ERRO – valor inválido |v6            |
    @Test
    public void testValorNegativoLadoALancaException() throws LadoInvalidoException{
        int LA = -1;
        int LB = 3;
        int LC = 4;

        Exception exception = assertThrows(LadoInvalidoException.class, () -> {
            Triangulo.classificaTriangulo(LA, LB, LC);
        });

        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(MSG_INSIDE_EXCEPTION));
    }
    
    //ID14| 1, 2, 2 |Isósceles             |v1            |
    @Test
    public void testRetornaIsoscelesLadoAMenorCorretamente() throws LadoInvalidoException{
        int LA = 1;
        int LB = 2;
        int LC = 2;

        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals(MSG_ISOSCELES,result);
    }
    
    //ID 15| 3, 0, 4 |ERRO – valor inválido |v7            |
    @Test
    public void testValorZeroLadoBLancaException() throws LadoInvalidoException{
        int LA = 3;
        int LB = 0;
        int LC = 4;

        Exception exception = assertThrows(LadoInvalidoException.class, () -> {
            Triangulo.classificaTriangulo(LA, LB, LC);
        });

        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(MSG_INSIDE_EXCEPTION));
    }
    
    //ID 16| 3, -1, 4|ERRO – valor inválido |v8            |
    @Test
    public void testValorNegativoLadoBLancaException() throws LadoInvalidoException{
        int LA = 3;
        int LB = -1;
        int LC = 4;

        Exception exception = assertThrows(LadoInvalidoException.class, () -> {
            Triangulo.classificaTriangulo(LA, LB, LC);
        });

        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(MSG_INSIDE_EXCEPTION));
    }

    //ID 17| 2, 1, 2 |Isósceles             |v2            |
    @Test
    public void testRetornaIsoscelesLadoBMenorCorretamente() throws LadoInvalidoException{
        int LA = 2;
        int LB = 1;
        int LC = 2;

        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals(MSG_ISOSCELES,result);
    }
    
    //ID 18| 2, 3, 0 |ERRO – valor inválido |v9            |
    @Test
    public void testValorZeroLadoCLancaException() throws LadoInvalidoException{
        int LA = 2;
        int LB = 3;
        int LC = 0;

        Exception exception = assertThrows(LadoInvalidoException.class, () -> {
            Triangulo.classificaTriangulo(LA, LB, LC);
        });

        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(MSG_INSIDE_EXCEPTION));
    }
    
    //ID 19| 2, 3, -1|ERRO – valor inválido |v10           |            |
    @Test
    public void testValorNegativoLadoCLancaException() throws LadoInvalidoException{
        int LA = 2;
        int LB = 3;
        int LC = -1;

        Exception exception = assertThrows(LadoInvalidoException.class, () -> {
            Triangulo.classificaTriangulo(LA, LB, LC);
        });

        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(MSG_INSIDE_EXCEPTION));
    }

    //ID 20| 2, 2, 1 |Isósceles             |v3            |
    @Test
    public void testRetornaIsoscelesLadoCMenorCorretamente() throws LadoInvalidoException{
        int LA = 2;
        int LB = 2;
        int LC = 1;

        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals(MSG_ISOSCELES,result);
    }
    
    //ID 21| 1, 1, 1 |Equilátero            |v4            |
    @Test
    public void testRetornaEquilateroLadosTodos1() throws LadoInvalidoException{
        int LA = 1;
        int LB = 1;
        int LC = 1;

        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals(MSG_EQUILATERO,result);
    }
    
    //ID 22| 5, 2, 3 |Não forma triângulo   |v11           |
    @Test
    public void testNaoFormaTrianguloV11() throws LadoInvalidoException{
        int LA = 5;
        int LB = 2;
        int LC = 3;

        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals(MSG_NAO_FORMA_TRIANGULO,result);
    }
    
    //ID 23|2, 5, 3  |Nao forma triângulo   |v12           |
    @Test
    public void testNaoFormaTrianguloV12() throws LadoInvalidoException{
        int LA = 2;
        int LB = 5;
        int LC = 3;

        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals(MSG_NAO_FORMA_TRIANGULO,result);
    }
    
    //ID 24|2, 3, 5  |Nao forma triângulo   |v13           |
    @Test
    public void testNaoFormaTrianguloV13() throws LadoInvalidoException{
        int LA = 2;
        int LB = 3;
        int LC = 5;

        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals(MSG_NAO_FORMA_TRIANGULO,result);
    }
}