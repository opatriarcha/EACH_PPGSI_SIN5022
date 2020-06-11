/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.each.ppgsi.testeDeSoftware.resolucaoBasica;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author orlando
 */
public class ResourceWriter {

    public void writeFile(final String filepath, final String content) {
        BufferedWriter bufferedWriter = null;
        try {
            File file = new File(filepath);

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
            } catch (Exception ex) {
                System.out.println("Error in closing the BufferedWriter" + ex);
            }
        }
    }
}
