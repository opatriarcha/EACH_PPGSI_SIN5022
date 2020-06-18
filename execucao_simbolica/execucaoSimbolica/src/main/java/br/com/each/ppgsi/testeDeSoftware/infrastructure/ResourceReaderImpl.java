package br.com.each.ppgsi.testeDeSoftware.infrastructure;

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
public class ResourceReaderImpl implements IResourceReader {
    
    private ResourceReaderImpl(){
        
    }
    
    public static ResourceReaderImpl getInstance(){
        return new ResourceReaderImpl();
    }
    
    @Override
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
    
    @Override
    public void printResource( final List<String> resource ){
        for( String str : resource ){
            System.out.println(str);
        }    
    }
    
}
