
package gui.model;

import ejb.TrainingProject;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TrainingProjectTableModel extends AbstractTableModel{
    private String [] columnNames= {"TPID","Training_ID","Project_ID"};
    private List <TrainingProject> data;
  
    public TrainingProjectTableModel(){
    
    }
    
    public TrainingProjectTableModel(List<TrainingProject> data){
        this.data=data;
    }
    
    @Override
    public int getRowCount() {
     return data.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
    
    public TrainingProject getTrainingProject(int x){
        return data.get(x);
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TrainingProject tp=(TrainingProject)data.get(rowIndex);
        switch (columnIndex){
            case 0:
                return tp.getTpId();
            case 1:
                return tp.getProjectID();
            case 2:
                return tp.getTrainingID();
            default:
                return null;
        }
    }
    
    public void add(List<TrainingProject> data){
        this.data=data;
    }
    
    public void remove (int row){
        data.remove(row);
    }
}




