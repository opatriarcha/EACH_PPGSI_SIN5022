package coffeemaker;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import coffeemaker.exceptions.*;

public class CoffeeMakerTest {

    private  CoffeeMaker CM;
    private  Recipe r1;
    private  Recipe r2;
    private  Recipe r3;
    private  Recipe r4;
    private  Recipe rZerado;

    @BeforeEach
    public  void setUp() throws Exception {
        CM = new CoffeeMaker();
        //System.out.println(CM.checkChocolateInventory());

        r1 = new Recipe("Coffee",50,4,0,1,0);
        r2 = new Recipe("Hot Chocolate",75,0,3,1,3);
        r3 = new Recipe("Latte",75,3,1,1,0);
        r4 = new Recipe("Mix",100,1,2,1,2);
    }



    /* Testando adicionar uma receita
     *
     * Primeiro critério de equivalência: quantidade de receitas
     * Classe válida: entre 1 e 3
     * Classe inválida: > 3
     *
     * Segundo critério de equivalência: nome
     * classe válida: nome único
     * classe inválida: nomes iguais
     *
     * Terceiro critério de equivalência: diferença de ingredientes
     * classe válida: número diferente de ingredientes
     * classe inválida: número igual de ingredientes
     */

    @Test
    public void testAddOneRecipe() throws AmountOfRecipeException, DuplicatedRecipeException{
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
    }

    @Test
    public void testAddThreeRecipes() throws AmountOfRecipeException, DuplicatedRecipeException{
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
        ok = CM.addRecipe(r2);
        assertTrue(ok);
        ok = CM.addRecipe(r3);
        assertTrue(ok);
    }

    @Test
    public void testAddFourRecipes() throws AmountOfRecipeException, DuplicatedRecipeException{
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
        ok = CM.addRecipe(r2);
        assertTrue(ok);
        ok = CM.addRecipe(r3);
        assertTrue(ok);
        assertThrows(AmountOfRecipeException.class, () -> CM.addRecipe(r4));
    }

    @Test
    public void testAddTwoRecipesSameName() throws AmountOfRecipeException, DuplicatedRecipeException, InvalidValueException{
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
        r2.setName(r1.getName());
        assertThrows(DuplicatedRecipeException.class, () -> CM.addRecipe(r2));
    }


    /* Testando remover receita
     *
     * Primeira classe de equivalência: existência da receita
     * classe válida: a receita existe na máquina
     * classe inválida: a receita não exsiste na máquina
     *
     */

    @Test
    public void testRemoveExistentRecipe() throws AmountOfRecipeException, DuplicatedRecipeException, RecipeException{
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
        ok = CM.addRecipe(r2);
        assertTrue(ok);
        ok = CM.addRecipe(r3);
        assertTrue(ok);
        boolean okDelete = CM.deleteRecipe(r3.getName());
        assertTrue(okDelete);
    }

    @Test
    public void testRemoveUnkownRecipe() throws AmountOfRecipeException, DuplicatedRecipeException, RecipeException{
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
        ok = CM.addRecipe(r2);
        assertTrue(ok);
        ok = CM.addRecipe(r3);
        assertTrue(ok);
        assertThrows(RecipeException.class, () -> CM.deleteRecipe(r3.getName()+"abc"));
    }

    /* Testando adicionar ingredientes ao estoque da máquina
     *
     * Primeira classe de equivalência: quantidade de unidades inserida
     * classe válida: unidade >= 0
     * classe inválida: unidade < 0
     *
     * Segunda classe de equivalência: quantidade total de unidades do ingrediente na máquina
     * classe válida: unidades ddo ingrediente na máquina <=100
     * classe inválida: unidades do ingrediente na máquina >100
     */

    @Test
    public void testAddCoffeeValidAmount() throws InvalidValueException{
        int amtCoffee = CM.checkCoffeeInventory();
        CM.addCoffeeInventory(10);
        assertEquals(amtCoffee+10,CM.checkCoffeeInventory());
    }

    @Test
    public void testAddCoffeeInvalidAmount() throws InvalidValueException{
        assertThrows(InvalidValueException.class, () -> CM.addCoffeeInventory(-10));
    }

    @Test
    public void testAddCoffeeAmountExceed() throws InvalidValueException{

        assertThrows(InvalidValueException.class, () -> CM.addCoffeeInventory(90));
    }


    @Test
    public void testAddMilkValidAmount() throws InvalidValueException{
        int amtMilk = CM.checkMilkInventory();
        CM.addMilkInventory(10);
        assertEquals(amtMilk+10,CM.checkMilkInventory());
    }

    @Test
    public void testAddMilkInvalidAmount() throws InvalidValueException{
        assertThrows(InvalidValueException.class, () -> CM.addMilkInventory(-10));
    }

    @Test
    public void testAddMilkAmountExceed() throws InvalidValueException{
        assertThrows(InvalidValueException.class, () -> CM.addMilkInventory(90));
    }

