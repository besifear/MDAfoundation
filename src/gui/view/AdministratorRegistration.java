
package gui.view;

import static Validation.Validation.TextFieldException;
import bl.AppException;
import bl.EntMngClass;
import bl.UserInterface;
import bl.UserRepository;
import ejb.Users;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.concurrent.ThreadLocalRandom;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

public class AdministratorRegistration extends javax.swing.JFrame {
EntityManager em;
UserInterface userir;
    
    public AdministratorRegistration(EntityManager emm) {
        em=emm;
        userir=new UserRepository(em);
        setLayout(new BorderLayout());
        setSize(400,308);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int jFramewidth=this.getSize().width;
        int jFrameheight=this.getSize().height;
        int locationx=(dim.width-jFramewidth)/2;
        int locationy=(dim.height-jFrameheight)/2;
        this.setLocation(locationx,locationy);
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        usernametxtf = new javax.swing.JTextField();
        nametxtf = new javax.swing.JTextField();
        surnametxtf = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        password1txtf = new javax.swing.JPasswordField();
        password2txtf = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        mbyllebtn = new javax.swing.JButton();
        ruajebtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Username:");

        jLabel2.setText("Password:");

        jLabel3.setText("Emri:");

        jLabel4.setText("Mbiemri:");

        usernametxtf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usernametxtfKeyPressed(evt);
            }
        });

        nametxtf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nametxtfKeyPressed(evt);
            }
        });

        surnametxtf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                surnametxtfKeyPressed(evt);
            }
        });

        jLabel6.setText("Re-Password:");

        password1txtf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                password1txtfKeyPressed(evt);
            }
        });

        password2txtf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                password2txtfKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usernametxtf)
                    .addComponent(nametxtf, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                    .addComponent(surnametxtf, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                    .addComponent(password1txtf)
                    .addComponent(password2txtf))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(usernametxtf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(password1txtf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(password2txtf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nametxtf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(surnametxtf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(26, 26, 26))
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Rregjistro Administratorin/ën");

        mbyllebtn.setText("Mbylle");
        mbyllebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mbyllebtnActionPerformed(evt);
            }
        });

        ruajebtn.setText("Ruaje");
        ruajebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ruajebtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(mbyllebtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ruajebtn)
                .addGap(59, 59, 59))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mbyllebtn)
                    .addComponent(ruajebtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(11, 11, 11)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(12, 12, 12)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 308, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mbyllebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mbyllebtnActionPerformed
        dispose();

    }//GEN-LAST:event_mbyllebtnActionPerformed

    private void ruajebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ruajebtnActionPerformed
        loginMethod();
    }//GEN-LAST:event_ruajebtnActionPerformed

    private void usernametxtfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernametxtfKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            loginMethod();
        }
    }//GEN-LAST:event_usernametxtfKeyPressed

    private void password1txtfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_password1txtfKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            loginMethod();
        }
    }//GEN-LAST:event_password1txtfKeyPressed

    private void password2txtfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_password2txtfKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            loginMethod();
        }
    }//GEN-LAST:event_password2txtfKeyPressed

    private void nametxtfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nametxtfKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            loginMethod();
        }
    }//GEN-LAST:event_nametxtfKeyPressed

    private void surnametxtfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_surnametxtfKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            loginMethod();
        }
    }//GEN-LAST:event_surnametxtfKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton mbyllebtn;
    private javax.swing.JTextField nametxtf;
    private javax.swing.JPasswordField password1txtf;
    private javax.swing.JPasswordField password2txtf;
    private javax.swing.JButton ruajebtn;
    private javax.swing.JTextField surnametxtf;
    private javax.swing.JTextField usernametxtf;
    // End of variables declaration//GEN-END:variables

    private void validation()throws AppException {
        TextFieldException(usernametxtf,"Shkruaje Usernamin e llogarisë të re.");
        if(userir.findByUsername(usernametxtf.getText().trim()))
            throw new AppException("Ekzistonë një llogari me Username të tillë.");
        TextFieldException(password1txtf,"Shkruaje Passwordin e llogarisë të re.");
        TextFieldException(password2txtf,"Rishkruaje Passwordin e llogarisë të re.");
        TextFieldException(nametxtf,"Shkruaje Emrin e personit që i takon llogaria.");
        TextFieldException(surnametxtf,"Shkruaje Mbiemrin e personit që i takon llogaria.");
        if(!password1txtf.getText().trim().equals(password2txtf.getText().trim()))
            throw new AppException("Passwordi i dytë dallon nga passwordi i parë.");
    }

    private void clearTxtf1() {
        usernametxtf.setText("");
        password1txtf.setText("");
        password2txtf.setText("");
        nametxtf.setText("");
        surnametxtf.setText("");
    }

    private void loginMethod() {
        try {
            validation();
            Users user=new Users();
            user.setUsername(usernametxtf.getText().trim());
            user.setName(nametxtf.getText().trim());
            user.setSurname(surnametxtf.getText().trim());
            user.setPozita("Administrator");
            String s="";
            for(int i=0;i<64;i++){
                char c=(char)ThreadLocalRandom.current().nextInt(65, 122 + 1);
                s+=c;
            }
            user.setSalt(s);
            byte[] passwordi = userir.kripto(s+password1txtf.getText().trim());
            user.setPasswordi(passwordi);
            try {
                userir.create(user);
                userir.createSqlLoginAdministrator(user.getUsername(),password1txtf.getText().trim());
                if(new UserRepository(new EntMngClass("Checker","12345","localhost").getEntityManager()).CheckAdminExists()!=0){
                    this.dispose();
                    new Login().setVisible(true);
                }
            } catch (AppException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
            clearTxtf1();

        } catch (AppException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

}



/*


CREATE LOGIN Bardhyl@seemda WITH password= '12345',DEFAULT_DATABASE = MDAfondation
	,DEFAULT_LANGUAGE = english
	,CHECK_EXPIRATION = OFF;
	
USE MDAfondation;
CREATE USER Bardhyl@seemda FOR LOGIN Bardhyl@seemda;

ALTER ROLE  Admini 
ADD MEMBER Bardhyl@seemda;
USE MDAfondation;
ALTER ROLE  db_accessadmin 
ADD MEMBER Bardhyl@seemda;
USE MDAfondation;
ALTER ROLE  db_securityadmin
ADD MEMBER Bardhyl@seemda;

GRANT ALTER ANY LOGIN TO Bardhyl@seemda;

*/