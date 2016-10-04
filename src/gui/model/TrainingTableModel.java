
package gui.model;
import ejb.Training;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TrainingTableModel extends AbstractTableModel{
    static String [] columnNames = { "TrainingID","Shqip","Anglisht","Serbisht"};
     

    List<Training> date;
    
    public TrainingTableModel() {

    }
    
    public TrainingTableModel(List<Training> list) {
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
        Training t= (Training)date.get(rowIndex);
         
        switch(columnIndex){
            
            case 0:
                return t.getTrainingID();
            case 1:
                return t.getTitleOfTrainingAlbanian();
            
            case 2:
                return t.getTitleOfTrainingEnglish();
            
            case 3:
                return t.getTitleOfTrainingSerbian();
            
            default:
                 return null;
        }
    }
    public Training getTraining(int rowIndex){
        return date.get(rowIndex);
    }
    
    public void remove(int rowIndex) {
        date.remove(rowIndex);
    }
    
    public void add(List<Training> date){
        this.date=date;
    }
       
}
    

