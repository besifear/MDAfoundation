package gui.view;

import PersonalJComponents.UnfocusableRenderer;
import Validation.PersonalNumberManager;
import Validation.TrainingCodeManager;
import static Validation.Validation.TextFieldIntException;
import bl.AppException;
import bl.CTPcomboInterface;
import bl.CTPcomboRepository;
import bl.CompanyInterface;
import bl.CompanyRepository;
import bl.LogsInterface;
import bl.LogsRepository;
import bl.ParticipantInterface;
import bl.ParticipantRepository;

import bl.TrainingProcessInterface;
import bl.TrainingProcessRepository;
import bl.TrainerInterface;
import bl.TrainerRepository;
import bl.ParticipatingCompanyMemberInterface;
import bl.ParticipatingCompanyMemberRepository;
import bl.TrainerEvaluationInterface;
import bl.TrainerEvaluationRepository;
import bl.ParticipantTeamInterface;
import bl.ParticipantTeamRepository;
import bl.StopException;
import bl.TeamInterface;
import bl.TeamRepository;
import bl.TrainerTeamComboInterface;
import bl.TrainerTeamComboRepository;
import bl.UserInterface;
import bl.UserRepository;
import com.mxrck.autocompleter.TextAutoCompleter;
import com.toedter.calendar.JDateChooser;
import ejb.CTPcombo;
import ejb.Company;
import ejb.Logs;
import ejb.Participant;

import ejb.TrainingProcess;
import ejb.Trainer;
import ejb.ParticipatingCompanyMember;
import ejb.ParticipantTeam;
import ejb.Team;
import ejb.TTrainerEvaluation;
import ejb.TrainersTeam;
import ejb.TrainerTeamCombo;
import ejb.Users;
import gui.model.CompanyTableModel;
import gui.model.ParticipantTableModel;

import gui.model.TrainingProcessTableModel;
import gui.model.TrainerTableModel;
import gui.model.ParticipantTeamTableModel;
import gui.model.ParticipatingCompanyMemberTableModel;
import gui.model.TeamTableModel;
import gui.model.TrainerEvaluationTableModel;
import gui.model.ParticipantTableModelMini;
import gui.model.TrainerEvaluationTableModelMini;
import gui.model.TrainingProcessTableModelMini;
import static gui.view.MergeCompany.initializeMergeCompany;
import static gui.view.MergeParticipant.initializeMergeParticipant;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.file.Paths;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.CellEditorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;




public class BigForm extends javax.swing.JInternalFrame {
ParticipantInterface participantir;
LogsInterface logsRep;
TrainingProcessInterface trainingpir;
TrainerEvaluationInterface trainerevir;
TrainerInterface trainerir;
ParticipatingCompanyMemberInterface participatingcompanymemberir;
TeamInterface teamir;
ParticipantTeamInterface participantteamir;
CompanyInterface companyir;
CTPcomboInterface ctpcir;
TrainerTeamComboInterface trainerteamcomboir;

TrainerEvaluationTableModelMini trainerEtmm;
TrainingProcessTableModelMini trainingptmm;
ParticipantTableModelMini participanttmm;
UserInterface userir;
LogsInterface logsir;

ArrayList<TTrainerEvaluation> trainerev=new ArrayList();
Company c;
TrainingProcess tp;
Participant pt;
EntityManager em;
Users useri;
TrainingCodeManager tcm;
PersonalNumberManager pnm;



 String absolutepath=Paths.get(".").toAbsolutePath().normalize().toString();
    ArrayList<String> pathfoldernames;
    
    public BigForm(Users useri,EntityManager emm) {
        em=emm;
        this.useri=useri;
        logsRep =new LogsRepository(em);
        participantir=new ParticipantRepository(em);
        trainingpir=new TrainingProcessRepository(em);
        trainerir=new TrainerRepository(em);
        trainerevir=new TrainerEvaluationRepository(em);
        participatingcompanymemberir=new ParticipatingCompanyMemberRepository(em);
        teamir=new TeamRepository(em);
        participantteamir=new ParticipantTeamRepository(em);
        companyir=new CompanyRepository(em);
        ctpcir=new CTPcomboRepository(em);
        trainerteamcomboir=new TrainerTeamComboRepository(em);
        userir=new UserRepository(em);
        logsir=new LogsRepository(em);

        trainerEtmm = new TrainerEvaluationTableModelMini();
        trainingptmm = new TrainingProcessTableModelMini();
        participanttmm = new ParticipantTableModelMini();
        this.setLocation(15,147);
        for(MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().getMouseListeners()){
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().removeMouseListener(listener);
        }
        initComponents();
        

        participantTabelaLoad();
        trainingprocessTabelaLoad();
        addFolderNames();
        CustomCursor();
        loadDropDown();
        ProcessClickEvent();
        ParticipantClickEvent();
        
        trainingprocesstbl.putClientProperty("terminateEditOnFocusLost", true);
        
        
        ParticipantFormListeners();
        
        
        tcm=new TrainingCodeManager(trainingcodetxtf);
        pnm=new PersonalNumberManager(idnumbertxtf);
        
        participantTabelaLoad();
        trainingprocessTabelaLoad();
        CustomCursor();
        loadDropDown();
    }
    
    private void emptyObject() {
        nametxtf.setText("");
        surnametxtf.setText("");
        sexcombo.setSelectedItem("M");
        addresstxtf.setText("");
        citytxtf.setText("");
        statetxtf.setText("");
        phonetxtf.setText("");
        emailtxtf.setText("");
        nametxtf.requestFocus();
        participanttbl.clearSelection();
        /*trainertbl.clearSelection();*/
        trainingprocesstbl.clearSelection();
        trainingcodetxtf.setText("");
        companytxtf.setText("");
        idnumbertxtf.setText("");
        emailtxtf.setText("");
        ziptxtf.setText("");
        pozitatxtf.setText("");
        sexcombo.setSelectedItem("null");
        birthDate.setCalendar(null);
        nametxtf.requestFocus();
    }
    
    private void participantTabelaLoad(){
    List<Participant> list=participantir.findAllActive();
    participanttmm.add(list);
    participanttbl.setModel(participanttmm);
    participanttmm.fireTableDataChanged();
    }
    
    private void trainerEvTabelaLoad(List<TTrainerEvaluation> list){
        trainerEtmm.add(list);
        trainingprocesstbl.setModel(trainerEtmm);
        trainerEtmm.fireTableDataChanged();
        for(int j=2;j<5;j++){
            TableColumn evalationColumn = trainingprocesstbl.getColumnModel().getColumn(j);
            JComboBox comboBox = new JComboBox();
            JComboBoxListener(comboBox);
            for(int i=1;i<6;i++){
                comboBox.addItem(i);
            }
            evalationColumn.setCellEditor(new DefaultCellEditor(comboBox));
            trainerEtmm.fireTableDataChanged();
            for(int i=0;i<2;i++){
            TableColumn tc = trainingprocesstbl.getColumnModel().getColumn(i);
            tc.setCellRenderer(new UnfocusableRenderer());
            }
        }
    }
    
    private void trainingprocessTabelaLoad(){
    List<TrainingProcess> list=trainingpir.findAllActive();
    trainingptmm.add(list);
    trainingprocesstbl.setModel(trainingptmm);
    trainingptmm.fireTableDataChanged();
    }
    
