
package Validation;

import bl.AppException;
import bl.ParticipantInterface;
import bl.ParticipantRepository;
import bl.ParticipantTeamInterface;
import bl.ParticipantTeamRepository;
import bl.TrainingProcessInterface;
import bl.TrainingProcessRepository;
import com.toedter.calendar.JDateChooser;
import ejb.Participant;
import ejb.ParticipantTeam;
import ejb.Trainer;
import ejb.TrainingProcess;
import gui.model.AddTrainerTableModel;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class Validation {
 
    public static void TextFieldTrainingCodeValidation(JTextField jtf,JLabel jl,String s1,String s2,EntityManager em){
        TrainingProcessInterface tpi=new TrainingProcessRepository(em);
        
            char [] karakterat=jtf.getText().toCharArray();
                if(jtf.getText().isEmpty()){
                    jl.setText(s1);
                }
                else {
                    int i=0;
                    boolean viza=false;
                    for(int j=0; j<karakterat.length;j++){
                            if(!(karakterat[j]>=48 && karakterat[j]<=57))
                                i++;
                            if(j==8){
                                if(karakterat[j]=='-')
                                    viza=true;
                            }
                    }
                    if(i==0 || (i==1 && viza)){
                            if(karakterat.length<8){
                                jl.setForeground(Color.GREEN);
                                jl.setText("Kodi i Trainimit duhet te ketë së paku edhe "+(8-karakterat.length)+" karaktera.");
                            }
                            else if((karakterat.length==8 && viza==false) || (karakterat.length>9 && viza==true) ){
                                try{
                                    tpi.findById(jtf.getText());
                                    jl.setText("");
                                }catch(NoResultException nre){
                                    jl.setForeground(Color.RED);
                                    jl.setText("Nuk ekziston trainimi me ID të tillë.");
                                }
                            }
                            else if (karakterat.length==9 && viza==true)
                                jl.setText("");
                    }
                    else if(i!=0){
                        jl.setForeground(Color.RED);
                        jl.setText(s2+" duhet të përmbajë vetëm numra.");
                    }
                }
        
}
    
    
    
    public static void TextFieldValdiation(JTextField jtf,JLabel jl,String s){
    if(jtf.getText().isEmpty())
               jl.setText(s);
           else 
               jl.setText("");
    }
    
    public static void DobValidation(JComboBox year,JComboBox month,JComboBox day,JLabel jl){
        if((year.getSelectedIndex()!=0 || year.getSelectedIndex()!=year.getItemCount()-1) 
                && month.getSelectedIndex()!=0 && day.getSelectedIndex()!=0)
            jl.setText("");
        else 
            jl.setText("Caktojeni Datën e lindjës së pjesëmarrësit.");
    }
    
    public static void JComboBoxValidation(JComboBox jcb,JLabel jl,String s){
        if(jcb.getSelectedIndex()==0)
                jl.setText(s);
            else
                jl.setText("");
    }
    
    public static void MultipleTextFieldValdiation(JTextField jtf1,JTextField jtf2,JTextField jtf3,JLabel jl1,JLabel jl2,JLabel jl3,String s){
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
    
    public static void JDateChoserValidation(JDateChooser jdc,JLabel jl,String s){
        if (jdc.getCalendar()==null)
            jl.setText(s);
    }
    
    public static void DobException(JComboBox year,JComboBox month,JComboBox day)throws AppException{
    if(!((year.getSelectedIndex()!=0 || year.getSelectedIndex()!=year.getItemCount()-1) 
                && month.getSelectedIndex()!=0 && day.getSelectedIndex()!=0))
            throw new AppException("Caktojeni Datën e lindjës së pjesëmarrësit.");
    }
    
    public static void TextFieldException(JTextField jtf,String s)throws AppException{
        if (jtf.getText()==null || jtf.getText().isEmpty())
            throw new AppException (s);
    }
    
    public static void TextFieldIntException(JTextField jtf,String s1,String s2,TrainingProcess tp,EntityManager em)throws AppException{
        ParticipantInterface pir=new ParticipantRepository(em);
        ParticipantTeamInterface ptir=new ParticipantTeamRepository(em);
        
        char [] karakterat=jtf.getText().toCharArray();
                if(jtf.getText().isEmpty()){
                     throw new AppException (s1);
                }
                else {
                    int i=0;
                    for(char c : karakterat){
                        if(!(c>='0' && c<='9'))
                            i++;
                    }
                    if(i==0){
                        if(karakterat.length<10){
                            throw new AppException (s2+" duhet të përmbajë së paku edhe "+(10-karakterat.length)+" karaktera.");
                        }
                        else{
                            try{
                            Participant p= pir.findByPersonalID(Long.parseLong(jtf.getText()));
                            if(ptir.CheckIfInTraining(p.getIDNumber(),tp.getTProcessID()))
                                throw new AppException (p.getName()+" "+p.getSurname()+" është i regjistruar në këtë Trainim.");
                            }catch(NoResultException nre){
                            }
                        }
                    }
                    else if(i!=0){
                        throw new AppException (s2+" duhet të përmbajë vetëm numra.");
                    }
                }
    }
    
    public static void JDateChoserException(JDateChooser jdc,String s)throws AppException{
        if (jdc.getCalendar()==null)
            throw new AppException (s);
    }
    
    public static void MultipleTextFieldException(JTextField jtf1,JTextField jtf2,String s)throws AppException{
    if (jtf1.getText().isEmpty() || jtf2.getText().isEmpty())
            throw new AppException (s);
    }
    
    public static void JComboBoxException(JComboBox jcb,String s)throws AppException{
    if(jcb.getSelectedIndex()==0)
                throw new AppException(s);
    }
    
    
    
    
    
    
    
    
    public static void TableModelException(Object tbl){
        /*Trainer test=null;
        for (int i=0;i<addtrainertm.getRowCount();i++){
            try{
                if (!addtrainertm.getTrainer(i).getName().isEmpty() 
                && !addtrainertm.getTrainer(i).getSurname().isEmpty())
                test=addtrainertm.getTrainer(i);
            }catch (NullPointerException npe){}
        }
        if(test==null)
                throw new AppException ("Shkruani se paku nje trainer.");
    */
    }
    
    
    
                
}
