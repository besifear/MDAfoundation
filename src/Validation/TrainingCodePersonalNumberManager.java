package Validation;

import PersonalJComponents.UnfocusableRenderer;
import bl.AppException;
import bl.ParticipantInterface;
import bl.ParticipantRepository;
import bl.ParticipantTeamInterface;
import bl.ParticipantTeamRepository;
import bl.TrainerInterface;
import bl.TrainerRepository;
import bl.TrainingProcessInterface;
import bl.TrainingProcessRepository;
import ejb.Participant;
import ejb.ParticipantTeam;
import ejb.TTrainerEvaluation;
import ejb.Trainer;
import ejb.TrainingProcess;
import gui.model.TrainerEvaluationTableModelMini;
import gui.model.TrainingProcessTableModelMini;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableColumn;

public final class TrainingCodePersonalNumberManager {
 private JTextField tCodeTxtf;
 private JTextField personalTxtf;
 private JLabel tCodeLbl;
 private JLabel personalLbl;
 private JTable trainingprocesstbl;
 private TrainerEvaluationTableModelMini trainerEtmm;
 private TrainingProcessTableModelMini trainingptmm;
 private String tCodeStr;
 private String personalStr;

 
 TrainingProcessInterface tpi;
 TrainerInterface trainerir;
 ParticipantInterface parir;
 ParticipantTeamInterface parteamir;
 
 public TrainingCodePersonalNumberManager(JTextField tCodeTxtf, JTextField personalTxtf, JLabel tCodeLbl, JLabel personalLbl,
  JTable trainingprocesstbl, TrainerEvaluationTableModelMini trainerEtmm, TrainingProcessTableModelMini trainingptmm,EntityManager em) {
  tpi = new TrainingProcessRepository(em);
  trainerir = new TrainerRepository(em);
  parir=new ParticipantRepository(em);
  parteamir=new ParticipantTeamRepository(em);
  
  this.tCodeTxtf = tCodeTxtf;
  this.personalTxtf = personalTxtf;
  this.tCodeLbl = tCodeLbl;
  this.personalLbl = personalLbl;
  this.trainerEtmm = trainerEtmm;
  this.trainingptmm = trainingptmm;
  this.trainingprocesstbl = trainingprocesstbl;
  addListeners(this.tCodeTxtf);
  addListeners(this.personalTxtf);
 }



 public void addListeners(JTextField target) {
target.getDocument()
          .addDocumentListener(new DocumentListener() {
   @Override
   public void insertUpdate(DocumentEvent e) {
    validator();
   }

   @Override
   public void removeUpdate(DocumentEvent e) {
    validator();
   }

   @Override
   public void changedUpdate(DocumentEvent e) {
    validator();
   }
  });

  target.addFocusListener(new FocusListener() {

   @Override
   public void focusGained(FocusEvent e) {
    validator();
   }

   @Override
   public void focusLost(FocusEvent e) {}
  });
 }


