package data;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonValue;

/**
 *
 * @author emil
 */
public class Products implements JsonExport {
    
    private static Products instance;
    
    private final List<Product> products = new ArrayList<>();

    public static Products getInstance() {
        if (instance == null) {
            instance = new Products();
        }
        return instance;
    }
    
    private Products() {}

    public List<Product> getProducts() {
        return products;
    }

    public int size() {
        return products.size();
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public boolean add(Product e) {
        boolean rv = products.add(e);
        Collections.sort(products);
        return rv;
    }

    public boolean remove(Object o) {
        boolean rv = products.remove(o);
        Collections.sort(products);
        return rv;
    }

    public Product get(int index) {
        return products.get(index);
    }

    public Product set(int index, Product element) {
        Product rv = products.set(index, element);
        Collections.sort(products);
        return rv;
    }

    public Product remove(int index) {
        Product rv = products.remove(index);
        Collections.sort(products);
        return rv;
    }

    @Override
    public void writeTo(BufferedWriter w) throws IOException {
        Collections.sort(products);
        
        JsonArrayBuilder ab = Json.createArrayBuilder();
        
        for (Product p : products) {
            ab.add(p.toJsonObject());
        }
        JsonArray values = ab.build();
        
        JsonObjectBuilder ob = Json.createObjectBuilder();
        ob.add("Products", values);
        
        JsonObject json = ob.build();
        w.write(json.toString());
    }

    @Override
    public void loadInto(InputStream fis) throws IOException {
        products.clear();
        
        JsonObject jsonObj;
        try (JsonReader jsonReader = Json.createReader(fis)) {
            jsonObj = jsonReader.readObject();
        }
        
        JsonArray array = jsonObj.getJsonArray("Products");
        for (JsonValue value : array) {
            products.add(new Product(value.asJsonObject()));
        }
        Collections.sort(products);
    }
    
}
