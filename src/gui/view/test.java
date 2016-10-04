/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.view;

import bl.AppException;
import bl.EntMngClass;
import bl.LogsInterface;
import bl.LogsRepository;
import bl.UserInterface;
import bl.UserRepository;
import com.itextpdf.text.pdf.parser.Path;
import ejb.Logs;
import ejb.Users;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.nio.file.Paths;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.swing.DefaultDesktopManager;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

public class test extends javax.swing.JFrame {
    static Users useri;
    static EntityManager em;
    LogsInterface logsir;
    UserInterface userir;
    String absolutepath=Paths.get(".").toAbsolutePath().normalize().toString();
    ArrayList<String> pathfoldernames;
    
    public test(Users useri, EntityManager emc) {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        initComponents();
        addFolderNames();
        CustomCursor();
        
        
        
        this.useri=useri;
        em=emc;
        usernameLbl.setText(this.useri.getUsername().toString());
        logsir=new LogsRepository(em);
        userir=new UserRepository(em);
        this.setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        desktopPane = new javax.swing.JDesktopPane();
        usernameLbl = new javax.swing.JLabel();
        shtotrajnim = new javax.swing.JLabel();
        shtopjesmarres = new javax.swing.JLabel();
        kerko = new javax.swing.JLabel();
        printo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        background = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        exitMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();
        MneaxhoUser = new javax.swing.JMenu();
        ShtoUserMI = new javax.swing.JMenuItem();
        logsMI = new javax.swing.JMenuItem();
        costumizableTblMI = new javax.swing.JMenuItem();
        fshijeUserMI = new javax.swing.JMenuItem();
        exp = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenuItem1.setText("jMenuItem1");

        jMenuItem4.setText("jMenuItem4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        usernameLbl.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 14)); // NOI18N
        usernameLbl.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        desktopPane.add(usernameLbl);
        usernameLbl.setBounds(880, 10, 390, 20);

        shtotrajnim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                shtotrajnimMouseClicked(evt);
            }
        });
        desktopPane.add(shtotrajnim);
        shtotrajnim.setBounds(93, 70, 282, 62);

        shtopjesmarres.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                shtopjesmarresMouseClicked(evt);
            }
        });
        desktopPane.add(shtopjesmarres);
        shtopjesmarres.setBounds(397, 70, 281, 64);

        kerko.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kerkoMouseClicked(evt);
            }
        });
        desktopPane.add(kerko);
        kerko.setBounds(695, 69, 282, 62);

        printo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                printoMouseClicked(evt);
            }
        });
        desktopPane.add(printo);
        printo.setBounds(999, 68, 282, 63);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/logout2.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        desktopPane.add(jLabel1);
        jLabel1.setBounds(1290, 3, 65, 65);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel2.setText("Log out");
        desktopPane.add(jLabel2);
        jLabel2.setBounds(1210, 24, 65, 30);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/designmda.png"))); // NOI18N
        desktopPane.add(background);
        background.setBounds(0, 0, 1365, 680);

        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        contentMenuItem.setMnemonic('c');
        contentMenuItem.setText("Contents");
        helpMenu.add(contentMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        MneaxhoUser.setText("Menaxho Userat");

        ShtoUserMI.setText("Shto User");
        ShtoUserMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShtoUserMIActionPerformed(evt);
            }
        });
        MneaxhoUser.add(ShtoUserMI);

        logsMI.setText("Logs");
        logsMI.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logsMIMouseClicked(evt);
            }
        });
        logsMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logsMIActionPerformed(evt);
            }
        });
        MneaxhoUser.add(logsMI);

        costumizableTblMI.setText("Costumizable Table");
        costumizableTblMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                costumizableTblMIActionPerformed(evt);
            }
        });
        MneaxhoUser.add(costumizableTblMI);

        fshijeUserMI.setText("Fshijë User");
        fshijeUserMI.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fshijeUserMIMouseClicked(evt);
            }
        });
        fshijeUserMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fshijeUserMIActionPerformed(evt);
            }
        });
        MneaxhoUser.add(fshijeUserMI);

        menuBar.add(MneaxhoUser);

        exp.setText("Export Report");

        jMenuItem5.setText("Export to Excel");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        exp.add(jMenuItem5);

        menuBar.add(exp);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 1365, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed
    
    public void setPriority(boolean b){
            ShtoUserMI.setVisible(b);
            fshijeUserMI.setVisible(b);
            logsMI.setVisible(b);
            costumizableTblMI.setVisible(false);
            MneaxhoUser.setVisible(b);
    }
    
    public void shtoTrajnim(){
        try {if(desktopPane.getSelectedFrame()!=null){
            
            desktopPane.getSelectedFrame().setClosed(true);
        }
        } catch (PropertyVetoException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     ImageIcon ic=null;
         if(pathfoldernames.contains("dist")){    
             ic=new ImageIcon("..\\src\\Photos\\main1.png");
         }
         else{
             ic=new ImageIcon("src\\Photos\\main1.png");
         }
     background.setIcon(ic);
     MultiCrudi traini= new MultiCrudi(useri,em);
     desktopPane.add(traini);
     traini.show();
    }
    
    public void shtoPjesmarres(){
        try {if(desktopPane.getSelectedFrame()!=null){
            desktopPane.getSelectedFrame().setClosed(true);
        }
        } catch (PropertyVetoException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
      ImageIcon ic=null;
         if(pathfoldernames.contains("dist")){    
             ic=new ImageIcon("..\\src\\Photos\\main2.png");
         }
         else{
             ic=new ImageIcon("src\\Photos\\main2.png");
         }
     background.setIcon(ic);   
     BigForm part= new BigForm(useri,em);
     desktopPane.add(part);
     part.show();
    }
    
    public void kerko(){
        try {if(desktopPane.getSelectedFrame()!=null){
            desktopPane.getSelectedFrame().setClosed(true);
        }
        } catch (PropertyVetoException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
        ImageIcon ic=null;
         if(pathfoldernames.contains("dist")){    
             ic=new ImageIcon("..\\src\\Photos\\main3.png");
         }
         else{
             ic=new ImageIcon("src\\Photos\\main3.png");
         }
        background.setIcon(ic);
      Kerko k=new Kerko(useri,em);
      desktopPane.add(k);
      k.show();
    }
    
    public void printo(){
        try {if(desktopPane.getSelectedFrame()!=null){
            desktopPane.getSelectedFrame().setClosed(true);
        }
        } catch (PropertyVetoException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
        ImageIcon ic=null;
         if(pathfoldernames.contains("dist")){    
             ic=new ImageIcon("..\\src\\Photos\\main4.png");
         }
         else{
             ic=new ImageIcon("src\\Photos\\main4.png");
         }
        background.setIcon(ic);
     Print p= new Print(useri,em);
     desktopPane.add(p);
     p.show();
    }                                   

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {                                     
        try {
            shtoLogoutLog();
            dispose();
            Login l=new Login();
            l.setVisible(true);
        } catch (AppException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }                                    

    private void ShtoUserMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShtoUserMIActionPerformed
        try {if(desktopPane.getSelectedFrame()!=null){
            desktopPane.getSelectedFrame().setClosed(true);
        }
        } catch (PropertyVetoException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
        ImageIcon ic=null;
         if(pathfoldernames.contains("dist")){    
             ic=new ImageIcon("..\\src\\Photos\\designmda.png");
         }
         else{
             ic=new ImageIcon("src\\Photos\\designmda.png");
         }
        background.setIcon(ic);
     ShtoUser shu= new ShtoUser (useri,em);
     desktopPane.add(shu);
     shu.show();
        
        
    }//GEN-LAST:event_ShtoUserMIActionPerformed

    private void logsMIMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logsMIMouseClicked
        
    }//GEN-LAST:event_logsMIMouseClicked

    private void logsMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logsMIActionPerformed
        try {if(desktopPane.getSelectedFrame()!=null){
            desktopPane.getSelectedFrame().setClosed(true);
        }
        } catch (PropertyVetoException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
        ImageIcon ic=null;
         if(pathfoldernames.contains("dist")){    
             ic=new ImageIcon("..\\src\\Photos\\designmda.png");
         }
         else{
             ic=new ImageIcon("src\\Photos\\designmda.png");
         }
        background.setIcon(ic);
     Logsa l= new Logsa (useri,em);
     desktopPane.add(l);
     l.show();
    }//GEN-LAST:event_logsMIActionPerformed

    private void costumizableTblMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_costumizableTblMIActionPerformed
       try {if(desktopPane.getSelectedFrame()!=null){
            desktopPane.getSelectedFrame().setClosed(true);
        }
        } catch (PropertyVetoException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
        ImageIcon ic=null;
         if(pathfoldernames.contains("dist")){    
             ic=new ImageIcon("..\\src\\Photos\\designmda.png");
         }
         else{
             ic=new ImageIcon("src\\Photos\\designmda.png");
         }
        background.setIcon(ic);
     CreateReport cr= new CreateReport (useri,em);
     desktopPane.add(cr);
     cr.show();
    }//GEN-LAST:event_costumizableTblMIActionPerformed
    public void exportReport(){
        try {if(desktopPane.getSelectedFrame()!=null){
            desktopPane.getSelectedFrame().setClosed(true);
        }
        } catch (PropertyVetoException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
        ImageIcon ic=null;
         if(pathfoldernames.contains("dist")){    
             ic=new ImageIcon("..\\src\\Photos\\designmda.png");
         }
         else{
             ic=new ImageIcon("src\\Photos\\designmda.png");
         }
        background.setIcon(ic);
        ExportEx ex=new ExportEx(useri,em);
        desktopPane.add(ex);
        ex.show();
    }
    
    private void shtotrajnimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shtotrajnimMouseClicked
        shtoTrajnim();
    }//GEN-LAST:event_shtotrajnimMouseClicked

    private void shtopjesmarresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_shtopjesmarresMouseClicked
      shtoPjesmarres();
    }//GEN-LAST:event_shtopjesmarresMouseClicked

    private void kerkoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kerkoMouseClicked
      kerko();
    }//GEN-LAST:event_kerkoMouseClicked

    private void printoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printoMouseClicked
     printo();
    }//GEN-LAST:event_printoMouseClicked
    
	private void ShtoUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShtoUserActionPerformed
        shtoUser();
    }//GEN-LAST:event_ShtoUserActionPerformed

    private void jMenuItem2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem2MouseClicked
        logs();
    }//GEN-LAST:event_jMenuItem2MouseClicked

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        logs();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        exportReport();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void fshijeUserMIMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fshijeUserMIMouseClicked
        
    }//GEN-LAST:event_fshijeUserMIMouseClicked

    private void fshijeUserMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fshijeUserMIActionPerformed
        try {if(desktopPane.getSelectedFrame()!=null){
            desktopPane.getSelectedFrame().setClosed(true);
        }
        } catch (PropertyVetoException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            new fshijeUserat(new EntMngClass("sa","12","localhost").getEntityManager()).setVisible(true);
        } catch (AppException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_fshijeUserMIActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_T){
            shtoTrajnim();
        }
        if(evt.getKeyCode()==KeyEvent.VK_P){
            shtoPjesmarres();
        }
        if(evt.getKeyCode()==KeyEvent.VK_K){
            kerko();
        }
        if(evt.getKeyCode()==KeyEvent.VK_O){
            printo();
        }
        if(evt.getKeyCode()==KeyEvent.VK_S){
            shtoUser();
        }
        if(evt.getKeyCode()==KeyEvent.VK_L){
            logs();
        }
        if(evt.getKeyCode()==KeyEvent.VK_E){
            exportReport();
        }
    }//GEN-LAST:event_formKeyPressed
    
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
        if(pathfoldernames.contains("dist")){
        img=toolkit.getImage("..\\src\\Photos\\cursor2.png");
        img2=toolkit.getImage("..\\src\\Photos\\buttoncursor2.png");
        }
        else{
            img=toolkit.getImage("src\\Photos\\cursor2.png");
            img2=toolkit.getImage("src\\Photos\\buttoncursor2.png");
        }
        Point point=new Point(0,0);
        Cursor cursor=toolkit.createCustomCursor(img, point,"Cursor");
        Cursor buttoncursor=toolkit.createCustomCursor(img2, point,"Cursor");
        background.setCursor(cursor);
        jLabel2.setCursor(cursor);
        this.setCursor(cursor);
        
        shtopjesmarres.setCursor(buttoncursor);
        shtotrajnim.setCursor(buttoncursor);
        kerko.setCursor(buttoncursor);
        printo.setCursor(buttoncursor);
        jLabel1.setCursor(buttoncursor);
        
    }
    
    public void setStrings(String e,String m,String u){
       /* emri=e;
        mbiemri=m;
        useri=u;*/
    }
    
    private void shtoLogoutLog() throws AppException{
        Logs l=new Logs();
        
        Users us=userir.findByUser(useri.getUsername());
        
        
        Date date = logsir.findDate();
        Time time=logsir.findTime();
        Date d1;
        Date d2;
        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat sdf2 = new SimpleDateFormat("HH:mm");
        String today=sdf.format(date);
        String koha=sdf2.format(date);
        
        String Mesazhi=useri.getName()+" "+useri.getSurname()+" me username-in: "+useri.getUsername()+" Është qkyqur nga programi në datën : "+today +" në ora "+koha;
        
        l.setUseri(useri.getName()+" "+useri.getSurname()+" - "+useri.getUsername());
        l.setKoha(time);
        l.setDita(date);
        l.setMesazhi(Mesazhi);
        l.setLloji("Log out");
        l.setUsername(us);
        
        logsir.create(l);
    }
    
    public void shtoUser(){
        try {if(desktopPane.getSelectedFrame()!=null){
            desktopPane.getSelectedFrame().setClosed(true);
        }
        } catch (PropertyVetoException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
        ImageIcon ic=null;
         if(pathfoldernames.contains("dist")){    
             ic=new ImageIcon("..\\src\\Photos\\designmda.png");
         }
         else{
             ic=new ImageIcon("src\\Photos\\designmda.png");
         }
        background.setIcon(ic);
     ShtoUser shu= new ShtoUser (useri,em);
     desktopPane.add(shu);
     shu.show();
    }
    
    public void logs(){
        try {if(desktopPane.getSelectedFrame()!=null){
            desktopPane.getSelectedFrame().setClosed(true);
        }
        } catch (PropertyVetoException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
        ImageIcon ic=null;
         if(pathfoldernames.contains("dist")){    
             ic=new ImageIcon("..\\src\\Photos\\designmda.png");
         }
         else{
             ic=new ImageIcon("src\\Photos\\designmda.png");
         }
        background.setIcon(ic);
     Logsa l= new Logsa (useri,em);
     desktopPane.add(l);
     l.show();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu MneaxhoUser;
    private javax.swing.JMenuItem ShtoUserMI;
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JLabel background;
    private javax.swing.JMenuItem contentMenuItem;
    private javax.swing.JMenuItem costumizableTblMI;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu exp;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem fshijeUserMI;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JLabel kerko;
    private javax.swing.JMenuItem logsMI;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JLabel printo;
    private javax.swing.JLabel shtopjesmarres;
    private javax.swing.JLabel shtotrajnim;
    private javax.swing.JLabel usernameLbl;
    // End of variables declaration//GEN-END:variables
}
