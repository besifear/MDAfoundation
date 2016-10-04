
package gui.view;

import static Utility.EzyListener.MultipleTextFieldDocumentListner;
import static Utility.EzyListener.TextFieldDocumentListner;
import Utility.TrainerTblCheck;
import static Validation.Validation.JDateChoserException;
import static Validation.Validation.MultipleTextFieldValdiation;
import static Validation.Validation.TextFieldValdiation;
import static Validation.Validation.TextFieldException;
import static Validation.Validation.MultipleTextFieldException;
import Validation.StartEndDateManager;
import bl.AppException;
import bl.CTPcomboInterface;
import bl.CTPcomboRepository;
import bl.ClientInterface;
import bl.ClientRepository;
import bl.CompanyInterface;
import bl.CompanyRepository;
import bl.LogsInterface;
import bl.LogsRepository;
import bl.ParticipantInterface;
import bl.ParticipantRepository;
import bl.ParticipantTeamInterface;
import bl.ParticipantTeamRepository;
import bl.ParticipatingCompanyMemberInterface;
import bl.ParticipatingCompanyMemberRepository;
import bl.ProjectInterface;
import bl.ProjectRepository;
import bl.TeamInterface;
import bl.TeamRepository;
import bl.TopicsComboInterface;
import bl.TopicsComboRepository;
import bl.TopicsCoveredInterface;
import bl.TopicsCoveredRepository;
import bl.TrainerContactInterface;
import bl.TrainerContactRepository;
import bl.TrainerEvaluationInterface;
import bl.TrainerEvaluationRepository;
import bl.TrainerInterface;
import bl.TrainerRepository;
import bl.TrainerTeamComboInterface;
import bl.TrainerTeamComboRepository;
import bl.TrainersTeamInterface;
import bl.TrainersTeamRepository;
import bl.TrainingInterface;
import bl.TrainingProcessInterface;
import bl.TrainingProcessRepository;
import bl.TrainingProjectInterface;
import bl.TrainingProjectRepository;
import bl.TrainingRepository;
import bl.UserInterface;
import bl.UserRepository;
import com.mxrck.autocompleter.TextAutoCompleter;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import ejb.CTPcombo;

import ejb.Company;
import ejb.Client;
import ejb.Logs;
import ejb.Participant;
import ejb.ParticipantTeam;
import ejb.ParticipatingCompanyMember;
import ejb.Project;
import ejb.TTrainerEvaluation;
import ejb.Team;
import ejb.TopicsCombo;
import ejb.TopicsCovered;
import ejb.Trainer;
import ejb.TrainerTeamCombo;
import ejb.TrainersTeam;
import ejb.Training;
import ejb.TrainingProcess;
import ejb.TrainingProject;
import ejb.Users;
import gui.model.AddCompanyTableModel;
import gui.model.AddTrainerTableModel;

import gui.model.CompanyTableModel;
import gui.model.ProjectTableModel;
import gui.model.TopicsCoveredTableModel;
import gui.model.TrainerContactTableModel;
import gui.model.TrainerEvaluationTableModelMini;
import gui.model.TrainerTableModel;
import gui.model.TrainerTeamComboTableModel;
import gui.model.TrainersTeamTableModel;
import gui.model.TrainingProcessTableModel;
import gui.model.TrainingProcessUltimateTableModel;
import gui.model.TrainingProjectTableModel;
import gui.model.TrainingTableModel;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.file.Paths;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellEditor;




public class MultiCrudi extends javax.swing.JInternalFrame {   
ArrayList<Trainer> listaTrainerav=new ArrayList();    
ArrayList<Company> listaCompanive=new ArrayList();
String[] TOCarray = {"Public Institutions", "International Organizations", "Non-Govermental", "Private Businesses", "Individual"};
StartEndDateManager sedm;

String e;
String m;
String u;
EntityManager em;
AddTrainerTableModel addtrainertm;
TrainingProcessInterface trainingpir;
TrainingProcessUltimateTableModel ultimatetm;
TrainingInterface trainingir;
ProjectInterface projectir;
TrainingProjectInterface trainingprojectir;
ClientInterface clientir;
TrainerInterface trainerir;
TrainerTeamComboInterface trainerTeamComboIr;
TrainersTeamInterface trainerTeamIr;
TopicsCoveredInterface topicscir;
TopicsComboInterface topiccomboir;
CTPcomboInterface ctpComboIr;
CompanyInterface companyir;
ParticipatingCompanyMemberInterface pcmir;
TeamInterface teamir;
UserInterface userir;
LogsInterface logsir;
ParticipantTeamInterface participantTeamIr;
ParticipantInterface participantIr;
TrainerEvaluationInterface tteir;
Users user;
TrainerTblCheck ttCheck;
String absolutepath=Paths.get(".").toAbsolutePath().normalize().toString();
    ArrayList<String> pathfoldernames;
    public MultiCrudi(Users user,EntityManager emm) {
        em=emm;
        this.user=user;
        addtrainertm=new AddTrainerTableModel();
        ultimatetm=new TrainingProcessUltimateTableModel(em);
        participantTeamIr=new ParticipantTeamRepository(em);
        trainingpir=new TrainingProcessRepository(em);
        participantIr=new ParticipantRepository(em);
        tteir=new TrainerEvaluationRepository(em);
        pcmir=new ParticipatingCompanyMemberRepository(em);
        ctpComboIr=new CTPcomboRepository(em);
        trainingir=new TrainingRepository(em);
        projectir=new ProjectRepository(em);
        trainingprojectir=new TrainingProjectRepository(em);
        clientir=new ClientRepository(em);
        trainerir=new TrainerRepository(em);
        trainerTeamComboIr=new TrainerTeamComboRepository(em);
        trainerTeamIr = new TrainersTeamRepository(em);
        topicscir=new TopicsCoveredRepository(em);
        topiccomboir=new TopicsComboRepository(em);
        teamir=new TeamRepository(em);
        userir=new UserRepository(em);
        logsir=new LogsRepository(em);
        ttCheck=new TrainerTblCheck(listaTrainerav);
        this.setLocation(15,147);
        
        for(MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().getMouseListeners()){
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().removeMouseListener(listener);
        }
        
        initComponents();
        TrainingFormListeners();
        trainingProcessTabelaLoad();
        AddTrainerRow();
        trainingCodeClickEvent();
        addFolderNames();
        CustomCursor();
        loadDropDown();
        e=user.getName();
        m=user.getSurname();
        u=user.getUsername();
        this.em=em;
        trainerNameLbl.setToolTipText("Për Kushën.");
        projecttxtf.requestFocus();
    }
    
