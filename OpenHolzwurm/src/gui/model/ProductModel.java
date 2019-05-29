package gui.model;

import data.Product;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author emil
 */
public class ProductModel extends AbstractTableModel {
    
    private final String[] colNames = {"Material", "Menge", "Kosten"};
    private final Product product;

    public ProductModel(Product product) {
        this.product = product;
    }

    @Override
    public int getRowCount() {
        return product.size();
    }

    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case 0: return product.get(rowIndex).getPreset().getName();
            case 1: return product.get(rowIndex).getValue();
            case 2: return product.get(rowIndex).getPrice();
            default: throw new RuntimeException("Wrong column index");
        }
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }

}
