/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eler.each.usp.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import each.usp.ach2006.adaptedfromcsc326.eler.Recipe;

/**
 *
 * @author orlando
 */
public class CoffeeMakerTemplateLoader implements TemplateLoader{        
    
    public void load() {
        Fixture.of(Recipe.class).addTemplate("valid01", new Rule(){{
            add("name", "Receita Valida 1");
            add("price", 1);
            add("amtCoffee", 1);
            add("amtMilk", 1);
            add("amtSugar", 1);
            add("amtChocolate", 1);            
            
        }});
    }
    
}