 public void validator() {
  tCodeLbl.setForeground(Color.RED);
  personalLbl.setForeground(Color.RED);

  if (tCodeTxtf.getText().trim().isEmpty() && personalTxtf.getText().trim().isEmpty()) {
   tCodeStr = "Shkruaje kodin e Trainimit.";
   personalStr = "Shkruaje numrin personal të pjesëmarrësit.";
  } else if (tCodeTxtf.getText().trim().isEmpty()) {
   tCodeStr = "Shkruaje kodin e Trainimit.";
   personalValidation();
  } else if (personalTxtf.getText().trim().isEmpty()) {
   personalStr = "Shkruaje numrin personal të pjesëmarrësit.";
   TrainingCodeValidation();
  } else {
   char[] tCodeCharacters = tCodeTxtf.getText().trim().toCharArray();
  char[] personalCharacters = personalTxtf.getText().trim().toCharArray();

  int iTC = 0;
  int iPN = 0;
  boolean viza = false;
  for (int j = 0; j < tCodeCharacters.length; j++) {
   if (!(tCodeCharacters[j] >= 48 && tCodeCharacters[j] <= 57))
    iPN++;
   if (j == 8) {
    if (tCodeCharacters[j] == '-')
     viza = true;
   }
  }

  for (char c: personalCharacters) {
   if (!(c >= '0' && c <= '9'))
    iPN++;
  }

  if (iTC == 0 && ((iPN == 0 && viza == false) || (iPN == 1 && viza == true))) {
   if (personalCharacters.length < 10 && tCodeCharacters.length < 8) {
    personalStr = "Numri Personal duhet të përmbajë së paku edhe " + (10 - personalCharacters.length) + " karaktera.";
    tCodeStr = "Kodi i Trainimit duhet te ketë së paku edhe " + (8 - tCodeCharacters.length) + " karaktera.";
   } else if (personalCharacters.length < 10 && ((tCodeStr.length() == 8 && viza == false) || (tCodeStr.length() > 9 && viza == true))) {
    personalStr = "Numri Personal duhet të përmbajë së paku edhe " + (10 - personalCharacters.length) + " karaktera.";
    try {
     TrainingProcess tp = tpi.findById(tCodeTxtf.getText());
     tCodeStr = "";
    } catch (NoResultException nre) {
     tCodeStr = "Nuk ekziston trainimi me ID të tillë.";
    }
   } else if (personalCharacters.length == 10 && tCodeCharacters.length < 8) {
    tCodeStr = "Kodi i Trainimit duhet te ketë së paku edhe " + (8 - tCodeCharacters.length) + " karaktera.";
    try {
     Participant p = parir.findByPersonalID(Long.parseLong(personalTxtf.getText().trim()));
     personalStr = p.getName() + " " + p.getSurname() + " është i regjistruar me këtë numër personal në databazë.";
     ParticipantTeam pt = parteamir.findByParticipantTP(p.getParticipantID(), tCodeTxtf.getText().trim());
     personalStr = p.getName() + " " + p.getSurname() + " është i regjistruar në këtë Trainim.";
    } catch (NoResultException nre) {
     personalStr = "";
    }
   } else if (personalCharacters.length < 10 && (tCodeCharacters.length == 9 && viza == true)) {
    personalStr = "Numri Personal duhet të përmbajë së paku edhe " + (10 - personalCharacters.length) + " karaktera.";
    tCodeStr = "Duhet të shkruhet edhe një numër që dallon trainmin.";
   } else if (personalCharacters.length < 10 && (tCodeCharacters.length > 8 && viza == false)) {
    personalStr = "Numri Personal duhet të përmbajë së paku edhe " + (10 - personalCharacters.length) + " karaktera.";
    tCodeStr = "Nuk ekziston trainimi me ID të tillë.";
   } else {
    try {
     TrainingProcess tp = tpi.findById(tCodeTxtf.getText());
     tCodeStr = "";
    } catch (NoResultException nre) {
     tCodeStr = "Nuk ekziston trainimi me ID të tillë.";
    }

    Participant p = null;
    try {
     p = parir.findByPersonalID(Long.parseLong(personalTxtf.getText().trim()));
     personalStr = p.getName() + " " + p.getSurname() + " është i regjistruar me këtë numër personal në databazë.";
    } catch (NoResultException nre) {
     personalStr = "";
    }
    try {
     if (p != null) {
      ParticipantTeam pt = parteamir.findByParticipantTP(p.getParticipantID(), tCodeTxtf.getText().trim());
      personalStr = p.getName() + " " + p.getSurname() + " është i regjistruar në këtë Trainim.";
     }
    } catch (NoResultException nre) {}
   }
  } else if (iTC != 0 && ((iPN == 0 && viza == false) || (iPN == 1 && viza == true))) {
   personalStr = "Numri personal duhet të përmbajë vetëm numra.";
   TrainingCodeValidation();
  } else if (iTC == 0 && (iPN != 0 && viza == false) || (iPN > 1 && viza == true)) {
   tCodeStr = "Kodi i Trainimit duhet të përmbajë vetëm numra.";
   personalValidation();
  } else {
   personalStr = "Numri personal duhet të përmbajë vetëm numra.";
   tCodeStr = "Kodi i Trainimit duhet të përmbajë vetëm numra.";
  }
  }

  setColor();
  tCodeLbl.setText(tCodeStr);
  personalLbl.setText(personalStr);
 }



