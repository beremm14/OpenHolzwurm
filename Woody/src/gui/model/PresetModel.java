package gui.model;

import data.Presets;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author emil
 */
public class PresetModel extends AbstractTableModel {
    
    private final String[] colNames = {"Name", "Typ", "Kosten"};

    @Override
    public int getRowCount() {
        return Presets.getInstance().size();
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0: return Presets.getInstance().get(rowIndex).getName();
            case 1: return Presets.getInstance().get(rowIndex).getType().getDisplayName();
            case 2: return Presets.getInstance().get(rowIndex).getPrice() + " "
                         + Presets.getInstance().get(rowIndex).getType().getUnit();
            default: throw new RuntimeException("Wrong column index");
        }
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }

}
