
package gui.view;
import bl.AppException;
import ejb.TrainingProcess;
import ejb.TrainingProject;
import ejb.Project;
import ejb.Training;
import ejb.TopicsCovered;
import ejb.Participant;
import ejb.ParticipatingCompanyMember;
import ejb.ParticipantTeam;
import ejb.Team;
import ejb.Users;
import ejb.Logs;
import bl.TrainingProcessInterface;
import bl.TrainingProcessRepository;
import bl.TrainingProjectInterface;
import bl.TrainingProjectRepository;
import bl.ProjectInterface;
import bl.ProjectRepository;
import bl.TrainingInterface;
import bl.TrainingRepository;
import bl.TopicsCoveredInterface;
import bl.TopicsCoveredRepository;
import bl.ParticipantInterface;
import bl.ParticipantRepository;
import bl.ParticipatingCompanyMemberInterface;
import bl.ParticipatingCompanyMemberRepository;
import bl.ParticipantTeamInterface;
import bl.ParticipantTeamRepository;
import bl.TeamInterface;
import bl.TeamRepository;
import bl.UserInterface;
import bl.UserRepository;
import bl.LogsInterface;
import bl.LogsRepository;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import ejb.TTrainerEvaluation;
import ejb.Trainer;
import gui.model.ParticipantTableModel;
import gui.model.TrainingProcessTableModelMini;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.VerticalAlign;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import ejb.Logs;
import ejb.Users;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.FileOutputStream;
import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.nio.file.Paths;
import java.sql.Time;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;




public class Print extends javax.swing.JInternalFrame {
    
    Users useri;
    EntityManager em;
    Participant p;
    TrainingProcess tp;
    ArrayList <Participant> participants =new ArrayList();
    
    TrainingProcessInterface trainingprocessir;
    TrainingProjectInterface trainingprojectir;
    ProjectInterface projectir;
    TrainingInterface trainingir;
    TopicsCoveredInterface topicscoveredir;
    ParticipantInterface participantir;
    ParticipatingCompanyMemberInterface pcmir;
    ParticipantTeamInterface participantteamir;
    TeamInterface trainingpir;
    LogsInterface logsir;
    UserInterface userir;
    
    TrainingProcessTableModelMini tptmm = new TrainingProcessTableModelMini();
    ParticipantTableModel ptm=new ParticipantTableModel();
    String pathi="";
    String absolutepath=Paths.get(".").toAbsolutePath().normalize().toString();
    ArrayList<String> pathfoldernames;
  
