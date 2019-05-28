package gui.model;

import data.Preset;
import data.Presets;
import javax.swing.AbstractListModel;

/**
 *
 * @author emil
 */
public class PresetListModel extends AbstractListModel<Preset> {

    @Override
    public int getSize() {
        return Presets.getInstance().size();
    }

    @Override
    public Preset getElementAt(int index) {
        return Presets.getInstance().get(index);
    }
    
}