    @Test
    public void testAddSugarValidAmount() throws InvalidValueException{
        int amtSugar = CM.checkSugarInventory();
        CM.addSugarInventory(10);
        assertEquals(amtSugar+10,CM.checkSugarInventory());
    }

    @Test
    public void testAddSugarInvalidAmount() throws InvalidValueException{
        assertThrows(InvalidValueException.class, () -> CM.addSugarInventory(-10));
    }

    @Test
    public void testAddSugarAmountExceed() throws InvalidValueException{
        assertThrows(InvalidValueException.class, () -> CM.addSugarInventory(110));
    }

    @Test
    public void testAddChocolateValidAmount() throws InvalidValueException{
        //System.out.println(CM.checkChocolateInventory());
        int amtChocolate = CM.checkChocolateInventory();
        CM.addChocolateInventory(10);
        assertEquals(amtChocolate+10,CM.checkChocolateInventory());
    }


    @Test
    public void testAddChocolateInvalidAmount() throws InvalidValueException{
        assertThrows(InvalidValueException.class, () -> CM.addChocolateInventory(-10));
    }

    @Test
    public void testAddChocolateAmountExceed() throws InvalidValueException{

        assertThrows(InvalidValueException.class, () -> CM.addChocolateInventory(100));
    }

    /*Testando a preparação das receitas
     *
     * Primeira classe de equivalência: bebida selecionada
     * classe válida: a bebida selecionada existe
     * classe inválida: a bebida selecionada não existe
     *
     * Segunda classe de equivalência: valor pago
     * classe válida: o valor é o suficiente para pagar a bebida
     * classe inválida: o valor é insuficiente para pagar a bebida
     *
     * Terceira classe de equivalência: quantidade de ingredientes na máquina
     * classe válida: a quantidade de ingredientes é suficiente para fazer a bebida
     * classe inválida: a quantidade de ingredientes é insuficiente para fazer a bebida (cada um)
     */

	/*
	 * r1 = new Recipe("Coffee",50,4,0,1,0);
		r2 = new Recipe("Hot Chocolate",75,0,3,1,3);
		r3 = new Recipe("Latte",75,3,1,1,0);
		r4 = new Recipe("Mix",100,1,2,1,2);
	 */

    @Test
    public void testMakeRecipe() throws InvalidValueException, InsufficientAmountOfMoneyException, RecipeException, InventoryException, AmountOfRecipeException, DuplicatedRecipeException{
        boolean ok = CM.addRecipe(r4);
        assertTrue(ok);
        ok = CM.addRecipe(r2);
        assertTrue(ok);
        ok = CM.addRecipe(r3);
        assertTrue(ok);
        int coffee=CM.checkCoffeeInventory();
        int milk = CM.checkMilkInventory();
        int sugar = CM.checkSugarInventory();
        int choco = CM.checkChocolateInventory();
        int change = CM.makeCoffee("Mix", 150);
        assertEquals(50,change);
        //check inventory (comecou com 20 unidades cada ingrediente)
        assertEquals(coffee-1,CM.checkCoffeeInventory());
        assertEquals(milk-2,CM.checkMilkInventory());
        assertEquals(sugar-1,CM.checkSugarInventory());
        assertEquals(choco-2,CM.checkChocolateInventory());
    }

    @Test
    public void testMakeRecipeInsufficientMoney() throws InvalidValueException, InsufficientAmountOfMoneyException, RecipeException, InventoryException, AmountOfRecipeException, DuplicatedRecipeException{
        boolean ok = CM.addRecipe(r4);
        ok = CM.addRecipe(r2);
        ok = CM.addRecipe(r3);
        assertThrows(InsufficientAmountOfMoneyException.class, () -> CM.makeCoffee("Mix", 90));
    }


    @Test
    public void testMakeRecipeInsufficientMilk() throws InsufficientAmountOfMoneyException, RecipeException, InventoryException, AmountOfRecipeException, DuplicatedRecipeException, InvalidValueException{
        boolean ok = CM.addRecipe(r1);
        ok = CM.addRecipe(r2);
        r3.setAmtMilk(21);
        ok = CM.addRecipe(r3);
        assertThrows(InventoryException.class, () -> CM.makeCoffee("Latte", 90));
    }


    @Test
    public void testMakeRecipeInsufficientSugar() throws InsufficientAmountOfMoneyException, RecipeException, InventoryException, AmountOfRecipeException, DuplicatedRecipeException, InvalidValueException{
        boolean ok = CM.addRecipe(r1);
        ok = CM.addRecipe(r2);
        r3.setAmtSugar(21);
        ok = CM.addRecipe(r3);
        assertThrows(InventoryException.class, () -> CM.makeCoffee("Latte", 90));
    }

    @Test
    public void testMakeRecipeInsufficientChocolate() throws InsufficientAmountOfMoneyException, RecipeException, InventoryException, AmountOfRecipeException, DuplicatedRecipeException, InvalidValueException{
        boolean ok = CM.addRecipe(r1);
        ok = CM.addRecipe(r2);
        r3.setAmtChocolate(21);
        ok = CM.addRecipe(r3);
        assertThrows(InventoryException.class, () -> CM.makeCoffee("Latte", 90));
    }

