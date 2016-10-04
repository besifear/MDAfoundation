package gui.view;

import bl.AppException;
import bl.EntMngClass;
import bl.UserInterface;
import bl.UserRepository;
import com.sun.glass.events.KeyEvent;
import ejb.Users;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

public class PasswordChangeFrame extends javax.swing.JFrame {
Users user;
EntityManager em;
UserInterface userIr;
    public PasswordChangeFrame(Users user) throws AppException {
        this.user=user;
        em=new EntMngClass("sa","12","localhost").getEntityManager();
        userIr=new UserRepository(this.em);
        initComponents();
        this.setLocation(15,147);
        usernameLbl.setText(this.user.getUsername());
        
    }

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        exitBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        usernameLbl = new javax.swing.JLabel();
        password1Txtf = new javax.swing.JPasswordField();
        password2Txtf = new javax.swing.JPasswordField();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Shkruaje fjalëkalimin e ri");

        exitBtn.setText("exit");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });

        saveBtn.setText("save");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        jLabel2.setText("Shkruaje përsëri fjalëkalimin e ri");

        password1Txtf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                password1TxtfActionPerformed(evt);
            }
        });
        password1Txtf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                password1TxtfKeyPressed(evt);
            }
        });

        password2Txtf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                password2TxtfKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(exitBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                        .addComponent(saveBtn))
                    .addComponent(jLabel2)
                    .addComponent(usernameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(password1Txtf)
                    .addComponent(password2Txtf))
                .addGap(95, 95, 95))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(usernameLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(password1Txtf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(password2Txtf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveBtn)
                    .addComponent(exitBtn))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        changePasswordMethod();
    }//GEN-LAST:event_saveBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        
    }//GEN-LAST:event_exitBtnActionPerformed

    private void password1TxtfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_password1TxtfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_password1TxtfActionPerformed

    private void password1TxtfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_password1TxtfKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
            changePasswordMethod();
    }//GEN-LAST:event_password1TxtfKeyPressed

    private void password2TxtfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_password2TxtfKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
            changePasswordMethod();
    }//GEN-LAST:event_password2TxtfKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField password1Txtf;
    private javax.swing.JPasswordField password2Txtf;
    private javax.swing.JButton saveBtn;
    private javax.swing.JLabel usernameLbl;
    // End of variables declaration//GEN-END:variables

    private void changePasswordMethod() {
    try{
            if(!password1Txtf.getText().equals(password2Txtf.getText()))
                throw new AppException("Fjalëkalimi i parë dallon nga fjalëkalimi i dytë");
            byte[] passwordi = userIr.kripto(user.getSalt()+password1Txtf.getText().trim());
            user.setPasswordi(passwordi);
            userIr.changeLoginPassword(user,password1Txtf.getText());
            user.setNumOfLogins(1);
            userIr.edit(user);
            this.dispose();
            new Login().setVisible(true);
        }catch(AppException ae){
            JOptionPane.showMessageDialog(this,ae.getMessage());
        }
    }
}
