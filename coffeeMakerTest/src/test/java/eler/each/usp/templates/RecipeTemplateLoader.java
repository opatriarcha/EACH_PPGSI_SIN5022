/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eler.each.usp.templates;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import each.usp.ach2006.adaptedfromcsc326.eler.Recipe;

/**
 *
 * @author orlando
 */
public class RecipeTemplateLoader implements TemplateLoader{     

    public void load() {
        Fixture.of(Recipe.class).addTemplate("valid01", new Rule(){{
            add("name", "Receita Valida 1");
            add("price", 1);
            add("amtCoffee", 1);
            add("amtMilk", 1);
            add("amtSugar", 1);
            add("amtChocolate", 1);            
            
        }});
        
        Fixture.of(Recipe.class).addTemplate("valid02", new Rule(){{
            add("name", "Receita Valida 2");
            add("price", 2);
            add("amtCoffee", 2);
            add("amtMilk", 2);
            add("amtSugar", 2);
            add("amtChocolate", 2);            
            
        }});
        
        Fixture.of(Recipe.class).addTemplate("valid03", new Rule(){{
            add("name", "Receita Valida 3");
            add("price", 2);
            add("amtCoffee", 2);
            add("amtMilk", 2);
            add("amtSugar", 2);
            add("amtChocolate", 2);            
            
        }});
        
        Fixture.of(Recipe.class).addTemplate("valid5UnitsEach", new Rule(){{
            add("name", "Receita Valida 3");
            add("price", 5);
            add("amtCoffee", 5);
            add("amtMilk", 5);
            add("amtSugar", 5);
            add("amtChocolate", 5);            
            
        }});
        
        Fixture.of(Recipe.class).addTemplate("nameEmpty").inherits("valid01", new Rule(){{
            add("name", "");
        }});
        
        Fixture.of(Recipe.class).addTemplate("nameOnlySpaces").inherits("valid01", new Rule(){{
            add("name", "  ");
        }});
        
        Fixture.of(Recipe.class).addTemplate("negativePrice").inherits("valid01", new Rule(){{
            add("price", -1);
        }});
        
        Fixture.of(Recipe.class).addTemplate("priceZero").inherits("valid01", new Rule(){{
            add("price", 0);
        }});
        
        Fixture.of(Recipe.class).addTemplate("cofeeNegative").inherits("valid5UnitsEach", new Rule(){{
            add("amtCoffee", -1);
        }});
        
        Fixture.of(Recipe.class).addTemplate("chocolateNegative").inherits("valid5UnitsEach", new Rule(){{
            add("amtChocolate", -1);
        }});
        
        Fixture.of(Recipe.class).addTemplate("sugarNegative").inherits("valid5UnitsEach", new Rule(){{
            add("amtSugar", -1);
        }});
        
        Fixture.of(Recipe.class).addTemplate("milkNegative").inherits("valid5UnitsEach", new Rule(){{
            add("amtMilk", -1);
        }});
        
        Fixture.of(Recipe.class).addTemplate("RecipeAllZeroes").inherits("valid01", new Rule(){{
            add("amtCoffee", 0);
            add("amtMilk", 0);
            add("amtSugar", 0);
            add("amtChocolate", 0);            
        }});
        
        Fixture.of(Recipe.class).addTemplate("recipeOnlyCoffee").inherits("RecipeAllZeroes", new Rule(){{
            add("amtCoffee", 1);            
        }});
        
        Fixture.of(Recipe.class).addTemplate("recipeOnlyMilk").inherits("RecipeAllZeroes", new Rule(){{
            add("amtMilk", 1);            
        }});
        
        Fixture.of(Recipe.class).addTemplate("recipeOnlySugar").inherits("RecipeAllZeroes", new Rule(){{
            add("amtSugar", 1);            
        }});
        
        Fixture.of(Recipe.class).addTemplate("recipeOnlyChocolate").inherits("RecipeAllZeroes", new Rule(){{
            add("amtChocolate", 1);            
        }});
        
    }
    
}
