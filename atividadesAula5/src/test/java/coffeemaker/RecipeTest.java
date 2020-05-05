package coffeemaker;

import coffeemaker.exceptions.InvalidValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RecipeTest {

     private  Recipe r3;

    @BeforeEach
    public  void setUp() throws Exception {
        r3 = new Recipe("Latte",75,3,1,1,0);
    }

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

    @Test
    public void testRetrieveChocolateMethod() throws InvalidValueException{
        Recipe recipe = new Recipe("rcp",1,1,1,1, 50);
        assertEquals(50, recipe.getAmtChocolate());
    }

    @Test
    public void testRetrieveMilkMethod() throws InvalidValueException{
        Recipe recipe = new Recipe("rcp",1,1,1,1, 50);
        assertEquals(1, recipe.getAmtMilk());
    }

    @Test
    public void testRetrieveCoffeeMethod() throws InvalidValueException{
        Recipe recipe = new Recipe("rcp",1,1,1,1, 50);
        assertEquals(1, recipe.getAmtCoffee());
    }

    @Test
    public void testRetrieveSugarMethod() throws InvalidValueException{
        Recipe recipe = new Recipe("rcp",1,1,1,1, 50);
        assertEquals(1, recipe.getAmtSugar());
    }

    @Test
    public void testRetrievePriceMethod() throws InvalidValueException{
        Recipe recipe = new Recipe("rcp",1,1,1,1, 50);
        assertEquals(1, recipe.getPrice());
    }

    @Test
    public void testRetrieveNameMethod() throws InvalidValueException{
        Recipe recipe = new Recipe("rcp",1,1,1,1, 50);
        assertEquals("rcp", recipe.getName());
    }

    @Test
    public void testCreateInvalidCoffeeAmount() throws InvalidValueException{
        assertThrows(InvalidValueException.class, () ->new Recipe("aName",50,-4,1,1,1));
    }

    @Test
    public void testCreateInvalidSugarAmount() throws InvalidValueException{
        assertThrows(InvalidValueException.class, () ->new Recipe("aName",50,4,1,-1,1));
    }

    @Test
    public void testCreateInvalidPrice() throws InvalidValueException{
        assertThrows(InvalidValueException.class, () ->new Recipe("aName",0,4,1,1,1));
    }

    @Test
    public void testAddInventoryWithWrongQuantityOfChocolate() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () -> r3.setAmtChocolate(-1));
    }

    @Test
    public void testAddInventoryWithWrongQuantityOfCoffee() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () -> r3.setAmtCoffee(-1));
    }

    @Test
    public void testAddInventoryWithWrongQuantityOfSugar() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () -> r3.setAmtSugar(-1));
    }

    @Test
    public void testAddInventoryWithWrongQuantityOfMilk() throws InvalidValueException {
        assertThrows(InvalidValueException.class, () -> r3.setAmtMilk(-1));
    }

}
