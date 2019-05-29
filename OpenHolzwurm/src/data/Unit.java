package data;

/**
 *
 * @author emil
 */
public enum Unit {
    
    MILLIMETER, CENTIMETER, METER, INCH;
    
    @Override
    public String toString() {
        switch(this) {
            case MILLIMETER: return "Millimeter";
            case CENTIMETER: return "Zentimeter";
            case METER: return "Meter";
            case INCH: return "Zoll";
            default: throw new RuntimeException("Wrong enum type");
        }
    }
    
    public static Unit setUnit(String s) {
        switch (s) {
            case "Millimeter": return MILLIMETER;
            case "Zentimeter": return CENTIMETER;
            case "Meter": return METER;
            case "Zoll": return INCH;
            default: throw new RuntimeException("Wrong enum String pattern");
        }
    }
    
    public double getMeter(double value) {
        switch(this) {
            case MILLIMETER: return value / 1000.;
            case CENTIMETER: return value / 100.;
            case METER: return value;
            case INCH: return value / 39.37;
            default: throw new RuntimeException("Wrong enum type");
        }
    }
}
