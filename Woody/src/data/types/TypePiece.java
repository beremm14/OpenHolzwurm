package data.types;

/**
 *
 * @author emil
 */
public class TypePiece extends Type {

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
        return "";
    }

}
