package data.types;

/**
 *
 * @author emil
 */
public abstract class Type {
    
    public static Type getType(String s) {
        switch (s) {
            case "Area": return TypeArea.getInstance();
            case "Length": return TypeLength.getInstance();
            case "Piece": return TypePiece.getInstance();
            case "Volume": return TypeVolume.getInstance();
            case "area": return TypeArea.getInstance();
            case "length": return TypeLength.getInstance();
            case "piece": return TypePiece.getInstance();
            case "volume": return TypeVolume.getInstance();
            default: throw new RuntimeException("Wrong type pattern");
        }
    }
    
    public abstract String getKey();
    public abstract String getDisplayName();
    public abstract String getUnit();
    public abstract double calcValue(double[] values);
    
}
