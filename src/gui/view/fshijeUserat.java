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
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

public class fshijeUserat extends javax.swing.JFrame {
UserTableModel userTM;
EntityManager em;
UserInterface userIr;
LogsInterface logsIr;
    public fshijeUserat(EntityManager em) {
        initComponents();
        this.setLocation(15,147);
        this.em=em;
        userIr=new UserRepository(this.em);
        logsIr=new LogsRepository(this.em);
        userTM=new UserTableModel();
        usersTabelaLoad();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        jButton1.setText("delete user");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(jButton2))
                .addGap(20, 20, 20))
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable usersTbl;
    // End of variables declaration//GEN-END:variables
}