 private void setColor() {
  if (personalStr.contains("Numri Personal duhet të përmbajë së paku edhe"))
   personalLbl.setForeground(Color.GREEN);
  else if (personalStr.contains("është i regjistruar me këtë numër personal në databazë."))
   personalLbl.setForeground(Color.WHITE);
  else if (personalStr.contains(" është i regjistruar në këtë Trainim."))
   personalLbl.setForeground(Color.RED);
  else if (personalStr.contains("Shkruaje numrin personal të"))
   personalLbl.setForeground(Color.RED);
  else if (personalStr.contains("Numri personal duhet të përmbajë vetëm numra."))
   personalLbl.setForeground(Color.RED);


  if (tCodeStr.contains("Kodi i Trainimit duhet te ketë së paku edhe"))
   tCodeLbl.setForeground(Color.GREEN);
  else if (tCodeStr.contains("Nuk ekziston trainimi me ID"))
   tCodeLbl.setForeground(Color.RED);
  else if (tCodeStr.contains("Duhet të shkruhet edhe një numër"))
   tCodeLbl.setForeground(Color.GREEN);
  else if (tCodeStr.contains("Kodi i Trainimit duhet të përmbajë"))
   tCodeLbl.setForeground(Color.RED);
  else if (tCodeStr.contains("Duhet të shkruhet edhe një numër që dallon trainmin."))
   tCodeLbl.setForeground(Color.GREEN);
  else if (tCodeStr.contains("Kodi i Trainimit duhet të përmbajë vetëm numra."))
   tCodeLbl.setForeground(Color.RED);
 }




