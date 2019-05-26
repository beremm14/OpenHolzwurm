package data;

/**
 *
 * @author emil
 */
public enum Type {
    
    PIECE, LENGTH, AREA, VOLUME;
    
    @Override
    public String toString() {
        switch(this) {
            case PIECE: return "Stück";
            case LENGTH: return "Länge";
            case AREA: return "Fläche";
            case VOLUME: return "Volumen";
            default: throw new RuntimeException("No type...");
        }
    }
    
    public String getUnit() {
        switch(this) {
            case PIECE: return "";
            case LENGTH: return "m";
            case AREA: return "m²";
            case VOLUME: return "m³";
            default: throw new RuntimeException("No unit...");
        }
    }
    
}
