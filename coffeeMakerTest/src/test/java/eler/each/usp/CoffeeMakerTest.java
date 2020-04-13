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
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

/**
 *
 * @author orlando
 */
public class CoffeeMakerTest  extends TestCase{
    
    static{ //working gabiarra
        FixtureFactoryLoader.loadTemplates("eler.each.usp.templates");
    }
    
    @Test
    public void testCoffeeMaker() throws Exception{
        CoffeeMaker cm = new CoffeeMaker();
        cm.addRecipe(gimmeRecipe("valid01"));
        cm.addRecipe(gimmeRecipe("valid02"));
        cm.addRecipe(gimmeRecipe("valid03"));                
    }
    
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