 private void bothValidation() {
  char[] tCodeCharacters = tCodeTxtf.getText().trim().toCharArray();
  char[] personalCharacters = personalTxtf.getText().trim().toCharArray();

  int iTC = 0;
  int iPN = 0;
  boolean viza = false;
  for (int j = 0; j < tCodeCharacters.length; j++) {
   if (!(tCodeCharacters[j] >= 48 && tCodeCharacters[j] <= 57))
    iPN++;
   if (j == 8) {
    if (tCodeCharacters[j] == '-')
     viza = true;
   }
  }

  for (char c: personalCharacters) {
   if (!(c >= '0' && c <= '9'))
    iPN++;
  }

  if (iTC == 0 && ((iPN == 0 && viza == false) || (iPN == 1 && viza == true))) {
   if (personalCharacters.length < 10 && tCodeCharacters.length < 8) {
    personalStr = "Numri Personal duhet të përmbajë së paku edhe " + (10 - personalCharacters.length) + " karaktera.";
    tCodeStr = "Kodi i Trainimit duhet te ketë së paku edhe " + (8 - tCodeCharacters.length) + " karaktera.";
   } else if (personalCharacters.length < 10 && ((tCodeStr.length() == 8 && viza == false) || (tCodeStr.length() > 9 && viza == true))) {
    personalStr = "Numri Personal duhet të përmbajë së paku edhe " + (10 - personalCharacters.length) + " karaktera.";
    try {
     TrainingProcess tp = tpi.findById(tCodeTxtf.getText());
     List<Trainer> traineret = trainerir.findByTP(tp.getTProcessID());
     /*nuk jom i sigurt */trainerEtmm.inicialize();
     for(int i=0;i<traineret.size();i++){
        TTrainerEvaluation temp = new TTrainerEvaluation();
        temp.setTrainerID(traineret.get(i));
        temp.setTProcessID(tp);
        trainerEtmm.getData().add(temp);
     }
     trainerEtmm.fireTableDataChanged();
     trainingprocesstbl.setModel(trainerEtmm);
     tCodeStr = "";          
                
       for (int j = 2; j < 5; j++) {
       TableColumn evalationColumn = trainingprocesstbl.getColumnModel().getColumn(j);
       JComboBox comboBox = new JComboBox();
       for (int z = 1; z < 6; z++) {
        comboBox.addItem(z);
       }
       evalationColumn.setCellEditor(new DefaultCellEditor(comboBox));
       trainerEtmm.fireTableDataChanged();
       }
       for (int x = 0; x < 2; x++) {
        TableColumn tc = trainingprocesstbl.getColumnModel().getColumn(x);
        tc.setCellRenderer(new UnfocusableRenderer());
       }
    } catch (NoResultException nre) {
     tCodeStr = "Nuk ekziston trainimi me ID të tillë.";
    }
   } else if (personalCharacters.length == 10 && tCodeCharacters.length < 8) {
    tCodeStr = "Kodi i Trainimit duhet te ketë së paku edhe " + (8 - tCodeCharacters.length) + " karaktera.";
    try {
     Participant p = parir.findByPersonalID(Long.parseLong(personalTxtf.getText().trim()));
     personalStr = p.getName() + " " + p.getSurname() + " është i regjistruar me këtë numër personal në databazë.";
     ParticipantTeam pt = parteamir.findByParticipantTP(p.getParticipantID(), tCodeTxtf.getText().trim());
     personalStr = p.getName() + " " + p.getSurname() + " është i regjistruar në këtë Trainim.";
    } catch (NoResultException nre) {
     personalStr = "";
    }
   } else if (personalCharacters.length < 10 && (tCodeCharacters.length == 9 && viza == true)) {
    personalStr = "Numri Personal duhet të përmbajë së paku edhe " + (10 - personalCharacters.length) + " karaktera.";
    tCodeStr = "Duhet të shkruhet edhe një numër që dallon trainmin.";
   } else if (personalCharacters.length < 10 && (tCodeCharacters.length > 8 && viza == false)) {
    personalStr = "Numri Personal duhet të përmbajë së paku edhe " + (10 - personalCharacters.length) + " karaktera.";
    tCodeStr = "Nuk ekziston trainimi me ID të tillë.";
   } else {
    try {
     TrainingProcess tp = tpi.findById(tCodeTxtf.getText());
     List<Trainer> traineret = trainerir.findByTP(tp.getTProcessID());
     /*nuk jom i sigurt */trainerEtmm.inicialize();
     for(int i=0;i<traineret.size();i++){
        TTrainerEvaluation temp = new TTrainerEvaluation();
        temp.setTrainerID(traineret.get(i));
        temp.setTProcessID(tp);
        trainerEtmm.getData().add(temp);
     }
     trainerEtmm.fireTableDataChanged();
     trainingprocesstbl.setModel(trainerEtmm);
     tCodeStr = "";          
                
       for (int j = 2; j < 5; j++) {
       TableColumn evalationColumn = trainingprocesstbl.getColumnModel().getColumn(j);
       JComboBox comboBox = new JComboBox();
       for (int z = 1; z < 6; z++) {
        comboBox.addItem(z);
       }
       evalationColumn.setCellEditor(new DefaultCellEditor(comboBox));
       trainerEtmm.fireTableDataChanged();
       }
       for (int x = 0; x < 2; x++) {
        TableColumn tc = trainingprocesstbl.getColumnModel().getColumn(x);
        tc.setCellRenderer(new UnfocusableRenderer());
       }
      
            
     
     /*
     List < Trainer > lista = trainerir.findByTP(tp.getTProcessID());
     ArrayList < TTrainerEvaluation > trainerev = new ArrayList();
     for (int k = 0; k < lista.size(); k++) {
      TTrainerEvaluation temp = new TTrainerEvaluation();
      temp.setTrainerID(lista.get(k));
      temp.setTProcessID(tp);
      trainerev.add(temp);
      if(!trainerEtmm.containsEveryElement(trainerev)){
      trainerEtmm.add(trainerev);
      trainingprocesstbl.setModel(trainerEtmm);
      trainerEtmm.fireTableDataChanged();
      for (int j = 2; j < 5; j++) {
       TableColumn evalationColumn = trainingprocesstbl.getColumnModel().getColumn(j);
       JComboBox comboBox = new JComboBox();
       for (int z = 1; z < 6; z++) {
        comboBox.addItem(z);
       }
       evalationColumn.setCellEditor(new DefaultCellEditor(comboBox));
       trainerEtmm.fireTableDataChanged();
       for (int x = 0; x < 2; x++) {
        TableColumn tc = trainingprocesstbl.getColumnModel().getColumn(x);
        tc.setCellRenderer(new UnfocusableRenderer());
       }
      }
     }
     }
*/
     
    } catch (NoResultException nre) {
     tCodeStr = "Nuk ekziston trainimi me ID të tillë.";
    }

    Participant p = null;
    try {
     p = parir.findByPersonalID(Long.parseLong(personalTxtf.getText().trim()));
     personalStr = p.getName() + " " + p.getSurname() + " është i regjistruar me këtë numër personal në databazë.";
    } catch (NoResultException nre) {
     personalStr = "";
    }
    try {
     if (p != null) {
      ParticipantTeam pt = parteamir.findByParticipantTP(p.getParticipantID(), tCodeTxtf.getText().trim());
      personalStr = p.getName() + " " + p.getSurname() + " është i regjistruar në këtë Trainim.";
     }
    } catch (NoResultException nre) {}
   }
  } else if (iTC != 0 && ((iPN == 0 && viza == false) || (iPN == 1 && viza == true))) {
   personalStr = "Numri personal duhet të përmbajë vetëm numra.";
   TrainingCodeValidation();
  } else if (iTC == 0 && (iPN != 0 && viza == false) || (iPN > 1 && viza == true)) {
   tCodeStr = "Kodi i Trainimit duhet të përmbajë vetëm numra.";
   personalValidation();
  } else {
   personalStr = "Numri personal duhet të përmbajë vetëm numra.";
   tCodeStr = "Kodi i Trainimit duhet të përmbajë vetëm numra.";
  }
 }

