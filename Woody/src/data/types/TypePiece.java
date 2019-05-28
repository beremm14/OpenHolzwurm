package data.types;

/**
 *
 * @author emil
 */
public class TypePiece extends Type {
    
    private static TypePiece instance;
    
    public static TypePiece getInstance() {
        if (instance == null) {
            instance = new TypePiece();
        }
        return instance;
    }
    
    private TypePiece() {}

    @Override
    public String getKey() {
        return "Piece";
    }

    @Override
    public String getDisplayName() {
        return "Stück";
    }

    @Override
    public String getUnit() {
        return "Stk.";
    }

    @Override
    public String toString() {
        return "Stück";
    }

}