     private void trainingCodeClickEvent(){
        idtptxtf.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null,"Kodi i Trainimit gjenerohët nga data e përfundimit të trainimit.");
            }
        });
    }
    
      
    private void emptyObject() {
        
        projecttxtf.setText("");
        titleoftrainingalbaniantxtf.setText("");
        titleoftrainingenglishtxtf.setText("");
        titleoftrainingserbiantxtf.setText("");
        topicscoveredalbaniantxtf.setText("");
        topicscoveredenglishtxtf.setText("");
        topicscoveredserbiantxtf.setText("");
        placetptxtf.setText("");
        addtrainertm.getData().clear();
        idtptxtf.setText("");
        clienttxtf.setText("");
        startdate.setCalendar(null);
        enddate.setCalendar(null);
        AddTrainerRow();
    }
    
    private void trainingProcessTabelaLoad(){
        List<TrainingProcess> list=trainingpir.findAllActive();
        if(list!=null && !list.isEmpty()){
            ultimatetm.add(list);
            trainingprocesstbl.setModel(ultimatetm);
            ultimatetm.fireTableDataChanged();
        }
        else{
            ultimatetm.resetData();
            ultimatetm.fireTableDataChanged();
        }
    }
    
    private void AddTrainerTabelaLoad(){
        addtrainertm.add(listaTrainerav);
        trainertbl.setModel(addtrainertm);
        addtrainertm.fireTableDataChanged();
        trainertbl.putClientProperty("terminateEditOnFocusLost", true);
    }
    
    private void TrainerRow(){
        Trainer t = new Trainer();
        listaTrainerav.add(t);
    }
    
    private void AddTrainerRow(){
        TrainerRow();
        AddTrainerTabelaLoad();
    }
    
    private void validation()throws AppException{
        TextFieldException(projecttxtf,"Shkruaje emrin e projektit.");
        MultipleTextFieldException(titleoftrainingalbaniantxtf,titleoftrainingenglishtxtf,
                                    "Shkruani titullin e trajnimit së paku në Shqip dhe Anglisht.");
        TrainerTblException();
        TextFieldException(clienttxtf,"Shkruani emrin e klientit.");
        MultipleTextFieldException(topicscoveredalbaniantxtf,topicscoveredenglishtxtf,
                                    "Shkruani temat e trajnimit së paku në Shqip dhe Anglisht.");
        TextFieldException(placetptxtf,"Shkruaje vendin e Mbajtjes se Trajnimit.");
        JDateChoserException(startdate,"Shkruaje daten e fillimit te Mbajtjes se Trajnimit.");
        JDateChoserException(enddate,"Shkruaje daten e perfundimit te Mbajtjes se Trajnimit.");
        if(startdate.getDate().after(enddate.getDate()))
            throw new AppException("Data e fillimit nuk mund të jetë më vonë se data e mbarimit.");
    }
    
  
    private void TrainerTblException()throws AppException{
        if(ttCheck.checkTbl()<1)
           throw new AppException("Duhet të rregjistrohët së paku një Trainer.");
        }
    
    
    
    private Training createTraining() throws AppException{
        Training t=new Training(titleoftrainingalbaniantxtf.getText().trim(),
                titleoftrainingenglishtxtf.getText().trim(),titleoftrainingserbiantxtf.getText().trim());
        try{
            t=trainingir.findByTOTs(titleoftrainingalbaniantxtf.getText().trim(),
            titleoftrainingenglishtxtf.getText().trim(),titleoftrainingserbiantxtf.getText().trim());
            if(!t.getStatusi().equals("Active")){
                t.setStatusi("Active");
                trainingir.edit(t);
            }
            return t;
        }catch (NoResultException nre){
            return trainingir.create(t);
        }
    }
    
   
    
    private Project createProject() throws AppException{
        Project pr= new Project(projecttxtf.getText().trim());
        try{
            pr=projectir.findByName(projecttxtf.getText().trim());
            if (!pr.getStatusi().equals("Active")){
                pr.setStatusi("Active");
                projectir.edit(pr);
            }
            return pr;
        }catch (NoResultException nre){
        return projectir.create(pr);
        }
    }
    
     private int gjenerateTrainingProjectID(){
        try{
            return (trainingprojectir.findMaxTrainingProjectID()+1);
        }catch (NoResultException nre){
            return 1;
        }
    }
    
    private TrainingProject createTrainingProject(Training trainingId,Project projectId)throws AppException{
        TrainingProject tp=new TrainingProject(gjenerateTrainingProjectID(),trainingId,projectId);
        try{
            tp=trainingprojectir.findByTrainingProject(tp.getTrainingID().getTrainingID(),tp.getProjectID().getProjectID());
            if(!tp.getStatusi().equals("Active")){
                tp.setStatusi("Active");
                trainingprojectir.edit(tp);
            }
            return tp;
        }catch (NoResultException nre){
            return trainingprojectir.create(tp);
        }
    };
    /*QETU KOM METE */
    private Client createClient() throws AppException{
        Client k = new Client();
        k.setEmri(clienttxtf.getText().trim());
        try{
            k=(clientir.findByName(k.getEmri()));
            if (!k.getStatusi().equals("Active")){
                k.setStatusi("Active");
                clientir.edit(k);
            }
            return k;
            
        }catch (NoResultException nre){
           return clientir.create(k);
        }
    }
    
    
    private int gjenerateTrainerID(){
            try{
            return    (trainerir.findMaxTrainierProjectID()+1);
            }catch (NoResultException nre){
                return 1;
            }
        }
    
    private void createTrainer(ArrayList<Trainer> Trainers) throws AppException{
        for (int i=0;i<addtrainertm.getRowCount();i++){
            Trainer train = addtrainertm.getTrainer(i);
            try{
            if (!train.getName().isEmpty() && !train.getSurname().isEmpty())
                try{
                    train=trainerir.findByNameSurname(train);
                    if (!train.getStatusi().equals("Active")){
                        train.setStatusi("Active");
                        trainerir.edit(train);
                    }
                    Trainers.add(train);
                }catch (NoResultException nre){
                    train.setTrainerID(gjenerateTrainerID());
                    Trainers.add(trainerir.create(train));
                }
                 
            }catch(NullPointerException npe){}
        }
    }
    
    private void createTrainerTeamCombo(ArrayList<Trainer> Trainers ,  TrainersTeam temp) throws AppException{
      Iterator<Trainer> iteratori =Trainers.iterator();
        while(iteratori.hasNext()){
            Trainer t=iteratori.next();
            TrainerTeamCombo tempTTC= new TrainerTeamCombo();
           tempTTC.setTrainerID(t);
           tempTTC.setTrainersTeamID(temp);
           try{
           tempTTC=trainerTeamComboIr.findByTrainerTrainerTeam(tempTTC.getTrainerID().getTrainerID(),tempTTC.getTrainersTeamID().getTrainersTeamID());
           if(!tempTTC.getTrainerID().getStatusi().equals("Active")){
               tempTTC.getTrainerID().setStatusi("Active");
               trainerir.edit(tempTTC.getTrainerID());
           }
           if (!tempTTC.getTrainersTeamID().getStatusi().equals("Active")){
               tempTTC.getTrainersTeamID().setStatusi("Active");
               trainerTeamIr.edit(tempTTC.getTrainersTeamID());
           }
           
           if(!tempTTC.getStatusi().equals("Active")){
               tempTTC.setStatusi("Active");
               trainerTeamComboIr.edit(tempTTC);
           }
           
           }catch(NoResultException nre){
            trainerTeamComboIr.create(tempTTC);
           }
        }
       }
    
    private String DataMbrapsht(Date d){
        String s;
        
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        s=dateFormat.format(d);
                char [] kohshme =s.toCharArray();
                char[]temp=new char[kohshme.length];
                for (int i=0;i<kohshme.length;i++){
                    temp[i]=kohshme[kohshme.length-i-1];
                }
                s="";
                for (int i=0;i<temp.length;i++){
                s+=temp[i];
                }
        return s;
}
    
    
    
    private String gjenerimiTpId (Date d){
        List<TrainingProcess>tplist=trainingpir.findNumberForID(d);
        if(tplist.isEmpty())
            return DataMbrapsht(d);
        return DataMbrapsht(d) +"-"+tplist.size();
    }
    
    private TrainingProcess createTrainingProcess(TrainingProject trainingp,TrainersTeam temp,Client c)throws AppException{
        
        
        TrainingProcess tprocess= new TrainingProcess();
       
       tprocess.setTrainingProjectID(trainingp);
       tprocess.setTrainersTeamID(temp);
       tprocess.setKlientiID(c);
       tprocess.setPlace(placetptxtf.getText().trim());
       Team t=new Team();
       teamir.create(t);
       tprocess.setTeamID(t);
       SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
       String data = sdf.format(startdate.getDate());
                Date d;
                
                try{
                 DateFormat formatter;
                 Date date;
                 formatter = new SimpleDateFormat("dd-MM-yyyy");
                 date = (Date)formatter.parse(data);
                 d=date;
                tprocess.setStartDate(d);
               
                data=sdf.format(enddate.getDate());
                date =(Date)formatter.parse(data);
                d=date;
                tprocess.setEndDate(d);
                }catch (ParseException e){throw new AppException ("Keni bere nje gabim gjate shtypjes se dates.");}
                tprocess.setTProcessID(gjenerimiTpId(tprocess.getEndDate()));
                idtptxtf.setText(tprocess.getTProcessID());
                try{
                    tprocess=trainingpir.findByEverything(tprocess);
                    if(!tprocess.getStatusi().equals("Active")){
                        int reply = JOptionPane.showConfirmDialog(null, "Trainimi ka ekzistuar më parë a dëshiron me e kthy trainimin e vjetër ? Shtyp Po për të kthyer trainimin e vjetër.Shtyp Jo për të krijuar trainim të rri", "Trainimi Ka ekzistuar më herët", JOptionPane.YES_NO_OPTION);
                        if (reply == JOptionPane.YES_OPTION) {
                            tprocess.setStatusi("Active");
                            trainingpir.edit(tprocess);
                            return tprocess;
                        }
                        else {
                            return trainingpir.create(tprocess);
                        }
                    }else{
                        int reply = JOptionPane.showConfirmDialog(null, "Trainimi ekziston a dëshiron të krijosh një trainim identik ?", "Trainimi Ekziston", JOptionPane.YES_NO_OPTION);
                        if (reply == JOptionPane.YES_OPTION) {
                            return trainingpir.create(tprocess);
                            
                    }else{
                        return tprocess;
                                    }
                    } 
                
                    }catch (NoResultException nre){
                    return trainingpir.create(tprocess);
                }      
    }
    
    
    private void createTopicsCovered (TrainingProcess tempTP)throws AppException{
            
            String sa=topicscoveredalbaniantxtf.getText().trim();
            String se=topicscoveredenglishtxtf.getText().trim();
            String ss=topicscoveredserbiantxtf.getText().trim();
            List<String> TopicsCoveredListAlbanian = Arrays.asList(sa.split("\\s*;\\s*"));
            List<String> TopicsCoveredListEnglish=Arrays.asList(se.split("\\s*;\\s*"));
            List<String> TopicsCoveredListSerbian=Arrays.asList(ss.split("\\s*;\\s*"));
            ArrayList<TopicsCovered> topics=new ArrayList<TopicsCovered>();
            for (String titleOfTraining : TopicsCoveredListAlbanian){
                if(titleOfTraining!=null &&!titleOfTraining.trim().isEmpty())
                topics.add(new TopicsCovered(titleOfTraining,"Albanian"));
            }
            for (String titleOfTraining : TopicsCoveredListEnglish){
                if(titleOfTraining!=null &&!titleOfTraining.trim().isEmpty())
                topics.add(new TopicsCovered(titleOfTraining,"English"));
            }
            for (String titleOfTraining : TopicsCoveredListSerbian){
                if(titleOfTraining!=null &&!titleOfTraining.trim().isEmpty())
                topics.add(new TopicsCovered(titleOfTraining,"Serbian"));
            }
            for (TopicsCovered tc : topics){
                if (tc.getTopicCovered()!=null && !tc.getTopicCovered().trim().isEmpty()){
                try{
                tc=topicscir.findByTopicsCovered(tc);
                    if(!tc.getStatusi().equals("Active")){
                        tc.setStatusi("Active");
                        topicscir.edit(tc);
                    }
                }catch (NoResultException nre){
                tc=topicscir.create(tc);
                }
                
                // QETU KRIJOHEN TOPICS COMBO 
                TopicsCombo tpc=new TopicsCombo();
                tpc.setTProcessID(tempTP);
                tpc.setTopicID(tc);
                try{
                tpc=topiccomboir.findByTpTc(tpc);
                if(!tpc.getStatusi().equals("Active")){
                        tpc.setStatusi("Active");
                        topiccomboir.edit(tpc);
                }
                }catch (NoResultException nre2){
                    topiccomboir.create(tpc);
                }
                }
            }       
            }
    
    private TrainersTeam createTrainersTeam(ArrayList<Trainer> Trainers) throws AppException {
        TrainersTeam tteam;
        
        try{
              tteam=trainerTeamIr.findByParticipants(Trainers);
              if (!tteam.getStatusi().equals("Active")){
                  tteam.setStatusi("Active");
                  trainerTeamIr.edit(tteam);
              }
              return tteam;
        }catch(NoResultException nre1){
            return trainerTeamIr.create(new TrainersTeam());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        trainingprocesstbl = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        trainertbl = new javax.swing.JTable();
        trainerNameLbl = new javax.swing.JLabel();
        placetptxtf = new javax.swing.JTextField();
        instructionLbl = new javax.swing.JLabel();
        startdate = new com.toedter.calendar.JDateChooser();
        enddate = new com.toedter.calendar.JDateChooser();
        shtobtn = new javax.swing.JButton();
        fshijbtn = new javax.swing.JButton();
        pastrobtn = new javax.swing.JButton();
        shtorreshtbtn = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        titleoftrainingserbiantxtf = new javax.swing.JTextField();
        titleoftrainingenglishtxtf = new javax.swing.JTextField();
        titleoftrainingalbaniantxtf = new javax.swing.JTextField();
        projecttxtf = new javax.swing.JTextField();
        idtptxtf = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        topicscoveredserbiantxtf = new javax.swing.JTextField();
        topicscoveredenglishtxtf = new javax.swing.JTextField();
        topicscoveredalbaniantxtf = new javax.swing.JTextField();
        clienttxtf = new javax.swing.JTextField();
        background = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setClosable(true);
        setTitle("SHTO TRAJNIM");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        trainingprocesstbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        trainingprocesstbl.setShowHorizontalLines(false);
        trainingprocesstbl.setShowVerticalLines(false);
        jScrollPane2.setViewportView(trainingprocesstbl);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 270, 1040, 210));

        trainertbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        trainertbl.setShowHorizontalLines(false);
        trainertbl.setShowVerticalLines(false);
        jScrollPane3.setViewportView(trainertbl);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 240, 210));

        trainerNameLbl.setText("Trainerët");
        jPanel1.add(trainerNameLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 252, 100, -1));

        placetptxtf.setBackground(new java.awt.Color(225, 225, 225));
        placetptxtf.setBorder(null);
        jPanel1.add(placetptxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 23, 153, 20));

        instructionLbl.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        instructionLbl.setForeground(new java.awt.Color(255, 0, 0));
        jPanel1.add(instructionLbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 180, 580, 20));

        startdate.setBackground(new java.awt.Color(225, 225, 225));
        startdate.setDateFormatString("dd-MM-yyyy");
        startdate.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                startdateInputMethodTextChanged(evt);
            }
        });
        jPanel1.add(startdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 68, 130, -1));

        enddate.setBackground(new java.awt.Color(225, 225, 225));
        enddate.setDateFormatString("dd-MM-yyyy");
        jPanel1.add(enddate, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 110, 130, -1));

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
        jPanel1.add(shtobtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 200, 160, 50));

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
        jPanel1.add(fshijbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 200, 160, 50));

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
        jPanel1.add(pastrobtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 200, 180, 50));

        shtorreshtbtn.setBackground(new java.awt.Color(105, 3, 3));
        shtorreshtbtn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        shtorreshtbtn.setForeground(new java.awt.Color(255, 255, 255));
        shtorreshtbtn.setText("Shto rresht");
        shtorreshtbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shtorreshtbtnActionPerformed(evt);
            }
        });
        jPanel1.add(shtorreshtbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 220, -1, -1));

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        titleoftrainingserbiantxtf.setBackground(new java.awt.Color(225, 225, 225));
        titleoftrainingserbiantxtf.setBorder(null);
        jPanel3.add(titleoftrainingserbiantxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 198, 275, 20));

        titleoftrainingenglishtxtf.setBackground(new java.awt.Color(225, 225, 225));
        titleoftrainingenglishtxtf.setBorder(null);
        jPanel3.add(titleoftrainingenglishtxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 152, 275, 20));

        titleoftrainingalbaniantxtf.setBackground(new java.awt.Color(225, 225, 225));
        titleoftrainingalbaniantxtf.setBorder(null);
        jPanel3.add(titleoftrainingalbaniantxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 108, 275, 20));

        projecttxtf.setBackground(new java.awt.Color(225, 225, 225));
        projecttxtf.setBorder(null);
        jPanel3.add(projecttxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 65, 275, 20));

        idtptxtf.setEditable(false);
        idtptxtf.setBackground(new java.awt.Color(225, 225, 225));
        idtptxtf.setBorder(null);
        jPanel3.add(idtptxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 23, 125, 20));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 320, 230));

        jPanel4.setOpaque(false);
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        topicscoveredserbiantxtf.setBackground(new java.awt.Color(225, 225, 225));
        topicscoveredserbiantxtf.setBorder(null);
        jPanel4.add(topicscoveredserbiantxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 157, 275, 20));

        topicscoveredenglishtxtf.setBackground(new java.awt.Color(225, 225, 225));
        topicscoveredenglishtxtf.setBorder(null);
        jPanel4.add(topicscoveredenglishtxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 108, 275, 20));

        topicscoveredalbaniantxtf.setBackground(new java.awt.Color(225, 225, 225));
        topicscoveredalbaniantxtf.setBorder(null);
        jPanel4.add(topicscoveredalbaniantxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 64, 275, 20));

        clienttxtf.setBackground(new java.awt.Color(225, 225, 225));
        clienttxtf.setBorder(null);
        jPanel4.add(clienttxtf, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 19, 115, 20));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 0, 305, 195));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Photos/Shtotrajnim2.png"))); // NOI18N
        jPanel1.add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 490));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void startdateInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_startdateInputMethodTextChanged
    }//GEN-LAST:event_startdateInputMethodTextChanged

    private void shtobtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shtobtnActionPerformed
    projecttxtf.requestFocus();
    TrainingProcess tempTP;
    Client tempc;
    
    Project tempP;
    Training tempT;
    TrainingProject trainingp;
    ArrayList<Trainer> Trainers = new ArrayList();
    
    try {
        checkTrainingForm();
        validation();
        tempP=createProject();
        tempT=createTraining();
        trainingp=createTrainingProject(tempT,tempP);
        tempc=createClient();
        createTrainer(Trainers);
        TrainersTeam temp=createTrainersTeam(Trainers);
        createTrainerTeamCombo(Trainers,temp);
        tempTP=createTrainingProcess(trainingp,temp,tempc);
        createTopicsCovered(tempTP);
        shtoCreateLog(tempTP.getTProcessID());
        emptyObject();
        trainingProcessTabelaLoad();
        JOptionPane.showMessageDialog(null, "Trajnimi u shtua me sukses.");
    } catch (AppException ex){
        checkTrainingForm();
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this,ex.getMessage());
    }
    }//GEN-LAST:event_shtobtnActionPerformed

    private void pastrobtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pastrobtnActionPerformed
        emptyObject();
    }//GEN-LAST:event_pastrobtnActionPerformed

    private void shtorreshtbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shtorreshtbtnActionPerformed
        AddTrainerRow();
    }//GEN-LAST:event_shtorreshtbtnActionPerformed

    private void fshijbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fshijbtnActionPerformed
        try {
            if(trainingprocesstbl.getSelectedRow()!=-1){
                String[] opcionet={"Po","Jo"};
                int response = JOptionPane.showOptionDialog(null,
                "A dëshiron me e fshi Trainimin ?","Kujdesë",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, opcionet, opcionet[0]);
                if(response==0){
                    TrainingProcess tp=ultimatetm.getTrainingProcess(trainingprocesstbl.getSelectedRow());
                    pasivizoTopicsComboAndTopicsCovered(tp);
                    pasivizoTrainingProcess(tp);
                    pasivizoCompanyAndCTPCombo(tp);
                    emptyObject();
                    trainingProcessTabelaLoad();
                    shtoDeleteLog();
                    JOptionPane.showMessageDialog(null,"Trainimi u fshi me sukses");
                }
            }else
                throw new AppException ("Duhet të selektoni trainimin të cilin dëshironi ta fshini. ");
        } catch (AppException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_fshijbtnActionPerformed
    
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
        startdate.setCursor(cursor);
        enddate.setCursor(cursor);
        jScrollPane2.setCursor(cursor);
        jScrollPane3.setCursor(cursor);
        this.setCursor(cursor);
        
        
        
        
        startdate.setCursor(buttoncursor);
        enddate.setCursor(buttoncursor);
        shtobtn.setCursor(buttoncursor);
        fshijbtn.setCursor(buttoncursor);
        pastrobtn.setCursor(buttoncursor);
        shtorreshtbtn.setCursor(buttoncursor);
        
        
        
        clienttxtf.setCursor(textcursor);
        idtptxtf.setCursor(textcursor);
        projecttxtf.setCursor(textcursor);
        titleoftrainingalbaniantxtf.setCursor(textcursor);
        titleoftrainingenglishtxtf.setCursor(textcursor);
        titleoftrainingserbiantxtf.setCursor(textcursor);
        topicscoveredalbaniantxtf.setCursor(textcursor);
        topicscoveredenglishtxtf.setCursor(textcursor);
        topicscoveredserbiantxtf.setCursor(textcursor);
        placetptxtf.setCursor(textcursor);
        
        
    }
    
    public void shtoCreateLog(String tpId) throws AppException{
        
        
        Logs l=new Logs();
        
        Users us=userir.findByUser(user.getUsername());
        
        
        Date date = logsir.findDate();
        Time time=logsir.findTime();
        Date d1;
        Date d2;
        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat sdf2 = new SimpleDateFormat("HH:mm");
        String today=sdf.format(date);
        String koha=sdf2.format(date);
        
        String Mesazhi=user.getName()+" "+user.getSurname()+" me username-in: "+user.getUsername()+" Ka shtuar trajnimin "+tpId+" në datën : "+today +" në ora "+koha;
        
        l.setUseri(user.getName()+" "+user.getSurname()+" - "+user.getUsername());
        l.setKoha(time);
        l.setDita(date);
        l.setMesazhi(Mesazhi);
        l.setLloji("Create");
        l.setUsername(us);
        
        logsir.create(l);
    }
    
    public void loadDropDown(){
        TrainingProcess tp;
        Training t;
        TopicsCovered tc;
        Project p;
        Client c;
        
        
        List<TrainingProcess> trainps=trainingpir.findAll();
        List<Training> trains=trainingir.findAll();
        List<Object> topicsal = topicscir.findByLang("Albanian");
        List<Object> topicsen = topicscir.findByLang("English");
        List<Object> topicssr = topicscir.findByLang("Serbian");
        List<Project> pros=projectir.findAll();
        List<Client> clis=clientir.findAll();
        ArrayList<String> s;
        Object[] projects = new Object[projectir.findAll().size()];
        Object[] titlesalb = new Object[trainingir.findAll().size()];
        Object[] titleseng = new Object[trainingir.findAll().size()];
        Object[] titlessrb = new Object[trainingir.findAll().size()];
        Object[] clients = new Object[clientir.findAll().size()];
        Object[] topicsalb = new Object[topicscir.findByLang("Albanian").size()];
        Object[] topicseng = new Object[topicscir.findByLang("English").size()];
        Object[] topicssrb = new Object[topicscir.findByLang("Serbian").size()];
        Object[] places = new Object[trainingpir.findAll().size()];
        
        for(int i=0;i<projectir.findAll().size();i++){
            p=pros.get(i);
            projects[i]=p.getEmri();
            
        }
        
        for(int i=0;i<trainingir.findAll().size();i++){
            t=trains.get(i);
            titlesalb[i]=t.getTitleOfTrainingAlbanian();
            titleseng[i]=t.getTitleOfTrainingEnglish();
            titlessrb[i]=t.getTitleOfTrainingSerbian();
            
        }
        
        for(int i=0;i<topicscir.findByLang("Albanian").size();i++){
            tc=(TopicsCovered) topicsal.get(i);
            topicsalb[i]=tc.getTopicCovered();
            
        }
        
        for(int i=0;i<topicscir.findByLang("English").size();i++){
            tc=(TopicsCovered) topicsen.get(i);
            topicseng[i]=tc.getTopicCovered();
            
        }
        
        for(int i=0;i<topicscir.findByLang("Serbian").size();i++){
            tc=(TopicsCovered) topicssr.get(i);
            topicssrb[i]=tc.getTopicCovered();
            
        }
        
        for(int i=0;i<clientir.findAll().size();i++){
            c=clis.get(i);
            clients[i]=c.getEmri();
            
        }
        
        
        
        for(int i=0;i<trainingpir.findAll().size();i++){
            tp=trainps.get(i);
            places[i]=tp.getPlace();
            
        }
        
        TextAutoCompleter projectcomplete=new TextAutoCompleter(projecttxtf);
        TextAutoCompleter trainingalbcomplete=new TextAutoCompleter(titleoftrainingalbaniantxtf);
        TextAutoCompleter trainingengcomplete=new TextAutoCompleter(titleoftrainingenglishtxtf);
        TextAutoCompleter trainingsrbcomplete=new TextAutoCompleter(titleoftrainingserbiantxtf);
        TextAutoCompleter clientcomplete=new TextAutoCompleter(clienttxtf);
        TextAutoCompleter topicsalbcomplete=new TextAutoCompleter(topicscoveredalbaniantxtf);
        TextAutoCompleter topicsengcomplete=new TextAutoCompleter(topicscoveredenglishtxtf);
        TextAutoCompleter topicssrbcomplete=new TextAutoCompleter(topicscoveredserbiantxtf);
        TextAutoCompleter placecomplete=new TextAutoCompleter(placetptxtf);
        
        
        projectcomplete.addItems(projects);
        trainingalbcomplete.addItems(titlesalb);
        trainingengcomplete.addItems(titleseng);
        trainingsrbcomplete.addItems(titlessrb);
        clientcomplete.addItems(clients);
        topicsalbcomplete.addItems(topicsalb);
        topicsengcomplete.addItems(topicseng);
        topicssrbcomplete.addItems(topicssrb);
        placecomplete.addItems(places);
        
    }
    
    public void shtoDeleteLog() throws AppException{
        
        
        Logs l=new Logs();
        
        Users us=userir.findByUser(user.getUsername());
        
        
        Date date = logsir.findDate();
        Time time=logsir.findTime();
        Date d1;
        Date d2;
        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat sdf2 = new SimpleDateFormat("HH:mm");
        String today=sdf.format(date);
        String koha=sdf2.format(date);
        
        String Mesazhi=user.getName()+" "+user.getSurname()+" me username-in: "+user.getUsername()+" Ka fshirë nje trajnim në datën : "+today +" në ora "+koha;
        
        l.setUseri(user.getName()+" "+user.getSurname()+" - "+user.getUsername());
        l.setKoha(time);
        l.setDita(date);
        l.setMesazhi(Mesazhi);
        l.setLloji("Delete");
        l.setUsername(us);
        
        logsir.create(l);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JTextField clienttxtf;
    private com.toedter.calendar.JDateChooser enddate;
    private javax.swing.JButton fshijbtn;
    private javax.swing.JTextField idtptxtf;
    private javax.swing.JLabel instructionLbl;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton pastrobtn;
    private javax.swing.JTextField placetptxtf;
    private javax.swing.JTextField projecttxtf;
    private javax.swing.JButton shtobtn;
    private javax.swing.JButton shtorreshtbtn;
    private com.toedter.calendar.JDateChooser startdate;
    private javax.swing.JTextField titleoftrainingalbaniantxtf;
    private javax.swing.JTextField titleoftrainingenglishtxtf;
    private javax.swing.JTextField titleoftrainingserbiantxtf;
    private javax.swing.JTextField topicscoveredalbaniantxtf;
    private javax.swing.JTextField topicscoveredenglishtxtf;
    private javax.swing.JTextField topicscoveredserbiantxtf;
    private javax.swing.JLabel trainerNameLbl;
    private javax.swing.JTable trainertbl;
    private javax.swing.JTable trainingprocesstbl;
    // End of variables declaration//GEN-END:variables

    private void checkTrainingForm() {
        
        if(projecttxtf.getText().trim().isEmpty())
            instructionLbl.setText("Shkruani emrin e Projektit.");
        else if(titleoftrainingalbaniantxtf.getText().trim().isEmpty())
            instructionLbl.setText("Shkruani titullin e trajnimit së paku në Shqip dhe Anglisht.");
        else if(titleoftrainingenglishtxtf.getText().trim().isEmpty())
            instructionLbl.setText("Shkruani titullin e trajnimit së paku në Shqip dhe Anglisht.");
        else if(ttCheck.checkTbl()<1)
            instructionLbl.setText("Shkruani së paku një Trainer.");    
        else if(clienttxtf.getText().trim().isEmpty())
            instructionLbl.setText("Shkruani emrin e klientit.");
        else if(topicscoveredalbaniantxtf.getText().trim().isEmpty())
            instructionLbl.setText("Shkruani temat e trajnimit së paku në Shqip dhe Anglisht.");
        else if(topicscoveredenglishtxtf.getText().trim().isEmpty())
            instructionLbl.setText("Shkruani temat e trajnimit së paku në Shqip dhe Anglisht.");
        else if(placetptxtf.getText().trim().isEmpty())
            instructionLbl.setText("Shkruaje vendin e Mbajtjes se Trajnimit.");
        else if(startdate.getCalendar()==null)
            instructionLbl.setText("Shkruaje daten e fillimit të Mbajtjës së Trajnimit.");
        else if(enddate.getCalendar()==null)
            instructionLbl.setText("Shkruaje daten e perfundimit të Mbajtjës së Trajnimit.");
        else if(startdate.getDate().after(enddate.getDate()))
            instructionLbl.setText("Data e fillimit nuk mund të jetë më vonë se data e mbarimit.");
        else
            instructionLbl.setText("");
    }
    
    private void TrainingFormListeners(){
        TrainingFormDocumentListener(projecttxtf);
        TrainingFormDocumentListener(titleoftrainingalbaniantxtf);
        TrainingFormDocumentListener(titleoftrainingenglishtxtf);
        TrainingFormDocumentListener(titleoftrainingserbiantxtf);
        TrainingFormDocumentListener(clienttxtf);
        TrainingFormDocumentListener(topicscoveredalbaniantxtf);
        TrainingFormDocumentListener(topicscoveredenglishtxtf);
        TrainingFormDocumentListener(topicscoveredserbiantxtf);        
        TrainingFormDocumentListener(placetptxtf);
        JDateChooserFocusListener(startdate);
        JDateChooserFocusListener(enddate);
        trainertbl.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent fe) {
                checkTrainingForm();
            }

            @Override
            public void focusLost(FocusEvent fe) {
                checkTrainingForm();
            }
        
        });
    }
    
    private void TrainingFormDocumentListener(JTextField target){
        target.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de) {
                checkTrainingForm();
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                checkTrainingForm();
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                checkTrainingForm();
            }
        });
        target.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent fe) {
                checkTrainingForm();}

            @Override
            public void focusLost(FocusEvent fe) {
                checkTrainingForm();
            }
        
        });
        
    }
    
    private void JDateChooserFocusListener(JDateChooser target){
        target.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent fe) {
                checkTrainingForm();
            }

            @Override
            public void focusLost(FocusEvent fe) {
                checkTrainingForm();
            }
        });
        
        ((JTextField)target.getDateEditor().getUiComponent()).getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                 checkTrainingForm();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                 checkTrainingForm();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                 checkTrainingForm();
            }
        });
    }

    private void pasivizoTopicsComboAndTopicsCovered(TrainingProcess tp)throws AppException{
        // pasivizimi i TopicsCombo
        List<TopicsCombo>topicsComboList=topiccomboir.findByTrainingProcess(tp);
        for(TopicsCombo temp: topicsComboList){
            if(!temp.getStatusi().equals("Passive")){
            temp.setStatusi("Passive");
            topiccomboir.edit(temp);
            }
        }
        
        //pasivizimi i TopicsCovered
        TopicsCovered topicCovered;
        for(TopicsCombo temp: topicsComboList){
            topicCovered=temp.getTopicID();
        //ne qofte se asnje trainim tjeter so tu e perdor qet topic atehere fshiihet 
            if(!topicscir.isUsedInATrainingProcess(topicCovered)){
                if(!topicCovered.getStatusi().equals("Passive")){
                    topicCovered.setStatusi("Passive");
                    topicscir.edit(topicCovered);
                }
            }
        }
        
        
    }

    private void pasivizoTrainingProcess(TrainingProcess tp)throws AppException{
        Team oldTeam=tp.getTeamID();
        // pasivizimi i TrainingProcessit
        tp.setStatusi("Passive");
        tp.setTeamID(null);
        //Pasivizimi i TrainerTeamit
        /*
        tp.setTrainersTeamID(null);
        */
        trainingpir.edit(tp);
        
        //Pasivizimi i teamit te vjeter
        if(oldTeam!=null){
            if(trainingpir.findByTeam(oldTeam.getTeamID()).isEmpty()){
                if(!oldTeam.getStatusi().equals("Passive")){
                    oldTeam.setStatusi("Passive");
                    teamir.edit(oldTeam);
                }
            }
        }
        
        //Pasivizimi i TTrainerEvaluations per kete Trainim
        List<TTrainerEvaluation>trainerEvaluationList=tteir.findByTrainingProcess(tp);
        for(TTrainerEvaluation tempTTE : trainerEvaluationList){
            if(!tempTTE.getStatusi().equals("Passive")){
                tempTTE.setStatusi("Passive");
                tteir.edit(tempTTE);
            }
        }
        
        
        
        
        List<ParticipantTeam> participantTeamList;    
        //Kontrollon se a ekziston ndonje pjesemarres ne ekipen e vjeter
            try{
                participantTeamList=participantTeamIr.findByTeamId(oldTeam.getTeamID());
                
                //fshirja e participantTeamave 
                for(ParticipantTeam ptTemp : participantTeamList){
                    participantTeamIr.remove(ptTemp);
                }
                
                
                //Pasivizimi i participatingCompanyMember qe nuk marrin pjese ne ndonje Trajnim dhe qe nuk kane asnje vlersime aktive
                for(ParticipantTeam ptTemp : participantTeamList){
                    ParticipatingCompanyMember pcmTemp=ptTemp.getPcmId();
                    if(participantTeamIr.findByPCM(pcmTemp).isEmpty() && tteir.findByPCM(pcmTemp).isEmpty()){
                        if(!pcmTemp.getStatusi().equals("Passive")){
                            pcmTemp.setStatusi("Passive");
                            pcmir.edit(pcmTemp);
                        }
                    }
                }

                //Pasivizimi i Participants qe nuk kane asnje pcm aktive
                Participant iteratingParticipant;
                for(ParticipantTeam ptTemp : participantTeamList){
                    iteratingParticipant=ptTemp.getPcmId().getParticipantID();
                    if(pcmir.findByParticipant(iteratingParticipant).isEmpty()){
                        if(!iteratingParticipant.getStatusi().equals("Passive")){
                            iteratingParticipant.setStatusi("Passive");
                            participantIr.edit(iteratingParticipant);
                        }
                    }
                }
            }catch(NullPointerException npe){
                npe.printStackTrace();
            }
            
            
            //Pasivizimi  i klientit ne qofte se nuk ka asnje trajnim aktive qe eshte duke e perdore.
            Client cli=tp.getKlientiID();
            if(trainingpir.findByClient(tp.getKlientiID()).isEmpty())
                if(!cli.getStatusi().equals("Passive")){
                    cli.setStatusi("Passive");
                    clientir.edit(cli);
                }
            }

    
        private void pasivizoCompanyAndCTPCombo(TrainingProcess tp)throws AppException{
            //pasivizimi i CTPcombove qe gjinden ne kete trainim
            List<CTPcombo> ctpComboList=ctpComboIr.findByTP(tp);
            for (CTPcombo tempCTPCombo : ctpComboList){
                if(!tempCTPCombo.getStatusi().equals("Passive")){
                    tempCTPCombo.setStatusi("Active");
                    ctpComboIr.edit(tempCTPCombo);
                }
            }
            
            //pasivizimi i kompanive qe nuk kane ndonje CTPcombo aktive
            Company tempCompany;
            for(CTPcombo tempCTPCombo : ctpComboList){
                tempCompany=tempCTPCombo.getCompanyID();
                if(ctpComboIr.findByCompanyActive(tempCompany).isEmpty()){
                    if(!tempCompany.getStatusi().equals("Passive")){
                        tempCompany.setStatusi("Passive");
                        companyir.edit(tempCompany);
                    }
                }
            }
        }
    
    }