    /* Testando obter receitas
     *
     */

    @Test
    public void testGetRecipes() throws AmountOfRecipeException, DuplicatedRecipeException{
        boolean ok = CM.addRecipe(r1);
        ok = CM.addRecipe(r2);
        ok = CM.addRecipe(r3);
        Vector<Recipe> recipes = CM.getRecipes();
        assertEquals(3,recipes.size());
        assertTrue(recipes.contains(r1));
        assertTrue(recipes.contains(r2));
        assertTrue(recipes.contains(r3));
    }


    //Com base na análise estrutural - alternar ingredientes parecidos na análise de receitas parecidas
    @Test
    public void testAddTwoRecipesSimilarIgredients1() throws AmountOfRecipeException, DuplicatedRecipeException, InvalidValueException{
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
        r2.setAmtChocolate(r1.getAmtChocolate());
        r2.setAmtCoffee(7);
        r2.setAmtMilk(7);
        r2.setAmtSugar(7);

        ok = CM.addRecipe(r2);
        assertTrue(ok);
    }

    @Test
    public void testAddTwoRecipesSimilarIgredients2() throws AmountOfRecipeException, DuplicatedRecipeException, InvalidValueException{
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
        r2.setAmtChocolate(r1.getAmtChocolate());
        r2.setAmtCoffee(r1.getAmtCoffee());
        r2.setAmtMilk(7);
        r2.setAmtSugar(7);

        ok = CM.addRecipe(r2);
        assertTrue(ok);
    }

    @Test
    public void testAddTwoRecipesSimilarIgredients3() throws AmountOfRecipeException, DuplicatedRecipeException, InvalidValueException{
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
        r2.setAmtChocolate(r1.getAmtChocolate());
        r2.setAmtCoffee(r1.getAmtCoffee());
        r2.setAmtMilk(r1.getAmtMilk());
        r2.setAmtSugar(4);

        ok = CM.addRecipe(r2);
        assertTrue(ok);
    }

    /*ESCREVA A PARTIR DAQUI OS CASOS DE TESTE ADICIONAIS
    PARA ATINGIR 100% DE BRANCH COVERAGE DE TODAS AS CLASSES DO PROGRAMA*/

    @Test //Não é possivel atender a qualquer um dos branches pois são mutuamente excludentes
    public void negativeAmountPaidShouldThrowException() throws InvalidValueException, DuplicatedRecipeException, AmountOfRecipeException {
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
        assertThrows(InventoryException.class, () -> CM.makeCoffee(r1.getName(), -1));
    }

    @Test //Não é possivel testar as duas condiçõe spor serem mutuamente excludentes.
    public void largePositiveAmountPaidShouldThrowException() throws InvalidValueException, DuplicatedRecipeException, AmountOfRecipeException {
        boolean ok = CM.addRecipe(r1);assertTrue(ok);
        assertThrows(InventoryException.class, () -> CM.makeCoffee(r1.getName(), 1000));
    }

    @Test
    public void inexistentRecipeShouldThrowException() throws InvalidValueException, DuplicatedRecipeException, AmountOfRecipeException {
        boolean ok = CM.addRecipe(r1);
        assertTrue(ok);
        assertThrows(RecipeException.class, () -> CM.makeCoffee("inexistente", 10));
    }

    @Test
    public void testAddMoreThen100ChocolateUnitsShouldThrowException() {
        assertThrows(InvalidValueException.class, () -> CM.addChocolateInventory(101));
    }

    @Test
    public void testRecipeWithZeroIngredientsShouldRaiseException(){
        assertThrows(InvalidValueException.class, () -> CM.addRecipe(new Recipe("Zerado",1,0,0,0,0)));
    }

    @Test
    public void testANullRecipeNameShouldRaiseException(){
        assertThrows(InvalidValueException.class, () -> CM.addRecipe(new Recipe(null,1,1,1,1,1)));
    }

    @Test
    public void testAEmptyRecipeNameShouldRaiseException(){
        assertThrows(InvalidValueException.class, () -> CM.addRecipe(new Recipe("",1,1,1,1,1)));
    }

    @Test
    public void testANegativePriceShouldRaiseException(){
        assertThrows(InvalidValueException.class, () -> CM.addRecipe(new Recipe("rcpt",-1,1,1,1,1)));
    }

    @Test
    public void testRecipeFullyDupicatedShouldRaiseException() throws DuplicatedRecipeException, AmountOfRecipeException, InvalidValueException {
        Recipe r1 = new Recipe("Coffee",50,1,1,1,1);
        Recipe r2 = new Recipe("Coffee2",50,1,1,1,1);

        CM.addRecipe(r1);
        assertThrows(DuplicatedRecipeException.class, () -> CM.addRecipe(r2));
    }
}
