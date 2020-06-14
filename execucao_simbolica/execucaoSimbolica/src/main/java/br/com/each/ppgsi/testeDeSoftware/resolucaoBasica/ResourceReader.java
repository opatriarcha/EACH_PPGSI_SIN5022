/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.each.ppgsi.testeDeSoftware.resolucaoBasica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author orlando
 */
public class ResourceReader {
    
    private ResourceReader(){
        
    }
    
    public static ResourceReader getInstance(){
        return new ResourceReader();
    }
    
    public List<String> read(final String fileName ){
        List<String> resultSet = new ArrayList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/" + fileName)));
            while(br != null && br.ready()){
                String linha = br.readLine();
                resultSet.add(linha);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }
       return resultSet;
    }
    
    public void printResource( final List<String> resource ){
        for( String str : resource ){
            System.out.println(str);
        }    
    }
    
    public static void main(String[] args) {
        ResourceReader reader = ResourceReader.getInstance();
        List<String> resultSet = reader.read("exemplorestricoes.txt");
        reader.printResource(resultSet);
         
    }
    
}