 private void personalValidation() {
  char[] karakterat = personalTxtf.getText().toCharArray();
  int i = 0;
  for (char c: karakterat) {
   if (!(c >= '0' && c <= '9'))
    i++;
  }
  if (i == 0) {
   if (karakterat.length < 10)
    personalStr = "Numri Personal duhet të përmbajë së paku edhe " + (10 - karakterat.length) + " karaktera.";
   else {
    Participant p = null;
    try {
     p = parir.findByPersonalID(Long.parseLong(personalTxtf.getText().trim()));
     personalStr = p.getName() + " " + p.getSurname() + " është i regjistruar me këtë numër personal në databazë.";
    } catch (NoResultException nre) {
     personalStr = "";
    }
    try {
     if (p != null) {
      ParticipantTeam pt = parteamir.findByParticipantTP(p.getParticipantID(), tCodeTxtf.getText().trim());
      personalStr = p.getName() + " " + p.getSurname() + " është i regjistruar në këtë Trainim.";
     }
    } catch (NoResultException nre) {}
   }
  } else {
   personalStr = "Numri personal duhet të përmbajë vetëm numra.";
  }
 }


 private void TrainingCodeValidation() {
  char[] karakterat = tCodeTxtf.getText().toCharArray();
  int i = 0;
  boolean viza = false;
  for (int j = 0; j < karakterat.length; j++) {
   if (!(karakterat[j] >= 48 && karakterat[j] <= 57))
    i++;
   if (j == 8) {
    if (karakterat[j] == '-')
     viza = true;
   }
  }
  if (i == 0 || (i == 1 && viza)) {
   if (karakterat.length < 8)
    tCodeStr = "Kodi i Trainimit duhet te ketë së paku edhe " + (8 - karakterat.length) + " karaktera.";
   else if ((karakterat.length == 8 && viza == false) || (karakterat.length > 9 && viza == true)) {
    try {
     TrainingProcess tp = tpi.findById(tCodeTxtf.getText());
     List<Trainer> traineret = trainerir.findByTP(tp.getTProcessID());
     /*nuk jom i sigurt */trainerEtmm.inicialize();
     for(int k=0;k<traineret.size();k++){
        TTrainerEvaluation temp = new TTrainerEvaluation();
        temp.setTrainerID(traineret.get(k));
        temp.setTProcessID(tp);
        trainerEtmm.getData().add(temp);
     }
     trainerEtmm.fireTableDataChanged();
     trainingprocesstbl.setModel(trainerEtmm);
     tCodeStr = "";          
                
       for (int j = 2; j < 5; j++) {
       TableColumn evalationColumn = trainingprocesstbl.getColumnModel().getColumn(j);
       JComboBox comboBox = new JComboBox();
       for (int z = 1; z < 6; z++) {
        comboBox.addItem(z);
       }
       evalationColumn.setCellEditor(new DefaultCellEditor(comboBox));
       trainerEtmm.fireTableDataChanged();
       }
       for (int x = 0; x < 2; x++) {
        TableColumn tc = trainingprocesstbl.getColumnModel().getColumn(x);
        tc.setCellRenderer(new UnfocusableRenderer());
       }
     
    } catch (NoResultException nre) {
     tCodeStr = "Nuk ekziston trainimi me ID të tillë.";
    }
   } else if (karakterat.length == 9 && viza == true)
    tCodeStr = "Duhet të shkruhet edhe një numër që dallon trainmin.";
  } else if (i != 0) {
   tCodeStr = "Kodi i Trainimit duhet të përmbajë vetëm numra.";
  }
 }


