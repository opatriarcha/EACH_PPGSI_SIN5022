package br.com.each.ppgsi.testeDeSoftware.infrastructure;

/**
 *
 * @author orlando
 */
public interface IResoureWriter {

    void deleteFile();

    void openFile();

    void writeFile(final String content);
    
}
