/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mybill;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.DefaultTreeCellEditor;

/**
 *
 * @author Shivam-PC
 */
public class createBill extends javax.swing.JFrame {
    ArrayList<orderProduct> product_list = new ArrayList<>();

    /**
     * Creates new form createBill
     */
    public createBill()  {
      
        initComponents();
        setfocus();
    }    
    
    void setfocus(){
        barcode_textfield.setText("");
        barcode_textfield.requestFocusInWindow();
        for(int i = 0; i < productTable.getRowCount();i++){
            DefaultTableModel model = (DefaultTableModel)productTable.getModel();
            try{
            int mrp = Integer.parseInt( model.getValueAt(i, 3).toString());
             int discount = Integer.parseInt( model.getValueAt(i, 4).toString());
             int quantity = Integer.parseInt( model.getValueAt(i, 5).toString());
             float price = (float) (mrp*(1 - 0.01*discount)*quantity);//quantity * (mrp - (mrp * (discount/100)));
             productTable.setValueAt(price, i, 6);
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(createBill.this,"Sorry product not found");
            }
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

        jScrollPane1 = new javax.swing.JScrollPane();
        productTable = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        label = new javax.swing.JLabel();
        barcode_textfield = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
                false, false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        productTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                productTableKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                productTableKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(productTable);

        jButton1.setText("remove row");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        label.setText("DD Trends BILL");

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

        jButton2.setText("New Bill");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Cancel");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(35, 35, 35)
                        .addComponent(barcode_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(jButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(605, 605, 605)
                        .addComponent(label)))
                .addGap(0, 641, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(barcode_textfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addComponent(jButton3)))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
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
    }//GEN-LAST:event_jButton1ActionPerformed

    private void barcode_textfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barcode_textfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_barcode_textfieldActionPerformed

    private void barcode_textfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_barcode_textfieldKeyPressed
        // TODO add your handling code here:
         if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String barcode = barcode_textfield.getText();
            product pro = ProductDao.selectAll(barcode);
            if (pro==null){
                JOptionPane.showMessageDialog(createBill.this,"Sorry product not found");
                setfocus();
                return;
            }
            DefaultTableModel model = (DefaultTableModel) productTable.getModel();
            int price = 0;
            int mrp = pro.getMrp();
            int discount = pro.getDiscount();
            price = mrp - (mrp*discount/100);
            Object[] item={pro.getBarcode_id(),pro.getProduct_name(),pro.getProduct_desc(),
                mrp, discount, 1, price};
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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        createBill.main(new String[]{});
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        frontPage.main(new String[]{});
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

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
    private javax.swing.JTextField barcode_textfield;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label;
    private javax.swing.JTable productTable;
    // End of variables declaration//GEN-END:variables
}
