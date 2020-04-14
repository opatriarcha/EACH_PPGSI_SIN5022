/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eler.each.usp;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import each.usp.ach2006.adaptedfromcsc326.eler.CoffeeMaker;
import each.usp.ach2006.adaptedfromcsc326.eler.Recipe;
import each.usp.ach2006.adaptedfromcsc326.eler.exceptions.DuplicatedRecipeException;
import each.usp.ach2006.adaptedfromcsc326.eler.exceptions.RecipeException;
import junit.framework.TestCase;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

/**
 *
 * @author orlando
 */
public class CoffeeMakerTest  extends TestCase{
    
    static{ //working gabiarra
        FixtureFactoryLoader.loadTemplates("eler.each.usp.templates");
    }
    /**
     * 



     * @throws Exception 
     */
    @Test// instancia corretamente
    public void testCoffeeMaker() throws Exception{
        CoffeeMaker cm = new CoffeeMaker();
        cm.addRecipe(gimmeRecipe("valid01"));
        cm.addRecipe(gimmeRecipe("valid02"));
        cm.addRecipe(gimmeRecipe("valid03"));                        
    }
    
    //ID 1   |checkChocolateInventory    |sem adicionar estoque       |   20 unidades    C1     | 20 < estoque < 20 C2, C3               |
    //ID 2   |addChocolateInventory      |adiciona 10 unidades        |   30 unidades    C4     | 30 < estoque < 30 C5, C6               |
    //ID 3   |checkChocolateInventory    |após adicionar as 10 unidades    |   30 unidades    C7     | estoque=30 demais 20 C8, C9               |
    @Test
    public void testChocolateInventoryAddAndCheck() throws Exception{
        CoffeeMaker cm = new CoffeeMaker();
        assertEquals( cm.checkChocolateInventory(), 20);        
        cm.addChocolateInventory(10);
        assertEquals( cm.checkChocolateInventory(), 30);        
        assertEquals( cm.checkCoffeeInventory(), 20);
        assertEquals( cm.checkMilkInventory(), 20);
        assertEquals( cm.checkSugarInventory(), 20);
    }
    
    //ID4   |checkCoffeeInventory       |sem adicionar estoque       |   20 unidades    C10    | 20 < estoque < 20 C11, C12             |
    //ID5   |addCoffeeInventory         |adiciona 10 unidades        |   30 unidades    C13    | 30 < estoque < 30 C14, C15             |
    //ID 6   |checkCoffeeInventory       |adicionar as 10 unidades    |   30 unidades    C16    | estoque=30 demais 20 C17, C18             |
    @Test
    public void testCoffeeInventoryAddAndCheck() throws Exception{
        CoffeeMaker cm = new CoffeeMaker();
        assertEquals( cm.checkCoffeeInventory(), 20);        
        cm.addCoffeeInventory(10);
        assertEquals( cm.checkCoffeeInventory(), 30);
        assertEquals( cm.checkChocolateInventory(), 20);                
        assertEquals( cm.checkMilkInventory(), 20);
        assertEquals( cm.checkSugarInventory(), 20);        
    }
    
    //ID 7   |checkMilkInventory         |sem adicionar estoque       |   20 unidades    C19    | 20 < estoque < 20 C20, C21             |
    //ID 8   |addMilkInventory           |adiciona 10 unidades        |   30 unidades    C22    | 30 < estoque < 30 C23, C24             |
    //ID 9   |checkMilkInventory         |adicionar as 10 unidades    |   30 unidades    C25    | estoque=30 demais 20 C26, C27             |
    @Test
    public void testMilkInventoryAddAndCheck() throws Exception{
        CoffeeMaker cm = new CoffeeMaker();
        assertEquals( cm.checkMilkInventory(), 20);        
        cm.addMilkInventory(10);
        assertEquals( cm.checkMilkInventory(), 30);  
        assertEquals( cm.checkChocolateInventory(), 20);        
        assertEquals( cm.checkCoffeeInventory(), 20);        
        assertEquals( cm.checkSugarInventory(), 20);
    }
    
    //ID 10  |checkSugarInventory        |sem adicionar estoque       |   20 unidades    C28    | 20 < estoque < 20 C29, C30             |
    //ID 11  |addSugarInventory          |adiciona 10 unidades        |   30 unidades    C31    | 30 < estoque < 30 C32, C33             |
    //ID 12  |checkSugarInventory        |adicionar as 10 unidades    |   30 unidades    C34    | estoque=30 demais 20 C35, C36             |
    @Test
    public void testSugarInventoryAddAndCheck() throws Exception{
        CoffeeMaker cm = new CoffeeMaker();
        assertEquals( cm.checkSugarInventory(), 20);        
        cm.addSugarInventory(10);
        assertEquals( cm.checkSugarInventory(), 30);
        assertEquals( cm.checkChocolateInventory(), 20);        
        assertEquals( cm.checkCoffeeInventory(), 20);
        assertEquals( cm.checkMilkInventory(), 20);        
    }
    
    //Colocar um assert por teste, caso um falhe ( como é o caso) não executará os outros desta maneira.
    
    //ID 13  |addRecipe                  |A receita (nome)            |   3 com nomes diferentes C37 | duas om nomes iguais C38          |
    @Test
    public void testAddRecipeNameValid() throws Exception{
        CoffeeMaker cm = new CoffeeMaker();
        boolean recipe1 = cm.addRecipe(gimmeRecipe("valid01"));
        boolean recipe2 = cm.addRecipe(gimmeRecipe("valid02"));
        boolean recipe3 = cm.addRecipe(gimmeRecipe("valid03"));
        assertTrue(recipe1 && recipe2 && recipe3);
    }
    
