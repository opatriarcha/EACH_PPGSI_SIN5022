package br.com.each.ppgsi.testeDeSoftware.resolucaoCompleta;

import jdk.jfr.DataAmount;
import lombok.Data;

/**
 *
 * @author orlando
 */
@Data
public class ResultSetHolder {
    
    public ResultSetHolder( String officialResult, String completeResult, String constraintResult ){
        this.officialResult = officialResult;
        this.completeResult = completeResult;
        this.constraintsResult = constraintResult;
    }
    
    private String officialResult;
    private String completeResult;
    private String constraintsResult;
    
   
}
