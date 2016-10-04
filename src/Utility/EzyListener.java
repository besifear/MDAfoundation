
package Utility;

import PersonalJComponents.UnfocusableRenderer;
import bl.AppException;
import bl.ParticipantRepository;
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

public class EzyListener {
    
    public static void phoneListenerMethod(JTextField jtf,JLabel jl){
    if(jtf.getText().isEmpty()){
               jl.setText("Shkruaje numri e telefonit të Pjesëmarrësit.");
           }
           else
           {
               
           char[] karakterat = jtf.getText().toCharArray();
            int i = 0;
            for (int j = 0; j < karakterat.length; j++) {
            if (!(karakterat[j] >= 48 && karakterat[j] <= 57 ) && karakterat[j]!='+' && karakterat[j]!='-' )
                i++;
            }
            if (i==0)
               jl.setText("");
            else 
                jl.setText("Numri i telefonit mund të përmbajë vetëm numra dhe + .");
           }
    }
    
    
    
public static void phoneTextFieldDocumentListner(JTextField jtf,JLabel jl){
    jtf.getDocument().addDocumentListener(new DocumentListener(){
        @Override
        public void insertUpdate(DocumentEvent e) {
          phoneListenerMethod(jtf, jl);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
           phoneListenerMethod(jtf, jl);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
           phoneListenerMethod(jtf, jl);
        }
        
        

        });
    
    jtf.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                phoneListenerMethod(jtf, jl);
            }

