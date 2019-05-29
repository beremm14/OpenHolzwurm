package data;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author emil
 */
public interface JsonExport {
    
    public void writeTo(BufferedWriter w) throws IOException;
    public void loadInto(InputStream fis) throws IOException;
    
}
