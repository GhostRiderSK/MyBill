/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybill;


import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Shivam-PC
 */
public class createBill extends javax.swing.JFrame {
    ArrayList<orderProduct> product_list = new ArrayList<>();
    ArrayList<String[]> productNames = new ArrayList<>();
                        
    /**
     * Creates new form createBill
     */
    public createBill()  {
      
        initComponents();
        setExtendedState(this.MAXIMIZED_BOTH);
        ButtonGroup group = new ButtonGroup();
        group.add(cashButton);
        group.add(cardButton);
        cashButton.setSelected(true);
        setfocus();
        ArrayList<String[]> productNames = ProductDao.ProductsNames();
        for (String[] productDetails : productNames){
             ProductName_jComboBox.addItem(productDetails[1]);
             }
      
        
        JTextComponent editor = (JTextComponent) ProductName_jComboBox.getEditor().getEditorComponent();
        editor.setText("");
        editor.addKeyListener(new KeyAdapter() {
            public void keyTyped (KeyEvent evt) {
              String typed =  editor.getText().toLowerCase();
              ProductName_jComboBox.removeAllItems();
               
              for (String[] productDetails : productNames){
                   if(productDetails[1].toLowerCase().contains(typed)){
                        ProductName_jComboBox.addItem(productDetails[1]);
             }
             }
             editor.setText(typed);
             
             ProductName_jComboBox.showPopup();
             
             }
              
            public void keyReleased(KeyEvent evt){
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                String productName=ProductName_jComboBox.getSelectedItem().toString();
                editor.setText(productName);
                String barcode="";
                ProductName_jComboBox.hidePopup();
                for (String[] productDetails : productNames){
                    if(productDetails[1].equalsIgnoreCase(productName)){
                        barcode=productDetails[0];
                    }
             }
            for(int i = 0; i < productTable.getRowCount();i++){
                DefaultTableModel model = (DefaultTableModel)productTable.getModel();
                String Barcode_id = model.getValueAt(i, 0).toString();
                if(Barcode_id.equalsIgnoreCase(barcode)){
                    int quantity = Integer.parseInt( model.getValueAt(i, 5).toString());
                    productTable.setValueAt(quantity+1, i, 5);
                    setfocus();
                    return;
                }
             }
            product pro = ProductDao.selectAll(barcode);
            if (pro==null){
                JOptionPane.showMessageDialog(createBill.this,"Sorry product not found");
                setfocus();
                return;
            }
            DefaultTableModel model = (DefaultTableModel) productTable.getModel();
            int mrp = pro.getMrp();
            int discount = pro.getDiscount();
            Object[] item={pro.getBarcode_id(),pro.getProduct_name(),pro.getProduct_desc(),
                mrp, discount, 1, 0.0};
            model.addRow(item);
            
            setfocus();
            editor.setText("");
            }
            
            }
        });
        }    
    
    void setfocus(){
        barcode_textfield.setText("");
        barcode_textfield.requestFocusInWindow();
        float total_price = 0;
        for(int i = 0; i < productTable.getRowCount();i++){
            DefaultTableModel model = (DefaultTableModel)productTable.getModel();
            try{
            int mrp = Integer.parseInt( model.getValueAt(i, 3).toString());
             int discount = Integer.parseInt( model.getValueAt(i, 4).toString());
             int quantity = Integer.parseInt( model.getValueAt(i, 5).toString());
             float price = (float) (mrp*(1 - 0.01*discount)*quantity);//quantity * (mrp - (mrp * (discount/100)));
             total_price += price;
             productTable.setValueAt(price, i, 6);
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(createBill.this,"Sorry wrong quantity");
            }
        }
        PriceLabel.setText(String.valueOf(total_price));
       
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        productTable = new javax.swing.JTable();
        RemoveRowButton = new javax.swing.JButton();
        label = new javax.swing.JLabel();
        barcode_textfield = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        NewBillButton = new javax.swing.JButton();
        CancelButton = new javax.swing.JButton();
        PrintButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        PriceLabel = new javax.swing.JLabel();
        cashButton = new javax.swing.JRadioButton();
        cardButton = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        ProductName_jComboBox = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        productTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Barcode", "Product Name", "Product Desription", "MRP", "Discount", "Quantity", "Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        productTable.getTableHeader().setReorderingAllowed(false);
        productTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                productTableKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                productTableKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(productTable);
        if (productTable.getColumnModel().getColumnCount() > 0) {
            productTable.getColumnModel().getColumn(3).setCellRenderer(cellCenter() );
            productTable.getColumnModel().getColumn(4).setCellRenderer(cellCenter() );
            productTable.getColumnModel().getColumn(5).setCellRenderer(cellCenter() );
            productTable.getColumnModel().getColumn(6).setCellRenderer(cellCenter() );
        }

        RemoveRowButton.setText("Remove row");
        RemoveRowButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveRowButtonActionPerformed(evt);
            }
        });

        label.setFont(new java.awt.Font("Bodoni MT Black", 1, 36)); // NOI18N
        label.setText("Vitus Mart");

        barcode_textfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barcode_textfieldActionPerformed(evt);
            }
        });
        barcode_textfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                barcode_textfieldKeyPressed(evt);
            }
        });

        jLabel1.setText("Barcode ID");

        NewBillButton.setText("New Bill");
        NewBillButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewBillButtonActionPerformed(evt);
            }
        });

        CancelButton.setText("Cancel");
        CancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButtonActionPerformed(evt);
            }
        });

        PrintButton.setText("Print Bill");
        PrintButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setText("Total Price - ");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel3.setText("Rs.");

        PriceLabel.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        PriceLabel.setText("0.0");

        cashButton.setText("Cash");
        cashButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cashButtonActionPerformed(evt);
            }
        });

        cardButton.setText("Card");

        jLabel4.setText("Product Name");

        ProductName_jComboBox.setEditable(true);
        ProductName_jComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(barcode_textfield, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(ProductName_jComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cashButton)
                    .addComponent(cardButton))
                .addGap(18, 18, 18)
                .addComponent(PrintButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1048, 1048, 1048))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(RemoveRowButton, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(NewBillButton, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(CancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(508, 508, 508)
                        .addComponent(label)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(label)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(barcode_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(ProductName_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cashButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cardButton))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(PrintButton)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(PriceLabel)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(RemoveRowButton)
                        .addComponent(NewBillButton)
                        .addComponent(CancelButton)))
                .addGap(53, 53, 53))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
        private DefaultTableCellRenderer cellCenter(){
          DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
          centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );  
          return centerRenderer;
      }
    
    private void RemoveRowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveRowButtonActionPerformed
        // TODO add your handling code here:
        try{
        int row = productTable.getSelectedRow();
        ((DefaultTableModel)productTable.getModel()).removeRow(row);
        setfocus();
        }
        catch(Exception e){
        JOptionPane.showMessageDialog(createBill.this,"Sorry no row selected");
         setfocus();    
        }
    }//GEN-LAST:event_RemoveRowButtonActionPerformed

    private void barcode_textfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barcode_textfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_barcode_textfieldActionPerformed

    private void barcode_textfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_barcode_textfieldKeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String barcode = barcode_textfield.getText().trim();
            for(int i = 0; i < productTable.getRowCount();i++){
                DefaultTableModel model = (DefaultTableModel)productTable.getModel();
                String Barcode_id = model.getValueAt(i, 0).toString();
                if(Barcode_id.equalsIgnoreCase(barcode)){
                    int quantity = Integer.parseInt( model.getValueAt(i, 5).toString());
                    productTable.setValueAt(quantity+1, i, 5);
                    setfocus();
                    return;
                }
             }
            product pro = ProductDao.selectAll(barcode);
            if (pro==null){
                JOptionPane.showMessageDialog(createBill.this,"Sorry product not found");
                setfocus();
                return;
            }
            DefaultTableModel model = (DefaultTableModel) productTable.getModel();
            int mrp = pro.getMrp();
            int discount = pro.getDiscount();
            Object[] item={pro.getBarcode_id(),pro.getProduct_name(),pro.getProduct_desc(),
                mrp, discount, 1, 0.0};
            model.addRow(item);
            
            setfocus();
        }
          
    
    }//GEN-LAST:event_barcode_textfieldKeyPressed

    private void productTableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_productTableKeyReleased
        // TODO add your handling code here:
        setfocus();
    }//GEN-LAST:event_productTableKeyReleased

    private void productTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_productTableKeyPressed
        // TODO add your handling code here:
         if(evt.getKeyChar() ==KeyEvent.VK_DELETE) {
           try{
        int row = productTable.getSelectedRow();
        ((DefaultTableModel)productTable.getModel()).removeRow(row);
        setfocus();
        }
        catch(Exception e){
        JOptionPane.showMessageDialog(createBill.this,"Sorry no row selected");
         setfocus();    
        }
            
        }
    }//GEN-LAST:event_productTableKeyPressed

    private void NewBillButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewBillButtonActionPerformed
        // TODO add your handling code here:
        createBill.main(new String[]{});
    }//GEN-LAST:event_NewBillButtonActionPerformed

    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButtonActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_CancelButtonActionPerformed

    private void PrintButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintButtonActionPerformed
        // TODO add your handling code here:
        String paymentMethod = "Cash";
        if(cashButton.isSelected()){
            paymentMethod = "Cash";
        }
        else if(cardButton.isSelected()){
            paymentMethod = "Card";
        }
        
        Date date = new Date();
        long currentTimeId = System.currentTimeMillis();
        String order_id = String.valueOf(currentTimeId);
        String current_date = orderDao.getDate(date);
        String current_time = orderDao.getTime(date);
        float total_price = 0;
        int statusOrder = 0;
        int statusUpdate = 0;
        ArrayList<Order> ordList = new ArrayList<>();
        for(int i = 0; i < productTable.getRowCount();i++){
            DefaultTableModel model = (DefaultTableModel)productTable.getModel();
            String Barcode_id = model.getValueAt(i, 0).toString();
            String productName = model.getValueAt(i, 1).toString();
            String productDesc = model.getValueAt(i, 2).toString();
            int mrp = Integer.parseInt( model.getValueAt(i, 3).toString());
            int discount = Integer.parseInt( model.getValueAt(i, 4).toString());
            int quantity = Integer.parseInt( model.getValueAt(i, 5).toString());
            Float price = Float.parseFloat(model.getValueAt(i, 6).toString());
            Order ord = new Order(Barcode_id, productName, current_time, current_date, mrp, discount, quantity, price, paymentMethod);
            statusOrder = orderDao.save(order_id, Barcode_id, productName, current_date, current_time, mrp, discount, quantity, price, paymentMethod);
            statusUpdate = ProductDao.updateQuantity(Barcode_id, quantity);
            if(statusOrder < 0 && statusUpdate < 0){
                    JOptionPane.showMessageDialog(createBill.this,"Sorry, unable to save!");
                    this.dispose();
                    break;
		}
            else{
                ordList.add(ord);
            }
            }
         if(statusOrder > 0 && statusUpdate > 0){
                    HelloWorldPrinter hwp = new HelloWorldPrinter();
                    hwp.initOrder( ordList, order_id, paymentMethod);
                    hwp.startPrinting();       
                    PrintBill.writeFile(ordList, order_id, paymentMethod);
                    
                    JOptionPane.showMessageDialog(createBill.this,"Order successfully!");
                    this.dispose();
                    createBill.main(new String[]{});
		}
    
    }//GEN-LAST:event_PrintButtonActionPerformed

    private void cashButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cashButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cashButtonActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(createBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(createBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(createBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(createBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new createBill().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelButton;
    private javax.swing.JButton NewBillButton;
    private javax.swing.JLabel PriceLabel;
    private javax.swing.JButton PrintButton;
    private javax.swing.JComboBox ProductName_jComboBox;
    private javax.swing.JButton RemoveRowButton;
    private javax.swing.JTextField barcode_textfield;
    private javax.swing.JRadioButton cardButton;
    private javax.swing.JRadioButton cashButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label;
    private javax.swing.JTable productTable;
    // End of variables declaration//GEN-END:variables
}
