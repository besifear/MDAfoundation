
package gui.view;

import ejb.Logs;
import bl.LogsInterface;
import bl.LogsRepository;
import ejb.Users;
import ejb.TrainingProcess;
import gui.model.LogsTableModel;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class Logsa extends javax.swing.JInternalFrame {
    Users useri;
    EntityManager em;
    
    LogsInterface logsir;
    LogsTableModel logstm;
    String absolutepath=Paths.get(".").toAbsolutePath().normalize().toString();
    ArrayList<String> pathfoldernames;

    public Logsa(Users useri,EntityManager emm) {
        this.useri=useri;
        em=emm;
        logsir=new LogsRepository(em);
        
        logstm=new LogsTableModel(em);
        this.setLocation(15,147);
        initComponents();
        removeLeftButton();
        lTabelaLoad();
        LogsTabelaMoveKey();
        setColWidth();
        addFolderNames();
        CustomCursor();
        
        
        searchtxtf.getDocument().addDocumentListener(new DocumentListener(){
        @Override
        public void insertUpdate(DocumentEvent e) {
           kerkologs();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            kerkologs();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            kerkologs();
        }
          });
        
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
    
    public void kerkologs(){
                    if(searchcombo.getSelectedItem().equals("Useri")){
                    if(orderbycombo.getSelectedItem().equals("Rritëse") && searchtxtf.getText().equals(""))
                        OrderByUserTabelaLoadAsc();
                    if(orderbycombo.getSelectedItem().equals("Zbritëse") && searchtxtf.getText().equals(""))
                        OrderByUserTabelaLoadDes();
                    else if (orderbycombo.getSelectedItem().equals("Rritëse") && !searchtxtf.getText().equals(""))
                        findByUserTabelaLoadAsc(searchtxtf.getText());
                    else if (orderbycombo.getSelectedItem().equals("Zbritëse") && !searchtxtf.getText().equals(""))
                        findByUserTabelaLoadDes(searchtxtf.getText());
                    }

                   else if(searchcombo.getSelectedItem().equals("Lloji")){

                    if (orderbycombo.getSelectedItem().equals("Rritëse") && searchtxtf.getText().equals(""))
                            OrderByLlojiTabelaLoadAsc();    
                    else if (orderbycombo.getSelectedItem().equals("Zbritëse") && searchtxtf.getText().equals(""))
                            OrderByLlojiTabelaLoadDes();
                    else if (orderbycombo.getSelectedItem().equals("Rritëse") && !searchtxtf.getText().equals(""))
                             findByLlojiTabelaLoadAsc(searchtxtf.getText());
                    else if (orderbycombo.getSelectedItem().equals("Zbritëse") && !searchtxtf.getText().equals(""))
                            findByLlojiTabelaLoadDes(searchtxtf.getText());
                    }

                }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        orderbycombo = new javax.swing.JComboBox<>();
        searchcombo = new javax.swing.JComboBox<>();
        searchtxtf = new javax.swing.JTextField();
        msgtxtf = new javax.swing.JTextField();
        searchbtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        background = new javax.swing.JLabel();

        setClosable(true);
        setTitle("LOGS");

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        orderbycombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Rritëse", "Zbritëse" }));
        jPanel1.add(orderbycombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 23, 160, 35));

        searchcombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Useri", "Lloji", "Data" }));
        jPanel1.add(searchcombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(333, 23, 170, 35));

        searchtxtf.setBackground(new java.awt.Color(225, 225, 225));
        searchtxtf.setBorder(null);
        jPanel1.add(searchtxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(558, 28, 140, 30));

        msgtxtf.setBackground(new java.awt.Color(225, 225, 225));
        msgtxtf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        msgtxtf.setBorder(null);
        jPanel1.add(msgtxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 83, 1020, 35));

        searchbtn.setBackground(new java.awt.Color(105, 3, 3));
        searchbtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        searchbtn.setForeground(new java.awt.Color(255, 255, 255));
        searchbtn.setText("Kërko");
        searchbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchbtnActionPerformed(evt);
            }
        });
        jPanel1.add(searchbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 20, 150, 50));

        tbl.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl.setShowHorizontalLines(false);
        tbl.setShowVerticalLines(false);
        jScrollPane1.setViewportView(tbl);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 1110, 330));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/logsdesign.png"))); // NOI18N
        jPanel1.add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1130, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 486, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchbtnActionPerformed(java.awt.event.ActionEvent evt) {                                          
        DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
            Date start1;
            Date start2;
        
            if(searchcombo.getSelectedItem().equals("Useri")){
                    if(orderbycombo.getSelectedItem().equals("Rritëse") && searchtxtf.getText().equals(""))
                        OrderByUserTabelaLoadAsc();
                    else if(orderbycombo.getSelectedItem().equals("Zbritëse") && searchtxtf.getText().equals(""))
                        OrderByUserTabelaLoadDes();
                    else if (orderbycombo.getSelectedItem().equals("Rritëse") && !searchtxtf.getText().equals(""))
                        findByUserTabelaLoadAsc(searchtxtf.getText());
                    else if (orderbycombo.getSelectedItem().equals("Zbritëse") && !searchtxtf.getText().equals(""))
                        findByUserTabelaLoadDes(searchtxtf.getText());
                    }

            else if(searchcombo.getSelectedItem().equals("Lloji")){

                    if (orderbycombo.getSelectedItem().equals("Rritëse") && searchtxtf.getText().equals(""))
                            OrderByLlojiTabelaLoadAsc();    
                    else if (orderbycombo.getSelectedItem().equals("Zbritëse") && searchtxtf.getText().equals(""))
                            OrderByLlojiTabelaLoadDes();
                    else if (orderbycombo.getSelectedItem().equals("Rritëse") && !searchtxtf.getText().equals(""))
                             findByLlojiTabelaLoadAsc(searchtxtf.getText());
                    else if (orderbycombo.getSelectedItem().equals("Zbritëse") && !searchtxtf.getText().equals(""))
                            findByLlojiTabelaLoadDes(searchtxtf.getText());
                    }

            else if(searchcombo.getSelectedItem().equals("Data")){

                    if (orderbycombo.getSelectedItem().equals("Rritëse") && searchtxtf.getText().equals(""))
                            OrderByDateTabelaLoadAsc();    
                    else if (orderbycombo.getSelectedItem().equals("Zbritëse") && searchtxtf.getText().equals(""))
                            OrderByDateTabelaLoadDes();
                    else if (orderbycombo.getSelectedItem().equals("Rritëse") && !searchtxtf.getText().equals(""))
                              try {
                                    start1=(Date)dateFormat.parse(searchtxtf.getText());
                                     findByDateTabelaLoadAsc(start1);
                            } catch (ParseException ex) {
                                Logger.getLogger(Kerko.class.getName()).log(Level.SEVERE, null, ex);
                            }
                    else if (orderbycombo.getSelectedItem().equals("Zbritëse") && !searchtxtf.getText().equals(""))
                             try {
                                    start2=(Date)dateFormat.parse(searchtxtf.getText());
                                     findByDateTabelaLoadAsc(start2);
                            } catch (ParseException ex) {
                                Logger.getLogger(Kerko.class.getName()).log(Level.SEVERE, null, ex);
                            }
                    }

    }                                        


    private void lTabelaLoad(){
          List<Logs> data=logsir.findAll();
          logstm.add(data);
          tbl.setModel(logstm);
          
          logstm.fireTableDataChanged();
          
         
    }
    
    private void OrderByLlojiTabelaLoadAsc(){
        List<Logs>Temp =logsir.OrderByTypeAsc();
        logstm.add(Temp);
        tbl.setModel(logstm);
        logstm.fireTableDataChanged();
    }
    
    private void OrderByLlojiTabelaLoadDes(){
        List<Logs>Temp =logsir.OrderByTypeDes();
        logstm.add(Temp);
        tbl.setModel(logstm);
        logstm.fireTableDataChanged();
    } 

    private void findByLlojiTabelaLoadAsc(String emri){
        List<Logs>Temp =logsir.findByTypeAsc(emri);
        logstm.add(Temp);
        tbl.setModel(logstm);
        logstm.fireTableDataChanged();
    }
    
    private void findByLlojiTabelaLoadDes(String emri){
        List<Logs>Temp =logsir.findByTypeDes(emri);
        logstm.add(Temp);
        tbl.setModel(logstm);
        logstm.fireTableDataChanged();
    }
    
    private void OrderByDateTabelaLoadAsc(){
        List<Logs>Temp =logsir.OrderByDateAsc();
        logstm.add(Temp);
        tbl.setModel(logstm);
        logstm.fireTableDataChanged();
    }
    
    private void OrderByDateTabelaLoadDes(){
        List<Logs>Temp =logsir.OrderByDateDes();
        logstm.add(Temp);
        tbl.setModel(logstm);
        logstm.fireTableDataChanged();
    } 

    private void findByDateTabelaLoadAsc(Date sd){
        List<Logs>Temp =logsir.findByDateAsc(sd);
        logstm.add(Temp);
        tbl.setModel(logstm);
        logstm.fireTableDataChanged();
    }
    
    private void findByDateTabelaLoadDes(Date sd){
        List<Logs>Temp =logsir.findByDateDes(sd);
        logstm.add(Temp);
        tbl.setModel(logstm);
        logstm.fireTableDataChanged();
    }
    
    private void OrderByUserTabelaLoadAsc(){
        List<Logs>Temp =logsir.OrderByUserAsc();
        logstm.add(Temp);
        tbl.setModel(logstm);
        logstm.fireTableDataChanged();
    }
    
    private void OrderByUserTabelaLoadDes(){
        List<Logs>Temp =logsir.OrderByUserDes();
        logstm.add(Temp);
        tbl.setModel(logstm);
        logstm.fireTableDataChanged();
    } 

    private void findByUserTabelaLoadAsc(String emri){
        List<Logs>Temp =logsir.findByUserAsc(emri);
        logstm.add(Temp);
        tbl.setModel(logstm);
        logstm.fireTableDataChanged();
    }
    
    private void findByUserTabelaLoadDes(String emri){
        List<Logs>Temp =logsir.findByUserDes(emri);
        logstm.add(Temp);
        tbl.setModel(logstm);
        logstm.fireTableDataChanged();
    }
    
    public void setColWidth(){
        
        
        TableModel tm=tbl.getModel();
        
        tbl.getColumnModel().getColumn(4).setPreferredWidth(450);
        
    }
    
    private void LogsTabelaMoveKey(){
        
    ListSelectionModel rowSM=tbl.getSelectionModel();
    rowSM.addListSelectionListener (new ListSelectionListener(){
    @Override
    public void valueChanged (ListSelectionEvent e){
        if(tbl.getModel()==logstm)
        {
    if(e.getValueIsAdjusting())
        return;
    ListSelectionModel rowSM = (ListSelectionModel)e.getSource();
    int selectedIndex = rowSM.getMinSelectionIndex();
    if(selectedIndex>-1){
        Logs l=logstm.getLogs(selectedIndex);
        msgtxtf.setText(l.getMesazhi());
    }
    }
    }
    });
    }
    
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
        
        
        
        
        
        jScrollPane1.setCursor(cursor);
        background.setCursor(cursor);
        this.setCursor(cursor);
        
        
        orderbycombo.setCursor(buttoncursor);
        searchcombo.setCursor(buttoncursor);
        searchbtn.setCursor(buttoncursor);
        
        
        
        searchtxtf.setCursor(textcursor);
        msgtxtf.setCursor(textcursor);
        
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField msgtxtf;
    private javax.swing.JComboBox<String> orderbycombo;
    private javax.swing.JButton searchbtn;
    private javax.swing.JComboBox<String> searchcombo;
    private javax.swing.JTextField searchtxtf;
    private javax.swing.JTable tbl;
    // End of variables declaration//GEN-END:variables
}
