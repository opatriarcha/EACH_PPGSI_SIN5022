package br.com.each.ppgsi.testeDeSoftware.resolucaoCompleta;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author orlando
 */
public class InstructionParser {
    
    public static InstructionParser getinstance(){
        return new InstructionParser();
    }
   
    
    public List<String> parseVariables( final String variables ){
        if(variables == null || variables.isBlank() || variables.isEmpty() || !variables.contains(","))
            return Collections.EMPTY_LIST;
        return Arrays.asList(variables.split(","));
    }
}