    private void ProcessClickEvent(){
        trainingprocesstbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(trainingprocesstbl.getModel()==trainingptmm){
                    if(e.getClickCount()==1){
                        if(trainingprocesstbl.getSelectedRow()!=-1){
                            JTable target = (JTable)e.getSource();
                            int row = target.getSelectedRow();
                            int column = target.getSelectedColumn();
                            tp=trainingptmm.getTrainingProcess(row);
                            trainingcodetxtf.setText(tp.getTProcessID());
                        }
                    }
                }
            }
        });
    }
    
    private void ParticipantClickEvent(){
        participanttbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==1){
                    if(participanttbl.getSelectedRow()!=-1){
                        JTable target = (JTable)e.getSource();
                        int row = target.getSelectedRow();
                        int column = target.getSelectedColumn();
                        pt=participanttmm.getParticipant(row);
                        idnumbertxtf.setText(Long.toString(pt.getIDNumber()));
                        nametxtf.setText(pt.getName());
                        surnametxtf.setText(pt.getSurname());
                        sexcombo.setSelectedItem(pt.getSex());
                        birthDate.setDate(pt.getDob());
                        phonetxtf.setText(pt.getPhone());
                        emailtxtf.setText(pt.getEmail());
                        statetxtf.setText(pt.getParticipantState());
                        citytxtf.setText(pt.getCity());
                        addresstxtf.setText(pt.getParticipantAddress());
                        ziptxtf.setText(pt.getZipCode());
                        pozitatxtf.setText(pt.getPozita());
                    }
                }
            }
        });
    }
    
    
    
    /*
    private void participantTabelaMoveKey(){
    ListSelectionModel rowSM=participanttbl.getSelectionModel();
    rowSM.addListSelectionListener (new ListSelectionListener(){
    @Override
    public void valueChanged (ListSelectionEvent e){
    if(e.getValueIsAdjusting())
        return;
    ListSelectionModel rowSM = (ListSelectionModel)e.getSource();
    int selectedIndex = rowSM.getMinSelectionIndex();
    if(selectedIndex>-1){
        Participant p=participanttm.getParticipant(selectedIndex);
                    nametxtf.setText(p.getName()+"");
                    surnametxtf.setText(p.getSurname()+(""));
                    addresstxtf.setText(p.getParticipantAddress());
                    sexcombo.setSelectedItem(p.getSex());
                    citytxtf.setText(p.getCity());
                    statetxtf.setText(p.getParticipantState());
                    ziptxtf.setText(p.getZipCode()+(""));
                    idnumbertxtf.setText(p.getIDNumber()+(""));
                    participanttxtf.setText(p.getParticipantID()+(""));
    }
    }
    });
    }
    */

     /*private void trainerTabelaMoveKey(){
    ListSelectionModel rowSM=trainertbl.getSelectionModel();
    rowSM.addListSelectionListener (new ListSelectionListener(){
    @Override
    public void valueChanged (ListSelectionEvent e){
    if(e.getValueIsAdjusting())
        return;
    ListSelectionModel rowSM = (ListSelectionModel)e.getSource();
    int selectedIndex = rowSM.getMinSelectionIndex();
    if(selectedIndex>-1){
        Trainer t=trainertm.getTrainer(selectedIndex);
        trainercombo.setSelectedItem(t.getTrainerID()+"");
        trainercombo.addItem(t.getTrainerID()+"");
        trainercombo.setSelectedItem(t.getTrainerID()+"");
        
    }
    }
    });
    }*/
  
      
      private void createTrainerEvaluation(ParticipatingCompanyMember pcm,TrainingProcess tp) throws AppException{
          
          Iterator<TTrainerEvaluation> iteratori =trainerEtmm.getData().iterator();
          TTrainerEvaluation te;
          while (iteratori.hasNext()){
                te=iteratori.next();
                te.setPcmId(pcm);
                te.setTProcessID(tp);
                try{
                    te=trainerevir.findByEverything(te);
                    if (!te.getStatusi().equals("Active")){
                        te.setStatusi("Active");
                        trainerevir.edit(te);
                    }
                }catch (NoResultException nre){
                     trainerevir.create(te);
                }
          }
      }
     
      private ParticipantTeam createTeam(TrainingProcess tp,ParticipatingCompanyMember pcm) throws AppException{
        Team newTeam;
        if(tp.getTeamID()==null){
            newTeam=new Team();
            teamir.create(newTeam);
            tp.setTeamID(newTeam);
        }else
            newTeam=tp.getTeamID();
            
        List<ParticipantTeam> participantTeamList =participantteamir.findByTeamId(newTeam.getTeamID());
        ParticipantTeam newPT=new ParticipantTeam();
        newPT.setPcmId(pcm);
        newPT.setTeamID(newTeam);
        if(!participantTeamList.contains(newPT)){
            participantteamir.create(newPT);
            participantTeamList.add(newPT);
        }
        return newPT;
      }
      
      private Company createCompany() throws AppException{
       
       Company cc=new Company();
       cc.setName(companytxtf.getText().trim());
       cc.setCompanytype(typeofcompanycombo.getSelectedItem().toString());
       try{
           cc=companyir.findByEverything(cc);
           if (!cc.getStatusi().equals("Active")){
               cc.setStatusi("Active");
               companyir.edit(cc);
           }
       }catch(NoResultException nre){
          cc=companyir.create(cc);
       }
        return cc;
       }
      
      private CTPcombo createCTPcombo(Company c,TrainingProcess tp) throws AppException{
          TrainingProcess tptemp=new TrainingProcess();
          
          CTPcombo ctpc=new CTPcombo();
          ctpc.setCompanyID(c);
          ctpc.setTProcessID(tp);
          try{
          ctpc=ctpcir.findByEverything(ctpc);
          if (!ctpc.getStatusi().equals("Active")){
            ctpc.setStatusi("Active");
            ctpcir.edit(ctpc);
          }
          tptemp=trainingpir.findById(ctpc.getTProcessID().getTProcessID());
          if(!tptemp.getStatusi().equals("Active")){
              tptemp.setStatusi("Active");
              trainingpir.edit(tptemp);
          }
          return ctpc;
          }catch(NoResultException nre){
              return ctpcir.create(ctpc);
          }
      }
      
      
       private ParticipatingCompanyMember createParticipatingCompanyMember(Participant p,Company c) throws AppException{
           ParticipatingCompanyMember pcm=new ParticipatingCompanyMember();
           pcm.setParticipantID(p);
           pcm.setCompanyID(c);
           try{
           pcm=participatingcompanymemberir.findByEverything(pcm);
            if(!pcm.getStatusi().equals("Active")){
                pcm.setStatusi("Active");
                participatingcompanymemberir.edit(pcm);
            }
            return pcm;
           }catch(NoResultException nre){
           return participatingcompanymemberir.create(pcm);
           }
       }
       
       
      
      public Participant createParticipant() throws AppException, StopException{
      
            Participant p= new Participant();
            
            p.setName(nametxtf.getText().trim());
            p.setSurname(surnametxtf.getText().trim());
            p.setParticipantAddress(addresstxtf.getText().trim());
            p.setPozita(pozitatxtf.getText().trim());
            p.setCity(citytxtf.getText().trim());
            p.setParticipantState(statetxtf.getText().trim());
            p.setSex(sexcombo.getSelectedItem()+(""));
            p.setIDNumber(Long.parseLong(idnumbertxtf.getText().trim()));
            p.setZipCode(ziptxtf.getText().trim());
            p.setEmail(emailtxtf.getText().trim());
            p.setPhone(phonetxtf.getText().trim());
            p.setDob(birthDate.getDate());
            try{
                //Kerkon se a ekziston ky pjesmarres me qeto attribute qe jon shtu  nqs jo 
                p=participantir.findByEverything(p);
                if (!p.getStatusi().equals("Active")){
                    p.setStatusi("Active");
                    participantir.edit(p);
                }
                return p;
            }catch (NoResultException nre){
                // Kthen pjesmarresin se si eshte regjistru ndatabaze edhe si po tentohet me u rujt
                try{
                Participant oldParticipant,newParticipant;
                oldParticipant=participantir.findByPersonalID(p.getIDNumber());
                newParticipant=p;
                MergeParticipant mp=initializeMergeParticipant(oldParticipant,newParticipant,em,this);
                mp.setVisible(true);
                throw new StopException("Na konvenon me e nal qetu regjistrimin deri sa të rregullohët pjesëmarrësi");
                }catch (NoResultException nre2){
                    return participantir.create(p);
                }
            }
      }
      
      
      
      public void setParticipantTxtf(Participant p){
          idnumbertxtf.setText(Long.toString(p.getIDNumber()));
          nametxtf.setText(p.getName());
          surnametxtf.setText(p.getSurname());
          sexcombo.setSelectedItem(p.getSex());
          birthDate.setDate(p.getDob());
          phonetxtf.setText(p.getPhone());
          emailtxtf.setText(p.getEmail());
          statetxtf.setText(p.getParticipantState());
          citytxtf.setText(p.getCity());
          addresstxtf.setText(p.getParticipantAddress());
          ziptxtf.setText(p.getZipCode());
          pozitatxtf.setText(p.getPozita());
      }
      
      public void setCompanyValues(Company c){
          companytxtf.setText(c.getName());
          typeofcompanycombo.setSelectedItem(c.getCompanytype());
      }
      
      
      
      
      
      
      
      
      
      
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        instructionLbl = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        trainingprocesstbl = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        participanttbl = new javax.swing.JTable();
        typeofcompanycombo = new javax.swing.JComboBox<>();
        companytxtf = new javax.swing.JTextField();
        pozitatxtf = new javax.swing.JTextField();
        shtobtn = new javax.swing.JButton();
        fshijbtn = new javax.swing.JButton();
        pastrobtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        sexcombo = new javax.swing.JComboBox<>();
        surnametxtf = new javax.swing.JTextField();
        nametxtf = new javax.swing.JTextField();
        idnumbertxtf = new javax.swing.JTextField();
        trainingcodetxtf = new javax.swing.JTextField();
        birthDate = new com.toedter.calendar.JDateChooser();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        ziptxtf = new javax.swing.JTextField();
        addresstxtf = new javax.swing.JTextField();
        citytxtf = new javax.swing.JTextField();
        statetxtf = new javax.swing.JTextField();
        emailtxtf = new javax.swing.JTextField();
        phonetxtf = new javax.swing.JTextField();
        background = new javax.swing.JLabel();

        setClosable(true);
        setTitle("SHTO PJESMARRËS");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(null);

        instructionLbl.setForeground(new java.awt.Color(255, 0, 0));
        jPanel1.add(instructionLbl);
        instructionLbl.setBounds(684, 254, 520, 20);

        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        trainingprocesstbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TrainingProcessID", "Vendi", "Data e fillimit", "Data e mbarimit"
            }
        ));
        trainingprocesstbl.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        trainingprocesstbl.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        trainingprocesstbl.setShowHorizontalLines(false);
        trainingprocesstbl.setShowVerticalLines(false);
        jScrollPane2.setViewportView(trainingprocesstbl);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(682, 279, 590, 190);

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        participanttbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Participant ID", "Emri", "Mbiemri", "Gjinia", "Data e Lindjes"
            }
        ));
        participanttbl.setShowHorizontalLines(false);
        participanttbl.setShowVerticalLines(false);
        jScrollPane1.setViewportView(participanttbl);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 280, 660, 190);

        typeofcompanycombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Null", "Public Institutions", "International Organizations", "Non-Govermental", "Private Businesses", "Individual" }));
        jPanel1.add(typeofcompanycombo);
        typeofcompanycombo.setBounds(920, 30, 170, 30);

        companytxtf.setBackground(new java.awt.Color(225, 225, 225));
        companytxtf.setBorder(null);
        jPanel1.add(companytxtf);
        companytxtf.setBounds(730, 37, 160, 18);

        pozitatxtf.setBackground(new java.awt.Color(225, 225, 225));
        pozitatxtf.setBorder(null);
        jPanel1.add(pozitatxtf);
        pozitatxtf.setBounds(730, 80, 160, 20);

        shtobtn.setBackground(new java.awt.Color(105, 3, 3));
        shtobtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        shtobtn.setForeground(new java.awt.Color(255, 255, 255));
        shtobtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/save.png"))); // NOI18N
        shtobtn.setText("Ruaje");
        shtobtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shtobtnActionPerformed(evt);
            }
        });
        jPanel1.add(shtobtn);
        shtobtn.setBounds(680, 170, 165, 45);

        fshijbtn.setBackground(new java.awt.Color(105, 3, 3));
        fshijbtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        fshijbtn.setForeground(new java.awt.Color(255, 255, 255));
        fshijbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/delete.png"))); // NOI18N
        fshijbtn.setText("Fshije");
        fshijbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fshijbtnActionPerformed(evt);
            }
        });
        jPanel1.add(fshijbtn);
        fshijbtn.setBounds(880, 170, 165, 45);

        pastrobtn.setBackground(new java.awt.Color(105, 3, 3));
        pastrobtn.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        pastrobtn.setForeground(new java.awt.Color(255, 255, 255));
        pastrobtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/broom.png"))); // NOI18N
        pastrobtn.setText("Pastro Fushat");
        pastrobtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pastrobtnActionPerformed(evt);
            }
        });
        jPanel1.add(pastrobtn);
        pastrobtn.setBounds(1080, 170, 165, 45);

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sexcombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "null", "M", "F" }));
        jPanel2.add(sexcombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 192, 70, 27));

        surnametxtf.setBackground(new java.awt.Color(225, 225, 225));
        surnametxtf.setBorder(null);
        jPanel2.add(surnametxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 152, 160, 20));

        nametxtf.setBackground(new java.awt.Color(225, 225, 225));
        nametxtf.setBorder(null);
        nametxtf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nametxtfActionPerformed(evt);
            }
        });
        jPanel2.add(nametxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 163, 20));

        idnumbertxtf.setBackground(new java.awt.Color(225, 225, 225));
        idnumbertxtf.setBorder(null);
        jPanel2.add(idnumbertxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 68, 163, 20));

        trainingcodetxtf.setBackground(new java.awt.Color(225, 225, 225));
        trainingcodetxtf.setBorder(null);
        jPanel2.add(trainingcodetxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 26, 160, 20));

        birthDate.setBackground(new java.awt.Color(225, 225, 225));
        birthDate.setDateFormatString("dd-MM-yyyy");
        birthDate.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                birthDateInputMethodTextChanged(evt);
            }
        });
        jPanel2.add(birthDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, 130, 30));

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 10, 360, 260);

        jButton1.setBackground(new java.awt.Color(105, 3, 3));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("BACK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(1210, 250, 61, 23);

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ziptxtf.setBackground(new java.awt.Color(225, 225, 225));
        ziptxtf.setBorder(null);
        jPanel3.add(ziptxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 160, 20));

        addresstxtf.setBackground(new java.awt.Color(225, 225, 225));
        addresstxtf.setBorder(null);
        jPanel3.add(addresstxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 196, 160, 20));

        citytxtf.setBackground(new java.awt.Color(225, 225, 225));
        citytxtf.setBorder(null);
        citytxtf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                citytxtf1ActionPerformed(evt);
            }
        });
        jPanel3.add(citytxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 155, 160, 20));

        statetxtf.setBackground(new java.awt.Color(225, 225, 225));
        statetxtf.setBorder(null);
        statetxtf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                citytxtf1ActionPerformed(evt);
            }
        });
        jPanel3.add(statetxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 113, 160, 20));

        emailtxtf.setBackground(new java.awt.Color(225, 225, 225));
        emailtxtf.setBorder(null);
        emailtxtf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailtxtfActionPerformed(evt);
            }
        });
        jPanel3.add(emailtxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 69, 160, 20));

        phonetxtf.setBackground(new java.awt.Color(225, 225, 225));
        phonetxtf.setBorder(null);
        jPanel3.add(phonetxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 28, 160, 20));

        jPanel1.add(jPanel3);
        jPanel3.setBounds(380, 10, 291, 260);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/participantdesign2.png"))); // NOI18N
        jPanel1.add(background);
        background.setBounds(0, 4, 1280, 480);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1279, 484));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void emailtxtfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailtxtfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailtxtfActionPerformed

    private void statetxtfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statetxtfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statetxtfActionPerformed

    private void nametxtfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nametxtfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nametxtfActionPerformed

    private void citytxtf1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_citytxtf1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_citytxtf1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        trainerEtmm.removeAll();
        trainingcodetxtf.setText("");
        trainingprocessTabelaLoad();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void shtobtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shtobtnActionPerformed
       Participant p;
        Company c;
        ParticipatingCompanyMember pcm;
        ParticipantTeam pt;
        try{
            checkParticipantForm();
            validation();
            TrainingProcess tp=trainingpir.findById((String)trainingcodetxtf.getText().trim());
            TextFieldIntException(idnumbertxtf,"Duhet të shkruhet numri personal i pjesëmarrësit.","Numri Personal",tp,em);
            checkIfInDatabase();
            p=createParticipant();
            c=createCompany();
            pcm=createParticipatingCompanyMember(p,c);
            pt=createTeam(tp, pcm);
            createCTPcombo(c,tp);
            createTrainerEvaluation(pcm,tp);
            shtoCreateLog(p.getName(),p.getSurname(),p.getIDNumber(),tp.getTProcessID());
            emptyObject();
            participantTabelaLoad();
            trainingprocessTabelaLoad();
            trainerEtmm.removeAll();
            JOptionPane.showMessageDialog(null, "Pjsëmarrësi u shtua me sukses.");
        }catch (AppException ex ){
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,ex.getMessage());
        }catch(StopException se){
        }
    }//GEN-LAST:event_shtobtnActionPerformed

    private void pastrobtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pastrobtnActionPerformed
        emptyObject();
    }//GEN-LAST:event_pastrobtnActionPerformed

    private void fshijbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fshijbtnActionPerformed
    try {
                fshijValidation();
                String[] opcionet={"Po","Jo"};
                int response = JOptionPane.showOptionDialog(null,
                "A dëshiron me e fshi pjesëmarrësin nga ky trainimë?","Kujdesë",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, opcionet, opcionet[0]);
                if(response==0){
                    TrainingProcess tptemp=trainingpir.findByIdActive(trainingcodetxtf.getText().trim());
                    Participant partemp=participantir.findByPersonalID(Long.parseLong(idnumbertxtf.getText().trim()));
                    ParticipatingCompanyMember pcm=participatingcompanymemberir.findBytTpParticipant(tptemp, partemp);
                    CTPcombo tempCTPcombo=ctpcir.findByTPCompany(tptemp,pcm.getCompanyID());
                    
                    TTevaluationDeactivate(tptemp,partemp);
                    TeamDeactivate(tptemp,pcm);
                    PCMDeactivate(pcm);
                    participantDeactivate(partemp);
                    CTPcomboDeactivate(tptemp,pcm.getCompanyID(),tempCTPcombo);
                    CompanyDeactivate(pcm.getCompanyID());
                    
                    emptyObject();
                    participantTabelaLoad();
                    trainingprocessTabelaLoad();
                    trainerEtmm.removeAll();
                    shtoDeleteLog(partemp.getName(),partemp.getSurname(),partemp.getIDNumber(),tptemp.getTProcessID());
                    JOptionPane.showMessageDialog(null, "U fshi me sukses.");
                }
    } catch (AppException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, ex.getMessage());
    }
    }//GEN-LAST:event_fshijbtnActionPerformed

    private void birthDateInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_birthDateInputMethodTextChanged

    }//GEN-LAST:event_birthDateInputMethodTextChanged

    public void shtoDeleteLog(String emriP,String mbiemriP,long idNumP,String tpId) throws AppException{
        
        
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
        
        String Mesazhi=useri.getName()+" "+useri.getSurname()+" me username-in: "+useri.getUsername()+" Ka fshirë pjesmarrësin "+emriP +" "+mbiemriP+" me numër personal "+ idNumP +" nga trajnimi "+tpId+" në datën "+today +" në ora "+koha;
        
        l.setUseri(useri.getName()+" "+useri.getSurname()+" - "+useri.getUsername());
        l.setKoha(time);
        l.setDita(date);
        l.setMesazhi(Mesazhi);
        l.setLloji("Delete");
        l.setUsername(us);
        
        logsir.create(l);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addresstxtf;
    private javax.swing.JLabel background;
    private com.toedter.calendar.JDateChooser birthDate;
    private javax.swing.JTextField citytxtf;
    private javax.swing.JTextField companytxtf;
    private javax.swing.JTextField emailtxtf;
    private javax.swing.JButton fshijbtn;
    private javax.swing.JTextField idnumbertxtf;
    private javax.swing.JLabel instructionLbl;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField nametxtf;
    private javax.swing.JTable participanttbl;
    private javax.swing.JButton pastrobtn;
    private javax.swing.JTextField phonetxtf;
    private javax.swing.JTextField pozitatxtf;
    private javax.swing.JComboBox<String> sexcombo;
    private javax.swing.JButton shtobtn;
    private javax.swing.JTextField statetxtf;
    private javax.swing.JTextField surnametxtf;
    private javax.swing.JTextField trainingcodetxtf;
    private javax.swing.JTable trainingprocesstbl;
    private javax.swing.JComboBox<String> typeofcompanycombo;
    private javax.swing.JTextField ziptxtf;
    // End of variables declaration//GEN-END:variables
    private void TrainerEvaluationException(String s1,String s2) throws AppException{
    boolean correct=checkTrainerEvaluation();
       if(!correct){
            if(trainingprocesstbl.getModel()==trainerEtmm)
                throw new AppException(s1);
            else
                throw new AppException(s2);
       }
    }
    
    
    private boolean checkTrainerEvaluation(){
        
        List<TTrainerEvaluation> ttes=trainerEtmm.getData();
        boolean correct=true;
        if(ttes==null){
            return false;
        }
        else if (ttes.isEmpty()){
            return false;
        }
        else{
            for (TTrainerEvaluation tte : ttes){
            if (tte.getPresentation()==0 || tte.getTrainerDiscussion()==0 
                    || tte.getTrainerPreperation()==0)
                return false;
        }
        }
        return correct;
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
        
        
        background.setCursor(cursor);
        jScrollPane1.setCursor(cursor);
        jScrollPane2.setCursor(cursor);
        birthDate.setCursor(cursor);
        this.setCursor(cursor);
        
        
        
        jButton1.setCursor(buttoncursor);
        sexcombo.setCursor(buttoncursor);
        typeofcompanycombo.setCursor(buttoncursor);
        shtobtn.setCursor(buttoncursor);
        fshijbtn.setCursor(buttoncursor);
        pastrobtn.setCursor(buttoncursor);
        
        pozitatxtf.setCursor(textcursor);
        companytxtf.setCursor(textcursor);
        nametxtf.setCursor(textcursor);
        surnametxtf.setCursor(textcursor);
        idnumbertxtf.setCursor(textcursor);
        addresstxtf.setCursor(textcursor);
        statetxtf.setCursor(textcursor);
        citytxtf.setCursor(textcursor);
        phonetxtf.setCursor(textcursor);
        trainingcodetxtf.setCursor(textcursor);
        ziptxtf.setCursor(textcursor);
        emailtxtf.setCursor(textcursor);
        
        
    }
    
    public void shtoCreateLog(String emriP,String mbiemriP,long idNumP,String tpId) throws AppException{
        
        
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
        
        String Mesazhi=useri.getName()+" "+useri.getSurname()+" me username-in: "+useri.getUsername()+" Ka shtuar pjesmarrësin "+emriP +" "+mbiemriP+" me numër personal "+ idNumP +" në trajnimin "+tpId+" me datën : "+today +" në ora "+koha;
        
        l.setUseri(useri.getName()+" "+useri.getSurname()+" - "+useri.getUsername());
        l.setKoha(time);
        l.setDita(date);
        l.setMesazhi(Mesazhi);
        l.setLloji("Create");
        l.setUsername(us);
        
        logsir.create(l);
    }
    
    public void loadDropDown(){
        Participant p;
        List<Participant> parts=participantir.findAllActive();
        Object[] names = new Object[participantir.findAllActive().size()];
        Object[] surnames = new Object[participantir.findAllActive().size()];
        Object[] phones = new Object[participantir.findAllActive().size()];
        Object[] emails = new Object[participantir.findAllActive().size()];
        Object[] states = new Object[participantir.findAllActive().size()];
        Object[] citys = new Object[participantir.findAllActive().size()];
        Object[] addresses = new Object[participantir.findAllActive().size()];
        Object[] idnumbers = new Object[participantir.findAllActive().size()];
        Object[] zips = new Object[participantir.findAllActive().size()];
        
        for(int i=0;i<participantir.findAllActive().size();i++){
            p=parts.get(i);
            names[i]=p.getName();
            surnames[i]=p.getSurname();
            phones[i]=p.getPhone();
            emails[i]=p.getEmail();
            states[i]=p.getParticipantState();
            citys[i]=p.getCity();
            addresses[i]=p.getParticipantAddress();
            idnumbers[i]=p.getIDNumber();
            zips[i]=p.getZipCode();
        }
        TextAutoCompleter namecomplete=new TextAutoCompleter(nametxtf);
        TextAutoCompleter surnamecomplete=new TextAutoCompleter(surnametxtf);
        TextAutoCompleter phonecomplete=new TextAutoCompleter(phonetxtf);
        TextAutoCompleter emailcomplete=new TextAutoCompleter(emailtxtf);
        TextAutoCompleter statecomplete=new TextAutoCompleter(statetxtf);
        TextAutoCompleter citycomplete=new TextAutoCompleter(citytxtf);
        TextAutoCompleter addresscomplete=new TextAutoCompleter(addresstxtf);
        TextAutoCompleter idnumbercomplete=new TextAutoCompleter(idnumbertxtf);
        TextAutoCompleter zipcomplete=new TextAutoCompleter(ziptxtf);
        
        
        namecomplete.addItems(names);
        surnamecomplete.addItems(surnames);
        phonecomplete.addItems(phones);
        emailcomplete.addItems(emails);
        statecomplete.addItems(states);
        citycomplete.addItems(citys);
        addresscomplete.addItems(addresses);
        idnumbercomplete.addItems(idnumbers);
        zipcomplete.addItems(zips);

    }

    private void fshijValidation()throws AppException {
        if(trainingcodetxtf.getText().trim().isEmpty())
            throw new AppException("Shkruaje kodin e trainimit prej të cilit dëshiron me fshi pjesëmarrësin (ose Zgjidhe nga lista Trainimin).");
        if(trainingcodetxtf.getText().toCharArray().length<8)
            throw new AppException ("Kodi i trainimit duhet të përmbajë së paku tetë karaktera.");
        if(idnumbertxtf.getText().trim().isEmpty())
            throw new AppException ("Shkruaje numrin personal të pjesëmarrësit të cilin dëshiron me e fshi(ose Zgjidhe nga lista Pjesëmarrësin).");
        if(idnumbertxtf.getText().trim().toCharArray().length<10)
            throw new AppException ("Numëri personal i një pjesëmarrësi duhet të jetë së paku dhjetë shifror.");
        
        Participant par;
        TrainingProcess tpe;
        try{
            tpe=trainingpir.findByIdActive(trainingcodetxtf.getText().trim());
        }catch(NoResultException nre){
            throw new AppException("Nuk ekziston ky trajnim.");
        }
    
        try{
            par=participantir.findByPersonalID(Long.parseLong(idnumbertxtf.getText().trim()));
            List<Participant> lista=participantir.findByTpIdActive(tpe.getTProcessID());
            if(!lista.contains(par))
                throw new AppException("Ky pjesëmarrës nuk ekzistonë në këtë trajnim.");
            }catch(NoResultException nre){
            throw new AppException("Nuk ekziston ky Pjesëmarrës.");
        }
    }
    
    private void pasivizoTevaluation(Iterator<TTrainerEvaluation> iterator) throws AppException{
        TTrainerEvaluation ttetemp;
        while(iterator.hasNext()){
            ttetemp=iterator.next();
            if(!ttetemp.getStatusi().equals("Passive")){
                ttetemp.setStatusi("Passive");
                trainerevir.edit(ttetemp);
            }     
        }
    }

    private void pasivizoCompanyAndFriends(TrainingProcess tptemp,Participant partemp) throws AppException {
        CTPcombo ctptemp=ctpcir.findCTPcomboTpParticipantForDelete(tptemp,partemp);
        if(participatingcompanymemberir.findByCTPcomboForDelete(ctptemp).size()==1)
            if(!ctptemp.getStatusi().equals("Passive")){
                ctptemp.setStatusi("Passive");
                ctpcir.edit(ctptemp);
            }
        /* Pasivizimi  i Kompanisë */
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
        if (participatingcompanymemberir.findByCompany(ctptemp.getCompanyID()).isEmpty()){
            if(ctptemp.getCompanyID().getStatusi().equals("Passive")){
                ctptemp.getCompanyID().setStatusi("Passive");
                companyir.edit(c);
            }
        /* Pasivizimi i CTPcombove*/
            Iterator<CTPcombo> iteratori=ctpcir.findByCompany(ctptemp.getCompanyID()).iterator();
            CTPcombo temp;
            while(iteratori.hasNext()){
                temp=iteratori.next();
                if(!temp.getStatusi().equals("Passive")){
                    temp.setStatusi("Active");
                } 
            }
        }
    }
    
    private void pasivizoParticipantTeam(TrainingProcess tptemp, Participant partemp) throws AppException {
        List<ParticipantTeam> lista=(List)tptemp.getTeamID().getParticipantTeamCollection();
        ArrayList<ParticipatingCompanyMember> pcmss=new ArrayList<ParticipatingCompanyMember>();
        for (ParticipantTeam part : lista){
            if(part.getPcmId().getParticipantID().equals(partemp)){
                lista.remove(part);
                break;
            }
        
            
        
        for (int i=0;i<lista.size();i++){
            pcmss.add(lista.get(i).getPcmId());
        }
        }
        Team te;
        try{
            if(!pcmss.isEmpty())
                te=teamir.findByParticipants(pcmss);
            else
                throw new NoResultException();
        }catch(NoResultException nre){
            te=new Team();
            for (ParticipatingCompanyMember temppcm : pcmss){
                ParticipantTeam pt=new ParticipantTeam();
                pt.setPcmId(temppcm);
                pt.setTeamID(te);
                participantteamir.create(pt);
            }
        }
        
        //Pasivizimi i teamit të vjetër në qoftë se nuk ka asnje trainingProcess
        Team old =tptemp.getTeamID();
        tptemp.setTeamID(te);
        trainingpir.edit(tptemp);
        if (trainingpir.findByTeam(old.getTeamID()).isEmpty()){
            if(!old.getStatusi().equals("Passive")){
                old.setStatusi("Passive");
                teamir.edit(old);
                List<ParticipantTeam>pteams=(List)old.getParticipantTeamCollection();
                for (ParticipantTeam tempi : pteams){
                    if(!tempi.getStatusi().equals("Passive")){
                        tempi.setStatusi("Passive");
                        participantteamir.edit(tempi);
                    }
                }
            }
        }        
    }

    private void pasivizoPCM(TrainingProcess tptemp, Participant partemp) throws AppException {
        try{
                //PASIVIizo pcmat tu i lyp cilat jon n'team qe sjon ne trainingProcess
            ParticipatingCompanyMember pcmtempi=participatingcompanymemberir.findBytTpParticipantForDelete(tptemp,partemp);
            if(participantteamir.findByPCM(pcmtempi).isEmpty()){
                if(!pcmtempi.getStatusi().equals("Passive")){
                    pcmtempi.setStatusi("Passive");
                    participatingcompanymemberir.edit(pcmtempi);
                }
            }
        }catch(NoResultException nre){
        }
    }
    // Pasivizimi i pjesëmarrësit 
        
    
        private void pasivizoParticipant(Participant partemp) throws AppException {
            try{
                if(participatingcompanymemberir.findByParticipant(partemp).isEmpty()){
                    if(!partemp.getStatusi().equals("Passive")){
                        partemp.setStatusi("Passive");
                        participantir.edit(partemp);
                    }
                }
            }catch(NoResultException nre){
            }
        }

    private void checkParticipantForm(){
        try{
            ArrayList<TTrainerEvaluation> newTTevaluation=new ArrayList<TTrainerEvaluation>();;
            tcm.checkValidInput();
            if(!tcm.getTrainingCode().equals(tcm.getLastTrainingCode())){
                trainingprocessTabelaLoad();
            }
            
            pnm.checkValidInput();
            
            
        
        if(trainingcodetxtf.getText().trim().isEmpty())
            throw new AppException("Shkruaje Kodin e Trajnimit që ka marrë pjesë Pjesëmarrësi.");
        else if ((tcm.getCount()>0 && !tcm.isViza())){
            throw new AppException("Kodi i Trajnimit duhet të përmbajë vetëm numra dhe një '-' .");
        }
        else if(tcm.getCount()!=1 && tcm.isViza()){
            throw new AppException("Kodi i Trajnimit duhet të përmbajë vetëm numra dhe një '-' .");
        }
        else if(tcm.getCount()==0 && tcm.getCharacters().length<8){
            throw new AppException("Kodi i Trajnimit duhet të përmbajë edhe "+(8-tcm.getCharacters().length)+" .");
        }
        else if(tcm.getCount()==1 && tcm.isViza() && tcm.getCharacters().length==9){
            throw new AppException("Shkruaje edhe një numër që e dallon Trainimin.");    
        }
        else if((tcm.getCount()==1 && tcm.isViza() && tcm.getCharacters().length!=10) || (tcm.getCount()==0 && !tcm.isViza() && tcm.getCharacters().length!=8))
            throw new AppException("Kodi i Trainimit duhet të përmbajë 8 karaktera ose 10 kur është me vizë.");
        else if(!tcm.getTrainingCode().equals(tcm.getLastTrainingCode())){
                tcm.setLastTrainingCode(tcm.getTrainingCode());
                trainerEtmm.setTrainingProcessID(tcm.getTrainingCode() );
                TrainingProcess tProcess=trainingpir.checkIfExists(trainingcodetxtf.getText().trim());
                    List<TrainerTeamCombo>ttcombos=trainerteamcomboir.findByTrainerTeam(tProcess.getTrainersTeamID().getTrainersTeamID());
                boolean simetry=false;
                if(ttcombos.size()==trainerev.size()){
                    for(int i=0;i<ttcombos.size();i++){
                        for (int j=0;j<trainerev.size();j++){
                            if(ttcombos.get(i).getTrainerID()==trainerev.get(j).getTrainerID()){
                                simetry=true;
                                break;
                            }
                        }
                    
                    if(simetry==false){
                        newTTevaluation=new ArrayList<TTrainerEvaluation>();
                        TTrainerEvaluation temptte;
                        for(TrainerTeamCombo tempttc : ttcombos){
                            temptte=new TTrainerEvaluation(tempttc.getTrainerID());
                            newTTevaluation.add(temptte);
                        }
                        if(trainerEtmm.getData()!=null)
                            trainerEtmm.getData().clear();
                        trainerEvTabelaLoad(newTTevaluation);
                        break;
                    }
                    }
                }else{
                    if(simetry==false){
                        newTTevaluation=new ArrayList<TTrainerEvaluation>();
                        TTrainerEvaluation temptte;
                        for(TrainerTeamCombo tempttc : ttcombos){
                            temptte=new TTrainerEvaluation(tempttc.getTrainerID());
                            newTTevaluation.add(temptte);
                        }
                        if(trainerEtmm.getData()!=null)
                            trainerEtmm.getData().clear();
                        trainerEvTabelaLoad(newTTevaluation);
                    }
                }
                
                
            }
        
        if(idnumbertxtf.getText().trim().isEmpty())
            throw new AppException("Shkruaje Numrin Personal të pjesëmarrësit.");
        else if(pnm.getCount()!=0)
            throw new AppException("Numri Personal i pjesëmarrësit mund të përmbajë vetëm numra (0-9).");
        else if(pnm.getCharacters().length<10){
            throw new AppException("Numri Personal i pjesëmarrësit duhet të përmbajë edhe "+(10-pnm.getCharacters().length)+" .");
        }
        else if (pnm.getCharacters().length>11){
                throw new AppException("Numri Personal i pjesëmarrësit mund të përmbajë maksimalisht 11 shifra.");
        }
        else if (pnm.getCharacters().length==10 || pnm.getCharacters().length==11){
            if(participantteamir.CheckIfInTraining(Long.parseLong(idnumbertxtf.getText().trim()), trainingcodetxtf.getText().trim()))
                throw new AppException("Ky Pjesëmarrës është i regjistruar në këtë Trajnim.");
            else{
            }
                
        }
        
        if(nametxtf.getText().trim().isEmpty())
            throw new AppException("Shkruaje emrin e pjesëmarrësit.");
        else if(surnametxtf.getText().trim().isEmpty())
            throw new AppException("Shkruaje mbiemrin e pjesëmarrësit.");
        else if(trainingprocesstbl.getModel()==trainerEtmm)
            checkTTevaluation();
        else 
            instructionLbl.setText("");
        
        
        }catch(AppException nre){
            instructionLbl.setText(nre.getMessage());
        }
        
        
    }
    
    private void TTrainerEvaluationTableModelListener(){
        trainerEtmm.addTableModelListener(new TableModelListener(){
            @Override
            public void tableChanged(TableModelEvent tme) {
                checkParticipantForm();
            }
        });
        
    }
    
   
    
    private void validation() throws AppException{
        if(trainingcodetxtf.getText().trim().isEmpty())
            throw new AppException("Shkruaje Kodin e Trajnimit që ka marrë pjesë Pjesëmarrësi.");
        else if ((tcm.getCount()>0 && !tcm.isViza())){
            throw new AppException("Kodi i Trajnimit duhet të përmbajë vetëm numra dhe një '-' .");
        }
        else if(tcm.getCount()!=1 && tcm.isViza()){
            throw new AppException("Kodi i Trajnimit duhet të përmbajë vetëm numra dhe një '-' .");
        }
        else if(tcm.getCount()==0 && tcm.getCharacters().length<8){
            throw new AppException("Kodi i Trajnimit duhet të përmbajë edhe "+(8-tcm.getCharacters().length)+" .");
        }
        else if(tcm.getCount()==1 && tcm.isViza() && tcm.getCharacters().length==9){
            throw new AppException("Shkruaje edhe një numër që e dallon Trainimin.");    
        }
        else if((tcm.getCount()==1 && tcm.isViza() && tcm.getCharacters().length!=10) || (tcm.getCount()==0 && !tcm.isViza() && tcm.getCharacters().length!=8))
            throw new AppException("Kodi i Trainimit duhet të përmbajë 8 karaktera ose 10 kur është me vizë.");
        else {
            trainingpir.checkIfExists(trainingcodetxtf.getText().trim());
             }
        
        if(idnumbertxtf.getText().trim().isEmpty())
            throw new AppException("Shkruaje Numrin Personal të pjesëmarrësit.");
        else if(pnm.getCount()!=0)
            throw new AppException("Numri Personal i pjesëmarrësit mund të përmbajë vetëm numra (0-9).");
        else if(pnm.getCharacters().length<10){
            throw new AppException("Numri Personal i pjesëmarrësit duhet të përmbajë edhe "+(10-pnm.getCharacters().length)+" .");
        }
        else if (pnm.getCharacters().length>11){
                throw new AppException("Numri Personal i pjesëmarrësit mund të përmbajë maksimalisht 11 shifra.");
        }
        else if (pnm.getCharacters().length==10 || pnm.getCharacters().length==11){
            if(participantteamir.CheckIfInTraining(Long.parseLong(idnumbertxtf.getText().trim()), trainingcodetxtf.getText().trim()))
                throw new AppException("Ky Pjesëmarrës është i regjistruar në këtë Trajnim.");
            else {
            }
                
        }
        
        if(nametxtf.getText().trim().isEmpty())
            throw new AppException("Shkruaje emrin e pjesëmarrësit.");
        else if(surnametxtf.getText().trim().isEmpty())
            throw new AppException("Shkruaje mbiemrin e pjesëmarrësit.");
        else if(trainingprocesstbl.getModel()==trainerEtmm)
            checkTTevaluation();
        else if(trainingprocesstbl.getModel()==trainingptmm)
            throw new AppException("Duhet që të zgjidhet trajnimi dhe pastaj të vlersohen trainerët.");
        else
            instructionLbl.setText("");
        
        
    }
    
    
    private void ParticipantFormListeners(){
        TrainingCodeParticipantFormDocumentListener(trainingcodetxtf);
        ParticipantFormDocumentListener(idnumbertxtf);
        ParticipantFormDocumentListener(nametxtf);
        ParticipantFormDocumentListener(surnametxtf);
        ParticipantFormDocumentListener(phonetxtf);
        ParticipantFormDocumentListener(emailtxtf);
        ParticipantFormDocumentListener(citytxtf);
        ParticipantFormDocumentListener(statetxtf);
        ParticipantFormDocumentListener(ziptxtf);
        ParticipantFormDocumentListener(addresstxtf);
        ParticipantFormDocumentListener(companytxtf);
        ParticipantFormDocumentListener(pozitatxtf);
        TTrainerEvaluationTableModelListener();
        JComboBoxListener(sexcombo);
        JComboBoxListener(typeofcompanycombo);
        JDateChooserFocusListener(birthDate);
    }
    
     private void ParticipantFormDocumentListener(JTextField target){
        target.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de) {
                checkParticipantForm();
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                checkParticipantForm();
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                checkParticipantForm();
            }
        });
        target.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent fe) {
                checkParticipantForm();}

            @Override
            public void focusLost(FocusEvent fe) {
                checkParticipantForm();
            }
        
        });
        
    }
     
     private void TrainingCodeParticipantFormDocumentListener(JTextField target){
        target.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de) {
                checkParticipantForm();
                tcm.setLastTrainingCode(target.getText().trim());
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                checkParticipantForm();
                tcm.setLastTrainingCode(target.getText().trim());
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                checkParticipantForm();
                tcm.setLastTrainingCode(target.getText().trim());
            }
        });
        target.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent fe) {
                checkParticipantForm();
                tcm.setLastTrainingCode(target.getText().trim());
            }

            @Override
            public void focusLost(FocusEvent fe) {
                checkParticipantForm();
                tcm.setLastTrainingCode(target.getText().trim());
            }
        
        });
        
    }
     
     
     private void JComboBoxListener(JComboBox target){
         target.addActionListener(new ActionListener(){
             @Override
             public void actionPerformed(ActionEvent ae) {
                 trainerEtmm.fireTableDataChanged();
                 checkParticipantForm();
                 
             }
         });
         target.addItemListener(new ItemListener(){
             @Override
             public void itemStateChanged(ItemEvent ie) {
                trainerEtmm.fireTableDataChanged();
                checkParticipantForm();
             }
         });
         
         target.addFocusListener(new FocusListener(){
             @Override
             public void focusGained(FocusEvent fe) {
                trainerEtmm.fireTableDataChanged();
                checkParticipantForm();
             }

             @Override
             public void focusLost(FocusEvent fe) {
                trainerEtmm.fireTableDataChanged();
                checkParticipantForm();}
         });
     }
     
     private void JDateChooserFocusListener(JDateChooser target){
        target.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent fe) {
                checkParticipantForm();
            }

            @Override
            public void focusLost(FocusEvent fe) {
                checkParticipantForm();
            }
        });
        
        ((JTextField)target.getDateEditor().getUiComponent()).getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                 checkParticipantForm();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                 checkParticipantForm();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                 checkParticipantForm();
            }
        });
    }

    private void checkTTevaluation()throws AppException {
        
        Iterator<TTrainerEvaluation>trainersIterator=trainerEtmm.getData().iterator();
        TTrainerEvaluation tempTTe;
        while(trainersIterator.hasNext()){
            tempTTe=trainersIterator.next();
            if(tempTTe.getPresentation()==0 || tempTTe.getTrainerDiscussion()==0 || tempTTe.getTrainerPreperation()==0)
                throw new AppException("Duhet të bëhet vlerësimi i trainerit ("+tempTTe.getTrainerID().getName()+" "+tempTTe.getTrainerID().getSurname()+".");
        }
        instructionLbl.setText("");
    }

    private void TTevaluationDeactivate(TrainingProcess tptemp, Participant partemp) throws AppException {
        List<TTrainerEvaluation> ttes=trainerevir.findByTrainingProcessParticipant(tptemp, partemp);
        TTrainerEvaluation tte;
        for (int i=0;i<ttes.size();i++){
            tte=ttes.get(i);
            if (!tte.getStatusi().equals("Passive")){
                tte.setStatusi("Passive");
                trainerevir.edit(tte);
            }
        }
    }

    private void TeamDeactivate(TrainingProcess tptemp, ParticipatingCompanyMember pcm) throws AppException {
        Team tpTeam=tptemp.getTeamID();
        ParticipantTeam pt=participantteamir.findByParticipantTP(pcm.getParticipantID().getParticipantID(), tptemp.getTProcessID());
        participantteamir.remove(pt);
    }

    private void PCMDeactivate(ParticipatingCompanyMember pcm)throws AppException {
        if(participantteamir.findByPCM(pcm).isEmpty() && trainerevir.findByPCM(pcm).isEmpty()){
            if(!pcm.getStatusi().equals("Passive")){
                pcm.setStatusi("Passive");
                participatingcompanymemberir.edit(pcm);
            }
        }
    }

    private void participantDeactivate(Participant partemp)throws AppException {
        if(participatingcompanymemberir.findByParticipant(partemp).isEmpty() ){
            if(!partemp.getStatusi().equals("Passive")){
                partemp.setStatusi("Passive");
                participantir.edit(partemp);
            }
        }
    }

    private void CTPcomboDeactivate(TrainingProcess tptemp, Company companyID,CTPcombo tempCTPcombo)throws AppException {
        if(participatingcompanymemberir.findByCompany(companyID).isEmpty()){
            if(!tempCTPcombo.getStatusi().equals("Passive")){
                tempCTPcombo.setStatusi("Passive");
                ctpcir.edit(tempCTPcombo);
            }
        }
    }

    private void CompanyDeactivate(Company companyID)throws AppException {
        if(ctpcir.findByCompanyActive(companyID).isEmpty() && participatingcompanymemberir.findByCompany(companyID).isEmpty() ){
            if(!companyID.getStatusi().equals("Passive")){
                companyID.setStatusi("Passive");
                companyir.edit(companyID);
            }
        }
    }

    private void checkIfInDatabase()throws AppException,StopException{
        checkIfInDatabaseParticipant();
        checkIfInDatabaseCompany();
    }
    
    private void checkIfInDatabaseParticipant()throws AppException,StopException{
        Participant p= new Participant();
        p.setName(nametxtf.getText().trim());
        p.setSurname(surnametxtf.getText().trim());
        p.setParticipantAddress(addresstxtf.getText().trim());
        p.setPozita(pozitatxtf.getText().trim());
        p.setCity(citytxtf.getText().trim());
        p.setParticipantState(statetxtf.getText().trim());
        p.setSex(sexcombo.getSelectedItem()+(""));
        p.setIDNumber(Long.parseLong(idnumbertxtf.getText().trim()));
        p.setZipCode(ziptxtf.getText().trim());
        p.setEmail(emailtxtf.getText().trim());
        p.setPhone(phonetxtf.getText().trim());
        p.setDob(birthDate.getDate());
        try{
            //Kerkon se a ekziston ky pjesmarres me qeto attribute qe jon shtu  nqs jo 
            p=participantir.findByEverything(p);
        }catch (NoResultException nre){
            // Kthen pjesmarresin se si eshte regjistru ndatabaze edhe si po tentohet me u rujt
            try{
                Participant oldParticipant,newParticipant;
                oldParticipant=participantir.findByPersonalID(p.getIDNumber());
                newParticipant=p;
                MergeParticipant mp=initializeMergeParticipant(oldParticipant,newParticipant,em,this);
                mp.setVisible(true);
                throw new StopException("Na konvenon me e nal qetu regjistrimin deri sa të rregullohët pjesëmarrësi");
            }catch (NoResultException nre2){
            }
        }
    }

    private void checkIfInDatabaseCompany()throws StopException{
        Company comp=new Company();
        comp.setName(companytxtf.getText());
        comp.setCompanytype(typeofcompanycombo.getSelectedItem().toString());
        try{
           comp=companyir.findByEverything(comp);
       }catch(NoResultException nre){
        try{  
            Company oldCompany,newCompany;
            oldCompany=companyir.findByCompanyName(comp);
            newCompany=comp;
            MergeCompany mc=initializeMergeCompany(oldCompany,newCompany,em,this);
            mc.setVisible(true);
            throw new StopException("Na konvenon me e nal qetu regjistrimin deri sa të rregullohët pjesëmarrësi");
        }catch(NoResultException nre2){
        }
       }
    }

    private List<ParticipatingCompanyMember> checkIfDouble(List<ParticipatingCompanyMember> pcms) {
        List<ParticipatingCompanyMember> newPcms= new ArrayList<ParticipatingCompanyMember>();
        for (ParticipatingCompanyMember pcm : pcms){
            if(!newPcms.contains(pcm)){
                newPcms.add(pcm);
            }
        }
        return newPcms;
    }
}
