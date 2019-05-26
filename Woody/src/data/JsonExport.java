package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 *
 * @author emil
 */
public interface JsonExport {
    
    public void writeTo(BufferedWriter w) throws IOException;
    public void loadInto(BufferedReader r) throws IOException;
    
}
