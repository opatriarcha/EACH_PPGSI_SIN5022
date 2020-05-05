package geometria;

import geometria.LadoInvalidoException;
import geometria.Triangulo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TrianguloTest {

    //C1,C2,C3,C4
    @Test
    public void testEquilatero() throws LadoInvalidoException{
        int LA = 5;
        int LB= 5;
        int LC = 5;
        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals("EQUILATERO",result);
    }


    //C1,C2,C3,C5.1
    @Test
    public void testIsosceles1() throws LadoInvalidoException{
        int LA = 5;
        int LB= 5;
        int LC = 6;
        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals("ISOSCELES",result);
    }

    //C1,C2,C3,C5.2
    @Test
    public void testIsosceles2() throws LadoInvalidoException{
        int LA = 6;
        int LB= 5;
        int LC = 5;
        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals("ISOSCELES",result);
    }

    //C1,C2,C3,C5.3
    @Test
    public void testIsosceles3() throws LadoInvalidoException{
        int LA = 6;
        int LB= 5;
        int LC = 6;
        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals("ISOSCELES",result);
    }

    //C1,C2,C3,C6
    @Test
    public void testEscaleno() throws LadoInvalidoException{
        int LA = 4;
        int LB= 5;
        int LC = 6;
        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals("ESCALENO",result);
    }

    //C7
    @Test
    public void testLAInvalido() throws LadoInvalidoException{
        int LA = -2;
        int LB= 4;
        int LC = 5;
        assertThrows(LadoInvalidoException.class, () -> Triangulo.classificaTriangulo(LA, LB, LC));
    }

    //C8
    @Test
    public void testLBInvalido() throws LadoInvalidoException{
        int LA = 4;
        int LB= -2;
        int LC = 5;
        assertThrows(LadoInvalidoException.class, () -> Triangulo.classificaTriangulo(LA, LB, LC));
    }

    //C9
    @Test
    public void testLCInvalido() throws LadoInvalidoException{
        int LA = 4;
        int LB= 5;
        int LC = -2;
        assertThrows(LadoInvalidoException.class, () -> Triangulo.classificaTriangulo(LA, LB, LC));
    }

    //C10.1
    @Test
    public void testNaoTriangulo1() throws LadoInvalidoException{
        int LA = 10;
        int LB= 2;
        int LC = 3;
        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals("NAO FORMA TRIANGULO",result);
    }

    //C10.2
    @Test
    public void testNaoTriangulo2() throws LadoInvalidoException{
        int LA = 2;
        int LB= 10;
        int LC = 3;
        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals("NAO FORMA TRIANGULO",result);
    }

    //C10.3
    @Test
    public void testNaoTriangulo3() throws LadoInvalidoException{
        int LA = 3;
        int LB= 2;
        int LC = 10;
        String result = Triangulo.classificaTriangulo(LA, LB, LC);
        assertEquals("NAO FORMA TRIANGULO",result);
    }


}