            @Override
            public void focusLost(FocusEvent e) {
                //Your code here
            }
        });
}    
    
    
public static void TextFieldDocumentListner(JTextField jtf,JLabel jl,String s){
    jtf.getDocument().addDocumentListener(new DocumentListener(){
        @Override
        public void insertUpdate(DocumentEvent e) {
           if(jtf.getText().isEmpty()){
               jl.setText(s);
           }
           else 
               jl.setText("");
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
           if(jtf.getText().isEmpty()){
               jl.setText(s);
               
           }
           else 
               jl.setText("");
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
           if(jtf.getText().isEmpty()){
               jl.setText(s);
           }
           else 
               jl.setText("");
        }
        
        

        });
    
    jtf.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if(jtf.getText().isEmpty())
                    jl.setText(s);
            }

            @Override
            public void focusLost(FocusEvent e) {
                //Your code here
            }
        });
}



    public static void MultipleTextFieldDocumentListner(JTextField jtf1,JTextField jtf2,JTextField jtf3,JLabel jl1,JLabel jl2,JLabel jl3,String s){
        
        jtf1.getDocument().addDocumentListener(new DocumentListener(){
        @Override
        public void insertUpdate(DocumentEvent e) {
           if(jtf1.getText().isEmpty() && jtf2.getText().isEmpty()
                   && jtf3.getText().isEmpty()){
               jl1.setText(s);
           }
           else {
               jl1.setText("");
               jl2.setText("");
               jl3.setText("");
           }
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
           if(jtf1.getText().isEmpty() && jtf2.getText().isEmpty()
                   && jtf3.getText().isEmpty()){
               jl1.setText(s);
           }
           else {
               jl1.setText("");
               jl2.setText("");
               jl3.setText("");
           }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
           if(jtf1.getText().isEmpty() && jtf2.getText().isEmpty()
                   && jtf3.getText().isEmpty()){
               jl1.setText(s);
           }
           else {
               jl1.setText("");
               jl2.setText("");
               jl3.setText("");
           }
        }

        });
        
        jtf2.getDocument().addDocumentListener(new DocumentListener(){
        @Override
        public void insertUpdate(DocumentEvent e) {
           if(jtf1.getText().isEmpty() && jtf2.getText().isEmpty()
                   && jtf3.getText().isEmpty()){
               jl2.setText(s);
           }
           else {
               jl1.setText("");
               jl2.setText("");
               jl3.setText("");
           }
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
           if(jtf1.getText().isEmpty() && jtf2.getText().isEmpty()
                   && jtf3.getText().isEmpty()){
               jl2.setText(s);
           }
           else {
               jl1.setText("");
               jl2.setText("");
               jl3.setText("");
           }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
           if(jtf1.getText().isEmpty() && jtf2.getText().isEmpty()
                   && jtf3.getText().isEmpty()){
               jl2.setText(s);
           }
           else {
               jl1.setText("");
               jl2.setText("");
               jl3.setText("");
           }
        }

        });
        
        
    jtf3.getDocument().addDocumentListener(new DocumentListener(){
        @Override
        public void insertUpdate(DocumentEvent e) {
           if(jtf1.getText().isEmpty() && jtf2.getText().isEmpty()
                   && jtf3.getText().isEmpty()){
               jl3.setText(s);
           }
           else {
               jl1.setText("");
               jl2.setText("");
               jl3.setText("");
           }
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
           if(jtf1.getText().isEmpty() && jtf2.getText().isEmpty()
                   && jtf3.getText().isEmpty()){
               jl3.setText(s);
           }
           else {
               jl1.setText("");
               jl2.setText("");
               jl3.setText("");
           }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
           if(jtf1.getText().isEmpty() && jtf2.getText().isEmpty()
                   && jtf3.getText().isEmpty()){
               jl3.setText(s);
           }
           else {
               jl1.setText("");
               jl2.setText("");
               jl3.setText("");
           }
        }

        });
    
    jtf1.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if(jtf1.getText().isEmpty() && jtf2.getText().isEmpty()
                   && jtf3.getText().isEmpty()){
               jl1.setText("Shkruaje Titullin e Trainimit së paku në një gjuhë.");
               jl2.setText("");
               jl3.setText("");
                }
                else{
                jl1.setText("");
                jl2.setText("");
                jl3.setText("");
                }
        } 

        @Override
        public void focusLost(FocusEvent e) {
        }
        });
    
    jtf2.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if(jtf1.getText().isEmpty() && jtf2.getText().isEmpty()
                   && jtf3.getText().isEmpty()){
               jl1.setText("");
               jl2.setText("Shkruaje Titullin e Trainimit së paku në një gjuhë.");
               jl3.setText("");
                }
                else{
                jl1.setText("");
                jl2.setText("");
                jl3.setText("");
                }
        } 

        @Override
        public void focusLost(FocusEvent e) {
        }
        });
    
    jtf3.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                if(jtf1.getText().isEmpty() && jtf2.getText().isEmpty()
                   && jtf3.getText().isEmpty()){
               jl1.setText("");
               jl2.setText("");
               jl3.setText("Shkruaje Titullin e Trainimit së paku në një gjuhë.");
                }
                else{
                jl1.setText("");
                jl2.setText("");
                jl3.setText("");
                }
        } 

        @Override
        public void focusLost(FocusEvent e) {
        }
        });
    
    
    }
    
    
    public static void JComboBoxDocumentListner(JComboBox jcb,JLabel jl,String s){
    jcb.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent ae) {
            if(jcb.getSelectedIndex()==0)
                jl.setText(s);
            else
                jl.setText("");
        }
    });
    
    jcb.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
            if(jcb.getSelectedIndex()==0)
                jl.setText(s);
            else
                jl.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                //Your code here
            }
        });
}
    
    public static void DayListener(JComboBox year,JComboBox month,JComboBox day,JLabel jl){
        day.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                if((year.getSelectedIndex()!=0 || year.getSelectedIndex()!=year.getItemCount()-1) 
                && month.getSelectedIndex()!=0 && day.getSelectedIndex()!=0)
            jl.setText("");
        else 
            jl.setText("Caktojeni Datën e lindjës së pjesëmarrësit.");

            }
        });
        
        day.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent fe) {
            if((year.getSelectedIndex()!=0 || year.getSelectedIndex()!=year.getItemCount()-1) 
                && month.getSelectedIndex()!=0 && day.getSelectedIndex()!=0)
            jl.setText("");
        else 
            jl.setText("Caktojeni Datën e lindjës së pjesëmarrësit.");
}

            @Override
            public void focusLost(FocusEvent fe) {
            if((year.getSelectedIndex()!=0 || year.getSelectedIndex()!=year.getItemCount()-1) 
                && month.getSelectedIndex()!=0 && day.getSelectedIndex()!=0)
            jl.setText("");
        else 
            jl.setText("Caktojeni Datën e lindjës së pjesëmarrësit.");
}
        });
        
    }
    
    public static void JComboBoxYearListner(JComboBox target,JComboBox year,JComboBox month,JComboBox day,JLabel jl){
    target.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent ae) {
            if((year.getSelectedIndex()!=0 || year.getSelectedIndex()!=year.getItemCount()-1) 
                && month.getSelectedIndex()!=0 && day.getSelectedIndex()!=0)
            jl.setText("");
        else 
            jl.setText("Caktojeni Datën e lindjës së pjesëmarrësit.");
            
                if(year.getSelectedIndex()!=0 && year.getSelectedIndex()!=year.getItemCount() && month.getSelectedIndex()!=0){
                    int i=Integer.parseInt(day.getItemAt(day.getItemCount()-1).toString());
                    switch(month.getSelectedItem().toString()){
                        case "1":
                        case "3":
                        case "5":
                        case "7":
                        case "8":
                        case "10":
                        case "12":
                            if(i!=31){
                                while (i<31)
                                    day.addItem(++i+"");
                            }
                            
                            break;
                        case "2":
                            if(Integer.parseInt(year.getSelectedItem().toString())%4==0){
                                if(i<29)
                                    while (i<29){
                                        day.addItem(++i+"");
                                    }
                                else
                                    while (i>29){
                                        day.removeItemAt(day.getItemCount()-1);
                                        i--;
                                    }
                            }
                            else{ 
                                if (i>28){
                                    while(i>28){
                                        day.removeItemAt(day.getItemCount()-1);
                                        i--;
                                    }
                                }
                            }
                            break;
                        case "4":
                        case "6":
                        case "9":
                        case "11":
                            if(i<30)
                                    while (i<30){
                                        day.addItem(++i+"");
                                    }
                                else
                                    while (i>30){
                                        day.removeItemAt(day.getItemCount()-1);
                                        i--;
                                    }
                            break;
                    }
                }
                
        }
    });
    
    target.addFocusListener(new FocusListener(){
        
            
        

        @Override
        public void focusGained(FocusEvent fe) {
            if((year.getSelectedIndex()!=0 || year.getSelectedIndex()!=year.getItemCount()-1) 
                && month.getSelectedIndex()!=0 && day.getSelectedIndex()!=0)
            jl.setText("");
            else 
            jl.setText("Caktojeni Datën e lindjës së pjesëmarrësit.");
            
            if(year.getSelectedIndex()!=0){
                if(month.getSelectedIndex()!=0){
                    int i=Integer.parseInt(day.getItemAt(day.getItemCount()-1).toString());
                    switch(month.getSelectedItem().toString()){
                        case "1":
                        case "3":
                        case "5":
                        case "7":
                        case "8":
                        case "10":
                        case "12":
                            if(i!=31){
                                while (i<31)
                                    day.addItem(++i+"");
                            }
                            
                            break;
                        case "2":
                            if(Integer.parseInt(year.getSelectedItem().toString())%4==0){
                                if(i<29)
                                    while (i<29){
                                        day.addItem(++i+"");
                                    }
                                else
                                    while (i>29){
                                        day.removeItemAt(day.getItemCount()-1);
                                        i--;
                                    }
                            }
                            else{ 
                                if (i>28){
                                    while(i>28){
                                        day.removeItemAt(day.getItemCount()-1);
                                        i--;
                                    }
                                }
                            }
                            break;
                        case "4":
                        case "6":
                        case "9":
                        case "11":
                            if(i<30)
                                    while (i<30){
                                        day.addItem(++i+"");
                                    }
                                else
                                    while (i>30){
                                        day.removeItemAt(day.getItemCount()-1);
                                        i--;
                                    }
                            break;
                    }
                }
            }}

        @Override
        public void focusLost(FocusEvent fe) {
            if((year.getSelectedIndex()!=0 || year.getSelectedIndex()!=year.getItemCount()-1) 
                && month.getSelectedIndex()!=0 && day.getSelectedIndex()!=0)
            jl.setText("");
            else 
            jl.setText("Caktojeni Datën e lindjës së pjesëmarrësit.");
            
            if(year.getSelectedIndex()!=0){
                if(month.getSelectedIndex()!=0){
                    switch(month.getSelectedItem().toString()){
                        case "1":
                        case "3":
                        case "5":
                        case "7":
                        case "8":
                        case "10":
                        case "12":
                            int i;
                            if(day.getItemCount()-1==28)
                                i=28;
                            else if (day.getItemCount()-1==29)
                                i=29;
                            else if (day.getItemCount()-1==30)
                                i=30;
                            else
                                i=31;
                                while(i<31){
                                    day.addItem(++i+"");
                                }
                            break;
                        case "2":
                            if(Integer.parseInt(year.getSelectedItem().toString())%4==0){
                                
                                if(day.getItemCount()-1==28)
                                    day.addItem(29+"");
                                else if(day.getItemCount()==30)
                                    day.removeItem(day.getItemCount()-1);
                                else if(day.getItemCount()==31){
                                    day.removeItem(day.getItemCount()-1);
                                    day.removeItem(day.getItemCount()-1);
                                }
                                else{
                                    if(day.getItemCount()==29)
                                        i=1;
                                    else if (day.getItemCount()==30)
                                        i=2;
                                    else if (day.getItemCount()==31)
                                        i=3;
                                    else i=0;
                                    while(i>0){
                                        day.removeItemAt(day.getItemCount()-1);
                                        i--;
                                    }
                                }
                            }
                            break;
                            
                        case "4":
                        case "6":
                        case "9":
                        case "11":
                            if(day.getItemCount()-1==28){
                                day.addItem(29+"");
                                day.addItem(30+"");
                            }
                            else if(day.getItemCount()-1==29)
                                day.addItem(30+"");
                            else if(day.getItemCount()-1==31)
                                day.removeItemAt(day.getItemCount()-1);
                            break;
                    }
                }
            }}
    });
}   
}