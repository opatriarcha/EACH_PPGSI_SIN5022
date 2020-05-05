package coffeemaker;

import coffeemaker.exceptions.InvalidValueException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class RecipeTest {


        @Test
        public void RecipeTest01() throws InvalidValueException {
            Recipe recipe = new Recipe("rcp",1,1,1,1, 50);
        }

    /*Testando a criação de uma receita
     * Primeiro critério: valor dos itens de cada ingrediente e do preço
     * Classe válida: itens de ingrediente >=0 e preço > 0
     * Classes inválidas: itens de ingredientes <0 e preço <=0, ou todos ingredientes 0
     *
     * Segundo critério: nome
     * classe válida: nome válido
     * classe inválida: nome vazio
     */

    @Test
    public void testCreateValidRecipe() throws InvalidValueException {
        Recipe recipe = new Recipe("Coffee",50,4,0,1,0);
    }

    @Test
    public void testCreateInvalidRecipeAmtCoffee() throws InvalidValueException{
        assertThrows(InvalidValueException.class, () ->new Recipe("Coffee",50,-1,0,1,0));
    }

    @Test
    public void testCreateInvalidRecipeAmtMilk() throws InvalidValueException{
        assertThrows(InvalidValueException.class, () -> new Recipe("Coffee",50,4,-1,1,0));
    }

    @Test
    public void testCreateInvalidRecipeAmtChocolate() throws InvalidValueException{
        assertThrows(InvalidValueException.class, () -> new Recipe("Coffee",50,4,0,1,-1));
    }

    @Test
    public void testCreateInvalidRecipeZeroIngredients() throws InvalidValueException{
        assertThrows(InvalidValueException.class, () ->new Recipe("Coffee",50,0,0,0,0));
    }

    @Test
    public void testCreateEmptyRecipeName() throws InvalidValueException{
        assertThrows(InvalidValueException.class, () ->new Recipe("",50,4,1,1,1));
    }

    /*ESCREVA A PARTIR DAQUI OS CASOS DE TESTE ADICIONAIS
    PARA ATINGIR 100% DE BRANCH COVERAGE DE TODAS AS CLASSES DO PROGRAMA*/

}
