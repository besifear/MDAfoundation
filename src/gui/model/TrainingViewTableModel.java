
package gui.model;

import ejb.TrainingView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TrainingViewTableModel extends AbstractTableModel{
    static String [] columnNames = { "TrainingID","Emri i Klientit","Emri Projektit","TitleOfTraining_Albanian","Temat Shqip","Temat Anglisht","Temat Serbisht","Place","StartDate","EndDate","Trainerat","TitleOfTraining_English","TitleOfTraining_Serbian"};
     

    public String getTitle(int i){
    return columnNames[i];
    
    }
    List<TrainingView> date;
    
    public TrainingViewTableModel() {

    }
    
    public TrainingViewTableModel(List<TrainingView> list) {
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
        TrainingView t= (TrainingView)date.get(rowIndex);
        Date start = t.getStartDate();
        Date end = t.getEndDate();
        
        
        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); 
        String starttime=sdf.format(start);
        String endtime=sdf.format(end);
        switch(columnIndex){
            
            case 0:
                return t.getTProcessID();
                
            case 1:
                return t.getEmriKlientit();
            
            case 2:
                return t.getEmriProjektit();
            
            case 3:
                return t.getTitleOfTrainingAlbanian();
                
            case 4:
            
                return t.getTematShqip();
            
            case 5:
                
                return t.getTematAnglisht();
                
            case 6:
                
                return t.getTematSerbisht();    
            
            case 7:
                
                return t.getPlace();
            
            case 8:
                return starttime;
            
            case 9:
                return endtime;
            
            case 10:
                
                return t.getTrainerat();
                
            case 11:
                return t.getTitleOfTrainingEnglish();
            
            case 12:
                return t.getTitleOfTrainingSerbian();
            
             
            default:
                 return null;
        }
    }
    public TrainingView getTraining(int rowIndex){
        return date.get(rowIndex);
    }
    
    public void remove(int rowIndex) {
        date.remove(rowIndex);
    }
    
    public void add(List<TrainingView> date){
        
        this.date=date;
        
    }
}