    //ID 13  |addRecipe                  |A receita (nome)            |   3 com nomes diferentes C37 | duas om nomes iguais C38          |
    @Test
    public void testAddThreeRecipleNameEqual() throws Exception{
        CoffeeMaker cm = new CoffeeMaker();
        assertTrue(cm.addRecipe(gimmeRecipe("valid01")));
        assertThrows(DuplicatedRecipeException.class, () -> cm.addRecipe(gimmeRecipe("valid01")));
        
    }
    
     //ID 14  |addRecipe                  |A receita (qtd)             |   3 com nomes diferentes C37 | 4 receitas    C39                 |    
    @Test
    public void testAddfourRecipes() throws Exception{
        CoffeeMaker cm = new CoffeeMaker();
        assertTrue(cm.addRecipe(gimmeRecipe("valid01")));
        assertTrue(cm.addRecipe(gimmeRecipe("valid02")));
        assertTrue(cm.addRecipe(gimmeRecipe("valid03")));
        assertThrows(DuplicatedRecipeException.class, () -> gimmeRecipe("valid01")); //ERRO aceirtou 4 receitas        
    }

//ID 15  |getRecipes                 |inserir receitas            |   3 com nomes diferentes C37 |                                   |
//ID 17  |deleteRecipe               |inserir receitas antes      |   quantidade Correta C38| Quantidade incorreta C39               |    
    @Test
    public void testDeleteRecipes() throws Exception{
        CoffeeMaker cm = new CoffeeMaker();
        assertTrue(cm.addRecipe(gimmeRecipe("valid01")));
        assertTrue(cm.addRecipe(gimmeRecipe("valid02")));
        assertTrue(cm.addRecipe(gimmeRecipe("valid03")));
        assertEquals(3, cm.getRecipes().size());
        assertTrue(cm.deleteRecipe(gimmeRecipe("valid03").getName())); 
        assertTrue(cm.deleteRecipe(gimmeRecipe("valid02").getName())); 
        assertTrue(cm.deleteRecipe(gimmeRecipe("valid01").getName())); //ERRO nao deleta primeira receita
        assertEquals(0, cm.getRecipes().size());
    }
    
    //ID 18  |deleteRecipe               |receita                     |   nome existente  C40   | nome inexistente C41, nome nulo C42    |     
    @Test
    public void testShouldNotDeleteRecipesWithEmptyName() throws Exception{
        CoffeeMaker cm = new CoffeeMaker();
        assertTrue(cm.addRecipe(gimmeRecipe("valid01")));        
        assertThrows(RecipeException.class, () -> cm.deleteRecipe(" "));         
        assertEquals(1, cm.getRecipes().size());
    }
    
    //ID 19  |addRecipe                  |receita (quantidades)       |   qtds diferentes  C43  | 2 qtds iguais, 3 qtds iguais    C44    |
    @Test
    public void testShouldNotAddRecipesWithSame2Ingredients () throws Exception{
        CoffeeMaker cm = new CoffeeMaker();
        assertTrue(cm.addRecipe(gimmeRecipe("recipeDuplicated1")));        
        assertThrows(DuplicatedRecipeException.class, () -> cm.addRecipe(gimmeRecipe("recipeDuplicated2")));         
        
    }
    
    |20  |makeCoffee                 |   receita, valor           |  receita existente  C45 | receita inexistente             C46    |
|21  |makeCoffee                 |   receita, valor           |  valor coerente     C47 | valor menor que o necessário     C48   |
|22  | addChocolateInventory     |  parametro unico           | 10, 80   v1, v2      | param=0 V3, param=-1 v4, param = 100 v5   |
|23  | addCoffeeInventory        |  parametro unico           | 10, 80   v6, v7      | param=0 V8, param=-1 v9, param = 100 v10  |
|24  | addMilkInventory          |  parametro unico           | 10, 80   v11, v12    | param=0 V13, param=-1 v14, param = 100 v15|
|25  | addSugarInventory         |  parametro unico           | 10, 80   v16, v17    | param=0 V18, param=-1 v19, param = 100 v20|
    /**
     * Return the correct Fixture or translate the inner exception.
     * @param fixtureName The fixture name from template
     * @return the fixture complete.
     * @throws Exception the inner exception raised.
     */
    private Recipe gimmeCofeeMaker(String fixtureName) throws Exception{
        try{
            return Fixture.from(CoffeeMaker.class).gimme(fixtureName);
        }catch(Exception ex){
            Exception innerException = (Exception) ex.getCause();
            throw innerException;
        }        
    }
    
    /**
     * Return the correct Fixture or translate the inner exception.
     * @param fixtureName The fixture name from template
     * @return the fixture complete.
     * @throws Exception the inner exception raised.
     */
    private Recipe gimmeRecipe(String fixtureName) throws Exception{
        try{
            Recipe recipe = Fixture.from(Recipe.class).gimme(fixtureName);
            //return Fixture.from(Recipe.class).gimme(fixtureName);
            return recipe;
        }catch(Exception ex){
            Exception innerException = (Exception) ex.getCause();
            throw innerException;
        }        
    }            
}