    public Print(Users useri,EntityManager emm) {
        this.useri=useri;
        em=emm;
        trainingprocessir=new TrainingProcessRepository(em);
        trainingprojectir=new TrainingProjectRepository(em);
        projectir=new ProjectRepository(em);
        trainingir=new TrainingRepository(em);
        topicscoveredir=new TopicsCoveredRepository(em);
        participantir=new ParticipantRepository(em);
        pcmir=new ParticipatingCompanyMemberRepository(em);
        participantteamir=new ParticipantTeamRepository(em);
        trainingpir=new TeamRepository(em);
        logsir=new LogsRepository(em);
        userir=new UserRepository(em);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int jFramewidth=this.getSize().width;
        int jFrameheight=this.getSize().height;
        int locationx=(dim.width-jFramewidth)/2;
        int locationy=(dim.height-jFrameheight)/2;
        this.setLocation(15,147);
        
        for(MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().getMouseListeners()){
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().removeMouseListener(listener);
        }
        
        initComponents();
        tpmTabelaLoad();
        TrainingProcessTabelaMoveKey();
        ParticipantTabelaMoveKey();
        addFolderNames();
        CustomCursor();
        
        tpidtxtf.getDocument().addDocumentListener(new DocumentListener(){
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
                    
                   List<Participant>participants=participantir.findByTpIdActive(tpidtxtf.getText().trim());
                   ptm.add(participants);
                   tbl.setModel(ptm);
                   ptm.fireTableDataChanged();
                   
                }
          });
        
        
        /*idnumtxtf.getDocument().addDocumentListener(new DocumentListener(){
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
                   if(tbl.getModel()==ptm&&!idnumtxtf.getText().trim().equals("")&&!tpidtxtf.getText().trim().equals("")){ 
                    
                    List<Participant>participants=participantir.findByPersonalIDAndTPId(Long.parseLong(idnumtxtf.getText().trim()),tpidtxtf.getText().trim());
                    
                    ptm.add(participants);
                    tbl.setModel(ptm);
                    ptm.fireTableDataChanged();
                    
                   }
                }
          }); */
        
    }

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl = new javax.swing.JTable();
        tpidtxtf = new javax.swing.JTextField();
        idnumtxtf = new javax.swing.JTextField();
        emritxtf = new javax.swing.JTextField();
        mbiemritxtf = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ikona = new javax.swing.JButton();
        label = new javax.swing.JLabel();
        printallbtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        langcombo = new javax.swing.JComboBox<>();
        extratxtf = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("PRINT");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 1200, 280));

        tpidtxtf.setBackground(new java.awt.Color(225, 225, 225));
        tpidtxtf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tpidtxtf.setBorder(null);
        tpidtxtf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tpidtxtfActionPerformed(evt);
            }
        });
        jPanel1.add(tpidtxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 64, 162, 30));

        idnumtxtf.setBackground(new java.awt.Color(225, 225, 225));
        idnumtxtf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        idnumtxtf.setBorder(null);
        idnumtxtf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idnumtxtfActionPerformed(evt);
            }
        });
        jPanel1.add(idnumtxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(249, 63, 222, 30));

        emritxtf.setEditable(false);
        emritxtf.setBackground(new java.awt.Color(225, 225, 225));
        emritxtf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        emritxtf.setBorder(null);
        emritxtf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emritxtfActionPerformed(evt);
            }
        });
        jPanel1.add(emritxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 63, 146, 30));

        mbiemritxtf.setEditable(false);
        mbiemritxtf.setBackground(new java.awt.Color(225, 225, 225));
        mbiemritxtf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        mbiemritxtf.setBorder(null);
        jPanel1.add(mbiemritxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(752, 65, 138, 30));

        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1152, 170, 54, 22));

        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 145, 190, 45));

        ikona.setBackground(new java.awt.Color(113, 3, 3));
        ikona.setFont(new java.awt.Font("Calibri Light", 1, 12)); // NOI18N
        ikona.setForeground(new java.awt.Color(255, 255, 255));
        ikona.setText("Shto ikonen e kompanis");
        ikona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ikonaActionPerformed(evt);
            }
        });
        jPanel1.add(ikona, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 60, 180, 40));

        label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(label, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 50, 80, 50));

        printallbtn.setBackground(new java.awt.Color(105, 3, 3));
        printallbtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        printallbtn.setForeground(new java.awt.Color(255, 255, 255));
        printallbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/printallicon.png"))); // NOI18N
        printallbtn.setText("Print All");
        printallbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printallbtnActionPerformed(evt);
            }
        });
        jPanel1.add(printallbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, 130, 40));

        jLabel3.setFont(new java.awt.Font("Calibri Light", 1, 12)); // NOI18N
        jLabel3.setText("IKONA :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 20, 50, 20));

        langcombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ALB-ENG", "SRB-ENG" }));
        jPanel1.add(langcombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 150, 180, 40));

        extratxtf.setBackground(new java.awt.Color(225, 225, 225));
        jPanel1.add(extratxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, 510, 30));

        jLabel4.setFont(new java.awt.Font("Calibri Light", 0, 17)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("Extra text:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 110, 200, 30));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/print.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void tpmTabelaLoad(){
          List<TrainingProcess> data=trainingprocessir.findAllActive();
          tptmm.add(data);
          
          //tptmm.setColWidth(tbl,0, 20);
          tbl.setModel(tptmm);
          tptmm.fireTableDataChanged();
          
    }
    
    private void participantTabelaLoadByTpid(String s){
        List<Participant> data=participantir.findByTpIdActive(s);
          ptm.add(data);
          tbl.setModel(ptm);
          ptm.fireTableDataChanged();
    }
    
    
    private void ptmTabelaLoad(Participant p){
        List<Participant> data=participantir.findAllActive();
          ptm.add(data);
          tbl.setModel(ptm);
          ptm.fireTableDataChanged();
    }
    
    private void TrainingProcessTabelaMoveKey(){
        
    ListSelectionModel rowSM=tbl.getSelectionModel();
    rowSM.addListSelectionListener (new ListSelectionListener(){
    @Override
    public void valueChanged (ListSelectionEvent e){
        if(tbl.getModel()==tptmm)
        {
    if(e.getValueIsAdjusting())
        return;
    ListSelectionModel rowSM = (ListSelectionModel)e.getSource();
    int selectedIndex = rowSM.getMinSelectionIndex();
    if(selectedIndex>-1){
        TrainingProcess p=tptmm.getTrainingProcess(selectedIndex);
        tpidtxtf.setText(p.getTProcessID()+"");
    }
    }
    }
    });
    }
    
    private void ParticipantTabelaMoveKey(){
        
    ListSelectionModel rowSM=tbl.getSelectionModel();
    rowSM.addListSelectionListener (new ListSelectionListener(){
    @Override
    public void valueChanged (ListSelectionEvent e){
        if(tbl.getModel()==ptm)
        {
    if(e.getValueIsAdjusting())
        return;
    ListSelectionModel rowSM = (ListSelectionModel)e.getSource();
    int selectedIndex = rowSM.getMinSelectionIndex();
    if(selectedIndex>-1){
        
        Participant p=ptm.getParticipant(selectedIndex);
        idnumtxtf.setText(p.getIDNumber()+"");
        emritxtf.setText(p.getName());
        mbiemritxtf.setText(p.getSurname());
    }
    }
    }
    });
    }
    
    
    private void tpidtxtfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tpidtxtfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tpidtxtfActionPerformed

    private void idnumtxtfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idnumtxtfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idnumtxtfActionPerformed

    private void emritxtfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emritxtfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emritxtfActionPerformed
    
    public void shtypPDF(String chunk,String directory){
        
       
        
        Document document=new Document();
        try{
            
            
            
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            int jFramewidth=this.getSize().width;
            int jFrameheight=this.getSize().height;
            int locationx=(dim.width-jFramewidth)/2;
            int locationy=(dim.height-jFrameheight)/2;
            
            List <TopicsCovered> topicsAlb;
            List <TopicsCovered> topicsEng;
            Training training;
            String idnum=idnumtxtf.getText();
            String tpid=tpidtxtf.getText();
            String s1=emritxtf.getText();
            String s2=mbiemritxtf.getText(); 
            TrainingProcess trainp=trainingprocessir.findById(tpid);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            String today=(dateFormat.format(trainp.getEndDate()));
            
            
            
            
            topicsAlb=topicscoveredir.findByTpLangId(tpidtxtf.getText().trim(),"Albanian");
            String tcs="";
            for(TopicsCovered tc :topicsAlb){
                tcs+=tc+"; ";
                if(tcs.length()>35){
                    
                }
            }
            
            topicsEng=topicscoveredir.findByTpLangId(tpidtxtf.getText().trim(), "English");
            String tcseng="";
            for(TopicsCovered tc :topicsEng){
                tcseng+=tc+"; ";
            }
                
            ArrayList<Chunk> chunkAlbanian=new ArrayList<Chunk>();
            char[] temafullAlb=tcs.toCharArray();
            ArrayList<Chunk> chunkEnglish=new ArrayList<Chunk>();
            char[] temafullEng=tcseng.toCharArray();
            
           
            String rreshtiTemes="";
            String tempTcsAlb=tcs;
            String tempTcsEng=tcseng;
            
            /*ALBANIAN*/
            if(tempTcsAlb.length()<40){
                chunkAlbanian.add(new Chunk(tempTcsAlb,FontFactory.getFont(FontFactory.HELVETICA,9)));
            }
            while(tempTcsAlb.length()>=40){
                temafullAlb=tempTcsAlb.toCharArray();
                for(int i=40;i<tempTcsAlb.length();i++){
                    if(i>=40 && temafullAlb[i]==32){
                        rreshtiTemes=tempTcsAlb.substring(0,i);
                        tempTcsAlb=tempTcsAlb.substring(i);
                        chunkAlbanian.add(new Chunk(rreshtiTemes,FontFactory.getFont(FontFactory.HELVETICA,9)));
                        break;
                    }else if(tempTcsAlb.length()==i)
                    {
                        rreshtiTemes=tempTcsAlb.substring(0,i);
                        chunkAlbanian.add(new Chunk(rreshtiTemes,FontFactory.getFont(FontFactory.HELVETICA,9)));
                        tempTcsAlb=tempTcsAlb.substring(i);
                    }
                }
                
            }
            
            /*ENGLISH*/
            if(tempTcsEng.length()<40){
                chunkEnglish.add(new Chunk(tempTcsEng,FontFactory.getFont(FontFactory.HELVETICA,9)));
            }
            while(tempTcsEng.length()>40){
                temafullEng=tempTcsEng.toCharArray();
                for(int i=40;i<tempTcsEng.length();i++){
                    if(i>=40 && temafullEng[i]==32){
                        rreshtiTemes=tempTcsEng.substring(0,i);
                        tempTcsEng=tempTcsEng.substring(i);
                        chunkEnglish.add(new Chunk(rreshtiTemes,FontFactory.getFont(FontFactory.HELVETICA,9)));
                        break;
                    }else if(tempTcsEng.length()==i)
                    {
                        rreshtiTemes=tempTcsEng.substring(0,i);
                        chunkEnglish.add(new Chunk(rreshtiTemes,FontFactory.getFont(FontFactory.HELVETICA,9)));
                        tempTcsEng=tempTcsEng.substring(i);
                    }
                }
                
            }
            
            int maxTopic=topicsAlb.size();
            if(maxTopic<topicsEng.size()){
                maxTopic=topicsEng.size();
            }
            ArrayList<Chunk> chunka=new ArrayList<Chunk>();
            ArrayList<Chunk> chunke=new ArrayList<Chunk>();
            for (int i=0;i<maxTopic;i++){
                try{
                if(topicsAlb.get(i).getTopicCovered()!=null){
                    chunka.add(new Chunk(topicsAlb.get(i).getTopicCovered(),FontFactory.getFont(FontFactory.HELVETICA,9)));
                    
                }
                 else{
                chunka.add(new Chunk("",FontFactory.getFont(FontFactory.HELVETICA,9)));
                }}
                catch(ArrayIndexOutOfBoundsException e){
                      chunka.add(new Chunk("",FontFactory.getFont(FontFactory.HELVETICA,9)));
                }
                try{
                if(topicsEng.get(i).getTopicCovered()!=null){
                    chunke.add(new Chunk(topicsEng.get(i).getTopicCovered(),FontFactory.getFont(FontFactory.HELVETICA,9)));
                }
                else{
                chunke.add(new Chunk("",FontFactory.getFont(FontFactory.HELVETICA,9)));
             }
                }
                catch(ArrayIndexOutOfBoundsException e){
                    chunke.add(new Chunk("",FontFactory.getFont(FontFactory.HELVETICA,9)));
                }
            
            }
            
            training=trainingir.findByTpId(tpidtxtf.getText());
            
            String ts=training.getTitleOfTrainingAlbanian();
            
            ArrayList<Chunk> chunkTOTAlbanian=new ArrayList<Chunk>();
            char[] TOTfullAlb=ts.toCharArray();
            
            String rreshtiTitullit="";
            String tempTOTAlb=ts;
            
            /*ALBANIAN*/
            if(tempTOTAlb.length()<36){
                chunkTOTAlbanian.add(new Chunk(tempTOTAlb,FontFactory.getFont(FontFactory.HELVETICA,11)));
            }
            while(tempTOTAlb.length()>=36){
                TOTfullAlb=tempTOTAlb.toCharArray();
                for(int i=36;i<tempTOTAlb.length();i++){
                    if(i>=36 && TOTfullAlb[i]==32){
                        rreshtiTitullit=tempTOTAlb.substring(0,i);
                        tempTOTAlb=tempTOTAlb.substring(i);
                        chunkTOTAlbanian.add(new Chunk(rreshtiTitullit,FontFactory.getFont(FontFactory.HELVETICA,11)));
                        if(!tempTOTAlb.isEmpty() && tempTOTAlb.length()<36){
                            rreshtiTitullit=tempTOTAlb;
                            chunkTOTAlbanian.add(new Chunk(rreshtiTitullit,FontFactory.getFont(FontFactory.HELVETICA,11)));
                        }
                        break;
                    }else if(tempTOTAlb.length()==i+1)
                    {
                        rreshtiTitullit=tempTOTAlb.substring(0,i+1);
                        chunkTOTAlbanian.add(new Chunk(rreshtiTitullit,FontFactory.getFont(FontFactory.HELVETICA,11)));
                        tempTOTAlb=tempTOTAlb.substring(i+1);
                    }
                }
            }
            
            /*ENGLISH*/
            String tse=training.getTitleOfTrainingEnglish();
            ArrayList<Chunk> chunkTOTEnglish=new ArrayList<Chunk>();
            char[] TOTfullEng=tse.toCharArray();
            String tempTOTEng=tse;
            
            
            if(tempTOTEng.length()<36){
                chunkTOTEnglish.add(new Chunk(tempTOTEng,FontFactory.getFont(FontFactory.HELVETICA,11)));
            }
            while(tempTOTEng.length()>=36){
                TOTfullEng=tempTOTEng.toCharArray();
                for(int i=36;i<tempTOTEng.length();i++){
                    if(i>=36 && TOTfullEng[i]==32){
                        rreshtiTitullit=tempTOTEng.substring(0,i);
                        tempTOTEng=tempTOTEng.substring(i);
                        chunkTOTEnglish.add(new Chunk(rreshtiTitullit,FontFactory.getFont(FontFactory.HELVETICA,11)));
                        if(!tempTOTEng.isEmpty() && tempTOTEng.length()<36){
                            rreshtiTitullit=tempTOTEng;
                            chunkTOTEnglish.add(new Chunk(rreshtiTitullit,FontFactory.getFont(FontFactory.HELVETICA,11)));
                        }
                        break;
                    }else if(tempTOTEng.length()==i+1)
                    {
                        rreshtiTitullit=tempTOTEng.substring(0,i+1);
                        chunkTOTEnglish.add(new Chunk(rreshtiTemes,FontFactory.getFont(FontFactory.HELVETICA,11)));
                        tempTOTEng=tempTOTEng.substring(i+1);
                    }
                }
            }
            
            PdfWriter.getInstance(document, new FileOutputStream(directory+s1+s2+" - "+idnum+"-"+tpid+"-ALB-ENG"+".pdf"));
            document.open();
           
            if(!pathi.equals("")){
            Image img = Image.getInstance(pathi);
            img.scaleToFit(120f, 80f);
            document.add(img);
            }
            
            
            Paragraph para=new Paragraph();
            Chunk t=new Chunk(s1+" "+s2,FontFactory.getFont(FontFactory.HELVETICA,18,Font.BOLD));
            Chunk space=new Chunk("\n");
            Phrase prat=new Phrase();
            if(pathi.equals("")){
                prat.add(space);
                prat.add(space);
                prat.add(space);
                prat.add(space);
                prat.add(space);
            }
            prat.add(space);
            prat.add(space);
            prat.add(space);
            prat.add(space);
            prat.add(t);
            para.setAlignment(Element.ALIGN_CENTER);
            para.add(prat);
           
            document.add(para);
            
            
            
            Phrase pha1=new Phrase();
            Phrase pha2=new Phrase();
            Phrase pha3=new Phrase();
            Phrase pha4=new Phrase();
            Phrase pha5=new Phrase();
            Phrase pha6=new Phrase();
            Phrase pha7=new Phrase();
            Phrase pha8=new Phrase();
            
            Paragraph para1=new Paragraph();
            Paragraph para2=new Paragraph();
            Paragraph para3=new Paragraph();
            Paragraph para4=new Paragraph();
            Paragraph para5=new Paragraph();
            Paragraph para6=new Paragraph();
            Paragraph para7=new Paragraph();
            Paragraph para8=new Paragraph();
            
            Chunk glue2 = new Chunk(new VerticalPositionMark());
            
            
            Chunk c1=new Chunk("Shpërblehet me ceritifkatë ",FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
            Chunk c8=new Chunk("Has been awarded with ",FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
            Chunk c2=new Chunk("\n");
            
            Chunk c3=new Chunk("të  pjesmarrjes pas ",FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
            Chunk c10=new Chunk("Certicate of participation ",FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
            Chunk c4=new Chunk("\n");
            
            Chunk c5=new Chunk("përfundimit me sukses ",FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
            Chunk c12=new Chunk("upon having successfully",FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
            Chunk c6=new Chunk("\n");
            
            Chunk c7=new Chunk("të trajnimit : ",FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
            Chunk c14=new Chunk("completed training of : ",FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
            Chunk c9=new Chunk("\n");
            
            
            pha1.add(c2);
            pha1.add(c2);
            pha1.add(c2);
            pha1.add(c1);
            pha1.add(new Chunk(glue2));
            pha1.add(c8);
            pha1.add(c2);
            
            pha1.add(c3);
            pha1.add(new Chunk(glue2));
            pha1.add(c10);
            pha1.add(c4);
            
            pha1.add(c5);
            pha1.add(new Chunk(glue2));
            pha1.add(c12);
            pha1.add(c6);
            
            pha1.add(c7);
            pha1.add(new Chunk(glue2));
            pha1.add(c14);
            pha1.add(c9);
            
            
            para1.add(pha1);
            
            /*Chunk c20=new Chunk(ts,FontFactory.getFont(FontFactory.HELVETICA,11));
                Chunk c21=new Chunk(tse,FontFactory.getFont(FontFactory.HELVETICA,11));
                Chunk c22=new Chunk("\n");
                
                if(ts.toString().equals("")){
                    c20=new Chunk(tse,FontFactory.getFont(FontFactory.HELVETICA,11));
                }   
                else if(tse.toString().equals("")){
                    c21=new Chunk(ts,FontFactory.getFont(FontFactory.HELVETICA,11));
                }
                else{
                    c20=new Chunk(ts,FontFactory.getFont(FontFactory.HELVETICA,11));
                    c21=new Chunk(tse,FontFactory.getFont(FontFactory.HELVETICA,11));
                }
            
            pha2.add(c2);
            pha2.add(c2);
            pha2.add(c20);
            pha2.add(new Chunk(glue2));
            pha2.add(c21);
            pha2.add(c22);*/
            
            Iterator<Chunk>iteratoriAlbanianTOT=chunkTOTAlbanian.iterator();
            Iterator<Chunk>iteratoriEnglishTOT=chunkTOTEnglish.iterator();
                pha2.add(c2);
                for(int j=0;j<2;j++){
                    if(iteratoriAlbanianTOT.hasNext()||iteratoriEnglishTOT.hasNext()){
                        
                        if(iteratoriAlbanianTOT.hasNext()&&iteratoriEnglishTOT.hasNext()) {  
                            Chunk cha=iteratoriAlbanianTOT.next();
                            Chunk che=iteratoriEnglishTOT.next();
                            pha2.add(cha);
                            pha2.add(new Chunk(glue2));
                            pha2.add(che);
                            pha2.add(c2);
                        }
                        else if(!iteratoriAlbanianTOT.hasNext()&&iteratoriEnglishTOT.hasNext()) {
                            Chunk che=iteratoriEnglishTOT.next();
                            pha2.add("");
                            pha2.add(new Chunk(glue2));
                            pha2.add(che);
                            pha2.add(c2);
                        }
                        else if(iteratoriAlbanianTOT.hasNext()&&!iteratoriEnglishTOT.hasNext()) { 
                            Chunk cha=iteratoriAlbanianTOT.next();
                            pha2.add(cha);
                            pha2.add(new Chunk(glue2));
                            pha2.add("");
                            pha2.add(c2);
                        }
                    } 
                    else{
                        pha2.add("");
                        pha2.add(new Chunk(glue2));
                        pha2.add("");
                        pha2.add(c2);
                    }
                }
            
            para2.add(pha2);
            
            
            Chunk c30=new Chunk("Me këto module :",FontFactory.getFont(FontFactory.HELVETICA,12,Font.BOLD));
            Chunk c31=new Chunk("With the following modules :",FontFactory.getFont(FontFactory.HELVETICA,12,Font.BOLD));
            Chunk c32=new Chunk("\n");
            
            Chunk c33=new Chunk("të  pjesmarrjes pas ",FontFactory.getFont(FontFactory.HELVETICA,12,Font.BOLD));
            Chunk c34=new Chunk("Certicate of participation ",FontFactory.getFont(FontFactory.HELVETICA,12,Font.BOLD));
            Chunk c35=new Chunk("\n");
            
            pha3.add(c2);
            pha3.add(c2);
            pha3.add(c30);
            pha3.add(new Chunk(glue2));
            pha3.add(c31);
            pha3.add(c32);
            
            para3.add(pha3);
            
            Chunk c42=new Chunk("\n");
            
            pha4.add(c2);
            
            Iterator<Chunk>iteratoriAlbanian=chunkAlbanian.iterator();
            Iterator<Chunk>iteratoriEnglish=chunkEnglish.iterator();
            
                for(int j=0;j<12;j++){
                    if(iteratoriAlbanian.hasNext()||iteratoriEnglish.hasNext()){
                        
                        if(iteratoriAlbanian.hasNext()&&iteratoriEnglish.hasNext()) {  
                            Chunk cha=iteratoriAlbanian.next();
                            Chunk che=iteratoriEnglish.next();
                            pha4.add(cha);
                            pha4.add(new Chunk(glue2));
                            pha4.add(che);
                            pha4.add(c42);
                        }
                        else if(!iteratoriAlbanian.hasNext()&&iteratoriEnglish.hasNext()) {
                            Chunk che=iteratoriEnglish.next();
                            pha4.add("");
                            pha4.add(new Chunk(glue2));
                            pha4.add(che);
                            pha4.add(c42);
                        }
                        else if(iteratoriAlbanian.hasNext()&&!iteratoriEnglish.hasNext()) { 
                            Chunk cha=iteratoriAlbanian.next();
                            pha4.add(cha);
                            pha4.add(new Chunk(glue2));
                            pha4.add("");
                            pha4.add(c42);
                        }
                    } 
                    else{
                        pha4.add("");
                        pha4.add(new Chunk(glue2));
                        pha4.add("");
                        pha4.add(c42);
                    }
                }
            /*Chunk c40=new Chunk(tcs,FontFactory.getFont(FontFactory.HELVETICA,10));
            Chunk c41=new Chunk(tcs,FontFactory.getFont(FontFactory.HELVETICA,10));
            Chunk c42=new Chunk("\n");
            
            pha4.add(c2);
            pha4.add(c2);
            pha4.add(c2);
            pha4.add(c40);
            pha4.add(new Chunk(glue2));
            pha4.add(c41);
            pha4.add(c42);*/
            
            para4.add(pha4);
            
            
            Chunk c50=new Chunk("Drejtori Ekzekutiv :",FontFactory.getFont(FontFactory.HELVETICA,10));
            Chunk c51=new Chunk("Chief Executive Officer :",FontFactory.getFont(FontFactory.HELVETICA,10));
            Chunk c52=new Chunk("\n");
            
            Chunk c53=new Chunk("Administratori i trajnimeve :",FontFactory.getFont(FontFactory.HELVETICA,10));
            Chunk c54=new Chunk("Training registar :",FontFactory.getFont(FontFactory.HELVETICA,10));
            Chunk c55=new Chunk("\n");
            
            
            pha5.add(c2);
            pha5.add(c2);
            pha5.add(c50);
            pha5.add(new Chunk(glue2));
            pha5.add(c53);
            pha5.add(c52);
            pha5.add(c51);
            pha5.add(new Chunk(glue2));
            pha5.add(c54);
            pha5.add(c55);
            
            para5.add(pha5);
            
            
            Chunk c60=new Chunk("_____________________________");
            Chunk c61=new Chunk("_____________________________");
            Chunk c62=new Chunk("\n");
            
            
            pha6.add(c2);
            pha6.add(c2);
            pha6.add(c60);
            pha6.add(new Chunk(glue2));
            pha6.add(c61);
            pha6.add(c62);
            
            para6.add(pha6);
            
            
            Chunk c70=new Chunk("Data | Date : " + today,FontFactory.getFont(FontFactory.HELVETICA,10));
            Chunk c71=new Chunk(tpid+"   "+ idnum,FontFactory.getFont(FontFactory.HELVETICA,10));
            Chunk c72=new Chunk("\n");
            Chunk c73=new Chunk(chunk,FontFactory.getFont(FontFactory.HELVETICA,9));
            
            pha7.add(c2);
            pha7.add(c2);
            pha7.add(c73);
            pha7.add(new Chunk(glue2));
            pha7.add(c70);
            pha7.add(c72);
            pha7.add(new Chunk(glue2));
            pha7.add(c71);
            pha7.add(c72);
            
            para7.add(pha7);
            
            
            document.add(para1);
            document.add(para2);
            document.add(para3);
            document.add(para4);
            document.add(para5);
            document.add(para6);
            document.add(para7);
            
            
            
            document.close();
            JOptionPane.showMessageDialog(null,"U ruajt me sukses");
        }catch(Exception e){
            
            e.printStackTrace();
        }
    }
    
    public void shtypPDF2(String chunk,String directory){
        
       
        
        Document document=new Document();
        try{
            
            
            
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            int jFramewidth=this.getSize().width;
            int jFrameheight=this.getSize().height;
            int locationx=(dim.width-jFramewidth)/2;
            int locationy=(dim.height-jFrameheight)/2;
            
            List <TopicsCovered> topics;
            Training training;
           
            List <TopicsCovered> topicsAlb;
            List <TopicsCovered> topicsEng;
            String idnum=idnumtxtf.getText();
            String tpid=tpidtxtf.getText();
            String s1=emritxtf.getText();
            String s2=mbiemritxtf.getText(); 
            TrainingProcess trainp=trainingprocessir.findById(tpid);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            String today=(dateFormat.format(trainp.getEndDate()));
            List <Participant> participants=participantir.findByTpId(tpid);
            
            topics=topicscoveredir.findByTpId(tpidtxtf.getText());
            String [] topicscovered = new String[topics.size()];
            
            topicsAlb=topicscoveredir.findByTpLangId(tpidtxtf.getText().trim(),"Albanian");
            String tcs="";
            for(TopicsCovered tc :topicsAlb){
                tcs+=tc+"; ";
            }
            
            topicsEng=topicscoveredir.findByTpLangId(tpidtxtf.getText().trim(), "English");
            String tcseng="";
            for(TopicsCovered tc :topicsEng){
                tcseng+=tc+"; ";
            }
                
            ArrayList<Chunk> chunkAlbanian=new ArrayList<Chunk>();
            char[] temafullAlb=tcs.toCharArray();
            ArrayList<Chunk> chunkEnglish=new ArrayList<Chunk>();
            char[] temafullEng=tcseng.toCharArray();
            
           
            String rreshtiTemes="";
            String tempTcsAlb=tcs;
            String tempTcsEng=tcseng;
            
            /*ALBANIAN*/
            if(tempTcsAlb.length()<40){
                chunkAlbanian.add(new Chunk(tempTcsAlb,FontFactory.getFont(FontFactory.HELVETICA,9)));
            }
            while(tempTcsAlb.length()>=40){
                temafullAlb=tempTcsAlb.toCharArray();
                for(int i=40;i<tempTcsAlb.length();i++){
                    if(i>=40 && temafullAlb[i]==32){
                        rreshtiTemes=tempTcsAlb.substring(0,i);
                        tempTcsAlb=tempTcsAlb.substring(i);
                        chunkAlbanian.add(new Chunk(rreshtiTemes,FontFactory.getFont(FontFactory.HELVETICA,9)));
                        break;
                    }else if(tempTcsAlb.length()==i)
                    {
                        rreshtiTemes=tempTcsAlb.substring(0,i);
                        chunkAlbanian.add(new Chunk(rreshtiTemes,FontFactory.getFont(FontFactory.HELVETICA,9)));
                        tempTcsAlb=tempTcsAlb.substring(i);
                    }
                }
                
            }
            
            /*ENGLISH*/
            if(tempTcsEng.length()<40){
                chunkEnglish.add(new Chunk(tempTcsEng,FontFactory.getFont(FontFactory.HELVETICA,9)));
            }
            while(tempTcsEng.length()>40){
                temafullEng=tempTcsEng.toCharArray();
                for(int i=40;i<tempTcsEng.length();i++){
                    if(i>=40 && temafullEng[i]==32){
                        rreshtiTemes=tempTcsEng.substring(0,i);
                        tempTcsEng=tempTcsEng.substring(i);
                        chunkEnglish.add(new Chunk(rreshtiTemes,FontFactory.getFont(FontFactory.HELVETICA,9)));
                        break;
                    }else if(tempTcsEng.length()==i)
                    {
                        rreshtiTemes=tempTcsEng.substring(0,i);
                        chunkEnglish.add(new Chunk(rreshtiTemes,FontFactory.getFont(FontFactory.HELVETICA,9)));
                        tempTcsEng=tempTcsEng.substring(i);
                    }
                }
                
            }
            
            int maxTopic=topicsAlb.size();
            if(maxTopic<topicsEng.size()){
                maxTopic=topicsEng.size();
            }
            ArrayList<Chunk> chunka=new ArrayList<Chunk>();
            ArrayList<Chunk> chunke=new ArrayList<Chunk>();
            for (int i=0;i<maxTopic;i++){
                try{
                if(topicsAlb.get(i).getTopicCovered()!=null){
                    chunka.add(new Chunk(topicsAlb.get(i).getTopicCovered(),FontFactory.getFont(FontFactory.HELVETICA,9)));
                    
                }
                 else{
                chunka.add(new Chunk("",FontFactory.getFont(FontFactory.HELVETICA,9)));
                }}
                catch(ArrayIndexOutOfBoundsException e){
                      chunka.add(new Chunk("",FontFactory.getFont(FontFactory.HELVETICA,9)));
                }
                try{
                if(topicsEng.get(i).getTopicCovered()!=null){
                    chunke.add(new Chunk(topicsEng.get(i).getTopicCovered(),FontFactory.getFont(FontFactory.HELVETICA,9)));
                }
                else{
                chunke.add(new Chunk("",FontFactory.getFont(FontFactory.HELVETICA,9)));
             }
                }
                catch(ArrayIndexOutOfBoundsException e){
                    chunke.add(new Chunk("",FontFactory.getFont(FontFactory.HELVETICA,9)));
                }
            
            }
            
            training=trainingir.findByTpId(tpidtxtf.getText());
            
            String ts=training.getTitleOfTrainingAlbanian();
            
            ArrayList<Chunk> chunkTOTAlbanian=new ArrayList<Chunk>();
            char[] TOTfullAlb=ts.toCharArray();
            
            String rreshtiTitullit="";
            String tempTOTAlb=ts;
            
            /*ALBANIAN*/
            if(tempTOTAlb.length()<36){
                chunkTOTAlbanian.add(new Chunk(tempTOTAlb,FontFactory.getFont(FontFactory.HELVETICA,11)));
            }
            while(tempTOTAlb.length()>=36){
                TOTfullAlb=tempTOTAlb.toCharArray();
                for(int i=36;i<tempTOTAlb.length();i++){
                    if(i>=36 && TOTfullAlb[i]==32){
                        rreshtiTitullit=tempTOTAlb.substring(0,i);
                        tempTOTAlb=tempTOTAlb.substring(i);
                        chunkTOTAlbanian.add(new Chunk(rreshtiTitullit,FontFactory.getFont(FontFactory.HELVETICA,11)));
                        if(!tempTOTAlb.isEmpty() && tempTOTAlb.length()<36){
                            rreshtiTitullit=tempTOTAlb;
                            chunkTOTAlbanian.add(new Chunk(rreshtiTitullit,FontFactory.getFont(FontFactory.HELVETICA,11)));
                        }
                        break;
                    }else if(tempTOTAlb.length()==i+1)
                    {
                        rreshtiTitullit=tempTOTAlb.substring(0,i+1);
                        chunkTOTAlbanian.add(new Chunk(rreshtiTitullit,FontFactory.getFont(FontFactory.HELVETICA,11)));
                        tempTOTAlb=tempTOTAlb.substring(i+1);
                    }
                }
            }
            
            /*ENGLISH*/
            String tse=training.getTitleOfTrainingEnglish();
            ArrayList<Chunk> chunkTOTEnglish=new ArrayList<Chunk>();
            char[] TOTfullEng=tse.toCharArray();
            String tempTOTEng=tse;
            
            
            if(tempTOTEng.length()<36){
                chunkTOTEnglish.add(new Chunk(tempTOTEng,FontFactory.getFont(FontFactory.HELVETICA,11)));
            }
            while(tempTOTEng.length()>=36){
                TOTfullEng=tempTOTEng.toCharArray();
                for(int i=36;i<tempTOTEng.length();i++){
                    if(i>=36 && TOTfullEng[i]==32){
                        rreshtiTitullit=tempTOTEng.substring(0,i);
                        tempTOTEng=tempTOTEng.substring(i);
                        chunkTOTEnglish.add(new Chunk(rreshtiTitullit,FontFactory.getFont(FontFactory.HELVETICA,11)));
                        if(!tempTOTEng.isEmpty() && tempTOTEng.length()<36){
                            rreshtiTitullit=tempTOTEng;
                            chunkTOTEnglish.add(new Chunk(rreshtiTitullit,FontFactory.getFont(FontFactory.HELVETICA,11)));
                        }
                        break;
                    }else if(tempTOTEng.length()==i+1)
                    {
                        rreshtiTitullit=tempTOTEng.substring(0,i+1);
                        chunkTOTEnglish.add(new Chunk(rreshtiTemes,FontFactory.getFont(FontFactory.HELVETICA,11)));
                        tempTOTEng=tempTOTEng.substring(i+1);
                    }
                }
            }
            
            PdfWriter.getInstance(document, new FileOutputStream(directory+"PrintAll-"+tpidtxtf.getText()+"-ALB-ENG"+".pdf"));
                document.open();
            
            for(int i=0;i<participants.size();i++){
                
                Participant p1=participants.get(i);
                
                long idn=p1.getIDNumber();
                
                
                
                
                
                if(!pathi.equals("")){
                Image img = Image.getInstance(pathi);
                img.scaleToFit(120f, 80f);
                document.add(img);
                }
                

                Paragraph para=new Paragraph();
                Chunk t=new Chunk(p1.getName()+" "+p1.getSurname(),FontFactory.getFont(FontFactory.HELVETICA,18,Font.BOLD));
                Chunk space=new Chunk("\n");
                Phrase prat=new Phrase();
                if(pathi.equals("")){
                    prat.add(space);
                    prat.add(space);
                    prat.add(space);
                    prat.add(space);
                    prat.add(space);
                }
                prat.add(space);
                prat.add(space);
                prat.add(t);
                para.setAlignment(Element.ALIGN_CENTER);
                para.add(prat);

                document.add(para);



                Phrase pha1=new Phrase();
                Phrase pha2=new Phrase();
                Phrase pha3=new Phrase();
                Phrase pha4=new Phrase();
                Phrase pha5=new Phrase();
                Phrase pha6=new Phrase();
                Phrase pha7=new Phrase();
                Phrase pha8=new Phrase();

                Paragraph para1=new Paragraph();
                Paragraph para2=new Paragraph();
                Paragraph para3=new Paragraph();
                Paragraph para4=new Paragraph();
                Paragraph para5=new Paragraph();
                Paragraph para6=new Paragraph();
                Paragraph para7=new Paragraph();
                Paragraph para8=new Paragraph();

                Chunk glue2 = new Chunk(new VerticalPositionMark());


                Chunk c1=new Chunk("Shpërblehet me ceritifkatë ",FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
                Chunk c8=new Chunk("Has been awarded with ",FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
                Chunk c2=new Chunk("\n");

                Chunk c3=new Chunk("të  pjesmarrjes pas ",FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
                Chunk c10=new Chunk("Certicate of participation ",FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
                Chunk c4=new Chunk("\n");

                Chunk c5=new Chunk("përfundimit me sukses ",FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
                Chunk c12=new Chunk("upon having successfully",FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
                Chunk c6=new Chunk("\n");

                Chunk c7=new Chunk("të trajnimit : ",FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
                Chunk c14=new Chunk("completed training of : ",FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
                Chunk c9=new Chunk("\n");

                pha1.add(c2);
                pha1.add(c2);
                pha1.add(c2);
                pha1.add(c1);
                pha1.add(new Chunk(glue2));
                pha1.add(c8);
                pha1.add(c2);

                pha1.add(c3);
                pha1.add(new Chunk(glue2));
                pha1.add(c10);
                pha1.add(c4);

                pha1.add(c5);
                pha1.add(new Chunk(glue2));
                pha1.add(c12);
                pha1.add(c6);

                pha1.add(c7);
                pha1.add(new Chunk(glue2));
                pha1.add(c14);
                pha1.add(c9);


                para1.add(pha1);
                
                Iterator<Chunk>iteratoriAlbanianTOT=chunkTOTAlbanian.iterator();
                Iterator<Chunk>iteratoriEnglishTOT=chunkTOTEnglish.iterator();
                    pha2.add(c2);
                    for(int j=0;j<2;j++){
                        if(iteratoriAlbanianTOT.hasNext()||iteratoriEnglishTOT.hasNext()){

                            if(iteratoriAlbanianTOT.hasNext()&&iteratoriEnglishTOT.hasNext()) {  
                                Chunk cha=iteratoriAlbanianTOT.next();
                                Chunk che=iteratoriEnglishTOT.next();
                                pha2.add(cha);
                                pha2.add(new Chunk(glue2));
                                pha2.add(che);
                                pha2.add(c2);
                            }
                            else if(!iteratoriAlbanianTOT.hasNext()&&iteratoriEnglishTOT.hasNext()) {
                                Chunk che=iteratoriEnglishTOT.next();
                                pha2.add("");
                                pha2.add(new Chunk(glue2));
                                pha2.add(che);
                                pha2.add(c2);
                            }
                            else if(iteratoriAlbanianTOT.hasNext()&&!iteratoriEnglishTOT.hasNext()) { 
                                Chunk cha=iteratoriAlbanianTOT.next();
                                pha2.add(cha);
                                pha2.add(new Chunk(glue2));
                                pha2.add("");
                                pha2.add(c2);
                            }
                        } 
                        else{
                            pha2.add("");
                            pha2.add(new Chunk(glue2));
                            pha2.add("");
                            pha2.add(c2);
                        }
                    }

                para2.add(pha2);


                Chunk c30=new Chunk("Me këto module",FontFactory.getFont(FontFactory.HELVETICA,12,Font.BOLD));
                Chunk c31=new Chunk("With the following modules",FontFactory.getFont(FontFactory.HELVETICA,12,Font.BOLD));
                Chunk c32=new Chunk("\n");

                Chunk c33=new Chunk("të  pjesmarrjes pas ",FontFactory.getFont(FontFactory.HELVETICA,12,Font.BOLD));
                Chunk c34=new Chunk("Certicate of participation ",FontFactory.getFont(FontFactory.HELVETICA,12,Font.BOLD));
                Chunk c35=new Chunk("\n");

                
                pha3.add(c2);
                pha3.add(c2);
                pha3.add(c30);
                pha3.add(new Chunk(glue2));
                pha3.add(c31);
                pha3.add(c32);

                para3.add(pha3);

                Chunk c42=new Chunk("\n");

                
                pha4.add(c2);
                Iterator<Chunk>iteratoriAlbanian=chunkAlbanian.iterator();
                Iterator<Chunk>iteratoriEnglish=chunkEnglish.iterator();
            
                for(int j=0;j<12;j++){
                    if(iteratoriAlbanian.hasNext()||iteratoriEnglish.hasNext()){
                        
                        if(iteratoriAlbanian.hasNext()&&iteratoriEnglish.hasNext()) {  
                            Chunk cha=iteratoriAlbanian.next();
                            Chunk che=iteratoriEnglish.next();
                            pha4.add(cha);
                            pha4.add(new Chunk(glue2));
                            pha4.add(che);
                            pha4.add(c42);
                        }
                        else if(!iteratoriAlbanian.hasNext()&&iteratoriEnglish.hasNext()) {
                            Chunk che=iteratoriEnglish.next();
                            pha4.add("");
                            pha4.add(new Chunk(glue2));
                            pha4.add(che);
                            pha4.add(c42);
                        }
                        else if(iteratoriAlbanian.hasNext()&&!iteratoriEnglish.hasNext()) { 
                            Chunk cha=iteratoriAlbanian.next();
                            pha4.add(cha);
                            pha4.add(new Chunk(glue2));
                            pha4.add("");
                            pha4.add(c42);
                        }
                    } 
                    else{
                        pha4.add("");
                        pha4.add(new Chunk(glue2));
                        pha4.add("");
                        pha4.add(c42);
                    }
                }
                /*Chunk c40=new Chunk(tcs,FontFactory.getFont(FontFactory.HELVETICA,10));
                Chunk c41=new Chunk(tcs,FontFactory.getFont(FontFactory.HELVETICA,10));
                Chunk c42=new Chunk("\n");

                pha4.add(c2);
                pha4.add(c2);
                pha4.add(c2);
                pha4.add(c40);
                pha4.add(new Chunk(glue2));
                pha4.add(c41);
                pha4.add(c42);*/

                para4.add(pha4);


                Chunk c50=new Chunk("Drejtori Ekzekutiv :",FontFactory.getFont(FontFactory.HELVETICA,10));
                Chunk c51=new Chunk("Chief Executive Officer :",FontFactory.getFont(FontFactory.HELVETICA,10));
                Chunk c52=new Chunk("\n");

                Chunk c53=new Chunk("Administratori i trajnimeve :",FontFactory.getFont(FontFactory.HELVETICA,10));
                Chunk c54=new Chunk("Training registar :",FontFactory.getFont(FontFactory.HELVETICA,10));
                Chunk c55=new Chunk("\n");

                pha5.add(c2);
                pha5.add(c2);
                pha5.add(c2);
                pha5.add(c50);
                pha5.add(new Chunk(glue2));
                pha5.add(c53);
                pha5.add(c52);
                pha5.add(c51);
                pha5.add(new Chunk(glue2));
                pha5.add(c54);
                pha5.add(c55);

                para5.add(pha5);


                Chunk c60=new Chunk("_____________________________");
                Chunk c61=new Chunk("_____________________________");
                Chunk c62=new Chunk("\n");


                pha6.add(c2);
                pha6.add(c2);
                pha6.add(c60);
                pha6.add(new Chunk(glue2));
                pha6.add(c61);
                pha6.add(c62);

                para6.add(pha6);


                Chunk c70=new Chunk("Data | Date : " + today,FontFactory.getFont(FontFactory.HELVETICA,10));
                Chunk c71=new Chunk(tpid+"   "+ idn,FontFactory.getFont(FontFactory.HELVETICA,10));
                Chunk c72=new Chunk("\n");
                Chunk c73=new Chunk(chunk,FontFactory.getFont(FontFactory.HELVETICA,9));


                pha7.add(c2);
                pha7.add(c2);
                pha7.add(c73);
                pha7.add(new Chunk(glue2));
                pha7.add(c70);
                pha7.add(c72);
                pha7.add(new Chunk(glue2));
                pha7.add(c71);
                pha7.add(c72);

                para7.add(pha7);


                document.add(para1);
                document.add(para2);
                document.add(para3);
                document.add(para4);
                document.add(para5);
                document.add(para6);
                document.add(para7);
                
                

                document.newPage();
              
            }    
            document.close();
            JOptionPane.showMessageDialog(null,"U ruajt me sukses");
        }catch(Exception e){
            
            e.printStackTrace();
        }
    }
    
    public void shtypPDFSRB(String chunk,String directory){
        
       
        
        Document document=new Document();
        try{
            
            
            
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            int jFramewidth=this.getSize().width;
            int jFrameheight=this.getSize().height;
            int locationx=(dim.width-jFramewidth)/2;
            int locationy=(dim.height-jFrameheight)/2;
            
            List <TopicsCovered> topicsAlb;
            List <TopicsCovered> topicsEng;
            Training training;
            String idnum=idnumtxtf.getText();
            String tpid=tpidtxtf.getText();
            String s1=emritxtf.getText();
            String s2=mbiemritxtf.getText(); 
            TrainingProcess trainp=trainingprocessir.findById(tpid);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            String today=(dateFormat.format(trainp.getEndDate()));
            
            
            topicsAlb=topicscoveredir.findByTpLangId(tpidtxtf.getText().trim(),"Serbian");
            String tcs="";
            for(TopicsCovered tc :topicsAlb){
                tcs+=tc+"; ";
            }
            
            topicsEng=topicscoveredir.findByTpLangId(tpidtxtf.getText().trim(), "English");
            String tcseng="";
            for(TopicsCovered tc :topicsEng){
                tcseng+=tc+"; ";
            }
            
            ArrayList<Chunk> chunkAlbanian=new ArrayList<Chunk>();
            char[] temafullAlb=tcs.toCharArray();
            ArrayList<Chunk> chunkEnglish=new ArrayList<Chunk>();
            char[] temafullEng=tcseng.toCharArray();
            
           
            String rreshtiTemes="";
            String tempTcsAlb=tcs;
            String tempTcsEng=tcseng;
            
            /*ALBANIAN*/
            if(tempTcsAlb.length()<40){
                chunkAlbanian.add(new Chunk(tempTcsAlb,FontFactory.getFont(FontFactory.HELVETICA,9)));
            }
            while(tempTcsAlb.length()>=40){
                temafullAlb=tempTcsAlb.toCharArray();
                for(int i=40;i<tempTcsAlb.length();i++){
                    if(i>=40 && temafullAlb[i]==32){
                        rreshtiTemes=tempTcsAlb.substring(0,i);
                        tempTcsAlb=tempTcsAlb.substring(i);
                        chunkAlbanian.add(new Chunk(rreshtiTemes,FontFactory.getFont(FontFactory.HELVETICA,9)));
                        break;
                    }else if(tempTcsAlb.length()==i)
                    {
                        rreshtiTemes=tempTcsAlb.substring(0,i);
                        chunkAlbanian.add(new Chunk(rreshtiTemes,FontFactory.getFont(FontFactory.HELVETICA,9)));
                        tempTcsAlb=tempTcsAlb.substring(i);
                    }
                }
                
            }
            
            /*ENGLISH*/
            if(tempTcsEng.length()<40){
                chunkEnglish.add(new Chunk(tempTcsEng,FontFactory.getFont(FontFactory.HELVETICA,9)));
            }
            while(tempTcsEng.length()>40){
                temafullEng=tempTcsEng.toCharArray();
                for(int i=40;i<tempTcsEng.length();i++){
                    if(i>=40 && temafullEng[i]==32){
                        rreshtiTemes=tempTcsEng.substring(0,i);
                        tempTcsEng=tempTcsEng.substring(i);
                        chunkEnglish.add(new Chunk(rreshtiTemes,FontFactory.getFont(FontFactory.HELVETICA,9)));
                        break;
                    }else if(tempTcsEng.length()==i)
                    {
                        rreshtiTemes=tempTcsEng.substring(0,i);
                        chunkEnglish.add(new Chunk(rreshtiTemes,FontFactory.getFont(FontFactory.HELVETICA,9)));
                        tempTcsEng=tempTcsEng.substring(i);
                    }
                }
                
            }
            
            int maxTopic=topicsAlb.size();
            if(maxTopic<topicsEng.size()){
                maxTopic=topicsEng.size();
            }
            ArrayList<Chunk> chunka=new ArrayList<Chunk>();
            ArrayList<Chunk> chunke=new ArrayList<Chunk>();
            for (int i=0;i<maxTopic;i++){
                try{
                if(topicsAlb.get(i).getTopicCovered()!=null){
                    chunka.add(new Chunk(topicsAlb.get(i).getTopicCovered(),FontFactory.getFont(FontFactory.HELVETICA,9)));
                    
                }
                 else{
                chunka.add(new Chunk("",FontFactory.getFont(FontFactory.HELVETICA,9)));
                }}
                catch(ArrayIndexOutOfBoundsException e){
                      chunka.add(new Chunk("",FontFactory.getFont(FontFactory.HELVETICA,9)));
                }
                try{
                if(topicsEng.get(i).getTopicCovered()!=null){
                    chunke.add(new Chunk(topicsEng.get(i).getTopicCovered(),FontFactory.getFont(FontFactory.HELVETICA,9)));
                }
                else{
                chunke.add(new Chunk("",FontFactory.getFont(FontFactory.HELVETICA,9)));
             }
                }
                catch(ArrayIndexOutOfBoundsException e){
                    chunke.add(new Chunk("",FontFactory.getFont(FontFactory.HELVETICA,9)));
                }
            
            }
            
            training=trainingir.findByTpId(tpidtxtf.getText());
            
            String ts=training.getTitleOfTrainingSerbian();
            
            ArrayList<Chunk> chunkTOTAlbanian=new ArrayList<Chunk>();
            char[] TOTfullAlb=ts.toCharArray();
            
            String rreshtiTitullit="";
            String tempTOTAlb=ts;
            
            /*ALBANIAN*/
            if(tempTOTAlb.length()<36){
                chunkTOTAlbanian.add(new Chunk(tempTOTAlb,FontFactory.getFont(FontFactory.HELVETICA,11)));
            }
            while(tempTOTAlb.length()>=36){
                TOTfullAlb=tempTOTAlb.toCharArray();
                for(int i=36;i<tempTOTAlb.length();i++){
                    if(i>=36 && TOTfullAlb[i]==32){
                        rreshtiTitullit=tempTOTAlb.substring(0,i);
                        tempTOTAlb=tempTOTAlb.substring(i);
                        chunkTOTAlbanian.add(new Chunk(rreshtiTitullit,FontFactory.getFont(FontFactory.HELVETICA,11)));
                        if(!tempTOTAlb.isEmpty() && tempTOTAlb.length()<36){
                            rreshtiTitullit=tempTOTAlb;
                            chunkTOTAlbanian.add(new Chunk(rreshtiTitullit,FontFactory.getFont(FontFactory.HELVETICA,11)));
                        }
                        break;
                    }else if(tempTOTAlb.length()==i+1)
                    {
                        rreshtiTitullit=tempTOTAlb.substring(0,i+1);
                        chunkTOTAlbanian.add(new Chunk(rreshtiTitullit,FontFactory.getFont(FontFactory.HELVETICA,11)));
                        tempTOTAlb=tempTOTAlb.substring(i+1);
                    }
                }
            }
            
            /*ENGLISH*/
            String tse=training.getTitleOfTrainingEnglish();
            ArrayList<Chunk> chunkTOTEnglish=new ArrayList<Chunk>();
            char[] TOTfullEng=tse.toCharArray();
            String tempTOTEng=tse;
            
            
            if(tempTOTEng.length()<36){
                chunkTOTEnglish.add(new Chunk(tempTOTEng,FontFactory.getFont(FontFactory.HELVETICA,11)));
            }
            while(tempTOTEng.length()>=36){
                TOTfullEng=tempTOTEng.toCharArray();
                for(int i=36;i<tempTOTEng.length();i++){
                    if(i>=36 && TOTfullEng[i]==32){
                        rreshtiTitullit=tempTOTEng.substring(0,i);
                        tempTOTEng=tempTOTEng.substring(i);
                        chunkTOTEnglish.add(new Chunk(rreshtiTitullit,FontFactory.getFont(FontFactory.HELVETICA,11)));
                        if(!tempTOTEng.isEmpty() && tempTOTEng.length()<36){
                            rreshtiTitullit=tempTOTEng;
                            chunkTOTEnglish.add(new Chunk(rreshtiTitullit,FontFactory.getFont(FontFactory.HELVETICA,11)));
                        }
                        break;
                    }else if(tempTOTEng.length()==i+1)
                    {
                        rreshtiTitullit=tempTOTEng.substring(0,i+1);
                        chunkTOTEnglish.add(new Chunk(rreshtiTemes,FontFactory.getFont(FontFactory.HELVETICA,11)));
                        tempTOTEng=tempTOTEng.substring(i+1);
                    }
                }
            }
            
            
            PdfWriter.getInstance(document, new FileOutputStream(directory+s1+s2+" - "+idnum+"-"+tpid+"-SRB-ENG"+".pdf"));
            document.open();
           
            if(!pathi.equals("")){
            Image img = Image.getInstance(pathi);
            img.scaleToFit(120f, 80f);
            document.add(img);
            }
            
            Paragraph para=new Paragraph();
            Chunk t=new Chunk(s1+" "+s2,FontFactory.getFont(FontFactory.HELVETICA,18,Font.BOLD));
            Chunk space=new Chunk("\n");
            Phrase prat=new Phrase();
            if(pathi.equals("")){
                prat.add(space);
                prat.add(space);
                prat.add(space);
                prat.add(space);
                prat.add(space);
            }
            prat.add(space);
            prat.add(space);
            prat.add(t);
            para.setAlignment(Element.ALIGN_CENTER);
            para.add(prat);
           
            document.add(para);
            
            
            
            Phrase pha1=new Phrase();
            Phrase pha2=new Phrase();
            Phrase pha3=new Phrase();
            Phrase pha4=new Phrase();
            Phrase pha5=new Phrase();
            Phrase pha6=new Phrase();
            Phrase pha7=new Phrase();
            Phrase pha8=new Phrase();
            
            Paragraph para1=new Paragraph();
            Paragraph para2=new Paragraph();
            Paragraph para3=new Paragraph();
            Paragraph para4=new Paragraph();
            Paragraph para5=new Paragraph();
            Paragraph para6=new Paragraph();
            Paragraph para7=new Paragraph();
            Paragraph para8=new Paragraph();
            
            Chunk glue2 = new Chunk(new VerticalPositionMark());
            
            
            Chunk c1=new Chunk("Nagradjuje se sa ",FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
            Chunk c8=new Chunk("Has been awarded with ",FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
            Chunk c2=new Chunk("\n");
            
            Chunk c3=new Chunk("Potvrdom-Certifikatom ",FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
            Chunk c10=new Chunk("Certicate of participation ",FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
            Chunk c4=new Chunk("\n");
            
            Chunk c5=new Chunk("nakon  učešća i uspešnog ",FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
            Chunk c12=new Chunk("upon having successfully",FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
            Chunk c6=new Chunk("\n");
            
            Chunk c7=new Chunk("završetka obuke: ",FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
            Chunk c14=new Chunk("completed training of : ",FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
            Chunk c9=new Chunk("\n");
            
            pha1.add(c2);
            pha1.add(c2);
            pha1.add(c2);
            pha1.add(c1);
            pha1.add(new Chunk(glue2));
            pha1.add(c8);
            pha1.add(c2);
            
            pha1.add(c3);
            pha1.add(new Chunk(glue2));
            pha1.add(c10);
            pha1.add(c4);
            
            pha1.add(c5);
            pha1.add(new Chunk(glue2));
            pha1.add(c12);
            pha1.add(c6);
            
            pha1.add(c7);
            pha1.add(new Chunk(glue2));
            pha1.add(c14);
            pha1.add(c9);
            
            
            para1.add(pha1);
            
            Iterator<Chunk>iteratoriAlbanianTOT=chunkTOTAlbanian.iterator();
                Iterator<Chunk>iteratoriEnglishTOT=chunkTOTEnglish.iterator();
                    pha2.add(c2);
                    for(int j=0;j<2;j++){
                        if(iteratoriAlbanianTOT.hasNext()||iteratoriEnglishTOT.hasNext()){

                            if(iteratoriAlbanianTOT.hasNext()&&iteratoriEnglishTOT.hasNext()) {  
                                Chunk cha=iteratoriAlbanianTOT.next();
                                Chunk che=iteratoriEnglishTOT.next();
                                pha2.add(cha);
                                pha2.add(new Chunk(glue2));
                                pha2.add(che);
                                pha2.add(c2);
                            }
                            else if(!iteratoriAlbanianTOT.hasNext()&&iteratoriEnglishTOT.hasNext()) {
                                Chunk che=iteratoriEnglishTOT.next();
                                pha2.add("");
                                pha2.add(new Chunk(glue2));
                                pha2.add(che);
                                pha2.add(c2);
                            }
                            else if(iteratoriAlbanianTOT.hasNext()&&!iteratoriEnglishTOT.hasNext()) { 
                                Chunk cha=iteratoriAlbanianTOT.next();
                                pha2.add(cha);
                                pha2.add(new Chunk(glue2));
                                pha2.add("");
                                pha2.add(c2);
                            }
                        } 
                        else{
                            pha2.add("");
                            pha2.add(new Chunk(glue2));
                            pha2.add("");
                            pha2.add(c2);
                        }
                    }

            
            
            para2.add(pha2);
            
            
            Chunk c30=new Chunk("Sa ovim modulima",FontFactory.getFont(FontFactory.HELVETICA,12,Font.BOLD));
            Chunk c31=new Chunk("With the following modules",FontFactory.getFont(FontFactory.HELVETICA,12,Font.BOLD));
            Chunk c32=new Chunk("\n");
            
            
            pha3.add(c2);
            pha3.add(c2);
            pha3.add(c30);
            pha3.add(new Chunk(glue2));
            pha3.add(c31);
            pha3.add(c32);
            
            para3.add(pha3);
            
            Chunk c42=new Chunk("\n");
            
            
            pha4.add(c2);
                Iterator<Chunk>iteratoriAlbanian=chunkAlbanian.iterator();
                Iterator<Chunk>iteratoriEnglish=chunkEnglish.iterator();
            
                for(int j=0;j<12;j++){
                    if(iteratoriAlbanian.hasNext()||iteratoriEnglish.hasNext()){
                        
                        if(iteratoriAlbanian.hasNext()&&iteratoriEnglish.hasNext()) {  
                            Chunk cha=iteratoriAlbanian.next();
                            Chunk che=iteratoriEnglish.next();
                            pha4.add(cha);
                            pha4.add(new Chunk(glue2));
                            pha4.add(che);
                            pha4.add(c42);
                        }
                        else if(!iteratoriAlbanian.hasNext()&&iteratoriEnglish.hasNext()) {
                            Chunk che=iteratoriEnglish.next();
                            pha4.add("");
                            pha4.add(new Chunk(glue2));
                            pha4.add(che);
                            pha4.add(c42);
                        }
                        else if(iteratoriAlbanian.hasNext()&&!iteratoriEnglish.hasNext()) { 
                            Chunk cha=iteratoriAlbanian.next();
                            pha4.add(cha);
                            pha4.add(new Chunk(glue2));
                            pha4.add("");
                            pha4.add(c42);
                        }
                    } 
                    else{
                        pha4.add("");
                        pha4.add(new Chunk(glue2));
                        pha4.add("");
                        pha4.add(c42);
                    }
                }
            /*Chunk c40=new Chunk(tcs,FontFactory.getFont(FontFactory.HELVETICA,10));
            Chunk c41=new Chunk(tcs,FontFactory.getFont(FontFactory.HELVETICA,10));
            Chunk c42=new Chunk("\n");
            
            pha4.add(c2);
            pha4.add(c2);
            pha4.add(c2);
            pha4.add(c40);
            pha4.add(new Chunk(glue2));
            pha4.add(c41);
            pha4.add(c42);*/
            
            para4.add(pha4);
            
            
            Chunk c50=new Chunk("Izvršni Direktor :",FontFactory.getFont(FontFactory.HELVETICA,10));
            Chunk c51=new Chunk("Chief Executive Officer :",FontFactory.getFont(FontFactory.HELVETICA,10));
            Chunk c52=new Chunk("\n");
            
            Chunk c53=new Chunk("Administrator obuke :",FontFactory.getFont(FontFactory.HELVETICA,10));
            Chunk c54=new Chunk("Training registar :",FontFactory.getFont(FontFactory.HELVETICA,10));
            Chunk c55=new Chunk("\n");
            
            pha5.add(c2);
            pha5.add(c2);
            pha5.add(c2);
            pha5.add(c50);
            pha5.add(new Chunk(glue2));
            pha5.add(c53);
            pha5.add(c52);
            pha5.add(c51);
            pha5.add(new Chunk(glue2));
            pha5.add(c54);
            pha5.add(c55);
            
            para5.add(pha5);
            
            
            Chunk c60=new Chunk("_____________________________");
            Chunk c61=new Chunk("_____________________________");
            Chunk c62=new Chunk("\n");
            
            
            pha6.add(c2);
            pha6.add(c2);
            pha6.add(c60);
            pha6.add(new Chunk(glue2));
            pha6.add(c61);
            pha6.add(c62);
            
            para6.add(pha6);
            
            
            Chunk c70=new Chunk("Datum | Date : " + today,FontFactory.getFont(FontFactory.HELVETICA,10));
            Chunk c71=new Chunk(tpid+"   "+ idnum,FontFactory.getFont(FontFactory.HELVETICA,10));
            Chunk c72=new Chunk("\n");
            Chunk c73=new Chunk(chunk,FontFactory.getFont(FontFactory.HELVETICA,9));
            
            
            pha7.add(c2);
            pha7.add(c2);
            pha7.add(c73);
            pha7.add(new Chunk(glue2));
            pha7.add(c70);
            pha7.add(c72);
            pha7.add(new Chunk(glue2));
            pha7.add(c71);
            pha7.add(c72);
            
            para7.add(pha7);
            
            
            document.add(para1);
            document.add(para2);
            document.add(para3);
            document.add(para4);
            document.add(para5);
            document.add(para6);
            document.add(para7);
            
            
            
            document.close();
            JOptionPane.showMessageDialog(null,"U ruajt me sukses");
        }catch(Exception e){
            
            e.printStackTrace();
        }
    }
    
    public void shtypPDF2SRB(String chunk,String directory){
        
       
        
        Document document=new Document();
        try{
            
            
            
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            int jFramewidth=this.getSize().width;
            int jFrameheight=this.getSize().height;
            int locationx=(dim.width-jFramewidth)/2;
            int locationy=(dim.height-jFrameheight)/2;
            
            List <TopicsCovered> topics;
            Training training;
           
            List <TopicsCovered> topicsAlb;
            List <TopicsCovered> topicsEng;
            String idnum=idnumtxtf.getText();
            String tpid=tpidtxtf.getText();
            String s1=emritxtf.getText();
            String s2=mbiemritxtf.getText(); 
            TrainingProcess trainp=trainingprocessir.findById(tpid);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            String today=(dateFormat.format(trainp.getEndDate()));
            List <Participant> participants=participantir.findByTpId(tpid);
            
            topics=topicscoveredir.findByTpId(tpidtxtf.getText());
            String [] topicscovered = new String[topics.size()];
            
            topicsAlb=topicscoveredir.findByTpLangId(tpidtxtf.getText().trim(),"Serbian");
            String tcs="";
            for(TopicsCovered tc :topicsAlb){
                tcs+=tc+"; ";
            }
            
            topicsEng=topicscoveredir.findByTpLangId(tpidtxtf.getText().trim(), "English");
            String tcseng="";
            for(TopicsCovered tc :topicsEng){
                tcseng+=tc+"; ";
            }
            
            ArrayList<Chunk> chunkAlbanian=new ArrayList<Chunk>();
            char[] temafullAlb=tcs.toCharArray();
            ArrayList<Chunk> chunkEnglish=new ArrayList<Chunk>();
            char[] temafullEng=tcseng.toCharArray();
            
           
            String rreshtiTemes="";
            String tempTcsAlb=tcs;
            String tempTcsEng=tcseng;
            
            /*ALBANIAN*/
            if(tempTcsAlb.length()<40){
                chunkAlbanian.add(new Chunk(tempTcsAlb,FontFactory.getFont(FontFactory.HELVETICA,9)));
            }
            while(tempTcsAlb.length()>=40){
                temafullAlb=tempTcsAlb.toCharArray();
                for(int i=40;i<tempTcsAlb.length();i++){
                    if(i>=40 && temafullAlb[i]==32){
                        rreshtiTemes=tempTcsAlb.substring(0,i);
                        tempTcsAlb=tempTcsAlb.substring(i);
                        chunkAlbanian.add(new Chunk(rreshtiTemes,FontFactory.getFont(FontFactory.HELVETICA,9)));
                        break;
                    }else if(tempTcsAlb.length()==i)
                    {
                        rreshtiTemes=tempTcsAlb.substring(0,i);
                        chunkAlbanian.add(new Chunk(rreshtiTemes,FontFactory.getFont(FontFactory.HELVETICA,9)));
                        tempTcsAlb=tempTcsAlb.substring(i);
                    }
                }
                
            }
            
            /*ENGLISH*/
            if(tempTcsEng.length()<40){
                chunkEnglish.add(new Chunk(tempTcsEng,FontFactory.getFont(FontFactory.HELVETICA,9)));
            }
            while(tempTcsEng.length()>40){
                temafullEng=tempTcsEng.toCharArray();
                for(int i=40;i<tempTcsEng.length();i++){
                    if(i>=40 && temafullEng[i]==32){
                        rreshtiTemes=tempTcsEng.substring(0,i);
                        tempTcsEng=tempTcsEng.substring(i);
                        chunkEnglish.add(new Chunk(rreshtiTemes,FontFactory.getFont(FontFactory.HELVETICA,9)));
                        break;
                    }else if(tempTcsEng.length()==i)
                    {
                        rreshtiTemes=tempTcsEng.substring(0,i);
                        chunkEnglish.add(new Chunk(rreshtiTemes,FontFactory.getFont(FontFactory.HELVETICA,9)));
                        tempTcsEng=tempTcsEng.substring(i);
                    }
                }
                
            }
            
            int maxTopic=topicsAlb.size();
            if(maxTopic<topicsEng.size()){
                maxTopic=topicsEng.size();
            }
            ArrayList<Chunk> chunka=new ArrayList<Chunk>();
            ArrayList<Chunk> chunke=new ArrayList<Chunk>();
            for (int i=0;i<maxTopic;i++){
                try{
                if(topicsAlb.get(i).getTopicCovered()!=null){
                    chunka.add(new Chunk(topicsAlb.get(i).getTopicCovered(),FontFactory.getFont(FontFactory.HELVETICA,9)));
                    
                }
                 else{
                chunka.add(new Chunk("",FontFactory.getFont(FontFactory.HELVETICA,9)));
                }}
                catch(ArrayIndexOutOfBoundsException e){
                      chunka.add(new Chunk("",FontFactory.getFont(FontFactory.HELVETICA,9)));
                }
                try{
                if(topicsEng.get(i).getTopicCovered()!=null){
                    chunke.add(new Chunk(topicsEng.get(i).getTopicCovered(),FontFactory.getFont(FontFactory.HELVETICA,9)));
                }
                else{
                chunke.add(new Chunk("",FontFactory.getFont(FontFactory.HELVETICA,9)));
             }
                }
                catch(ArrayIndexOutOfBoundsException e){
                    chunke.add(new Chunk("",FontFactory.getFont(FontFactory.HELVETICA,9)));
                }
            
            }
            
            training=trainingir.findByTpId(tpidtxtf.getText());
            
            String ts=training.getTitleOfTrainingSerbian();
            
            ArrayList<Chunk> chunkTOTAlbanian=new ArrayList<Chunk>();
            char[] TOTfullAlb=ts.toCharArray();
            
            String rreshtiTitullit="";
            String tempTOTAlb=ts;
            
            /*ALBANIAN*/
            if(tempTOTAlb.length()<36){
                chunkTOTAlbanian.add(new Chunk(tempTOTAlb,FontFactory.getFont(FontFactory.HELVETICA,11)));
            }
            while(tempTOTAlb.length()>=36){
                TOTfullAlb=tempTOTAlb.toCharArray();
                for(int i=36;i<tempTOTAlb.length();i++){
                    if(i>=36 && TOTfullAlb[i]==32){
                        rreshtiTitullit=tempTOTAlb.substring(0,i);
                        tempTOTAlb=tempTOTAlb.substring(i);
                        chunkTOTAlbanian.add(new Chunk(rreshtiTitullit,FontFactory.getFont(FontFactory.HELVETICA,11)));
                        if(!tempTOTAlb.isEmpty() && tempTOTAlb.length()<36){
                            rreshtiTitullit=tempTOTAlb;
                            chunkTOTAlbanian.add(new Chunk(rreshtiTitullit,FontFactory.getFont(FontFactory.HELVETICA,11)));
                        }
                        break;
                    }else if(tempTOTAlb.length()==i+1)
                    {
                        rreshtiTitullit=tempTOTAlb.substring(0,i+1);
                        chunkTOTAlbanian.add(new Chunk(rreshtiTitullit,FontFactory.getFont(FontFactory.HELVETICA,11)));
                        tempTOTAlb=tempTOTAlb.substring(i+1);
                    }
                }
            }
            
            /*ENGLISH*/
            String tse=training.getTitleOfTrainingEnglish();
            ArrayList<Chunk> chunkTOTEnglish=new ArrayList<Chunk>();
            char[] TOTfullEng=tse.toCharArray();
            String tempTOTEng=tse;
            
            
            if(tempTOTEng.length()<36){
                chunkTOTEnglish.add(new Chunk(tempTOTEng,FontFactory.getFont(FontFactory.HELVETICA,11)));
            }
            while(tempTOTEng.length()>=36){
                TOTfullEng=tempTOTEng.toCharArray();
                for(int i=36;i<tempTOTEng.length();i++){
                    if(i>=36 && TOTfullEng[i]==32){
                        rreshtiTitullit=tempTOTEng.substring(0,i);
                        tempTOTEng=tempTOTEng.substring(i);
                        chunkTOTEnglish.add(new Chunk(rreshtiTitullit,FontFactory.getFont(FontFactory.HELVETICA,11)));
                        if(!tempTOTEng.isEmpty() && tempTOTEng.length()<36){
                            rreshtiTitullit=tempTOTEng;
                            chunkTOTEnglish.add(new Chunk(rreshtiTitullit,FontFactory.getFont(FontFactory.HELVETICA,11)));
                        }
                        break;
                    }else if(tempTOTEng.length()==i+1)
                    {
                        rreshtiTitullit=tempTOTEng.substring(0,i+1);
                        chunkTOTEnglish.add(new Chunk(rreshtiTemes,FontFactory.getFont(FontFactory.HELVETICA,11)));
                        tempTOTEng=tempTOTEng.substring(i+1);
                    }
                }
            }
            
            PdfWriter.getInstance(document, new FileOutputStream(directory+"PrintAll-"+tpidtxtf.getText()+"-SRB-ENG"+".pdf"));
                document.open();
            
            for(int i=0;i<participants.size();i++){
                
                Participant p1=participants.get(i);
                
                long idn=p1.getIDNumber();
                
                
                
                
                
                if(!pathi.equals("")){
                Image img = Image.getInstance(pathi);
                img.scaleToFit(120f, 80f);
                document.add(img);
                }

                Paragraph para=new Paragraph();
                Chunk t=new Chunk(p1.getName()+" "+p1.getSurname(),FontFactory.getFont(FontFactory.HELVETICA,18,Font.BOLD));
                Chunk space=new Chunk("\n");
                Phrase prat=new Phrase();
                if(pathi.equals("")){
                    prat.add(space);
                    prat.add(space);
                    prat.add(space);
                    prat.add(space);
                    prat.add(space);
                }
                prat.add(space);
                prat.add(space);
                prat.add(t);
                para.setAlignment(Element.ALIGN_CENTER);
                para.add(prat);

                document.add(para);



                Phrase pha1=new Phrase();
                Phrase pha2=new Phrase();
                Phrase pha3=new Phrase();
                Phrase pha4=new Phrase();
                Phrase pha5=new Phrase();
                Phrase pha6=new Phrase();
                Phrase pha7=new Phrase();
                Phrase pha8=new Phrase();

                Paragraph para1=new Paragraph();
                Paragraph para2=new Paragraph();
                Paragraph para3=new Paragraph();
                Paragraph para4=new Paragraph();
                Paragraph para5=new Paragraph();
                Paragraph para6=new Paragraph();
                Paragraph para7=new Paragraph();
                Paragraph para8=new Paragraph();

                Chunk glue2 = new Chunk(new VerticalPositionMark());


                Chunk c1=new Chunk("Nagradjuje se sa  ",FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
                Chunk c8=new Chunk("Has been awarded with ",FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
                Chunk c2=new Chunk("\n");

                Chunk c3=new Chunk("Potvrdom-Certifikatom ",FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
                Chunk c10=new Chunk("Certicate of participation ",FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
                Chunk c4=new Chunk("\n");

                Chunk c5=new Chunk("nakon  učešća i uspešnog ",FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
                Chunk c12=new Chunk("upon having successfully",FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
                Chunk c6=new Chunk("\n");

                Chunk c7=new Chunk("završetka obuke:",FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
                Chunk c14=new Chunk("completed training of : ",FontFactory.getFont(FontFactory.HELVETICA,11,Font.BOLD));
                Chunk c9=new Chunk("\n");

                pha1.add(c2);
                pha1.add(c2);
                pha1.add(c2);
                pha1.add(c1);
                pha1.add(new Chunk(glue2));
                pha1.add(c8);
                pha1.add(c2);

                pha1.add(c3);
                pha1.add(new Chunk(glue2));
                pha1.add(c10);
                pha1.add(c4);

                pha1.add(c5);
                pha1.add(new Chunk(glue2));
                pha1.add(c12);
                pha1.add(c6);

                pha1.add(c7);
                pha1.add(new Chunk(glue2));
                pha1.add(c14);
                pha1.add(c9);


                para1.add(pha1);
                
                Iterator<Chunk>iteratoriAlbanianTOT=chunkTOTAlbanian.iterator();
                Iterator<Chunk>iteratoriEnglishTOT=chunkTOTEnglish.iterator();
                    pha2.add(c2);
                    for(int j=0;j<2;j++){
                        if(iteratoriAlbanianTOT.hasNext()||iteratoriEnglishTOT.hasNext()){

                            if(iteratoriAlbanianTOT.hasNext()&&iteratoriEnglishTOT.hasNext()) {  
                                Chunk cha=iteratoriAlbanianTOT.next();
                                Chunk che=iteratoriEnglishTOT.next();
                                pha2.add(cha);
                                pha2.add(new Chunk(glue2));
                                pha2.add(che);
                                pha2.add(c2);
                            }
                            else if(!iteratoriAlbanianTOT.hasNext()&&iteratoriEnglishTOT.hasNext()) {
                                Chunk che=iteratoriEnglishTOT.next();
                                pha2.add("");
                                pha2.add(new Chunk(glue2));
                                pha2.add(che);
                                pha2.add(c2);
                            }
                            else if(iteratoriAlbanianTOT.hasNext()&&!iteratoriEnglishTOT.hasNext()) { 
                                Chunk cha=iteratoriAlbanianTOT.next();
                                pha2.add(cha);
                                pha2.add(new Chunk(glue2));
                                pha2.add("");
                                pha2.add(c2);
                            }
                        } 
                        else{
                            pha2.add("");
                            pha2.add(new Chunk(glue2));
                            pha2.add("");
                            pha2.add(c2);
                        }
                    }

                para2.add(pha2);


                Chunk c30=new Chunk("Sa ovim modulima ",FontFactory.getFont(FontFactory.HELVETICA,12,Font.BOLD));
                Chunk c31=new Chunk("With the following modules",FontFactory.getFont(FontFactory.HELVETICA,12,Font.BOLD));
                Chunk c32=new Chunk("\n");


                pha3.add(c2);
                pha3.add(c2);
                pha3.add(c30);
                pha3.add(new Chunk(glue2));
                pha3.add(c31);
                pha3.add(c32);

                para3.add(pha3);

                Chunk c42=new Chunk("\n");

                
                pha4.add(c2);
                Iterator<Chunk>iteratoriAlbanian=chunkAlbanian.iterator();
                Iterator<Chunk>iteratoriEnglish=chunkEnglish.iterator();
            
                for(int j=0;j<12;j++){
                    if(iteratoriAlbanian.hasNext()||iteratoriEnglish.hasNext()){
                        
                        if(iteratoriAlbanian.hasNext()&&iteratoriEnglish.hasNext()) {  
                            Chunk cha=iteratoriAlbanian.next();
                            Chunk che=iteratoriEnglish.next();
                            pha4.add(cha);
                            pha4.add(new Chunk(glue2));
                            pha4.add(che);
                            pha4.add(c42);
                        }
                        else if(!iteratoriAlbanian.hasNext()&&iteratoriEnglish.hasNext()) {
                            Chunk che=iteratoriEnglish.next();
                            pha4.add("");
                            pha4.add(new Chunk(glue2));
                            pha4.add(che);
                            pha4.add(c42);
                        }
                        else if(iteratoriAlbanian.hasNext()&&!iteratoriEnglish.hasNext()) { 
                            Chunk cha=iteratoriAlbanian.next();
                            pha4.add(cha);
                            pha4.add(new Chunk(glue2));
                            pha4.add("");
                            pha4.add(c42);
                        }
                    } 
                    else{
                        pha4.add("");
                        pha4.add(new Chunk(glue2));
                        pha4.add("");
                        pha4.add(c42);
                    }
                }
                /*Chunk c40=new Chunk(tcs,FontFactory.getFont(FontFactory.HELVETICA,10));
                Chunk c41=new Chunk(tcs,FontFactory.getFont(FontFactory.HELVETICA,10));
                Chunk c42=new Chunk("\n");

                pha4.add(c2);
                pha4.add(c2);
                pha4.add(c2);
                pha4.add(c40);
                pha4.add(new Chunk(glue2));
                pha4.add(c41);
                pha4.add(c42);*/

                para4.add(pha4);


                Chunk c50=new Chunk("Izvršni Direktor :",FontFactory.getFont(FontFactory.HELVETICA,10));
                Chunk c51=new Chunk("Chief Executive Officer :",FontFactory.getFont(FontFactory.HELVETICA,10));
                Chunk c52=new Chunk("\n");

                Chunk c53=new Chunk("Administrator obuke :",FontFactory.getFont(FontFactory.HELVETICA,10));
                Chunk c54=new Chunk("Training registar :",FontFactory.getFont(FontFactory.HELVETICA,10));
                Chunk c55=new Chunk("\n");

                pha5.add(c2);
                pha5.add(c2);
                pha5.add(c2);
                pha5.add(c50);
                pha5.add(new Chunk(glue2));
                pha5.add(c53);
                pha5.add(c52);
                pha5.add(c51);
                pha5.add(new Chunk(glue2));
                pha5.add(c54);
                pha5.add(c55);

                para5.add(pha5);


                Chunk c60=new Chunk("_____________________________");
                Chunk c61=new Chunk("_____________________________");
                Chunk c62=new Chunk("\n");


                pha6.add(c2);
                pha6.add(c2);
                pha6.add(c60);
                pha6.add(new Chunk(glue2));
                pha6.add(c61);
                pha6.add(c62);

                para6.add(pha6);


                Chunk c70=new Chunk("Datum | Date : " + today,FontFactory.getFont(FontFactory.HELVETICA,10));
                Chunk c71=new Chunk(tpid+"   "+ idn,FontFactory.getFont(FontFactory.HELVETICA,10));
                Chunk c72=new Chunk("\n");
                Chunk c73=new Chunk(chunk ,FontFactory.getFont(FontFactory.HELVETICA,9));


                pha7.add(c2);
                pha7.add(c2);
                pha7.add(c73);
                pha7.add(new Chunk(glue2));
                pha7.add(c70);
                pha7.add(c72);
                pha7.add(new Chunk(glue2));
                pha7.add(c71);
                pha7.add(c72);

                para7.add(pha7);


                document.add(para1);
                document.add(para2);
                document.add(para3);
                document.add(para4);
                document.add(para5);
                document.add(para6);
                document.add(para7);
                
                

                document.newPage();
              
            }    
            document.close();
            
            JOptionPane.showMessageDialog(null,"U ruajt me sukses");
        }catch(Exception e){
            
            e.printStackTrace();
        }
    }
    
    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        
        
        JFileChooser chooser = new JFileChooser(); 
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Chooser");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        String directory="";
        //
        // disable the "All files" option.
        //
        chooser.setAcceptAllFileFilterUsed(false);
        //    
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
          directory=chooser.getSelectedFile().toString()+"\\";
          
            if(langcombo.getSelectedItem().toString().equals("ALB-ENG")){
              if(tbl.getModel()==ptm&&ptm.getRowCount()>=1&&!emritxtf.getText().trim().equals("")&&!idnumtxtf.getText().trim().equals("")&&!mbiemritxtf.getText().trim().equals("")){
                 try{ 
                  String chunk="";
                  if(extratxtf.getText().trim().toCharArray().length>95){
                      throw new AppException("Numri maksimal i karaktereve të lejuara është 95");
                  }
                  else if(!extratxtf.getText().trim().equals("")&&extratxtf.getText().trim().toCharArray().length<=95){
                      chunk=extratxtf.getText().trim();
                  }

                  shtypPDF(chunk,directory);
                  pathi="";
                  try {
                      shtoPrintLog();
                  } catch (AppException ex) {
                      Logger.getLogger(Print.class.getName()).log(Level.SEVERE, null, ex);
                  }
                  label.setIcon(null);
                 }catch(AppException ae){
                     JOptionPane.showMessageDialog(null, "Numri maksimal i karaktereve të lejuara është 95");
                 }
              }
              else if(tbl.getModel()==tptmm){
                  JOptionPane.showMessageDialog(null, "Zgjidh njerin nga trajnimet");
              }
              else if(tbl.getModel()==ptm&&ptm.getRowCount()<1){
                  JOptionPane.showMessageDialog(null, "Nuk ka pjesmarrës në këtë trajnim");
              }   
              else if(tbl.getModel()==ptm&&ptm.getRowCount()>=1&&emritxtf.getText().trim().equals("")&&idnumtxtf.getText().trim().equals("")&&mbiemritxtf.getText().trim().equals("")){
                  JOptionPane.showMessageDialog(null, "Zgjidh njerin nga pjesmarresit");
              }
         }
         else if(langcombo.getSelectedItem().toString().equals("SRB-ENG")){
             if(tbl.getModel()==ptm&&ptm.getRowCount()>=1&&!emritxtf.getText().trim().equals("")&&!idnumtxtf.getText().trim().equals("")&&!mbiemritxtf.getText().trim().equals("")){
                 try{
                 String chunk="";
                 if(extratxtf.getText().trim().toCharArray().length>95){
                     throw new AppException("Numri maksimal i karaktereve të lejuara është 95");
                 }
                 else if(!extratxtf.getText().trim().equals("")&&extratxtf.getText().trim().toCharArray().length<=95){
                     chunk=extratxtf.getText().trim();
                 }
                 shtypPDFSRB(chunk,directory);
                 pathi="";
                 try {
                     shtoPrintLog();
                 } catch (AppException ex) {
                     Logger.getLogger(Print.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 label.setIcon(null);
                 }catch(AppException ae){
                     JOptionPane.showMessageDialog(null, "Numri maksimal i karaktereve të lejuara është 95");
                  }
              }
              else if(tbl.getModel()==tptmm){
                  JOptionPane.showMessageDialog(null, "Zgjidh njerin nga trajnimet");
              }
              else if(tbl.getModel()==ptm&&ptm.getRowCount()<1){
                  JOptionPane.showMessageDialog(null, "Nuk ka pjesmarrës në këtë trajnim");
              }   
              else if(tbl.getModel()==ptm&&ptm.getRowCount()>=1&&emritxtf.getText().trim().equals("")&&idnumtxtf.getText().trim().equals("")&&mbiemritxtf.getText().trim().equals("")){
                  JOptionPane.showMessageDialog(null, "Zgjidh njerin nga pjesmarresit");
              } 

         }
          
        }
        else {
          JOptionPane.showMessageDialog(null, "Duhet te zgjedhni lokacionin se ku deshironi te ruani file ");
          }
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        if(tbl.getModel()==ptm){
           idnumtxtf.setText("");
           emritxtf.setText("");
           mbiemritxtf.setText("");
           tpidtxtf.setText("");
            tpmTabelaLoad();
           
       }
    }//GEN-LAST:event_jLabel1MouseClicked

    private void ikonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ikonaActionPerformed
       gjejfoton();
    }//GEN-LAST:event_ikonaActionPerformed

    private void printallbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printallbtnActionPerformed
        JFileChooser chooser = new JFileChooser(); 
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Chooser");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        String directory="";
        //
        // disable the "All files" option.
        //
        chooser.setAcceptAllFileFilterUsed(false);
        //    
        
        if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
            
            directory=chooser.getSelectedFile().toString()+"\\";
            
            if(langcombo.getSelectedItem().toString().equals("ALB-ENG")){
            
            
            if(tbl.getModel()==ptm&&ptm.getRowCount()<1){
                JOptionPane.showMessageDialog(null, "Nuk ka pjesmarrës në këtë trajnim");
            }
            else if((tbl.getModel()==tptmm||tbl.getModel()==ptm)&&!tpidtxtf.getText().trim().equals("")){
                try{
                String chunk="";
                if(extratxtf.getText().trim().toCharArray().length>95){
                    throw new AppException("Numri maksimal i karaktereve të lejuara është 95");
                }
                else if(!extratxtf.getText().trim().equals("")&&extratxtf.getText().trim().toCharArray().length<=95){
                    chunk=extratxtf.getText().trim();
                }
                shtypPDF2(chunk,directory);
                pathi="";
                label.setIcon(null);
                }catch(AppException ae){
                   JOptionPane.showMessageDialog(null, "Numri maksimal i karaktereve të lejuara është 95");
                }
                try {
                    shtoPrintLog();
                } catch (AppException ex) {
                    Logger.getLogger(Print.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Zgjidh njerin nga trajnimet");
            } 
        
       }
       else if(langcombo.getSelectedItem().toString().equals("SRB-ENG")){
           if(tbl.getModel()==ptm&&ptm.getRowCount()<1){
                JOptionPane.showMessageDialog(null, "Nuk ka pjesmarrës në këtë trajnim");
            }
            else if((tbl.getModel()==tptmm||tbl.getModel()==ptm)&&!tpidtxtf.getText().trim().equals("")){
                try{
                String chunk="";
                if(extratxtf.getText().trim().toCharArray().length>95){
                    throw new AppException("Numri maksimal i karaktereve të lejuara është 95");
                }
                else if(!extratxtf.getText().trim().equals("")&&extratxtf.getText().trim().toCharArray().length<=95){
                    chunk=extratxtf.getText().trim();
                }
                shtypPDF2SRB(chunk,directory);
                pathi="";
                label.setIcon(null);
                }catch(AppException ae){
                   JOptionPane.showMessageDialog(null, "Numri maksimal i karaktereve të lejuara është 95");
                }
               try {
                   shtoPrintLog();
               } catch (AppException ex) {
                   Logger.getLogger(Print.class.getName()).log(Level.SEVERE, null, ex);
               }
            }
           
            else{
                JOptionPane.showMessageDialog(null, "Zgjidh njerin nga trajnimet");
            } 
           
       }
        }
        else{
            JOptionPane.showMessageDialog(null, "Duhet te zgjedhni lokacionin se ku deshironi te ruani file ");
        }
        
        
    }//GEN-LAST:event_printallbtnActionPerformed
    
    
    private String findPath(File selectedFile){
        String path=selectedFile.getAbsolutePath();
        return path;
    }
   
    private void gjejfoton(){
        JFileChooser file = new JFileChooser();
          file.setCurrentDirectory(new File(System.getProperty("user.home")));
          //filter the files
          FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png");
          file.addChoosableFileFilter(filter);
          int result = file.showSaveDialog(null);
          String path="";
           //if the user click on save in Jfilechooser
          if(result == JFileChooser.APPROVE_OPTION){
              File selectedFile = file.getSelectedFile();
              path = findPath(selectedFile);
              pathi=path;
              label.setIcon(ResizeImage(path));
              
          }
           //if the user click on save in Jfilechooser


          else if(result == JFileChooser.CANCEL_OPTION){
              System.out.println("No File Select");
          }
          
                 
    }
    
    
     public ImageIcon ResizeImage(String ImagePath)
            {
                ImageIcon MyImage = new ImageIcon(ImagePath);
                java.awt.Image img = MyImage.getImage();
                java.awt.Image newImg = img.getScaledInstance(label.getWidth(), label.getHeight(), java.awt.Image.SCALE_SMOOTH);
                ImageIcon image = new ImageIcon(newImg);
                return image;
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
        java.awt.Image img=null;
        java.awt.Image img2=null;
        java.awt.Image img3=null;
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
        
        
        
        
        
        jLabel5.setCursor(cursor);
        jScrollPane1.setCursor(cursor);
        jLabel1.setCursor(cursor);
        jLabel2.setCursor(cursor);
        jLabel3.setCursor(cursor);
        label.setCursor(cursor);
        this.setCursor(cursor);
        
        
        jLabel1.setCursor(buttoncursor);
        jLabel2.setCursor(buttoncursor);
        ikona.setCursor(buttoncursor);
        printallbtn.setCursor(buttoncursor);
        
        
        
        tpidtxtf.setCursor(textcursor);
        idnumtxtf.setCursor(textcursor);
        emritxtf.setCursor(textcursor);
        mbiemritxtf.setCursor(textcursor);
        extratxtf.setCursor(textcursor);
        
        
        
    }
     
     public void shtoPrintLog() throws AppException{
        
        
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
        
        String Mesazhi=useri.getName()+" "+useri.getSurname()+" me username-in: "+useri.getUsername()+" Ka printuar në datën : "+today +" në ora "+koha;
        
        l.setUseri(useri.getName()+" "+useri.getSurname()+" - "+useri.getUsername());
        l.setKoha(time);
        l.setDita(date);
        l.setMesazhi(Mesazhi);
        l.setLloji("Print");
        l.setUsername(us);
        
        logsir.create(l);
    }
     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField emritxtf;
    private javax.swing.JTextField extratxtf;
    private javax.swing.JTextField idnumtxtf;
    private javax.swing.JButton ikona;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label;
    private javax.swing.JComboBox<String> langcombo;
    private javax.swing.JTextField mbiemritxtf;
    private javax.swing.JButton printallbtn;
    private javax.swing.JTable tbl;
    private javax.swing.JTextField tpidtxtf;
    // End of variables declaration//GEN-END:variables

    private List<Participant> dataManager(List<Participant> data) {
        List<Participant> newLista= new ArrayList<Participant>();
        for (Participant tempPart : data){
            if(!newLista.contains(tempPart))
                newLista.add(tempPart);
        }
        return newLista;
    }
}
