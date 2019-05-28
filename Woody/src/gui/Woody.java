package gui;

import data.Presets;
import data.Product;
import data.Products;
import gui.model.OverviewModel;
import gui.model.PresetModel;
import java.awt.Dimension;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

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

        try {
            loadPresets();
            loadProducts();
        } catch (Exception e) {
        }
        refreshGui();
    }

    public void refreshGui() {
        jbutEditPreset.setEnabled(false);
        jmiEditPreset.setEnabled(false);
        jbutRemovePreset.setEnabled(false);
        jmiRemovePreset.setEnabled(false);
        jmiExportPresets.setEnabled(false);

        jbutEditProduct.setEnabled(false);
        jmiEditProduct.setEnabled(false);
        jbutRemoveProduct.setEnabled(false);
        jmiRemoveProduct.setEnabled(false);
        jmiSaveOne.setEnabled(false);
        jmiSaveMore.setEnabled(false);

        if (!Presets.getInstance().isEmpty()) {
            jbutEditPreset.setEnabled(true);
            jmiEditPreset.setEnabled(true);
            jbutRemovePreset.setEnabled(true);
            jmiRemovePreset.setEnabled(true);
            jmiExportPresets.setEnabled(true);
        }

        if (!Products.getInstance().isEmpty()) {
            jbutEditProduct.setEnabled(true);
            jmiEditProduct.setEnabled(true);
            jbutRemoveProduct.setEnabled(true);
            jmiRemoveProduct.setEnabled(true);
            jmiSaveOne.setEnabled(true);
            jmiSaveMore.setEnabled(true);
        }
        
        presetModel.fireTableDataChanged();
        overviewModel.fireTableDataChanged();
    }
    
    private void showErrMess(String message) {
        JOptionPane.showMessageDialog(this, message, "Fehler aufgetreten!", JOptionPane.ERROR_MESSAGE);
    }
    
    private void showWarnMess(String message) {
        JOptionPane.showMessageDialog(this, message, "Warnung!", JOptionPane.WARNING_MESSAGE);
    }
    
    private void showInfoMess(String message) {
        JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
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

        try (BufferedWriter w = new BufferedWriter(new FileWriter(presetFile))) {
            Presets.getInstance().writeTo(w);
        } catch (IOException ex) {
            showErrMess("Fehler beim Speichern aufgetreten...");
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
            showErrMess("Fehler beim Laden aufgetreten...");
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

        try (BufferedWriter w = new BufferedWriter(new FileWriter(productFile))) {
            Products.getInstance().writeTo(w);
        } catch (IOException ex) {
            showErrMess("Fehler beim Speichern aufgetreten...");
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
            showErrMess("Fehler beim Laden aufgetreten...");
        }

    }

    private void addProduct() {
        final AddProductDialog dialog = new AddProductDialog(this, true);
        dialog.setVisible(true);
        
        refreshGui();
        try {
            saveProducts();
        } catch (IOException ex) {
        }
    }

    private void editProduct() {
        final AddProductDialog dialog = new AddProductDialog(this, true);
        final Product product = Products.getInstance().get(jTableProducts.getSelectedRow());

        dialog.setVisible(true);
        
        refreshGui();
        try {
            saveProducts();
        } catch (IOException ex) {
        }
    }

    private void removeProduct() {
        Products.getInstance().remove(jTableProducts.getSelectedRow());
        refreshGui();
        try {
            saveProducts();
        } catch (IOException ex) {
        }
    }
    
    private void addPreset() {
        final AddPresetDialog dialog = new AddPresetDialog(this, true);
        dialog.setVisible(true);
        
        refreshGui();
        try {
            savePresets();
        } catch (IOException ex) {
        }
    }
    
    private void editPreset() {
        final AddPresetDialog dialog = new AddPresetDialog(this, true);
        final Product product = Products.getInstance().get(jTablePresets.getSelectedRow());
        
        dialog.setVisible(true);
        
        refreshGui();
        try {
            savePresets();
        } catch (IOException ex) {
        }
    }
    
    private void removePreset() {
        Products.getInstance().remove(jTablePresets.getSelectedRow());
        refreshGui();
        try {
            savePresets();
        } catch (IOException ex) {
        }
    }
    
    private FileInputStream openFile() throws FileNotFoundException, Exception {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON File", "json");
        chooser.setFileFilter(filter);
        
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            return new FileInputStream(chooser.getSelectedFile());
        } else {
            throw new Exception("User canceled");
        }
    }
    
    private BufferedWriter saveFile() throws IOException, Exception {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON File", "json");
        chooser.setFileFilter(filter);
        
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            return new BufferedWriter(new FileWriter(chooser.getSelectedFile()));
        } else {
            throw new Exception("User canceled");
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
        jbutAddProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbutAddProductActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        jPanProdButt.add(jbutAddProduct, gridBagConstraints);

        jbutEditProduct.setText("Bearbeiten");
        jbutEditProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbutEditProductActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        jPanProdButt.add(jbutEditProduct, gridBagConstraints);

        jbutRemoveProduct.setText("Entfernen");
        jbutRemoveProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbutRemoveProductActionPerformed(evt);
            }
        });
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
        jbutAddPreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbutAddPresetActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        jPanPreButt.add(jbutAddPreset, gridBagConstraints);

        jbutEditPreset.setText("Bearbeiten");
        jbutEditPreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbutEditPresetActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.weighty = 0.5;
        jPanPreButt.add(jbutEditPreset, gridBagConstraints);

        jbutRemovePreset.setText("Entfernen");
        jbutRemovePreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbutRemovePresetActionPerformed(evt);
            }
        });
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
        jmiOpenOne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiOpenOneActionPerformed(evt);
            }
        });
        jMenuProduct.add(jmiOpenOne);

        jmiOpenMore.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.META_MASK));
        jmiOpenMore.setText("Katalog öffnen");
        jmiOpenMore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiOpenMoreActionPerformed(evt);
            }
        });
        jMenuProduct.add(jmiOpenMore);

        jmiSaveOne.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.META_MASK));
        jmiSaveOne.setText("Produkt speichern");
        jmiSaveOne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSaveOneActionPerformed(evt);
            }
        });
        jMenuProduct.add(jmiSaveOne);

        jmiSaveMore.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.META_MASK));
        jmiSaveMore.setText("Katalog speichern");
        jmiSaveMore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSaveMoreActionPerformed(evt);
            }
        });
        jMenuProduct.add(jmiSaveMore);
        jMenuProduct.add(jSeparator1);

        jmiAddProduct.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.META_MASK));
        jmiAddProduct.setText("Produkt hinzufügen");
        jmiAddProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiAddProductActionPerformed(evt);
            }
        });
        jMenuProduct.add(jmiAddProduct);

        jmiEditProduct.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.META_MASK));
        jmiEditProduct.setText("Produkt bearbeiten");
        jmiEditProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiEditProductActionPerformed(evt);
            }
        });
        jMenuProduct.add(jmiEditProduct);

        jmiRemoveProduct.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_BACK_SPACE, java.awt.event.InputEvent.META_MASK));
        jmiRemoveProduct.setText("Produkt entfernen");
        jmiRemoveProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRemoveProductActionPerformed(evt);
            }
        });
        jMenuProduct.add(jmiRemoveProduct);

        jMenuBar1.add(jMenuProduct);

        jMenuPresets.setText("Vorlagen");

        jmiLoadPresets.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.META_MASK));
        jmiLoadPresets.setText("Vorlagen laden");
        jmiLoadPresets.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiLoadPresetsActionPerformed(evt);
            }
        });
        jMenuPresets.add(jmiLoadPresets);

        jmiExportPresets.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.META_MASK));
        jmiExportPresets.setText("Vorlagen exportieren");
        jmiExportPresets.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiExportPresetsActionPerformed(evt);
            }
        });
        jMenuPresets.add(jmiExportPresets);
        jMenuPresets.add(jSeparator2);

        jmiAddPreset.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.META_MASK));
        jmiAddPreset.setText("Vorlage hinzufügen");
        jmiAddPreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiAddPresetActionPerformed(evt);
            }
        });
        jMenuPresets.add(jmiAddPreset);

        jmiEditPreset.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.META_MASK));
        jmiEditPreset.setText("Vorlage bearbeiten");
        jmiEditPreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiEditPresetActionPerformed(evt);
            }
        });
        jMenuPresets.add(jmiEditPreset);

        jmiRemovePreset.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_BACK_SPACE, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.META_MASK));
        jmiRemovePreset.setText("Vorlage entfernen");
        jmiRemovePreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiRemovePresetActionPerformed(evt);
            }
        });
        jMenuPresets.add(jmiRemovePreset);

        jMenuBar1.add(jMenuPresets);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbutAddProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbutAddProductActionPerformed
        addProduct();
    }//GEN-LAST:event_jbutAddProductActionPerformed

    private void jmiAddProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAddProductActionPerformed
        addProduct();
    }//GEN-LAST:event_jmiAddProductActionPerformed

    private void jbutEditProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbutEditProductActionPerformed
        editProduct();
    }//GEN-LAST:event_jbutEditProductActionPerformed

    private void jmiEditProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiEditProductActionPerformed
        editProduct();
    }//GEN-LAST:event_jmiEditProductActionPerformed

    private void jbutRemoveProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbutRemoveProductActionPerformed
        removeProduct();
    }//GEN-LAST:event_jbutRemoveProductActionPerformed

    private void jmiRemoveProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRemoveProductActionPerformed
        removeProduct();
    }//GEN-LAST:event_jmiRemoveProductActionPerformed

    private void jmiOpenOneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiOpenOneActionPerformed
        JsonObject jsonObj = null;
        try (JsonReader jsonReader = Json.createReader(openFile())) {
            jsonObj = jsonReader.readObject();
        } catch (Exception ex) {
        }
        Products.getInstance().add(new Product(jsonObj));
        refreshGui();
    }//GEN-LAST:event_jmiOpenOneActionPerformed

    private void jmiOpenMoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiOpenMoreActionPerformed
        try {
            Products.getInstance().loadInto(openFile());
            refreshGui();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jmiOpenMoreActionPerformed

    private void jmiSaveOneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSaveOneActionPerformed
        final Product product = Products.getInstance().get(jTableProducts.getSelectedRow());
        try {
            product.writeTo(saveFile());
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jmiSaveOneActionPerformed

    private void jmiSaveMoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSaveMoreActionPerformed
        try {
            Products.getInstance().writeTo(saveFile());
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jmiSaveMoreActionPerformed

    private void jbutAddPresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbutAddPresetActionPerformed
        addPreset();
    }//GEN-LAST:event_jbutAddPresetActionPerformed

    private void jmiAddPresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAddPresetActionPerformed
        addPreset();
    }//GEN-LAST:event_jmiAddPresetActionPerformed

    private void jbutEditPresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbutEditPresetActionPerformed
        editPreset();
    }//GEN-LAST:event_jbutEditPresetActionPerformed

    private void jmiEditPresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiEditPresetActionPerformed
        editPreset();
    }//GEN-LAST:event_jmiEditPresetActionPerformed

    private void jbutRemovePresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbutRemovePresetActionPerformed
        removePreset();
    }//GEN-LAST:event_jbutRemovePresetActionPerformed

    private void jmiRemovePresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiRemovePresetActionPerformed
        removePreset();
    }//GEN-LAST:event_jmiRemovePresetActionPerformed

    private void jmiLoadPresetsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiLoadPresetsActionPerformed
        try {
            Presets.getInstance().loadInto(openFile());
            refreshGui();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jmiLoadPresetsActionPerformed

    private void jmiExportPresetsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiExportPresetsActionPerformed
        try {
            Products.getInstance().writeTo(saveFile());
            refreshGui();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_jmiExportPresetsActionPerformed

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
