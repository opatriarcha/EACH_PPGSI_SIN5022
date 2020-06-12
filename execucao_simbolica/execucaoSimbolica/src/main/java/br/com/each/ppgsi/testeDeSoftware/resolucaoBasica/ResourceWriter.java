package br.com.each.ppgsi.testeDeSoftware.resolucaoBasica;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author orlando
 */

public class ResourceWriter {
    
    private String fileName;
    
    public ResourceWriter(String fileName){
        this.fileName = fileName;
    }
    
    public void openFile(){
        File file = new File(this.fileName);
        if( file.exists() )
            try {
                file.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(ResourceWriter.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Cannot Create a new File in destination: " + this.fileName);
        }
    }
    

    public void writeFile(final String content) {
        BufferedWriter bufferedWriter = null;
        try {
            File file = new File(this.fileName);

            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fw);
            bufferedWriter.write(content);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException ex) {
                System.out.println("Error in closing the BufferedWriter" + ex);
            }
        }
    }
}
