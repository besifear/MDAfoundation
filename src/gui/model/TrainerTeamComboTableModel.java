
package gui.model;


import ejb.TrainerTeamCombo;
import java.util.List;
import javax.swing.table.AbstractTableModel;



public class TrainerTeamComboTableModel extends AbstractTableModel{
    private String [] columnNames= {"TrainerTeamComboID","Trainer_ID","TrainersTeamID"};
private List <TrainerTeamCombo> data;
    


    public TrainerTeamComboTableModel(){
    
    }
    
    public TrainerTeamComboTableModel(List<TrainerTeamCombo> data){
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
    
    public TrainerTeamCombo getTrainerTeamCombo(int x){
        return data.get(x);
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TrainerTeamCombo ttc=(TrainerTeamCombo)data.get(rowIndex);
        switch (columnIndex){
            case 0:
                return ttc.getTrainerTeamComboID();
            case 1:
                return ttc.getTrainerID();
            case 2:
                return ttc.getTrainersTeamID();
            default:
                return null;
        }
    }
    
    public void add(List<TrainerTeamCombo> data){
        this.data=data;
    }
    
    public void remove (int row){
        data.remove(row);
    }
}
