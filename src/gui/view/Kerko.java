
package gui.view;

import ejb.Participant;
import bl.ParticipantInterface;
import bl.ParticipantRepository;
import bl.TrainingProcessInterface;
import bl.TrainingProcessRepository;
import bl.TopicsCoveredInterface;
import bl.TopicsCoveredRepository;
import bl.CompanyInterface;
import bl.CompanyRepository;
import bl.TrainingInterface;
import bl.TrainingRepository;
import bl.TrainerInterface;
import bl.TrainerRepository;
import bl.TrainerEvaluationInterface;
import bl.TrainerEvaluationRepository;
import ejb.Training;
import bl.TrainingInterface;
import bl.TrainingRepository;
import ejb.Training;
import ejb.Company;
import ejb.TrainingProcess;
import ejb.TTrainerEvaluation;
import gui.model.ParticipantTableModel;
import gui.model.TrainingProcessUltimateTableModel;
import gui.model.ParticipantKerkoCustomTableModel;
import Utility.MyTablePrintable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import ejb.Trainer;
import ejb.Users;
import gui.model.ParticipantKerkoTableModel;
import java.io.FileOutputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Cursor;
import static java.awt.Font.ITALIC;
import java.awt.GradientPaint;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.awt.print.PrinterJob;
import java.nio.file.Paths;
import javafx.scene.chart.CategoryAxis;
import javax.persistence.EntityManager;
import javax.swing.JCheckBox;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

public class Kerko extends javax.swing.JInternalFrame {
    
    ParticipantInterface participantir;
    ParticipantKerkoTableModel participanttm;
    ParticipantKerkoCustomTableModel participantcustomtm;
    TrainingProcessInterface trainingir;
    TrainingProcessUltimateTableModel trainingtm;
    CompanyInterface companyir ;
    TrainingInterface titleir;
    TrainerInterface trainerir;
    TrainerEvaluationInterface trainerevir;
    TopicsCoveredInterface topicsir;
    ParticipantKerkoTableModel participantkerkotm;
    TrainingInterface trainir;
    Users useri;
    EntityManager em;
    ArrayList<String> colnamesarraylist;
    String [] columnNames;
    String absolutepath=Paths.get(".").toAbsolutePath().normalize().toString();
    ArrayList<String> pathfoldernames;
   
    public Kerko(Users useri,EntityManager emm) {
        this.useri=useri;
        em=emm;
        participanttm=new ParticipantKerkoTableModel();
        trainingtm= new TrainingProcessUltimateTableModel(em);
        participantir = new ParticipantRepository(em);
        trainingir=new TrainingProcessRepository(em);
        companyir =new CompanyRepository(em);
        titleir=new TrainingRepository(em);
        trainerir=new TrainerRepository(em);
        trainerevir= new TrainerEvaluationRepository(em);
        topicsir=new TopicsCoveredRepository(em);
        trainir=new TrainingRepository(em);
        colnamesarraylist=new ArrayList<String>();
        
        this.setLocation(15,147);
        
        for(MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().getMouseListeners()){
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().removeMouseListener(listener);
        }
        
        initComponents();
        
        
        
        
        ParticipantTabelaLoad();
        TrainingTabelaLoadAsc();
        addFolderNames();
        CustomCursor();
        setColWidth();
        mbushStatistikat();
        dynamicListener();
        
        searchtxtf.getDocument().addDocumentListener(new DocumentListener(){
        @Override
        public void insertUpdate(DocumentEvent e) {
           kerkoparticipant();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            kerkoparticipant();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            kerkoparticipant();
        }
        
                public void kerkoparticipant(){
                    if(searchcombo.getSelectedItem().equals("Emrit/Mbiemrit")){
                    if(orderbycombo.getSelectedItem().equals("Rritëse") && searchtxtf.getText().equals(""))
                        OrderByEmriTabelaLoadAsc();
                    if(orderbycombo.getSelectedItem().equals("Zbritëse") && searchtxtf.getText().equals(""))
                        OrderByEmriTabelaLoadDes();
                    else if (orderbycombo.getSelectedItem().equals("Rritëse") && !searchtxtf.getText().equals(""))
                        findByEmriTabelaLoadAsc(searchtxtf.getText());
                    else if (orderbycombo.getSelectedItem().equals("Zbritëse") && !searchtxtf.getText().equals(""))
                        findByEmriTabelaLoadDes(searchtxtf.getText());
                    }

                   else if(searchcombo.getSelectedItem().equals("Gjinis")){

                    if (orderbycombo.getSelectedItem().equals("Rritëse") && searchtxtf.getText().equals(""))
                            OrderByGjiniaTabelaLoadAsc();    
                    else if (orderbycombo.getSelectedItem().equals("Zbritëse") && searchtxtf.getText().equals(""))
                            OrderByGjiniaTabelaLoadDes();
                    else if (orderbycombo.getSelectedItem().equals("Rritëse") && !searchtxtf.getText().equals(""))
                             findByGjiniaTabelaLoadAsc(searchtxtf.getText());
                    else if (orderbycombo.getSelectedItem().equals("Zbritëse") && !searchtxtf.getText().equals(""))
                            findByGjiniaTabelaLoadDes(searchtxtf.getText());
                    }

                   else if(searchcombo.getSelectedItem().equals("Numrit Personal")){
                      if(getLetters()==0){
                    if (orderbycombo.getSelectedItem().equals("Rritëse") && searchtxtf.getText().equals(""))
                            OrderByPersonalIDTabelaLoadAsc();    
                    else if (orderbycombo.getSelectedItem().equals("Zbritëse") && searchtxtf.getText().equals(""))
                            OrderByPersonalIDTabelaLoadDes();
                    else if (orderbycombo.getSelectedItem().equals("Rritëse") && !searchtxtf.getText().equals(""))
                            findByPersonalIDTabelaLoadAsc(Integer.parseInt(searchtxtf.getText()));
                    else if (orderbycombo.getSelectedItem().equals("Zbritëse") && !searchtxtf.getText().equals(""))
                            findByPersonalIDTabelaLoadDes(Integer.parseInt(searchtxtf.getText()));
                      }
                    }

                   if(searchcombo.getSelectedItem().equals("Qytetit")){
                    if(orderbycombo.getSelectedItem().equals("Rritëse") && searchtxtf.getText().equals(""))
                        OrderByQytetiTabelaLoadAsc();
                    if(orderbycombo.getSelectedItem().equals("Zbritëse") && searchtxtf.getText().equals(""))
                        OrderByQytetiTabelaLoadDes();
                    else if (orderbycombo.getSelectedItem().equals("Rritëse") && !searchtxtf.getText().equals(""))
                        findByQytetiTabelaLoadAsc(searchtxtf.getText());
                    else if (orderbycombo.getSelectedItem().equals("Zbritëse") && !searchtxtf.getText().equals(""))
                        findByQytetiTabelaLoadDes(searchtxtf.getText());
                    }
                }
          });
        
        
        searchtxtf2.getDocument().addDocumentListener(new DocumentListener(){
        @Override
        public void insertUpdate(DocumentEvent e) {
           kerkotraining();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            kerkotraining();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            kerkotraining();
        }
        
                public void kerkotraining(){
                    
                     if(searchcombo2.getSelectedItem().equals("Vendit")){
                     if(orderbycombo2.getSelectedItem().equals("Rritëse") && searchtxtf2.getText().equals(""))
                         OrderByVendiTabelaLoadAsc();
                     if(orderbycombo2.getSelectedItem().equals("Zbritëse") && searchtxtf2.getText().equals(""))
                         OrderByVendiTabelaLoadDes();
                     else if (orderbycombo2.getSelectedItem().equals("Rritëse") && !searchtxtf2.getText().equals(""))
                         findByVendiTabelaLoadAsc(searchtxtf2.getText());
                     else if (orderbycombo2.getSelectedItem().equals("Zbritëse") && !searchtxtf2.getText().equals(""))
                         findByVendiTabelaLoadDes(searchtxtf2.getText());
                     }


                    

                     if(searchcombo2.getSelectedItem().equals("ID e Trajnimit")){
                         if(getLetters()==0){
                     if(orderbycombo2.getSelectedItem().equals("Rritëse") && searchtxtf2.getText().equals(""))
                         OrderByTrainingProcessIDTabelaLoadAsc();
                     if(orderbycombo2.getSelectedItem().equals("Zbritëse") && searchtxtf2.getText().equals(""))
                         OrderByTrainingProcessIDTabelaLoadDes();
                     else if (orderbycombo2.getSelectedItem().equals("Rritëse") && !searchtxtf2.getText().equals(""))
                         findByTrainingProcessIDTabelaLoadAsc(searchtxtf2.getText());
                     else if (orderbycombo2.getSelectedItem().equals("Zbritëse") && !searchtxtf2.getText().equals(""))
                         findByTrainingProcessIDTabelaLoadDes(searchtxtf2.getText());
                     }
                     }

                     

                     if(searchcombo2.getSelectedItem().equals("Titulli i trajnimit")){
                     if(orderbycombo2.getSelectedItem().equals("Rritëse") && searchtxtf2.getText().equals(""))
                         OrderByTitleTabelaLoadAsc();
                     if(orderbycombo2.getSelectedItem().equals("Zbritëse") && searchtxtf2.getText().equals(""))
                         OrderByTitleTabelaLoadDes();
                     else if (orderbycombo2.getSelectedItem().equals("Rritëse") && !searchtxtf2.getText().equals(""))
                         findByTitleTabelaLoadAsc(searchtxtf2.getText());
                     else if (orderbycombo2.getSelectedItem().equals("Zbritëse") && !searchtxtf2.getText().equals(""))
                         findByTitleTabelaLoadDes(searchtxtf2.getText());
                     }

                     if(searchcombo2.getSelectedItem().equals("Projektit")){
                     if(orderbycombo2.getSelectedItem().equals("Rritëse") && searchtxtf2.getText().equals(""))
                         OrderByProjectTabelaLoadAsc();
                     if(orderbycombo2.getSelectedItem().equals("Zbritëse") && searchtxtf2.getText().equals(""))
                         OrderByProjectTabelaLoadDes();
                     else if (orderbycombo2.getSelectedItem().equals("Rritëse") && !searchtxtf2.getText().equals(""))
                         findByProjectTabelaLoadAsc(searchtxtf2.getText());
                     else if (orderbycombo2.getSelectedItem().equals("Zbritëse") && !searchtxtf2.getText().equals(""))
                         findByProjectTabelaLoadDes(searchtxtf2.getText());
                     }

                     
        
                }
          });
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        searchLbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        orderbycombo = new javax.swing.JComboBox<>();
        searchcombo = new javax.swing.JComboBox<>();
        searchtxtf = new javax.swing.JTextField();
        shtyppdfbtn1 = new javax.swing.JButton();
        kerkobtn1 = new javax.swing.JButton();
        emricheck = new javax.swing.JCheckBox();
        mbiemricheck = new javax.swing.JCheckBox();
        dobcheck = new javax.swing.JCheckBox();
        idnumcheck = new javax.swing.JCheckBox();
        emailcheck = new javax.swing.JCheckBox();
        telefonicheck = new javax.swing.JCheckBox();
        qyteticheck = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        orderbycombo2 = new javax.swing.JComboBox<>();
        searchcombo2 = new javax.swing.JComboBox<>();
        searchtxtf2 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        traintbl = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        shtyppdfbtn2 = new javax.swing.JButton();
        kerkobtn2 = new javax.swing.JButton();
        searchLbl2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        statsbycombo = new javax.swing.JComboBox<>();
        barchartbtn = new javax.swing.JButton();
        tprocessstat = new javax.swing.JLabel();
        participantstat = new javax.swing.JLabel();
        trainerstat = new javax.swing.JLabel();
        partMstat = new javax.swing.JLabel();
        partFstat = new javax.swing.JLabel();
        topicsf = new javax.swing.JLabel();
        publicinstif = new javax.swing.JLabel();
        internationalorgf = new javax.swing.JLabel();
        nongoverf = new javax.swing.JLabel();
        privatebus = new javax.swing.JLabel();
        individualf = new javax.swing.JLabel();
        companysf = new javax.swing.JLabel();
        tevf1 = new javax.swing.JLabel();
        tevf2 = new javax.swing.JLabel();
        tevf3 = new javax.swing.JLabel();
        tevf4 = new javax.swing.JLabel();
        trainerf = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setClosable(true);
        setTitle("KËRKO");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        searchLbl.setForeground(new java.awt.Color(255, 0, 0));
        jPanel3.add(searchLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 380, 10));

