package gui;

import data.Material;
import data.Preset;
import data.Presets;
import data.Product;
import data.Products;
import data.types.TypePiece;
import gui.model.OverviewModel;
import gui.model.PresetModel;
import java.awt.Dimension;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author emil
 */
public class Woody extends javax.swing.JFrame {

    private final OverviewModel overviewModel = new OverviewModel();
    private final PresetModel presetModel = new PresetModel();

    /**
     * Creates new form Woody
     */
    public Woody() {
        initComponents();

        setTitle("Woody");
        setSize(new Dimension(1000, 750));
        setLocationRelativeTo(null);

        jTablePresets.setModel(presetModel);
        jTableProducts.setModel(overviewModel);

        Presets.getInstance().add(new Preset("Räder", new TypePiece(), 0.20));
        presetModel.fireTableDataChanged();

        Products.getInstance().add(new Product("Auto"));
        Products.getInstance().get(0).add(new Material(Presets.getInstance().get(0), 4));
        overviewModel.fireTableDataChanged();

        try {
            savePresets();
            saveProducts();
        } catch (Exception e) {
        }
    }

    public void savePresets() throws IOException {
        File home;
        File folder;
        File presetFile;

        try {
            home = new File(System.getProperty("user.home"));
        } catch (Exception e) {
            home = null;
        }

        if (home != null && home.exists()) {
            folder = new File(home + File.separator + "Woody");
            if (!folder.exists()) {
                if (!folder.mkdir()) {
                    throw new IOException("Internal Error");
                }
            }
            presetFile = new File(folder + File.separator + "Presets.json");
        } else {
            presetFile = new File("Presets.json");
        }

        try {
            Presets.getInstance().writeTo(new BufferedWriter(new FileWriter(presetFile)));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Fehler beim Spiechern aufgetreten...", "Fehler aufgetreten", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void loadPresets() throws IOException {
        File home;
        File folder;
        File presetFile;

        try {
            home = new File(System.getProperty("user.home"));
        } catch (Exception e) {
            home = null;
        }

        if (home != null && home.exists()) {
            folder = new File(home + File.separator + "Woody");
            if (!folder.exists()) {
                if (!folder.mkdir()) {
                    throw new IOException("Internal Error");
                }
            }
            presetFile = new File(folder + File.separator + "Presets.json");
        } else {
            presetFile = new File("Presets.json");
        }

        try {
            Presets.getInstance().loadInto(new FileInputStream(presetFile));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Fehler beim Spiechern aufgetreten...", "Fehler aufgetreten", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void saveProducts() throws IOException {
        File home;
        File folder;
        File productFile;

        try {
            home = new File(System.getProperty("user.home"));
        } catch (Exception e) {
            home = null;
        }

        if (home != null && home.exists()) {
            folder = new File(home + File.separator + "Woody");
            if (!folder.exists()) {
                if (!folder.mkdir()) {
                    throw new IOException("Internal Error");
                }
            }
            productFile = new File(folder + File.separator + "Products.json");
        } else {
            productFile = new File("Products.json");
        }

        try {
            Products.getInstance().writeTo(new BufferedWriter(new FileWriter(productFile)));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Fehler beim Spiechern aufgetreten...", "Fehler aufgetreten", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void loadProducts() throws IOException {
        File home;
        File folder;
        File presetFile;

        try {
            home = new File(System.getProperty("user.home"));
        } catch (Exception e) {
            home = null;
        }

        if (home != null && home.exists()) {
            folder = new File(home + File.separator + "Woody");
            if (!folder.exists()) {
                if (!folder.mkdir()) {
                    throw new IOException("Internal Error");
                }
            }
            presetFile = new File(folder + File.separator + "Products.json");
        } else {
            presetFile = new File("Products.json");
        }

        try {
            Products.getInstance().loadInto(new FileInputStream(presetFile));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Fehler beim Spiechern aufgetreten...", "Fehler aufgetreten", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jTab = new javax.swing.JTabbedPane();
        jPanProducts = new javax.swing.JPanel();
        jspProducts = new javax.swing.JScrollPane();
        jTableProducts = new javax.swing.JTable();
        jPanProdButt = new javax.swing.JPanel();
        jbutAddProduct = new javax.swing.JButton();
        jbutEditProduct = new javax.swing.JButton();
        jbutRemoveProduct = new javax.swing.JButton();
        jPanPresets = new javax.swing.JPanel();
        jspPresets = new javax.swing.JScrollPane();
        jTablePresets = new javax.swing.JTable();
        jPanPreButt = new javax.swing.JPanel();
        jbutAddPreset = new javax.swing.JButton();
        jbutEditPreset = new javax.swing.JButton();
        jbutRemovePreset = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jmiSettings = new javax.swing.JMenuItem();
        jmiExit = new javax.swing.JMenuItem();
        jMenuProduct = new javax.swing.JMenu();
        jmiOpenOne = new javax.swing.JMenuItem();
        jmiOpenMore = new javax.swing.JMenuItem();
        jmiSaveOne = new javax.swing.JMenuItem();
        jmiSaveMore = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jmiAddProduct = new javax.swing.JMenuItem();
        jmiEditProduct = new javax.swing.JMenuItem();
        jmiRemoveProduct = new javax.swing.JMenuItem();
        jMenuPresets = new javax.swing.JMenu();
        jmiLoadPresets = new javax.swing.JMenuItem();
        jmiExportPresets = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jmiAddPreset = new javax.swing.JMenuItem();
        jmiEditPreset = new javax.swing.JMenuItem();
        jmiRemovePreset = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jTab.setBackground(new java.awt.Color(255, 255, 255));

        jPanProducts.setBackground(new java.awt.Color(255, 255, 255));
        jPanProducts.setLayout(new java.awt.BorderLayout());

        jTableProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jspProducts.setViewportView(jTableProducts);

        jPanProducts.add(jspProducts, java.awt.BorderLayout.CENTER);

        jPanProdButt.setBackground(new java.awt.Color(255, 255, 255));
        jPanProdButt.setLayout(new java.awt.GridBagLayout());

        jbutAddProduct.setText("Hinzufügen");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        jPanProdButt.add(jbutAddProduct, gridBagConstraints);

        jbutEditProduct.setText("Bearbeiten");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        jPanProdButt.add(jbutEditProduct, gridBagConstraints);

        jbutRemoveProduct.setText("Entfernen");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        jPanProdButt.add(jbutRemoveProduct, gridBagConstraints);

        jPanProducts.add(jPanProdButt, java.awt.BorderLayout.SOUTH);

        jTab.addTab("Produkte", jPanProducts);

        jPanPresets.setBackground(new java.awt.Color(255, 255, 255));
        jPanPresets.setLayout(new java.awt.BorderLayout());

        jTablePresets.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jspPresets.setViewportView(jTablePresets);

        jPanPresets.add(jspPresets, java.awt.BorderLayout.CENTER);

        jPanPreButt.setBackground(new java.awt.Color(255, 255, 255));
        jPanPreButt.setLayout(new java.awt.GridBagLayout());

        jbutAddPreset.setText("Hinzufügen");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        jPanPreButt.add(jbutAddPreset, gridBagConstraints);

        jbutEditPreset.setText("Bearbeiten");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        jPanPreButt.add(jbutEditPreset, gridBagConstraints);

        jbutRemovePreset.setText("Entfernen");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        jPanPreButt.add(jbutRemovePreset, gridBagConstraints);

        jPanPresets.add(jPanPreButt, java.awt.BorderLayout.SOUTH);

        jTab.addTab("Vorlagen", jPanPresets);

        getContentPane().add(jTab, java.awt.BorderLayout.CENTER);

        jMenuFile.setText("Datei");

        jmiSettings.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_COMMA, java.awt.event.InputEvent.META_MASK));
        jmiSettings.setText("Einstellungen");
        jMenuFile.add(jmiSettings);

        jmiExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.META_MASK));
        jmiExit.setText("Beenden");
        jMenuFile.add(jmiExit);

        jMenuBar1.add(jMenuFile);

        jMenuProduct.setText("Produkte");

        jmiOpenOne.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.META_MASK));
        jmiOpenOne.setText("Produkt öffnen");
        jMenuProduct.add(jmiOpenOne);

        jmiOpenMore.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.META_MASK));
        jmiOpenMore.setText("Katalog öffnen");
        jMenuProduct.add(jmiOpenMore);

        jmiSaveOne.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.META_MASK));
        jmiSaveOne.setText("Produkt speichern");
        jMenuProduct.add(jmiSaveOne);

        jmiSaveMore.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.META_MASK));
        jmiSaveMore.setText("Katalog speichern");
        jMenuProduct.add(jmiSaveMore);
        jMenuProduct.add(jSeparator1);

        jmiAddProduct.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.META_MASK));
        jmiAddProduct.setText("Produkt hinzufügen");
        jMenuProduct.add(jmiAddProduct);

        jmiEditProduct.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.META_MASK));
        jmiEditProduct.setText("Produkt bearbeiten");
        jMenuProduct.add(jmiEditProduct);

        jmiRemoveProduct.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_BACK_SPACE, java.awt.event.InputEvent.META_MASK));
        jmiRemoveProduct.setText("Produkt entfernen");
        jMenuProduct.add(jmiRemoveProduct);

        jMenuBar1.add(jMenuProduct);

        jMenuPresets.setText("Vorlagen");

        jmiLoadPresets.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.META_MASK));
        jmiLoadPresets.setText("Vorlagen laden");
        jMenuPresets.add(jmiLoadPresets);

        jmiExportPresets.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.META_MASK));
        jmiExportPresets.setText("Vorlagen exportieren");
        jMenuPresets.add(jmiExportPresets);
        jMenuPresets.add(jSeparator2);

        jmiAddPreset.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.META_MASK));
        jmiAddPreset.setText("Vorlage hinzufügen");
        jMenuPresets.add(jmiAddPreset);

        jmiEditPreset.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.META_MASK));
        jmiEditPreset.setText("Vorlage bearbeiten");
        jMenuPresets.add(jmiEditPreset);

        jmiRemovePreset.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_BACK_SPACE, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.META_MASK));
        jmiRemovePreset.setText("Vorlage entfernen");
        jMenuPresets.add(jmiRemovePreset);

        jMenuBar1.add(jMenuPresets);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Woody.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (System.getProperty("os.name").contains("Mac OS X")) {
            System.setProperty("apple.awt.fileDialogForDirectories", "true");
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Woody");
        }

        java.awt.EventQueue.invokeLater(() -> {
            new Woody().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenu jMenuPresets;
    private javax.swing.JMenu jMenuProduct;
    private javax.swing.JPanel jPanPreButt;
    private javax.swing.JPanel jPanPresets;
    private javax.swing.JPanel jPanProdButt;
    private javax.swing.JPanel jPanProducts;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JTabbedPane jTab;
    private javax.swing.JTable jTablePresets;
    private javax.swing.JTable jTableProducts;
    private javax.swing.JButton jbutAddPreset;
    private javax.swing.JButton jbutAddProduct;
    private javax.swing.JButton jbutEditPreset;
    private javax.swing.JButton jbutEditProduct;
    private javax.swing.JButton jbutRemovePreset;
    private javax.swing.JButton jbutRemoveProduct;
    private javax.swing.JMenuItem jmiAddPreset;
    private javax.swing.JMenuItem jmiAddProduct;
    private javax.swing.JMenuItem jmiEditPreset;
    private javax.swing.JMenuItem jmiEditProduct;
    private javax.swing.JMenuItem jmiExit;
    private javax.swing.JMenuItem jmiExportPresets;
    private javax.swing.JMenuItem jmiLoadPresets;
    private javax.swing.JMenuItem jmiOpenMore;
    private javax.swing.JMenuItem jmiOpenOne;
    private javax.swing.JMenuItem jmiRemovePreset;
    private javax.swing.JMenuItem jmiRemoveProduct;
    private javax.swing.JMenuItem jmiSaveMore;
    private javax.swing.JMenuItem jmiSaveOne;
    private javax.swing.JMenuItem jmiSettings;
    private javax.swing.JScrollPane jspPresets;
    private javax.swing.JScrollPane jspProducts;
    // End of variables declaration//GEN-END:variables
}
