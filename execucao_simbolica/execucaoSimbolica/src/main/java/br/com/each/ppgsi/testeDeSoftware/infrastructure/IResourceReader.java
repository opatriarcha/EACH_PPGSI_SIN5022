package br.com.each.ppgsi.testeDeSoftware.infrastructure;

import java.util.List;

/**
 *
 * @author orlando
 */
public interface IResourceReader {

    void printResource(final List<String> resource);

    List<String> read(final String fileName);
    
}