        tbl.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl.setShowHorizontalLines(false);
        tbl.setShowVerticalLines(false);
        jScrollPane1.setViewportView(tbl);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 103, 1225, 370));

        orderbycombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Rritëse", "Zbritëse" }));
        jPanel3.add(orderbycombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 150, 30));

        searchcombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Emrit/Mbiemrit", "Gjinis", "Numrit Personal", "Qytetit", "TrainingID" }));
        jPanel3.add(searchcombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 150, 30));

        searchtxtf.setBackground(new java.awt.Color(225, 225, 225));
        searchtxtf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        searchtxtf.setBorder(null);
        jPanel3.add(searchtxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(557, 30, 146, 28));

        shtyppdfbtn1.setBackground(new java.awt.Color(113, 3, 3));
        shtyppdfbtn1.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        shtyppdfbtn1.setForeground(new java.awt.Color(255, 255, 255));
        shtyppdfbtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/pdficon.png"))); // NOI18N
        shtyppdfbtn1.setText("Shtyp PDF file");
        shtyppdfbtn1.setPreferredSize(new java.awt.Dimension(180, 45));
        shtyppdfbtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shtyppdfbtn1ActionPerformed(evt);
            }
        });
        jPanel3.add(shtyppdfbtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 20, 200, 50));

        kerkobtn1.setBackground(new java.awt.Color(105, 3, 3));
        kerkobtn1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        kerkobtn1.setForeground(new java.awt.Color(255, 255, 255));
        kerkobtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/searchicon.png"))); // NOI18N
        kerkobtn1.setText("Kërko");
        kerkobtn1.setPreferredSize(new java.awt.Dimension(110, 47));
        kerkobtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kerkobtn1ActionPerformed(evt);
            }
        });
        jPanel3.add(kerkobtn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 22, 110, 40));

        emricheck.setText("Emri");
        jPanel3.add(emricheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 70, -1, -1));

        mbiemricheck.setText("Mbiemri");
        jPanel3.add(mbiemricheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, -1, -1));

        dobcheck.setText("Data e Lindjes");
        jPanel3.add(dobcheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, -1, -1));

        idnumcheck.setText("Numri Personal");
        jPanel3.add(idnumcheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, -1, -1));

        emailcheck.setText("Email");
        jPanel3.add(emailcheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, -1, -1));

        telefonicheck.setText("Telefoni");
        jPanel3.add(telefonicheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 70, -1, -1));

        qyteticheck.setText("Qyteti");
        jPanel3.add(qyteticheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 70, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/kerko2.png"))); // NOI18N
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1244, -1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Kerko Pjesmarres", jPanel1);

        jPanel4.setLayout(null);

        orderbycombo2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Rritëse", "Zbritëse" }));
        jPanel4.add(orderbycombo2);
        orderbycombo2.setBounds(100, 30, 150, 30);

        searchcombo2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID e Trajnimit", "Vendit", "Projektit", "Titulli i trajnimit", "Koha e fillimit", "Koha e perfundimit" }));
        jPanel4.add(searchcombo2);
        searchcombo2.setBounds(340, 30, 150, 30);

        searchtxtf2.setBackground(new java.awt.Color(225, 225, 225));
        searchtxtf2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        searchtxtf2.setBorder(null);
        jPanel4.add(searchtxtf2);
        searchtxtf2.setBounds(560, 30, 147, 28);

        traintbl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        traintbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        traintbl.setShowHorizontalLines(false);
        traintbl.setShowVerticalLines(false);
        jScrollPane2.setViewportView(traintbl);

        jPanel4.add(jScrollPane2);
        jScrollPane2.setBounds(10, 102, 1225, 370);

        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel3);
        jLabel3.setBounds(590, 70, 85, 20);

        shtyppdfbtn2.setBackground(new java.awt.Color(105, 3, 3));
        shtyppdfbtn2.setFont(new java.awt.Font("Calibri Light", 1, 18)); // NOI18N
        shtyppdfbtn2.setForeground(new java.awt.Color(255, 255, 255));
        shtyppdfbtn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/pdficon.png"))); // NOI18N
        shtyppdfbtn2.setText("Shtyp në PDF");
        shtyppdfbtn2.setPreferredSize(new java.awt.Dimension(180, 45));
        shtyppdfbtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shtyppdfbtn2ActionPerformed(evt);
            }
        });
        jPanel4.add(shtyppdfbtn2);
        shtyppdfbtn2.setBounds(940, 20, 200, 50);

        kerkobtn2.setBackground(new java.awt.Color(105, 3, 3));
        kerkobtn2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        kerkobtn2.setForeground(new java.awt.Color(255, 255, 255));
        kerkobtn2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/searchicon.png"))); // NOI18N
        kerkobtn2.setText("Kërko");
        kerkobtn2.setPreferredSize(new java.awt.Dimension(110, 47));
        kerkobtn2.setRequestFocusEnabled(false);
        kerkobtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kerkobtn2ActionPerformed(evt);
            }
        });
        jPanel4.add(kerkobtn2);
        kerkobtn2.setBounds(740, 23, 110, 40);

        searchLbl2.setForeground(new java.awt.Color(255, 0, 0));
        jPanel4.add(searchLbl2);
        searchLbl2.setBounds(470, 10, 260, 0);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/kerko2.png"))); // NOI18N
        jPanel4.add(jLabel5);
        jLabel5.setBounds(0, 0, 1243, 490);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Kerko Trajnime", jPanel2);

        jPanel6.setLayout(null);

        statsbycombo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        statsbycombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Numri i pjesmarrësve", "Numri i trajnimeve", "Vlerësimet e trajnerëve" }));
        jPanel6.add(statsbycombo);
        statsbycombo.setBounds(390, 430, 350, 30);

        barchartbtn.setBackground(new java.awt.Color(105, 3, 3));
        barchartbtn.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        barchartbtn.setForeground(new java.awt.Color(255, 255, 255));
        barchartbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/bar-chart-md.png"))); // NOI18N
        barchartbtn.setText("OK");
        barchartbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barchartbtnActionPerformed(evt);
            }
        });
        jPanel6.add(barchartbtn);
        barchartbtn.setBounds(790, 420, 140, 50);

        tprocessstat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tprocessstat.setForeground(new java.awt.Color(231, 112, 12));
        jPanel6.add(tprocessstat);
        tprocessstat.setBounds(335, 83, 75, 20);

        participantstat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        participantstat.setForeground(new java.awt.Color(231, 112, 12));
        jPanel6.add(participantstat);
        participantstat.setBounds(335, 132, 75, 20);

        trainerstat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        trainerstat.setForeground(new java.awt.Color(231, 112, 12));
        jPanel6.add(trainerstat);
        trainerstat.setBounds(335, 182, 75, 20);

        partMstat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        partMstat.setForeground(new java.awt.Color(231, 112, 12));
        jPanel6.add(partMstat);
        partMstat.setBounds(335, 232, 75, 20);

        partFstat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        partFstat.setForeground(new java.awt.Color(231, 112, 12));
        jPanel6.add(partFstat);
        partFstat.setBounds(335, 282, 75, 20);

        topicsf.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        topicsf.setForeground(new java.awt.Color(231, 112, 12));
        jPanel6.add(topicsf);
        topicsf.setBounds(335, 332, 75, 20);

        publicinstif.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        publicinstif.setForeground(new java.awt.Color(231, 112, 12));
        jPanel6.add(publicinstif);
        publicinstif.setBounds(700, 82, 75, 20);

        internationalorgf.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        internationalorgf.setForeground(new java.awt.Color(231, 112, 12));
        jPanel6.add(internationalorgf);
        internationalorgf.setBounds(700, 132, 75, 20);

        nongoverf.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nongoverf.setForeground(new java.awt.Color(231, 112, 12));
        jPanel6.add(nongoverf);
        nongoverf.setBounds(700, 182, 75, 20);

        privatebus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        privatebus.setForeground(new java.awt.Color(231, 112, 12));
        jPanel6.add(privatebus);
        privatebus.setBounds(700, 232, 75, 20);

        individualf.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        individualf.setForeground(new java.awt.Color(231, 112, 12));
        jPanel6.add(individualf);
        individualf.setBounds(700, 282, 75, 20);

        companysf.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        companysf.setForeground(new java.awt.Color(0, 0, 102));
        jPanel6.add(companysf);
        companysf.setBounds(700, 332, 75, 20);

        tevf1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tevf1.setForeground(new java.awt.Color(231, 112, 12));
        jPanel6.add(tevf1);
        tevf1.setBounds(1130, 82, 75, 20);

        tevf2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tevf2.setForeground(new java.awt.Color(231, 112, 12));
        jPanel6.add(tevf2);
        tevf2.setBounds(1130, 132, 75, 20);

        tevf3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tevf3.setForeground(new java.awt.Color(231, 112, 12));
        jPanel6.add(tevf3);
        tevf3.setBounds(1130, 182, 75, 20);

        tevf4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tevf4.setForeground(new java.awt.Color(0, 0, 102));
        jPanel6.add(tevf4);
        tevf4.setBounds(1130, 232, 75, 20);

        trainerf.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        trainerf.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jPanel6.add(trainerf);
        trainerf.setBounds(1077, 282, 128, 20);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/statisticsdesign.png"))); // NOI18N
        jPanel6.add(background);
        background.setBounds(0, 0, 1250, 490);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Statistika", jPanel5);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 518));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public int getLetters(){
            char[] karakterat = searchtxtf.getText().toCharArray();
            int i = 0;
            for (char c: karakterat) {
            if (!(c >= '0' && c <= '9'))
            i++;
            }
            return i;
        }
    
    private void ParticipantTabelaLoad(){
    List<Participant>Temp =participantir.findAllActive();
    participanttm.add(Temp);
    tbl.setModel(participanttm);
    participanttm.fireTableDataChanged();
    }
    
    private void TrainingTabelaLoadAsc(){
    List<TrainingProcess>Temp =trainingir.findAllActive();
    trainingtm.add(Temp);
    traintbl.setModel(trainingtm);
    trainingtm.fireTableDataChanged();
    }
    
    private void OrderByEmriTabelaLoadAsc(){
    List<Participant>Temp =participantir.OrderByEmriAsc();
    participanttm.add(Temp);
    tbl.setModel(participanttm);
    participanttm.fireTableDataChanged();
    }
    
    private void OrderByEmriTabelaLoadDes(){
    List<Participant>Temp =participantir.OrderByEmriDes();
    participanttm.add(Temp);
    tbl.setModel(participanttm);
    participanttm.fireTableDataChanged();
    } 

    private void findByEmriTabelaLoadAsc(String emri){
    List<Participant>Temp =participantir.findByEmriAsc(emri);
    participanttm.add(Temp);
    tbl.setModel(participanttm);
    participanttm.fireTableDataChanged();
    }
    
    private void findByEmriTabelaLoadDes(String emri){
    List<Participant>Temp =participantir.findByEmriDes(emri);
    participanttm.add(Temp);
    tbl.setModel(participanttm);
    participanttm.fireTableDataChanged();
    }
    
    private void OrderByGjiniaTabelaLoadAsc(){
    List<Participant>Temp =participantir.OrderByGjiniaAsc();
    participanttm.add(Temp);
    tbl.setModel(participanttm);
    participanttm.fireTableDataChanged();
    }
    
    private void OrderByGjiniaTabelaLoadDes(){
    List<Participant>Temp =participantir.OrderByGjiniaDes();
    participanttm.add(Temp);
    tbl.setModel(participanttm);
    participanttm.fireTableDataChanged();
    } 

    private void findByGjiniaTabelaLoadAsc(String emri){
    List<Participant>Temp =participantir.findByGjiniaAsc(emri);
    participanttm.add(Temp);
    tbl.setModel(participanttm);
    participanttm.fireTableDataChanged();
    }
    
    private void findByGjiniaTabelaLoadDes(String emri){
    List<Participant>Temp =participantir.findByGjiniaDes(emri);
    participanttm.add(Temp);
    tbl.setModel(participanttm);
    participanttm.fireTableDataChanged();
    }
    
    private void OrderByPersonalIDTabelaLoadAsc(){
    List<Participant>Temp =participantir.OrderByPersonalIDAsc();
    participanttm.add(Temp);
    tbl.setModel(participanttm);
    participanttm.fireTableDataChanged();
    }
    
    private void OrderByPersonalIDTabelaLoadDes(){
    List<Participant>Temp =participantir.OrderByPersonalIDDes();
    participanttm.add(Temp);
    tbl.setModel(participanttm);
    participanttm.fireTableDataChanged();
    } 

    private void findByPersonalIDTabelaLoadAsc(int id){
    List<Participant>Temp =participantir.findByPersonalIDAsc(id);
    participanttm.add(Temp);
    tbl.setModel(participanttm);
    participanttm.fireTableDataChanged();
    }
    
    private void findByPersonalIDTabelaLoadDes(int id){
    List<Participant>Temp =participantir.findByPersonalIDDes(id);
    participanttm.add(Temp);
    tbl.setModel(participanttm);
    participanttm.fireTableDataChanged();
    }
    
     private void OrderByQytetiTabelaLoadAsc(){
    List<Participant>Temp =participantir.OrderByQytetiAsc();
    participanttm.add(Temp);
    tbl.setModel(participanttm);
    participanttm.fireTableDataChanged();
    }
    
    private void OrderByQytetiTabelaLoadDes(){
    List<Participant>Temp =participantir.OrderByQytetiDes();
    participanttm.add(Temp);
    tbl.setModel(participanttm);
    participanttm.fireTableDataChanged();
    } 

    private void findByQytetiTabelaLoadAsc(String qyteti){
    List<Participant>Temp =participantir.findByQytetiAsc(qyteti);
    participanttm.add(Temp);
    tbl.setModel(participanttm);
    participanttm.fireTableDataChanged();
    }
    
    private void findByQytetiTabelaLoadDes(String qyteti){
    List<Participant>Temp =participantir.findByQytetiDes(qyteti);
    participanttm.add(Temp);
    tbl.setModel(participanttm);
    participanttm.fireTableDataChanged();
    }
    
    private void findByTpIdTabelaLoad(String tpId){
    List<Participant>Temp =participantir.findByTpIdActive(tpId);
    participanttm.add(Temp);
    tbl.setModel(participanttm);
    participanttm.fireTableDataChanged();
    }
    
    /* Trajnimi Trajnimi Trajnimi Trajnimi Trajnimi Trajnimi Trajnimi
    Trajnimi Trajnimi Trajnimi Trajnimi Trajnimi Trajnimi Trajnimi
    Trajnimi Trajnimi Trajnimi Trajnimi Trajnimi Trajnimi Trajnimi
    Trajnimi Trajnimi Trajnimi Trajnimi Trajnimi Trajnimi Trajnimi
    Trajnimi Trajnimi Trajnimi Trajnimi Trajnimi Trajnimi Trajnimi
    Trajnimi Trajnimi Trajnimi Trajnimi Trajnimi Trajnimi Trajnimi
    
    */
    
    private void OrderByVendiTabelaLoadAsc(){
    List<TrainingProcess>Temp =trainingir.OrderByVendiAsc();
    trainingtm.add(Temp);
    traintbl.setModel(trainingtm);
    trainingtm.fireTableDataChanged();
    }
    
    private void OrderByVendiTabelaLoadDes(){
    List<TrainingProcess>Temp =trainingir.OrderByVendiDes();
    trainingtm.add(Temp);
    traintbl.setModel(trainingtm);
    trainingtm.fireTableDataChanged();
    } 

    private void findByVendiTabelaLoadAsc(String emri){
    List<TrainingProcess>Temp =trainingir.findByVendiAsc(emri);
    trainingtm.add(Temp);
    traintbl.setModel(trainingtm);
    trainingtm.fireTableDataChanged();
    }
    
    private void findByVendiTabelaLoadDes(String emri){
    List<TrainingProcess>Temp =trainingir.findByVendiDes(emri);
    trainingtm.add(Temp);
    traintbl.setModel(trainingtm);
    trainingtm.fireTableDataChanged();
    }
    
    private void OrderByStartDateTabelaLoadAsc(){
    List<TrainingProcess>Temp =trainingir.OrderByStartDateAsc();
    trainingtm.add(Temp);
    traintbl.setModel(trainingtm);
    trainingtm.fireTableDataChanged();
    }
    
    private void OrderByStartDateTabelaLoadDes(){
    List<TrainingProcess>Temp =trainingir.OrderByStartDateDes();
    trainingtm.add(Temp);
    traintbl.setModel(trainingtm);
    trainingtm.fireTableDataChanged();
    } 

    private void findByStartDateTabelaLoadAsc(Date sd){
    List<TrainingProcess>Temp =trainingir.findByStartDateAsc(sd);
    trainingtm.add(Temp);
    traintbl.setModel(trainingtm);
    trainingtm.fireTableDataChanged();
    }
    
    private void findByStartDateTabelaLoadDes(Date sd){
    List<TrainingProcess>Temp =trainingir.findByStartDateDes(sd);
    trainingtm.add(Temp);
    traintbl.setModel(trainingtm);
    trainingtm.fireTableDataChanged();
    }
    
     private void OrderByEndDateTabelaLoadAsc(){
    List<TrainingProcess>Temp =trainingir.OrderByEndDateAsc();
    trainingtm.add(Temp);
    traintbl.setModel(trainingtm);
    trainingtm.fireTableDataChanged();
    }
    
    private void OrderByEndDateTabelaLoadDes(){
    List<TrainingProcess>Temp =trainingir.OrderByEndDateDes();
    trainingtm.add(Temp);
    traintbl.setModel(trainingtm);
    trainingtm.fireTableDataChanged();
    } 

    private void findByEndDateTabelaLoadAsc(Date sd){
    List<TrainingProcess>Temp =trainingir.findByEndDateAsc(sd);
    trainingtm.add(Temp);
    traintbl.setModel(trainingtm);
    trainingtm.fireTableDataChanged();
    }
    
    private void findByEndDateTabelaLoadDes(Date sd){
    List<TrainingProcess>Temp =trainingir.findByEndDateDes(sd);
    trainingtm.add(Temp);
    traintbl.setModel(trainingtm);
    trainingtm.fireTableDataChanged();
    }
    
     private void OrderByTrainingProcessIDTabelaLoadAsc(){
    List<TrainingProcess>Temp =trainingir.OrderByTrainingIDAsc();
    trainingtm.add(Temp);
    traintbl.setModel(trainingtm);
    trainingtm.fireTableDataChanged();
    }
    
    private void OrderByTrainingProcessIDTabelaLoadDes(){
    List<TrainingProcess>Temp =trainingir.OrderByTrainingIDDes();
    trainingtm.add(Temp);
    traintbl.setModel(trainingtm);
    trainingtm.fireTableDataChanged();
    } 

    private void findByTrainingProcessIDTabelaLoadAsc(String id){
    List<TrainingProcess>Temp =trainingir.findByTrainingIDAsc(id);
    trainingtm.add(Temp);
    traintbl.setModel(trainingtm);
    trainingtm.fireTableDataChanged();
    }
    
    private void findByTrainingProcessIDTabelaLoadDes(String id){
    List<TrainingProcess>Temp =trainingir.findByTrainingIDDes(id);
    trainingtm.add(Temp);
    traintbl.setModel(trainingtm);
    trainingtm.fireTableDataChanged();
    }
    
    
    
     private void OrderByTitleTabelaLoadAsc(){
    List<TrainingProcess>Temp =trainingir.OrderByTitleAsc();
    trainingtm.add(Temp);
    traintbl.setModel(trainingtm);
    trainingtm.fireTableDataChanged();
    }
    
    private void OrderByTitleTabelaLoadDes(){
    List<TrainingProcess>Temp =trainingir.OrderByTitleDes();
    trainingtm.add(Temp);
    traintbl.setModel(trainingtm);
    trainingtm.fireTableDataChanged();
    } 

    private void findByTitleTabelaLoadAsc(String emri){
    List<TrainingProcess>Temp =trainingir.findByTitleAsc(emri);
    trainingtm.add(Temp);
    traintbl.setModel(trainingtm);
    trainingtm.fireTableDataChanged();
    }
    
    private void findByTitleTabelaLoadDes(String emri){
    List<TrainingProcess>Temp =trainingir.findByTitleDes(emri);
    trainingtm.add(Temp);
    traintbl.setModel(trainingtm);
    trainingtm.fireTableDataChanged();
    }
    
      private void OrderByProjectTabelaLoadAsc(){
    List<TrainingProcess>Temp =trainingir.OrderByProjectAsc();
    trainingtm.add(Temp);
    traintbl.setModel(trainingtm);
    trainingtm.fireTableDataChanged();
    }
    
    private void OrderByProjectTabelaLoadDes(){
    List<TrainingProcess>Temp =trainingir.OrderByProjectDes();
    trainingtm.add(Temp);
    traintbl.setModel(trainingtm);
    trainingtm.fireTableDataChanged();
    } 

    private void findByProjectTabelaLoadAsc(String emri){
    List<TrainingProcess>Temp =trainingir.findByProjectAsc(emri);
    trainingtm.add(Temp);
    traintbl.setModel(trainingtm);
    trainingtm.fireTableDataChanged();
    }
    
    private void findByProjectTabelaLoadDes(String emri){
    List<TrainingProcess>Temp =trainingir.findByProjectDes(emri);
    trainingtm.add(Temp);
    traintbl.setModel(trainingtm);
    trainingtm.fireTableDataChanged();
    }
    
      
    
    public void kerkoTrajnim(){
        DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
       Date start1;
       Date start2;
        if(searchcombo2.getSelectedItem().equals("Vendit")){
        if(orderbycombo2.getSelectedItem().equals("Rritëse") && searchtxtf2.getText().equals(""))
            OrderByVendiTabelaLoadAsc();
        if(orderbycombo2.getSelectedItem().equals("Zbritëse") && searchtxtf2.getText().equals(""))
            OrderByVendiTabelaLoadDes();
        else if (orderbycombo2.getSelectedItem().equals("Rritëse") && !searchtxtf2.getText().equals(""))
            findByVendiTabelaLoadAsc(searchtxtf2.getText());
        else if (orderbycombo2.getSelectedItem().equals("Zbritëse") && !searchtxtf2.getText().equals(""))
            findByVendiTabelaLoadDes(searchtxtf2.getText());
        }
        
        
        else if(searchcombo2.getSelectedItem().equals("Koha e fillimit")){
             char[] karakterat = searchtxtf2.getText().toCharArray();
            int i = 0;
            int j = 0;
            for (char c: karakterat) {
            if (c >= '0' && c <= '9')
                i++;
            if (c=='-')
                j++;
            }
            
        if(orderbycombo2.getSelectedItem().equals("Rritëse") && searchtxtf2.getText().equals(""))
            OrderByStartDateTabelaLoadAsc();
        if(orderbycombo2.getSelectedItem().equals("Zbritëse") && searchtxtf2.getText().equals(""))
            OrderByStartDateTabelaLoadDes();
        else if (orderbycombo2.getSelectedItem().equals("Rritëse") && !searchtxtf2.getText().equals("")){
            if ((i==6||i==7||i==8) && j==2){
                try {
                    start1=(Date)dateFormat.parse(searchtxtf2.getText());
                    findByStartDateTabelaLoadAsc(start1);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Shkruaje daten ne format dita-muaji-viti");
                }
            }else
                JOptionPane.showMessageDialog(null, "Shkruaje daten ne format dita-muaji-viti");
            }
        else if (orderbycombo2.getSelectedItem().equals("Zbritëse") && !searchtxtf2.getText().equals("")){
            if((i==6||i==7||i==8) && j==2){ 
            try {
                start1=(Date)dateFormat.parse(searchtxtf2.getText());
                 findByStartDateTabelaLoadDes(start1);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Shkruaje daten ne format dita-muaji-viti");
        }
        }else
             JOptionPane.showMessageDialog(null, "Shkruaje daten ne format dita-muaji-viti");
        }
        }    
       
         else if(searchcombo2.getSelectedItem().equals("Koha e perfundimit")){
             
             char[] karakterat = searchtxtf2.getText().toCharArray();
            int i = 0;
            int j = 0;
            for (char c: karakterat) {
            if (c >= '0' && c <= '9')
                i++;
            if (c=='-')
                j++;
            }
        if(orderbycombo2.getSelectedItem().equals("Rritëse") && searchtxtf2.getText().equals(""))
            OrderByEndDateTabelaLoadAsc();
        if(orderbycombo2.getSelectedItem().equals("Zbritëse") && searchtxtf2.getText().equals(""))
            OrderByEndDateTabelaLoadDes();
        else if (orderbycombo2.getSelectedItem().equals("Rritëse") && !searchtxtf2.getText().equals("")){
            if ((i==6||i==7||i==8) && j==2){
                try {
                    start1=(Date)dateFormat.parse(searchtxtf2.getText());
                    findByEndDateTabelaLoadAsc(start1);
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Shkruaje daten ne format dita-muaji-viti");
                }
            }else
                JOptionPane.showMessageDialog(null, "Shkruaje daten ne format dita-muaji-viti");
            }
            
        else if (orderbycombo2.getSelectedItem().equals("Zbritëse") && !searchtxtf2.getText().equals("")){
            if((i==6||i==7||i==8) && j==2){ 
            try {
                start1=(Date)dateFormat.parse(searchtxtf2.getText());
                 findByEndDateTabelaLoadDes(start1);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Shkruaje daten ne format dita-muaji-viti");
        }
        }else
             JOptionPane.showMessageDialog(null, "Shkruaje daten ne format dita-muaji-viti");
        }
        }    
        
        if(searchcombo2.getSelectedItem().equals("ID e Trajnimit")){
        if(orderbycombo2.getSelectedItem().equals("Rritëse") && searchtxtf2.getText().equals(""))
            OrderByTrainingProcessIDTabelaLoadAsc();
        if(orderbycombo2.getSelectedItem().equals("Zbritëse") && searchtxtf2.getText().equals(""))
            OrderByTrainingProcessIDTabelaLoadDes();
        else if (orderbycombo2.getSelectedItem().equals("Rritëse") && !searchtxtf2.getText().equals(""))
            findByTrainingProcessIDTabelaLoadAsc(searchtxtf2.getText());
        else if (orderbycombo2.getSelectedItem().equals("Zbritëse") && !searchtxtf2.getText().equals(""))
            findByTrainingProcessIDTabelaLoadDes(searchtxtf2.getText());
        }
        
        
        
        if(searchcombo2.getSelectedItem().equals("Titulli i trajnimit")){
        if(orderbycombo2.getSelectedItem().equals("Rritëse") && searchtxtf2.getText().equals(""))
            OrderByTitleTabelaLoadAsc();
        if(orderbycombo2.getSelectedItem().equals("Zbritëse") && searchtxtf2.getText().equals(""))
            OrderByTitleTabelaLoadDes();
        else if (orderbycombo2.getSelectedItem().equals("Rritëse") && !searchtxtf2.getText().equals(""))
            findByTitleTabelaLoadAsc(searchtxtf2.getText());
        else if (orderbycombo2.getSelectedItem().equals("Zbritëse") && !searchtxtf2.getText().equals(""))
            findByTitleTabelaLoadDes(searchtxtf2.getText());
        }
        
        if(searchcombo2.getSelectedItem().equals("Projektit")){
        if(orderbycombo2.getSelectedItem().equals("Rritëse") && searchtxtf2.getText().equals(""))
            OrderByProjectTabelaLoadAsc();
        if(orderbycombo2.getSelectedItem().equals("Zbritëse") && searchtxtf2.getText().equals(""))
            OrderByProjectTabelaLoadDes();
        else if (orderbycombo2.getSelectedItem().equals("Rritëse") && !searchtxtf2.getText().equals(""))
            findByProjectTabelaLoadAsc(searchtxtf2.getText());
        else if (orderbycombo2.getSelectedItem().equals("Zbritëse") && !searchtxtf2.getText().equals(""))
            findByProjectTabelaLoadDes(searchtxtf2.getText());
        }
        
    }
    
    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
       DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
       Date start1;
       Date start2;
        if(searchcombo2.getSelectedItem().equals("Vendit")){
        if(orderbycombo2.getSelectedItem().equals("Rritëse") && searchtxtf2.getText().equals(""))
            OrderByVendiTabelaLoadAsc();
        if(orderbycombo2.getSelectedItem().equals("Zbritëse") && searchtxtf2.getText().equals(""))
            OrderByVendiTabelaLoadDes();
        else if (orderbycombo2.getSelectedItem().equals("Rritëse") && !searchtxtf2.getText().equals(""))
            findByVendiTabelaLoadAsc(searchtxtf2.getText());
        else if (orderbycombo2.getSelectedItem().equals("Zbritëse") && !searchtxtf2.getText().equals(""))
            findByVendiTabelaLoadDes(searchtxtf2.getText());
        }
        
        
        else if(searchcombo2.getSelectedItem().equals("Koha e fillimit")){
             char[] karakterat = searchtxtf2.getText().toCharArray();
            int i = 0;
            int j = 0;
            for (char c: karakterat) {
            if (!(c >= '0' && c <= '9'))
                i++;
            else if (c=='-')
                j++;
            }
            
        if(orderbycombo2.getSelectedItem().equals("Rritëse") && searchtxtf2.getText().equals(""))
            OrderByStartDateTabelaLoadAsc();
        if(orderbycombo2.getSelectedItem().equals("Zbritëse") && searchtxtf2.getText().equals(""))
            OrderByStartDateTabelaLoadDes();
        else if (orderbycombo2.getSelectedItem().equals("Rritëse") && !searchtxtf2.getText().equals("")){
            if (i==2 && j==2){
            try {
                start1=(Date)dateFormat.parse(searchtxtf2.getText());
                 findByStartDateTabelaLoadAsc(start1);
        } catch (ParseException ex) {
            searchLbl.setText("Shkruaje daten ne format dita-muaji-viti");
        }
        }else
                searchLbl.setText("Shkruaje daten ne format dita-muaji-viti");
        }
        else if (orderbycombo2.getSelectedItem().equals("Zbritëse") && !searchtxtf2.getText().equals("")){
            if(i==2 && j==2){ 
            try {
                start1=(Date)dateFormat.parse(searchtxtf2.getText());
                 findByStartDateTabelaLoadDes(start1);
        } catch (ParseException ex) {
            searchLbl.setText("Shkruaje daten ne format dita-muaji-viti");
        }
        }else
             searchLbl.setText("Shkruaje daten ne format dita-muaji-viti");   
        }
        }    
       
         else if(searchcombo2.getSelectedItem().equals("Koha e perfundimit")){
        if(orderbycombo2.getSelectedItem().equals("Rritëse") && searchtxtf2.getText().equals(""))
            OrderByEndDateTabelaLoadAsc();
        if(orderbycombo2.getSelectedItem().equals("Zbritëse") && searchtxtf2.getText().equals(""))
            OrderByEndDateTabelaLoadDes();
        else if (orderbycombo2.getSelectedItem().equals("Rritëse") && !searchtxtf2.getText().equals(""))
            try {
                start2=(Date)dateFormat.parse(searchtxtf2.getText());
                 findByEndDateTabelaLoadAsc(start2);
        } catch (ParseException ex) {
            Logger.getLogger(Kerko.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        else if (orderbycombo2.getSelectedItem().equals("Zbritëse") && !searchtxtf2.getText().equals(""))
             try {
                start2=(Date)dateFormat.parse(searchtxtf2.getText());
                 findByEndDateTabelaLoadDes(start2);
        } catch (ParseException ex) {
            Logger.getLogger(Kerko.class.getName()).log(Level.SEVERE, null, ex);
        }
        }    
        
        if(searchcombo2.getSelectedItem().equals("ID e Trajnimit")){
        if(orderbycombo2.getSelectedItem().equals("Rritëse") && searchtxtf2.getText().equals(""))
            OrderByTrainingProcessIDTabelaLoadAsc();
        if(orderbycombo2.getSelectedItem().equals("Zbritëse") && searchtxtf2.getText().equals(""))
            OrderByTrainingProcessIDTabelaLoadDes();
        else if (orderbycombo2.getSelectedItem().equals("Rritëse") && !searchtxtf2.getText().equals(""))
            findByTrainingProcessIDTabelaLoadAsc(searchtxtf2.getText());
        else if (orderbycombo2.getSelectedItem().equals("Zbritëse") && !searchtxtf2.getText().equals(""))
            findByTrainingProcessIDTabelaLoadDes(searchtxtf2.getText());
        }
        
        
        
        if(searchcombo2.getSelectedItem().equals("Titulli i trajnimit")){
        if(orderbycombo2.getSelectedItem().equals("Rritëse") && searchtxtf2.getText().equals(""))
            OrderByTitleTabelaLoadAsc();
        if(orderbycombo2.getSelectedItem().equals("Zbritëse") && searchtxtf2.getText().equals(""))
            OrderByTitleTabelaLoadDes();
        else if (orderbycombo2.getSelectedItem().equals("Rritëse") && !searchtxtf2.getText().equals(""))
            findByTitleTabelaLoadAsc(searchtxtf2.getText());
        else if (orderbycombo2.getSelectedItem().equals("Zbritëse") && !searchtxtf2.getText().equals(""))
            findByTitleTabelaLoadDes(searchtxtf2.getText());
        }
        
        if(searchcombo2.getSelectedItem().equals("Projektit")){
        if(orderbycombo2.getSelectedItem().equals("Rritëse") && searchtxtf2.getText().equals(""))
            OrderByProjectTabelaLoadAsc();
        if(orderbycombo2.getSelectedItem().equals("Zbritëse") && searchtxtf2.getText().equals(""))
            OrderByProjectTabelaLoadDes();
        else if (orderbycombo2.getSelectedItem().equals("Rritëse") && !searchtxtf2.getText().equals(""))
            findByProjectTabelaLoadAsc(searchtxtf2.getText());
        else if (orderbycombo2.getSelectedItem().equals("Zbritëse") && !searchtxtf2.getText().equals(""))
            findByProjectTabelaLoadDes(searchtxtf2.getText());
        }
        
        
    }//GEN-LAST:event_jLabel3MouseClicked

    
    
    
    
    
    
    
    private void shtyppdfbtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shtyppdfbtn1ActionPerformed
        //shtypRaportin();
        printoPdf(tbl);
    }//GEN-LAST:event_shtyppdfbtn1ActionPerformed

    private void barchartbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barchartbtnActionPerformed
        barChart();
    }//GEN-LAST:event_barchartbtnActionPerformed

    private void shtyppdfbtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shtyppdfbtn2ActionPerformed
        printoPdf(traintbl);
    }//GEN-LAST:event_shtyppdfbtn2ActionPerformed

    public void kerko(){
        if(searchcombo.getSelectedItem().equals("Emrit/Mbiemrit")){
        if(orderbycombo.getSelectedItem().equals("Rritëse") && searchtxtf.getText().equals(""))
            OrderByEmriTabelaLoadAsc();
        if(orderbycombo.getSelectedItem().equals("Zbritëse") && searchtxtf.getText().equals(""))
            OrderByEmriTabelaLoadDes();
        else if (orderbycombo.getSelectedItem().equals("Rritëse") && !searchtxtf.getText().equals(""))
            findByEmriTabelaLoadAsc(searchtxtf.getText());
        else if (orderbycombo.getSelectedItem().equals("Zbritëse") && !searchtxtf.getText().equals(""))
            findByEmriTabelaLoadDes(searchtxtf.getText());
        }
       
       else if(searchcombo.getSelectedItem().equals("Gjinis")){
        
        if (orderbycombo.getSelectedItem().equals("Rritëse") && searchtxtf.getText().equals(""))
                OrderByGjiniaTabelaLoadAsc();    
        else if (orderbycombo.getSelectedItem().equals("Zbritëse") && searchtxtf.getText().equals(""))
                OrderByGjiniaTabelaLoadDes();
        else if (orderbycombo.getSelectedItem().equals("Rritëse") && !searchtxtf.getText().equals(""))
                 findByGjiniaTabelaLoadAsc(searchtxtf.getText());
        else if (orderbycombo.getSelectedItem().equals("Zbritëse") && !searchtxtf.getText().equals(""))
                findByGjiniaTabelaLoadDes(searchtxtf.getText());
        }
       
       else if(searchcombo.getSelectedItem().equals("Numrit Personal")){
        
        if (orderbycombo.getSelectedItem().equals("Rritëse") && searchtxtf.getText().equals(""))
                OrderByPersonalIDTabelaLoadAsc();    
        else if (orderbycombo.getSelectedItem().equals("Zbritëse") && searchtxtf.getText().equals(""))
                OrderByPersonalIDTabelaLoadDes();
        else if (orderbycombo.getSelectedItem().equals("Rritëse") && !searchtxtf.getText().equals(""))
            try{     
            findByPersonalIDTabelaLoadAsc(Integer.parseInt(searchtxtf.getText()));
            }catch(NumberFormatException nfe){
                nfe.printStackTrace();
                JOptionPane.showMessageDialog(null, "Numri Personal duhet te kerkohet vetem ne baze te numrave (jo shkronjave).");
            }
        else if (orderbycombo.getSelectedItem().equals("Zbritëse") && !searchtxtf.getText().equals(""))
             try{   
                findByPersonalIDTabelaLoadDes(Integer.parseInt(searchtxtf.getText()));
                }catch(NumberFormatException nfe){
                nfe.printStackTrace();
                JOptionPane.showMessageDialog(null, "Numri Personal duhet te kerkohet vetem ne baze te numrave (jo shkronjave).");
            }
        }
       
       if(searchcombo.getSelectedItem().equals("Qytetit")){
        if(orderbycombo.getSelectedItem().equals("Rritëse") && searchtxtf.getText().equals(""))
            OrderByQytetiTabelaLoadAsc();
        if(orderbycombo.getSelectedItem().equals("Zbritëse") && searchtxtf.getText().equals(""))
            OrderByQytetiTabelaLoadDes();
        else if (orderbycombo.getSelectedItem().equals("Rritëse") && !searchtxtf.getText().equals(""))
            findByQytetiTabelaLoadAsc(searchtxtf.getText());
        else if (orderbycombo.getSelectedItem().equals("Zbritëse") && !searchtxtf.getText().equals(""))
            findByQytetiTabelaLoadDes(searchtxtf.getText());
        }
       
        if(searchcombo.getSelectedItem().equals("TrainingID")){
            findByTpIdTabelaLoad(searchtxtf.getText().trim());
        }
    }
    
    private void kerkobtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kerkobtn1ActionPerformed
        kerko();
    }//GEN-LAST:event_kerkobtn1ActionPerformed

    private void kerkobtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kerkobtn2ActionPerformed
        kerkoTrajnim();
    }//GEN-LAST:event_kerkobtn2ActionPerformed
    
    public void printoPdf(JTable tbl){
        try{
            
                Date today=new Date();
                SimpleDateFormat sdf =new SimpleDateFormat("dd-MM-yyyy");

                String printday=sdf.format(today);

                MessageFormat[] footer = new MessageFormat[1];

                footer[0]=new MessageFormat(printday);

                PrinterJob job = PrinterJob.getPrinterJob();

            if((searchcombo.getSelectedItem().toString().trim().equals("TrainingID"))&& (tbl.getModel()==participanttm||tbl.getModel()==participantcustomtm)){

                String trainingId=searchtxtf.getText().trim();
                TrainingProcess tp=trainingir.findById(trainingId);
                Training t=trainir.findByTpId(trainingId);
                List<Trainer> trainersInTp=trainerir.findByTP(trainingId);

                String trainersList="";
                
                SimpleDateFormat sdf2 =new SimpleDateFormat("dd-MM-yyyy");
                
                String start=sdf2.format(tp.getStartDate());
                
                String end=sdf2.format(tp.getEndDate());
                
                for(int i=0;i<trainersInTp.size();i++){
                    trainersList=trainersList+trainersInTp.get(i).getName()+" "+trainersInTp.get(i).getSurname()+" ; ";
                }

                MessageFormat[] headeri = new MessageFormat[9];
                headeri[0] = new MessageFormat("");
                headeri[1] = new MessageFormat("Kodi i Trajnimit :"+trainingId);
                headeri[2] = new MessageFormat("Titulli i Trajnimit :"+t.getTitleOfTrainingAlbanian());
                headeri[3] = new MessageFormat("Data e mbajtjes së trajnimit : nga "+start+"  deri më "+end);
                headeri[4] = new MessageFormat("Trainerët :"+trainersList);
                headeri[5] = new MessageFormat("Numri i pjesmarrësve :"+participantir.findByTpId(trainingId).size());
                headeri[6] = new MessageFormat("Numri i mashkujve në këtë trajnim :"+participantir.findByGjiniaTpIdActive(trainingId,"M").size());
                headeri[7] = new MessageFormat("Numri i femrave në këtë trajnim :"+participantir.findByGjiniaTpIdActive(trainingId,"F").size());
                headeri[8] = new MessageFormat("");

                //MessageFormat footer=new MessageFormat("");

                job.setPrintable(new MyTablePrintable(tbl, JTable.PrintMode.FIT_WIDTH, headeri, footer ));
               // tbl.print(JTable.PrintMode.FIT_WIDTH,header,footer);

                job.print();
                
                
            }  
            
            else if(!searchcombo.getSelectedItem().toString().trim().equals("TrainingID")){
             MessageFormat[] header = new MessageFormat[2];


             header[0] = new MessageFormat(" ");
                header[1] = new MessageFormat("Report Print");
                
                
                job.setPrintable(new MyTablePrintable(tbl, JTable.PrintMode.FIT_WIDTH, header, footer ));
            
               // tbl.print(JTable.PrintMode.FIT_WIDTH,header,footer);

                job.print();
                    }            
        }
        catch (Exception e)
        {   e.printStackTrace();
            System.err.format("Cannot Print ",e.getMessage());
        }
    }
    
    
    
    public void mbushStatistikat(){
        
        int c1=0;
        int c2=0;
        int c3=0;
        int c4=0;
        int c5=0;
        
        int countTrainingProcess=trainingir.findAllActive().size();
        int countTrainer=trainerir.findAll().size();
        int countParticipant=participantir.findAllActive().size();
        int countParticipantGjiniaM=participantir.findByGjiniaAsc("M").size();
        int countParticipantGjiniaF=participantir.findByGjiniaAsc("F").size();
        
        
        int countpublicinsti=companyir.findByCompanyType("Public Institutions").size();
        int countinternationalorg=companyir.findByCompanyType("International Organizations").size();
        int countnongover=companyir.findByCompanyType("Non-Govermental").size();
        int countprivatebus=companyir.findByCompanyType("Private Businesses").size();
        int countindividual=companyir.findByCompanyType("Individual").size();
        int countcompany=companyir.findAll().size()-1;
        int counttopics=topicsir.findAll().size();
        
        if(countpublicinsti !=0){
            c1=countpublicinsti;
        }
        if(countinternationalorg !=0){
            c2=countinternationalorg;
        }
        if(countnongover !=0){
            c3=countnongover;
        }
        if(countprivatebus !=0){
            c4=countprivatebus;
        }
        if(countindividual !=0){
            c5=countindividual;
        }
        
        
        /*List <Integer> trainerpreperationlist=trainerevir.findByPreperation2();
        List <Integer> trainerdiscussionlist=trainerevir.findByDiscussion2();
        List <Integer> trainerpresentationlist=trainerevir.findByPresentation2();*/
        List <TTrainerEvaluation> trainerevs=trainerevir.findAllActive();
        
        int tev1=0;
        int tev2=0;
        int tev3=0;
        
        double tevavg1;
        double tevavg2;
        double tevavg3;
        double tevavg;
        if(trainerevs.size()!=0){
        for(int i=0;i<trainerevs.size();i++){
            tev1=tev1+trainerevs.get(i).getTrainerPreperation();
            tev2=tev2+trainerevs.get(i).getTrainerDiscussion();
            tev3=tev3+trainerevs.get(i).getPresentation();
          
         }
        
        String trainername="";
        double maxtrainer=0;
        for(int i=0;i<trainerevs.size();i++){
            int trainerprep=trainerevs.get(i).getTrainerPreperation();
            int trainerdisc=trainerevs.get(i).getTrainerDiscussion();
            int trainerpres=trainerevs.get(i).getPresentation();
            
            if(trainerprep+trainerdisc+trainerpres>maxtrainer){
                maxtrainer=trainerprep+trainerdisc+trainerpres;
                trainername=trainerevs.get(i).getTrainerID().getName()+" "+trainerevs.get(i).getTrainerID().getSurname();
            }
            
          
         }
        
        
            tevavg1=(double)tev1/trainerevs.size();
            BigDecimal bd = new BigDecimal(tevavg1).setScale(2, RoundingMode.HALF_EVEN);
            tevavg1 = bd.doubleValue();
            tevavg2=(double)tev2/trainerevs.size();
            BigDecimal bd2 = new BigDecimal(tevavg2).setScale(2, RoundingMode.HALF_EVEN);
            tevavg2 = bd2.doubleValue();
            tevavg3=(double)tev3/trainerevs.size();
            BigDecimal bd3 = new BigDecimal(tevavg3).setScale(2, RoundingMode.HALF_EVEN);
            tevavg3 = bd3.doubleValue();
            tevavg=(tevavg1+tevavg2+tevavg3)/3;
            BigDecimal bd4 = new BigDecimal(tevavg).setScale(2, RoundingMode.HALF_EVEN);
            tevavg = bd4.doubleValue();
            
            tevf1.setText(tevavg1+"");
            tevf2.setText(tevavg2+"");
            tevf3.setText(tevavg3+"");
            tevf4.setText(tevavg+"");
            trainerf.setText(trainername);
        }
        tprocessstat.setText(countTrainingProcess+"");
        trainerstat.setText(countTrainer+"");
        participantstat.setText(countParticipant+"");
        partMstat.setText(countParticipantGjiniaM+"");
        partFstat.setText(countParticipantGjiniaF+"");
        topicsf.setText(counttopics+"");
        
        publicinstif.setText(c1+"");
        internationalorgf.setText(c2+"");
        nongoverf.setText(c3+"");
        privatebus.setText(c4+"");
        individualf.setText(c5+"");
        companysf.setText(countcompany+"");
        
        
        
        
    }
    
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    
    private void shtypRaportin(){
        
       Document document = new Document(PageSize.A4, 0, 0, 0, 0);

        try {
            Font bfBold12 = new Font(FontFamily.TIMES_ROMAN, 12, Font.BOLD, new BaseColor(0, 0, 0)); 
            Font bf12 = new Font(FontFamily.TIMES_ROMAN, 12); 
            
            
                    
            
            
            
            PdfWriter.getInstance(document,
                new FileOutputStream("ReportTest.pdf"));

            document.open();

            PdfPTable table = new PdfPTable(3); 
            
            List <Participant> participants=participantir.findAllActive();
            int row=tbl.getSelectedRow();
            Participant p=this.participanttm.getParticipant(row);
            for(int i=0;i<participants.size();i++){
                String a,b,c;
                a=p.getParticipantID()+"";
                b=p.getName();
                c=p.getSurname();
                insertCell(table,a, Element.ALIGN_LEFT, 1, bf12);
                insertCell(table,b, Element.ALIGN_LEFT, 1, bf12);
                insertCell(table,c, Element.ALIGN_LEFT, 1, bf12);
            }
            
            String [] arr=new String[24];
            int index=0;
            for(int i=0;i<arr.length;i++){
                arr[i]=index++ +"";
            }
            
            table.setWidthPercentage(90);
            
           
            
           /* for(int i=0;i<arr.length;i++){
                
               insertCell(table,arr[i], Element.ALIGN_LEFT, 1, bf12);
            }
            
                PdfPCell cell = new PdfPCell(new Phrase("Products"));
            cell.setColspan(3);
            PdfPCell cell1 = new PdfPCell(new Paragraph("Po "));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Bon"));
            PdfPCell cell3 = new PdfPCell(new Paragraph("Tybe"));
            PdfPCell cell4 = new PdfPCell(new Paragraph("Rreshti"));
            PdfPCell cell5 = new PdfPCell(new Paragraph(" i "));
            PdfPCell cell6 = new PdfPCell(new Paragraph("Dyt"));
            
            cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
            
            table.addCell(cell);
            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
            table.addCell(cell6);*/
            
            insertCell(table, "Mir po bon edhe kjo", Element.ALIGN_LEFT, 4, bf12);
            
            
            document.add(table);

            document.close();
            JOptionPane.showMessageDialog(null,"U ruajt me sukses");
        } catch(Exception e){

        } 
    }
    
    private void barChart(){
         
        if(statsbycombo.getSelectedItem().equals("Numri i trajnimeve")){
        DefaultCategoryDataset dataset=new DefaultCategoryDataset();
           
         int countTrainingProcess2013=trainingir.findByYear(2013).size();
         int countTrainingProcess2014=trainingir.findByYear(2014).size();
         int countTrainingProcess2015=trainingir.findByYear(2015).size();
         int countTrainingProcess2016=trainingir.findByYear(2016).size();
         int countTrainingProcess2017=trainingir.findByYear(2017).size();
         
         
         
        dataset.setValue(countTrainingProcess2013, "Trajnime","2013");
        dataset.setValue(countTrainingProcess2014, "Trajnime","2014");
        dataset.setValue(countTrainingProcess2015, "Trajnime","2015");
        dataset.setValue(countTrainingProcess2016, "Trajnime","2016");
        dataset.setValue(countTrainingProcess2017, "Trajnime","2017");
        JFreeChart chart=ChartFactory.createBarChart("Numri i trajnimeve ndër vite", "Viti", "Numri i trajnimeve", dataset, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot p=chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.black);
        final BarRenderer renderer = (BarRenderer) p.getRenderer();
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);
        final NumberAxis rangeAxis = (NumberAxis) p.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        chart.getCategoryPlot().setRenderer(renderer);
        ChartFrame frame=new ChartFrame("Frame per barchart",chart);
        frame.setVisible(true);
        frame.setSize(800,500);
        }
        
        else if(statsbycombo.getSelectedItem().equals("Vlerësimet e trajnerëve")){
        DefaultCategoryDataset dataset=new DefaultCategoryDataset();
        List <TTrainerEvaluation> trainerevs1=trainerevir.findByTPDate(2013);
        List <TTrainerEvaluation> trainerevs2=trainerevir.findByTPDate(2014);
        List <TTrainerEvaluation> trainerevs3=trainerevir.findByTPDate(2015);
        List <TTrainerEvaluation> trainerevs4=trainerevir.findByTPDate(2016);
        List <TTrainerEvaluation> trainerevs5=trainerevir.findByTPDate(2017);
        
        int tev20131=0;
        int tev20132=0;
        int tev20133=0;
        int tev20134=0;
        
        int tev20141=0;
        int tev20142=0;
        int tev20143=0;
        int tev20144=0;
        
        int tev20151=0;
        int tev20152=0;
        int tev20153=0;
        int tev20154=0;
        
        int tev20161=0;
        int tev20162=0;
        int tev20163=0;
        int tev20164=0;
        
        int tev20171=0;
        int tev20172=0;
        int tev20173=0;
        int tev20174=0;
       
           
        for(int i=0;i<trainerevs1.size();i++){
            int trainerprep=trainerevs1.get(i).getTrainerPreperation();
            int trainerdisc=trainerevs1.get(i).getTrainerDiscussion();
            int trainerpres=trainerevs1.get(i).getPresentation();
            tev20131=tev20131+trainerprep;
            tev20132=tev20132+trainerdisc;
            tev20133=tev20133+trainerpres;
        }
        
        for(int i=0;i<trainerevs2.size();i++){
            int trainerprep=trainerevs2.get(i).getTrainerPreperation();
            int trainerdisc=trainerevs2.get(i).getTrainerDiscussion();
            int trainerpres=trainerevs2.get(i).getPresentation();
            tev20141=tev20141+trainerprep;
            tev20142=tev20142+trainerdisc;
            tev20143=tev20143+trainerpres;
        }
        
        for(int i=0;i<trainerevs3.size();i++){
            int trainerprep=trainerevs3.get(i).getTrainerPreperation();
            int trainerdisc=trainerevs3.get(i).getTrainerDiscussion();
            int trainerpres=trainerevs3.get(i).getPresentation();
            tev20151=tev20151+trainerprep;
            tev20152=tev20152+trainerdisc;
            tev20153=tev20153+trainerpres;
        }
        
        for(int i=0;i<trainerevs4.size();i++){
            int trainerprep=trainerevs4.get(i).getTrainerPreperation();
            int trainerdisc=trainerevs4.get(i).getTrainerDiscussion();
            int trainerpres=trainerevs4.get(i).getPresentation();
            tev20161=tev20161+trainerprep;
            tev20162=tev20162+trainerdisc;
            tev20163=tev20163+trainerpres;
        }
        
        for(int i=0;i<trainerevs5.size();i++){
            int trainerprep=trainerevs5.get(i).getTrainerPreperation();
            int trainerdisc=trainerevs5.get(i).getTrainerDiscussion();
            int trainerpres=trainerevs5.get(i).getPresentation();
            tev20171=tev20171+trainerprep;
            tev20172=tev20172+trainerdisc;
            tev20173=tev20173+trainerpres;
        }
        
        double tevavg2013=0;
        double tevavg2014=0;
        double tevavg2015=0;
        double tevavg2016=0;
        double tevavg2017=0;
        
        double tot1=(tev20131+tev20132+tev20133)/3;
        double tot2=(tev20141+tev20142+tev20143)/3;
        double tot3=(tev20151+tev20152+tev20153)/3;
        double tot4=(tev20161+tev20162+tev20163)/3;
        double tot5=(tev20171+tev20172+tev20173)/3;
        
        tevavg2013=(double)tot1/trainerevs1.size();
            /*BigDecimal bd1 = new BigDecimal(tevavg2013).setScale(2, RoundingMode.HALF_EVEN);
            tevavg2013 = bd1.doubleValue();*/
            
        tevavg2014=(double)tot2/trainerevs2.size();
            /*BigDecimal bd2 = new BigDecimal(tevavg2014).setScale(2, RoundingMode.HALF_EVEN);
            tevavg2014 = bd2.doubleValue();*/
            
        tevavg2015=(double)tot3/trainerevs3.size();
           /* BigDecimal bd3 = new BigDecimal(tevavg2015).setScale(2, RoundingMode.HALF_EVEN);
            tevavg2015 = bd3.doubleValue();*/
         
        tevavg2016=(double)tot4/trainerevs4.size();
            /*BigDecimal bd4 = new BigDecimal(tevavg2016).setScale(2, RoundingMode.HALF_EVEN);
            tevavg2016 = bd4.doubleValue();   */
        tevavg2017=(double)tot5/trainerevs4.size();
            
        String te1="Përgaditja";
        String te2="Diskutimi";
        String te3="Prezantimi";
        
        String v1="2013";
        String v2="2014";
        String v3="2015";
        String v4="2016";
        String v5="2017";
         
        dataset.setValue((double)tev20131/trainerevs1.size(), te1, v1);
        dataset.setValue((double)tev20132/trainerevs1.size(), te2, v1);
        dataset.setValue((double)tev20133/trainerevs1.size(), te3, v1);
        
        dataset.setValue((double)tev20141/trainerevs2.size(), te1, v2);
        dataset.setValue((double)tev20142/trainerevs2.size(), te2, v2);
        dataset.setValue((double)tev20143/trainerevs2.size(), te3, v2);
       
        dataset.setValue((double)tev20151/trainerevs3.size(), te1, v3);
        dataset.setValue((double)tev20152/trainerevs3.size(), te2, v3);
        dataset.setValue((double)tev20153/trainerevs3.size(), te3, v3);
        
        dataset.setValue((double)tev20161/trainerevs4.size(), te1, v4);
        dataset.setValue((double)tev20162/trainerevs4.size(), te2, v4);
        dataset.setValue((double)tev20163/trainerevs4.size(), te3, v4);
        
        dataset.setValue((double)tev20171/trainerevs5.size(), te1, v5);
        dataset.setValue((double)tev20172/trainerevs5.size(), te2, v5);
        dataset.setValue((double)tev20173/trainerevs5.size(), te3, v5);
         
        /*dataset.setValue(tevavg2013, "Vlerësimi mesatar","2013");
        dataset.setValue(tevavg2014, "Vlerësimi mesatar","2014");
        dataset.setValue(tevavg2015, "Vlerësimi mesatar","2015");
        dataset.setValue(tevavg2016, "Vlerësimi mesatar","2016");
        dataset.addValue(tevavg2015,"Ki qef","Ani");*/
        
        //qitu ke met tu u munu me ndreq renderer barchart 
        JFreeChart chart = createChart(dataset);
        CategoryPlot p=chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.black);
        ChartFrame frame=new ChartFrame("Frame per rendered barchart",chart);
        frame.setVisible(true);
        frame.setSize(800,500);
        }
        else if(statsbycombo.getSelectedItem().equals("Numri i pjesmarrësve")){
        
        DefaultCategoryDataset datas=new DefaultCategoryDataset();
           
         int countParticipant2013=participantir.findByTPDate(2013).size();
         int countParticipant2014=participantir.findByTPDate(2014).size();
         int countParticipant2015=participantir.findByTPDate(2015).size();
         int countParticipant2016=participantir.findByTPDate(2016).size();
         int countParticipant2017=participantir.findByTPDate(2017).size();
         
         
         
        datas.setValue(countParticipant2013, "Pjesmarrës","2013");
        datas.setValue(countParticipant2014, "Pjesmarrës","2014");
        datas.setValue(countParticipant2015, "Pjesmarrës","2015");
        datas.setValue(countParticipant2016, "Pjesmarrës","2016");
        datas.setValue(countParticipant2017, "Pjesmarrës","2017");
        
        JFreeChart chart=ChartFactory.createBarChart("Numri i pjesmarrësve ndër vite", "Viti", "Numri i pjesmarrësve", datas, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot p=chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.black);
        final BarRenderer renderer = (BarRenderer) p.getRenderer();
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);
        final NumberAxis rangeAxis = (NumberAxis) p.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        chart.getCategoryPlot().setRenderer(renderer);
        ChartFrame frame=new ChartFrame("Frame per barchart",chart);
        frame.setVisible(true);
        frame.setSize(800,500);
        }
        
        
    }
    
    private void insertCell(PdfPTable table, String text, int align, int colspan, Font font){
  
        //create a new cell with the specified Text and Font
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        //set the cell alignment
        cell.setHorizontalAlignment(align);
        //set the cell column span in case you want to merge two or more cells
        cell.setColspan(colspan);
        
        
        //in case there is no text and you wan to create an empty row
        if(text.trim().equalsIgnoreCase("")){
         cell.setMinimumHeight(10f);
        }
        //add the call to the table
        table.addCell(cell);

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
        
        
        
        
        jLabel4.setCursor(cursor);
        jLabel5.setCursor(cursor);
        jScrollPane1.setCursor(cursor);
        jScrollPane2.setCursor(cursor);
        this.setCursor(cursor);
        
        
        orderbycombo.setCursor(buttoncursor);
        searchcombo.setCursor(buttoncursor);
        shtyppdfbtn1.setCursor(buttoncursor);
        orderbycombo2.setCursor(buttoncursor);
        searchcombo2.setCursor(buttoncursor);
        jLabel3.setCursor(buttoncursor);
        kerkobtn1.setCursor(buttoncursor);
        shtyppdfbtn1.setCursor(buttoncursor);
        kerkobtn2.setCursor(buttoncursor);
        shtyppdfbtn2.setCursor(buttoncursor);
        barchartbtn.setCursor(buttoncursor);
        statsbycombo.setCursor(buttoncursor);
        
        
        searchtxtf.setCursor(textcursor);
        searchtxtf2.setCursor(textcursor);
        
        
    }
    private JFreeChart createChart(final CategoryDataset dataset) {
        
        // create the chart...
        final JFreeChart chart = ChartFactory.createBarChart(
            "Bar Chart Demo",         // chart title
            "Category",               // domain axis label
            "Value",                  // range axis label
            dataset,                  // data
            PlotOrientation.VERTICAL, // orientation
            true,                     // include legend
            true,                     // tooltips?
            false                     // URLs?
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...

        // set the background color for the chart...
        chart.setBackgroundPaint(Color.white);

        // get a reference to the plot for further customisation...
        final CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        // set the range axis to display integers only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createStandardTickUnits());

        // disable bar outlines...
        final BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        
        // set up gradient paints for series...
        final GradientPaint gp0 = new GradientPaint(
            0.0f, 0.0f, Color.blue, 
            0.0f, 0.0f, Color.lightGray
        );
        final GradientPaint gp1 = new GradientPaint(
            0.0f, 0.0f, Color.green, 
            0.0f, 0.0f, Color.lightGray
        );
        final GradientPaint gp2 = new GradientPaint(
            0.0f, 0.0f, Color.red, 
            0.0f, 0.0f, Color.lightGray
        );
        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);
        
        //renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12,TextAnchor.TOP_CENTER  ));
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);
        chart.getCategoryPlot().setRenderer(renderer);
        final org.jfree.chart.axis.CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
            CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
        );
        // OPTIONAL CUSTOMISATION COMPLETED.
        
        return chart;
        
    }
    
    public void setColWidth(){
        
       tbl.getColumnModel().getColumn(0).setPreferredWidth(100);
       tbl.getColumnModel().getColumn(1).setPreferredWidth(100);
       tbl.getColumnModel().getColumn(2).setPreferredWidth(100);
       tbl.getColumnModel().getColumn(3).setPreferredWidth(120);
       tbl.getColumnModel().getColumn(4).setPreferredWidth(220);
       tbl.getColumnModel().getColumn(5).setPreferredWidth(174);
       tbl.getColumnModel().getColumn(6).setPreferredWidth(174);
        
        
       traintbl.getColumnModel().getColumn(0).setPreferredWidth(130);
       traintbl.getColumnModel().getColumn(1).setPreferredWidth(265);
       traintbl.getColumnModel().getColumn(2).setPreferredWidth(250);
       traintbl.getColumnModel().getColumn(3).setPreferredWidth(340);
       traintbl.getColumnModel().getColumn(4).setPreferredWidth(85);
       traintbl.getColumnModel().getColumn(5).setPreferredWidth(85);
       traintbl.getColumnModel().getColumn(6).setPreferredWidth(85);
       
        
        JTableHeader headertbl = tbl.getTableHeader();
        headertbl.setFont( headertbl.getFont().deriveFont(19f) );
        
        JTableHeader headertraintbl = traintbl.getTableHeader();
        headertraintbl.setFont( headertraintbl.getFont().deriveFont(17f) );
        
        
        
    }
    
    public void addJCheckBoxListener(JCheckBox target,String s){
        target.addItemListener(new ItemListener() {
        public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED){
                    if(target.isSelected()){
                    colnamesarraylist.add(s);
                    target.setSelected(true);

                    int count=colnamesarraylist.size();
                        columnNames=new String[count];
                    for(int i=0;i<count;i++){
                       columnNames[i]=colnamesarraylist.get(i);

                    }
                     
                     participantcustomtm=new ParticipantKerkoCustomTableModel(columnNames);
                     List<Participant>Temp=participantir.findAllActive();
                    if(searchcombo.getSelectedItem().equals("Emrit/Mbiemrit")){
                    if(orderbycombo.getSelectedItem().equals("Rritëse") && searchtxtf.getText().equals(""))
                    {
                        
                        Temp = participantir.OrderByEmriAsc();
                    }
                    if(orderbycombo.getSelectedItem().equals("Zbritëse") && searchtxtf.getText().equals(""))
                        {
                        Temp = participantir.OrderByEmriDes();
                       }
                    else if (orderbycombo.getSelectedItem().equals("Rritëse") && !searchtxtf.getText().equals(""))
                        {
                        Temp = participantir.findByEmriAsc(searchtxtf.getText());
                       }
                    else if (orderbycombo.getSelectedItem().equals("Zbritëse") && !searchtxtf.getText().equals(""))
                        {
                        Temp = participantir.findByEmriDes(searchtxtf.getText());
                       }
                    }
                    
                    if(searchcombo.getSelectedItem().equals("Gjinis")){
                    if(orderbycombo.getSelectedItem().equals("Rritëse") && searchtxtf.getText().equals(""))
                    {
                        
                        Temp = participantir.OrderByGjiniaAsc();
                    }
                    if(orderbycombo.getSelectedItem().equals("Zbritëse") && searchtxtf.getText().equals(""))
                        {
                        Temp = participantir.OrderByGjiniaDes();
                       }
                    else if (orderbycombo.getSelectedItem().equals("Rritëse") && !searchtxtf.getText().equals(""))
                        {
                        Temp = participantir.findByGjiniaAsc(searchtxtf.getText());
                       }
                    else if (orderbycombo.getSelectedItem().equals("Zbritëse") && !searchtxtf.getText().equals(""))
                        {
                        Temp = participantir.findByGjiniaDes(searchtxtf.getText());
                       }
                    }
                    
                    if(searchcombo.getSelectedItem().equals("Numrit Personal")){
                    if(orderbycombo.getSelectedItem().equals("Rritëse") && searchtxtf.getText().equals(""))
                    {
                        
                        Temp = participantir.OrderByPersonalIDAsc();
                    }
                    if(orderbycombo.getSelectedItem().equals("Zbritëse") && searchtxtf.getText().equals(""))
                        {
                        Temp = participantir.OrderByPersonalIDDes();
                       }
                    else if (orderbycombo.getSelectedItem().equals("Rritëse") && !searchtxtf.getText().equals(""))
                        {
                        Temp = participantir.findByPersonalIDAsc(Integer.parseInt(searchtxtf.getText()));
                       }
                    else if (orderbycombo.getSelectedItem().equals("Zbritëse") && !searchtxtf.getText().equals(""))
                        {
                        Temp = participantir.findByPersonalIDDes(Integer.parseInt(searchtxtf.getText()));
                       }
                    }
                     
                    if(searchcombo.getSelectedItem().equals("Qytetit")){
                    if(orderbycombo.getSelectedItem().equals("Rritëse") && searchtxtf.getText().equals(""))
                    {
                        
                        Temp = participantir.OrderByQytetiAsc();
                    }
                    if(orderbycombo.getSelectedItem().equals("Zbritëse") && searchtxtf.getText().equals(""))
                        {
                        Temp = participantir.OrderByQytetiDes();
                       }
                    else if (orderbycombo.getSelectedItem().equals("Rritëse") && !searchtxtf.getText().equals(""))
                        {
                        Temp = participantir.findByQytetiAsc(searchtxtf.getText());
                       }
                    else if (orderbycombo.getSelectedItem().equals("Zbritëse") && !searchtxtf.getText().equals(""))
                        {
                        Temp = participantir.findByQytetiDes(searchtxtf.getText());
                        
                       }
                    }
                    if(searchcombo.getSelectedItem().equals("TrainingID")){
                        Temp = participantir.findByTpIdActive(searchtxtf.getText());
                    }
                     participantcustomtm.add(Temp);
                        tbl.setModel(participantcustomtm);
                        participantcustomtm.fireTableDataChanged();       
                     /*List<Participant>Temp = participantir.findAllActive();
                    participantcustomtm.add(Temp);
                    tbl.setModel(participantcustomtm);
                    participantcustomtm.fireTableDataChanged();*/
                    
                    }
                }
                else{
                        colnamesarraylist.remove(s);
                        target.setSelected(false);

                        int count=colnamesarraylist.size();
                        columnNames=new String[count];
                        for(int i=0;i<count;i++){
                           columnNames[i]=colnamesarraylist.get(i);
                        } 
                        
                        participantcustomtm=new ParticipantKerkoCustomTableModel(columnNames);
                     List<Participant>Temp=participantir.findAllActive();
                    if(searchcombo.getSelectedItem().equals("Emrit/Mbiemrit")){
                    if(orderbycombo.getSelectedItem().equals("Rritëse") && searchtxtf.getText().equals(""))
                    {
                        
                        Temp = participantir.OrderByEmriAsc();
                    }
                    if(orderbycombo.getSelectedItem().equals("Zbritëse") && searchtxtf.getText().equals(""))
                        {
                        Temp = participantir.OrderByEmriDes();
                       }
                    else if (orderbycombo.getSelectedItem().equals("Rritëse") && !searchtxtf.getText().equals(""))
                        {
                        Temp = participantir.findByEmriAsc(searchtxtf.getText());
                       }
                    else if (orderbycombo.getSelectedItem().equals("Zbritëse") && !searchtxtf.getText().equals(""))
                        {
                        Temp = participantir.findByEmriDes(searchtxtf.getText());
                       }
                    }
                    
                    if(searchcombo.getSelectedItem().equals("Gjinis")){
                    if(orderbycombo.getSelectedItem().equals("Rritëse") && searchtxtf.getText().equals(""))
                    {
                        
                        Temp = participantir.OrderByGjiniaAsc();
                    }
                    if(orderbycombo.getSelectedItem().equals("Zbritëse") && searchtxtf.getText().equals(""))
                        {
                        Temp = participantir.OrderByGjiniaDes();
                       }
                    else if (orderbycombo.getSelectedItem().equals("Rritëse") && !searchtxtf.getText().equals(""))
                        {
                        Temp = participantir.findByGjiniaAsc(searchtxtf.getText());
                       }
                    else if (orderbycombo.getSelectedItem().equals("Zbritëse") && !searchtxtf.getText().equals(""))
                        {
                        Temp = participantir.findByGjiniaDes(searchtxtf.getText());
                       }
                    }
                    
                    if(searchcombo.getSelectedItem().equals("Numrit Personal")){
                    if(orderbycombo.getSelectedItem().equals("Rritëse") && searchtxtf.getText().equals(""))
                    {
                        
                        Temp = participantir.OrderByPersonalIDAsc();
                    }
                    if(orderbycombo.getSelectedItem().equals("Zbritëse") && searchtxtf.getText().equals(""))
                        {
                        Temp = participantir.OrderByPersonalIDDes();
                       }
                    else if (orderbycombo.getSelectedItem().equals("Rritëse") && !searchtxtf.getText().equals(""))
                        {
                        Temp = participantir.findByPersonalIDAsc(Integer.parseInt(searchtxtf.getText()));
                       }
                    else if (orderbycombo.getSelectedItem().equals("Zbritëse") && !searchtxtf.getText().equals(""))
                        {
                        Temp = participantir.findByPersonalIDDes(Integer.parseInt(searchtxtf.getText()));
                       }
                    }
                     
                    if(searchcombo.getSelectedItem().equals("Qytetit")){
                    if(orderbycombo.getSelectedItem().equals("Rritëse") && searchtxtf.getText().equals(""))
                    {
                        
                        Temp = participantir.OrderByQytetiAsc();
                    }
                    if(orderbycombo.getSelectedItem().equals("Zbritëse") && searchtxtf.getText().equals(""))
                        {
                        Temp = participantir.OrderByQytetiDes();
                       }
                    else if (orderbycombo.getSelectedItem().equals("Rritëse") && !searchtxtf.getText().equals(""))
                        {
                        Temp = participantir.findByQytetiAsc(searchtxtf.getText());
                       }
                    else if (orderbycombo.getSelectedItem().equals("Zbritëse") && !searchtxtf.getText().equals(""))
                        {
                        Temp = participantir.findByQytetiDes(searchtxtf.getText());
                        
                       }
                    }
                    if(searchcombo.getSelectedItem().equals("TrainingID")){
                        Temp = participantir.findByTpIdActive(searchtxtf.getText());
                    }
                     participantcustomtm.add(Temp);
                        tbl.setModel(participantcustomtm);
                        participantcustomtm.fireTableDataChanged();    
                    }
            
        }
      });
    }
    
    public void dynamicListener(){
        
        
        
        addJCheckBoxListener(emricheck,"Name");
        addJCheckBoxListener(mbiemricheck,"Surname");
        addJCheckBoxListener(dobcheck,"Data e Lindjes");
        addJCheckBoxListener(idnumcheck,"Numri Personal");
        addJCheckBoxListener(emailcheck,"Email");
        addJCheckBoxListener(telefonicheck,"Telefoni");
        addJCheckBoxListener(qyteticheck,"Qyteti");
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JButton barchartbtn;
    private javax.swing.JLabel companysf;
    private javax.swing.JCheckBox dobcheck;
    private javax.swing.JCheckBox emailcheck;
    private javax.swing.JCheckBox emricheck;
    private javax.swing.JCheckBox idnumcheck;
    private javax.swing.JLabel individualf;
    private javax.swing.JLabel internationalorgf;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton kerkobtn1;
    private javax.swing.JButton kerkobtn2;
    private javax.swing.JCheckBox mbiemricheck;
    private javax.swing.JLabel nongoverf;
    private javax.swing.JComboBox<String> orderbycombo;
    private javax.swing.JComboBox<String> orderbycombo2;
    private javax.swing.JLabel partFstat;
    private javax.swing.JLabel partMstat;
    private javax.swing.JLabel participantstat;
    private javax.swing.JLabel privatebus;
    private javax.swing.JLabel publicinstif;
    private javax.swing.JCheckBox qyteticheck;
    private javax.swing.JLabel searchLbl;
    private javax.swing.JLabel searchLbl2;
    private javax.swing.JComboBox<String> searchcombo;
    private javax.swing.JComboBox<String> searchcombo2;
    private javax.swing.JTextField searchtxtf;
    private javax.swing.JTextField searchtxtf2;
    private javax.swing.JButton shtyppdfbtn1;
    private javax.swing.JButton shtyppdfbtn2;
    private javax.swing.JComboBox<String> statsbycombo;
    private javax.swing.JTable tbl;
    private javax.swing.JCheckBox telefonicheck;
    private javax.swing.JLabel tevf1;
    private javax.swing.JLabel tevf2;
    private javax.swing.JLabel tevf3;
    private javax.swing.JLabel tevf4;
    private javax.swing.JLabel topicsf;
    private javax.swing.JLabel tprocessstat;
    private javax.swing.JLabel trainerf;
    private javax.swing.JLabel trainerstat;
    private javax.swing.JTable traintbl;
    // End of variables declaration//GEN-END:variables
}
