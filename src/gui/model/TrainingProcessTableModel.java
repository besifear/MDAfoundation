
package gui.model;

import ejb.TrainingProcess;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class TrainingProcessTableModel extends AbstractTableModel {
    private String [] columnNames= {"TProcessID","TrainersTeamID","TrainingProjectID","Place","StartDate","EndDate"};
private List <TrainingProcess> data;

    


    public TrainingProcessTableModel(){
    
    }
    
    public TrainingProcessTableModel(List<TrainingProcess> data){
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
    
    public TrainingProcess getTrainingProcess(int x){
        return data.get(x);
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TrainingProcess tp=(TrainingProcess)data.get(rowIndex);
        switch (columnIndex){
            case 0:
                return tp.getTProcessID();
            case 1:
                return tp.getTrainersTeamID();
            case 2:
                return tp.getTrainingProjectID();
            case 3:
                return tp.getPlace();
            case 4:
                return tp.getStartDate();
            case 5:
                return tp.getEndDate();
            default:
                return null;
        }
    }
    
    public void add(List<TrainingProcess> data){
        this.data=data;
    }
    
    public void remove (int row){
        data.remove(row);
    }
    
}