 public void TrainingCodePersonalNumberException() throws AppException {
  if (tCodeTxtf.getText().trim().isEmpty() && personalTxtf.getText().trim().isEmpty()) {
   throw new AppException("Shkruaje Kodin e Trainimit dhe numrin Personal të pjesëmarrësit.");
  } else if (tCodeTxtf.getText().trim().isEmpty()) {
   throw new AppException("Shkruaje kodin e Trainimit.");
  } else if (personalTxtf.getText().trim().isEmpty()) {
   throw new AppException("Shkruaje numrin personal të pjesëmarrësit.");
  } else {
   char[] tCodeCharacters = tCodeTxtf.getText().trim().toCharArray();
   char[] personalCharacters = personalTxtf.getText().trim().toCharArray();

   int iTC = 0;
   int iPN = 0;
   boolean viza = false;
   for (int j = 0; j < tCodeCharacters.length; j++) {
    if (!(tCodeCharacters[j] >= 48 && tCodeCharacters[j] <= 57))
     iPN++;
    if (j == 8) {
     if (tCodeCharacters[j] == '-')
      viza = true;
    }
   }

   for (char c: personalCharacters) {
    if (!(c >= '0' && c <= '9'))
     iPN++;
   }

   if (iTC == 0 && ((iPN == 0 && viza == false) || (iPN == 1 && viza == true))) {
    if (personalCharacters.length < 10 && tCodeCharacters.length < 8) {
     throw new AppException("Numri Personal duhet të përmbajë së paku edhe " + (10 - personalCharacters.length) + " karaktera ndërsa Kodi i Trainimit duhet te ketë së paku edhe " + (8 - tCodeCharacters.length) + " karaktera.");
    } else if (personalCharacters.length < 10 && ((tCodeStr.length() == 8 && viza == false) || (tCodeStr.length() > 9 && viza == true))) {
     throw new AppException("Numri Personal duhet të përmbajë së paku edhe " + (10 - personalCharacters.length) + " karaktera.");
    } else if (personalCharacters.length == 10 && tCodeCharacters.length < 8) {
     throw new AppException("Kodi i Trainimit duhet te ketë së paku edhe " + (8 - tCodeCharacters.length) + " karaktera.");
    } else if (personalCharacters.length < 10 && (tCodeCharacters.length == 9 && viza == true)) {
     throw new AppException("Numri Personal duhet të përmbajë së paku edhe " + (10 - personalCharacters.length) + " karaktera.");

    } else if (personalCharacters.length < 10 && (tCodeCharacters.length > 8 && viza == false)) {
     throw new AppException("Numri Personal duhet të përmbajë së paku edhe " + (10 - personalCharacters.length) + " karaktera.");
    } else {
     try {
      TrainingProcess tp = tpi.findById(tCodeTxtf.getText());
     trainerir.findByTP(tp.getTProcessID());
     } catch (NoResultException nre) {
      throw new AppException("Nuk ekziston trainimi me ID të tillë.");
     }

     Participant p = null;
     try {
      p = parir.findByPersonalID(Long.parseLong(personalTxtf.getText().trim()));
     } catch (NoResultException nre) {
      personalStr = "";
     }
     try {
      if (p != null) {
       ParticipantTeam pt =parteamir.findByParticipantTP(p.getParticipantID(), tCodeTxtf.getText().trim());
       throw new AppException(p.getName() + " " + p.getSurname() + " është i regjistruar në këtë Trainim.");
      }
     } catch (NoResultException nre) {}
    }
   } else if (iTC != 0 && ((iPN == 0 && viza == false) || (iPN == 1 && viza == true))) {
    throw new AppException("Numri personal duhet të përmbajë vetëm numra.");

   } else if (iTC == 0 && (iPN != 0 && viza == false) || (iPN > 1 && viza == true)) {
    throw new AppException("Kodi i Trainimit duhet të përmbajë vetëm numra.");
   } else {
    throw new AppException("Numri personal dhe Kodi i Trainimit duhet të përmbajë vetëm numra.");
   }
  }
 }

}