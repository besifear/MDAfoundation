
package gui.model;

import ejb.ParticipantView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ParticipantViewTableModel extends AbstractTableModel{
    static String [] columnNames = { "TrainingID","ParticipantID","Pjesmarrësi","Gjinia","Data e Lindjës","ID_Number","Email","Phone","Shteti","Qyteti","ZIPcode","Adresa","Trajneri","Prezantimi","Diskutimi","Përgaditja"};
     

    List<ParticipantView> date;
    
    public ParticipantViewTableModel() {

    }
    
    public String getTitle(int i){
    return columnNames[i];
    
    }
    
    public ParticipantViewTableModel(List<ParticipantView> list) {
        date = list;
    }

    @Override
    public int getRowCount() {
        return date.size();
    }
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
    @Override
    public int getColumnCount() {
       return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ParticipantView t= (ParticipantView)date.get(rowIndex);
        Date date = t.getDataeLindjës();
        
        Date d1;
        Date d2;
        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); 
        String dob=sdf.format(date);
        switch(columnIndex){
            
            case 0:
                return t.getTProcessID();
            case 1:
                return t.getParticipantID();
            
            case 2:
                return t.getPjesmarresi();
            
            case 3:
                return t.getGjinia();
                
            case 4:
            
                return dob;
            
            case 5:
                
                return t.getIDNumber();
            
            case 6:
                
                return t.getEmail();
            
            case 7:
                return t.getPhone();
            
            case 8:
                return t.getParticipantState();
            
            case 9:
                
                return t.getCity();
                
            case 10:
                return t.getZipCode();
            
            case 11:
                return t.getParticipantAddress();
            
             case 12:
                return t.getTraineri();
            
            case 13:
                
                return t.getPresentation();
                
            case 14:
                return t.getTrainerDiscussion();
            
            case 15:
                return t.getTrainerPreperation();
                
            default:
                 return null;
        }
    }
    public ParticipantView getTraining(int rowIndex){
        return date.get(rowIndex);
    }
    
    public void remove(int rowIndex) {
        date.remove(rowIndex);
    }
    
    public void add(List<ParticipantView> date){
        this.date=date;
    }
}

