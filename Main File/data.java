
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author HP
 */
public class data extends javax.swing.JFrame {

    //private Object jTable;
    String loginUserId = "";
    private JPanel topPanel;
  private JScrollPane scrollPane;
  JButton button = new JButton();
    /**
     * Creates new form data
     */
    public data() {
        initComponents();
    }

    data(String loginUserId) {
        this.loginUserId = loginUserId;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        btnShow = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Communication ID", "Full Name", "Username", "Received At", "Decrypt Data"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable);

        btnShow.setText("Refresh Table");
        btnShow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnShowMouseClicked(evt);
            }
        });
        btnShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(194, 194, 194)
                        .addComponent(btnShow)))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnShow)
                .addContainerGap(234, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowActionPerformed
        // TODO add your handling code here:
        
        
       
    }//GEN-LAST:event_btnShowActionPerformed

    int cntr =0;
    
    private void btnShowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnShowMouseClicked
        // TODO add your handling code here:
        
         topPanel = new JPanel();
    topPanel.setLayout(new BorderLayout());
    getContentPane().add(topPanel);
        
         jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Communication ID", "Full Name", "Username", "Received At", "Decrypt Data"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
    
         
         jTable.getColumn("Decrypt Data").setCellRenderer(new ButtonRenderer());
         jTable.getColumn("Decrypt Data").setCellEditor(new ButtonEditor(new JCheckBox()));
             
        Connection myCon=null;
        Statement myStmt=null;
        ResultSet myRs=null;
            try {
                //Database URL	jdbc:mysql://localhost:3306/image_encryption_decryption?zeroDateTimeBehavior=CONVERT_TO_NULL
                myCon= DriverManager.getConnection("jdbc:mysql://localhost:3306/image_encryption_decryption","root","root");
            } catch (SQLException ex) {
                Logger.getLogger(data.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                myStmt=myCon.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(data.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                myRs=myStmt.executeQuery("select u.FULL_NAME, u.USERNAME, c.COMMUNICATIONS_ID, DATE_FORMAT(c.RECEIVED_AT, '%d-%m-%Y %h:%m:%s') as RECEIVED_AT   from user u\n" +
                        "inner join communications c on c.SENDER_USERID = u.ID \n" +
                        "where c.RECIEVER_USERID = "+loginUserId);
                
                
                
                 while (myRs.next()){
            //System.out.println("name :: "+ myRs.getString("FULL_NAME") +" :: ID :: "+ myRs.getString("USERNAME"));
            
            
            String fullName = myRs.getString("FULL_NAME");
            String userName = myRs.getString("USERNAME");
            String receivedAt = myRs.getString("RECEIVED_AT");
            String communicationId = myRs.getString("COMMUNICATIONS_ID");
            
            Object[] row = { communicationId, fullName, userName, receivedAt };
            DefaultTableModel model = (DefaultTableModel) jTable.getModel();
            model.addRow(row);
            
    
            
           
    //jScrollPane1 = new JScrollPane(jTable);
    //topPanel.add(scrollPane,BorderLayout.CENTER);  
    
   
            
          
            
            }
                 
    
                 
                  button.addActionListener(
      new ActionListener()
      {
        public void actionPerformed(ActionEvent event)
        {
         if(cntr==0){
             cntr++;
          JOptionPane.showMessageDialog(null,"Do you want to modify this line?");
          
          
          
          
         }
        }
      }
    );
            
                    } catch (SQLException ex) {
            Logger.getLogger(Dashboard.class.getName()).log(Level.SEVERE, null, ex);
           
        }
           
        
    }//GEN-LAST:event_btnShowMouseClicked

     //***********************************************************
    
     class ButtonRenderer extends JButton implements TableCellRenderer 
  {
    public ButtonRenderer() {
      setOpaque(true);
    }
    public Component getTableCellRendererComponent(JTable table, Object value,
    boolean isSelected, boolean hasFocus, int row, int column) {
      setText((value == null) ? "Decrypt Data" : value.toString());
      cntr = 0;
      return this;
    }
  }
  class ButtonEditor extends DefaultCellEditor 
  {
    private String label;
    
    public ButtonEditor(JCheckBox checkBox)
    {
        super(checkBox);
    }
    public Component getTableCellEditorComponent(JTable table, Object value,
    boolean isSelected, int row, int column) 
    {
        String communicationId = (Object)table.getValueAt(row, 0)+"";
        System.out.println("Modify :: "+communicationId);
      label = (value == null) ? "Decrypt Data" : value.toString();
      button.setText(label);
      
      Decryption fm=new Decryption(communicationId);
                fm.setVisible(true);
      
      return button;
    }
    public Object getCellEditorValue() 
    {
        System.out.println("Modify1");
      return new String(label);
    }
  }
    
    
    //**********************************************************
   
    
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
            java.util.logging.Logger.getLogger(data.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(data.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(data.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(data.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new data().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnShow;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    // End of variables declaration//GEN-END:variables
}
