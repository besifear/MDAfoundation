package gui.view;

import bl.AppException;
import bl.LogsInterface;
import bl.LogsRepository;
import bl.UserInterface;
import bl.UserRepository;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import ejb.Logs;
import ejb.Users;
import gui.model.UserTableModel;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class MenaxhoUserat extends javax.swing.JInternalFrame {
UserTableModel userTM;
EntityManager em;
UserInterface userIr;
LogsInterface logsIr;
    public MenaxhoUserat(EntityManager em) {
        initComponents();
        removeLeftButton();
        this.setLocation(15,147);
        this.em=em;
        userIr=new UserRepository(this.em);
        logsIr=new LogsRepository(this.em);
        userTM=new UserTableModel();
        usersTabelaLoad();
    }
    
    private void removeLeftButton(){
        Container pane = ((BasicInternalFrameUI) this.getUI()).getNorthPane();
        // And remove the button:
        pane.remove(0);
        /*
        // OR make it invisible:
        pane.getComponent(0).setVisible(false);
        */
    }

    private void usersTabelaLoad(){
        List<Users> userat=userIr.findAll();
        userTM.add(userat);
        usersTbl.setModel(userTM);
        userTM.fireTableDataChanged();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        usersTbl = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        changePasswordTxtf = new javax.swing.JButton();

        usersTbl.setModel(new javax.swing.table.DefaultTableModel(
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
        usersTbl.setShowHorizontalLines(false);
        usersTbl.setShowVerticalLines(false);
        jScrollPane1.setViewportView(usersTbl);

        jButton1.setForeground(new java.awt.Color(255, 51, 51));
        jButton1.setText("fshije userin");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("exit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        changePasswordTxtf.setText("Restarto Passwordin");
        changePasswordTxtf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePasswordTxtfActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(changePasswordTxtf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(changePasswordTxtf))
                .addGap(13, 13, 13))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       try{
        if(usersTbl.getSelectedRow()!=-1){
                String[] opcionet={"Po","Jo"};
                int response = JOptionPane.showOptionDialog(this,
                "A dëshiron me e fshi Userin ?","Kujdesë",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, opcionet, opcionet[0]);
                if(response==0){
                    Users victimUser=userTM.getUsers(usersTbl.getSelectedRow());
                    if(victimUser.getPozita().equals("Administrator"))
                        throw new AppException("Nuk mund të fshihet useri administrator.");
                    userIr.deleteUser(victimUser);
                    
                    try{
                    List<Logs> logsList=logsIr.findByUserAsc(victimUser.getUsername());
                    for(Logs iteratingLog : logsList){
                        logsIr.remove(iteratingLog);
                    }
                    }catch(NullPointerException npe){
                    }
                    userIr.remove(victimUser);
                    usersTabelaLoad();
                    JOptionPane.showMessageDialog(this, "Useri u fshi me suksesë.");
                }
        }
        else throw new AppException("Selekto Userin qe deshiron me e fshi.");
        }catch(AppException ae){
           JOptionPane.showMessageDialog(this,ae.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void changePasswordTxtfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePasswordTxtfActionPerformed
       try{
           int index;
            if((index=usersTbl.getSelectedRow())!=-1){
                Users useri=userTM.getUsers(index);
                String[] opcionet={"Po","Jo"};
                int response = JOptionPane.showOptionDialog(this,
                "A dëshiron me restartu passwordin e userit : "+useri.getUsername()+" ?","Kujdesë",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, opcionet, opcionet[0]);
                if(response==0){
                useri.setNumOfLogins(0);
                byte[] passwordi = userIr.kripto(useri.getSalt()+"12345678");
                useri.setPasswordi(passwordi);
                userIr.setUserPassword(useri);
                userIr.edit(useri);
                JOptionPane.showMessageDialog(this, "Passwordi i userit :"+useri.getUsername()+" është 12345678");
                }
            }else throw new AppException("Selekto Userin qe deshiron me i restartu passwordin.");
        }catch(AppException ae){
           JOptionPane.showMessageDialog(this,ae.getMessage());
        }
    }//GEN-LAST:event_changePasswordTxtfActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton changePasswordTxtf;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable usersTbl;
    // End of variables declaration//GEN-END:variables
}
