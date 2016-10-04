
package gui.model;

import ejb.TrainersTeam;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class TrainersTeamTableModel extends AbstractTableModel{
    
    private String [] columnNames= {"getTrainersTeamID","Name"};
private List <TrainersTeam> data;
    


    public TrainersTeamTableModel(){
    
    }
    
    public TrainersTeamTableModel(List<TrainersTeam> data){
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
    
    public TrainersTeam getTrainersTeam(int x){
        return data.get(x);
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TrainersTeam tt=(TrainersTeam)data.get(rowIndex);
        switch (columnIndex){
            case 0:
                return tt.getTrainersTeamID();
            default:
                return null;
        }
    }
    
    public void add(List<TrainersTeam> data){
        this.data=data;
    }
    
    public void remove (int row){
        data.remove(row);
    }
    
}
