/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.view;
import static Validation.Validation.TextFieldException;
import bl.AppException;
import bl.UserInterface;
import bl.UserRepository;
import ejb.Users;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Besniku
 */
public class ShtoUser extends javax.swing.JInternalFrame {
    Users useri;
    EntityManager em;
    UserInterface userir;
    String absolutepath=Paths.get(".").toAbsolutePath().normalize().toString();
    ArrayList<String> pathfoldernames;
    public ShtoUser(Users useri,EntityManager emm) {
        this.setLocation(15,147);
        
        for(MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().getMouseListeners()){
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().removeMouseListener(listener);
        }
        
        this.useri=useri;
        em=emm;
        userir=new UserRepository(em);
        initComponents();
        addFolderNames();
        CustomCursor();
    }

    private void clearTxtf1() {
    usernametxtf.setText("");
    password1txtf.setText("");
    password2txtf.setText("");
    nametxtf.setText("");
    surnametxtf.setText("");
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
        jLabel7 = new javax.swing.JLabel();
        pozitaCombo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        mbyllebtn = new javax.swing.JButton();
        ruajebtn = new javax.swing.JButton();

        setClosable(true);

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

        jLabel7.setText("Pozita:");

        pozitaCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "stafi" }));
        pozitaCombo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pozitaComboKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(37, 37, 37)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addGap(44, 44, 44)
                            .addComponent(jLabel7)))
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
                    .addComponent(password2txtf)
                    .addComponent(pozitaCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(pozitaCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 24, Short.MAX_VALUE)
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
        jLabel5.setText("Shto LLogari");

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
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(mbyllebtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ruajebtn)
                .addGap(70, 70, 70))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ruajebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ruajebtnActionPerformed
        addUser();
    }//GEN-LAST:event_ruajebtnActionPerformed

    
    
    
    private void mbyllebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mbyllebtnActionPerformed
        dispose();
        
    }//GEN-LAST:event_mbyllebtnActionPerformed

    private void usernametxtfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernametxtfKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
            addUser();
    }//GEN-LAST:event_usernametxtfKeyPressed

    private void password1txtfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_password1txtfKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
            addUser();
    }//GEN-LAST:event_password1txtfKeyPressed

    private void password2txtfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_password2txtfKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
            addUser();
    }//GEN-LAST:event_password2txtfKeyPressed

    private void nametxtfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nametxtfKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
            addUser();
    }//GEN-LAST:event_nametxtfKeyPressed

    private void surnametxtfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_surnametxtfKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
            addUser();
    }//GEN-LAST:event_surnametxtfKeyPressed

    private void pozitaComboKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pozitaComboKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
            addUser();
    }//GEN-LAST:event_pozitaComboKeyPressed
    
    public void addFolderNames(){
        absolutepath = absolutepath.replace("\\", "/");
        String [] morrqat= absolutepath.split("/");
        pathfoldernames=new ArrayList<String>();
        for(int i =0;i<morrqat.length;i++){
            
            pathfoldernames.add(morrqat[i]);
        }
        
    }
    
    public void CustomCursor(){
        Toolkit toolkit=Toolkit.getDefaultToolkit();
        Image img=null;
        Image img2=null;
        Image img3=null;
        if(pathfoldernames.contains("dist")){
        img=toolkit.getImage("..\\src\\Photos\\cursor2.png");
        img2=toolkit.getImage("..\\src\\Photos\\buttoncursor2.png");
        img3=toolkit.getImage("..\\src\\Photos\\textcursor2.png");
        }
        else{
            img=toolkit.getImage("src\\Photos\\cursor2.png");
            img2=toolkit.getImage("src\\Photos\\buttoncursor2.png");
            img3=toolkit.getImage("src\\Photos\\textcursor2.png");
        }
        Point point=new Point(0,0);
        Cursor cursor=toolkit.createCustomCursor(img, point,"Cursor");
        Cursor buttoncursor=toolkit.createCustomCursor(img2, point,"Cursor");
        Cursor textcursor=toolkit.createCustomCursor(img3, point,"Cursor");
        
        
        
        
        
        jLabel1.setCursor(cursor);
        jLabel2.setCursor(cursor);
        jLabel3.setCursor(cursor);
        jLabel4.setCursor(cursor);
        jLabel5.setCursor(cursor);
        jLabel6.setCursor(cursor);
        jLabel7.setCursor(cursor);
        this.setCursor(cursor);
        
        
        
        
        mbyllebtn.setCursor(buttoncursor);
        ruajebtn.setCursor(buttoncursor);
        pozitaCombo.setCursor(buttoncursor);
        
        
        
        nametxtf.setCursor(textcursor);
        surnametxtf.setCursor(textcursor);
        usernametxtf.setCursor(textcursor);
        password1txtf.setCursor(textcursor);
        password2txtf.setCursor(textcursor);
        
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton mbyllebtn;
    private javax.swing.JTextField nametxtf;
    private javax.swing.JPasswordField password1txtf;
    private javax.swing.JPasswordField password2txtf;
    private javax.swing.JComboBox<String> pozitaCombo;
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

    private void addUser() {
    try {
            validation();
            
        Users user=new Users();
        user.setUsername(usernametxtf.getText().trim());
        user.setName(nametxtf.getText().trim());
        user.setSurname(surnametxtf.getText().trim());
        user.setPozita(pozitaCombo.getSelectedItem().toString().trim());
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
                if(user.getPozita().equals("stafi"))
                    userir.createSqlLogin(user.getUsername(),password1txtf.getText().trim());
                else
                    userir.createSqlLoginAdministrator(user.getUsername(),password1txtf.getText().trim());
            } catch (AppException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        clearTxtf1();
            JOptionPane.showMessageDialog(null,"U ruajt me sukses.");
            } catch (AppException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
