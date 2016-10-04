

package gui.model;

import ejb.Trainer;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class TrainerTableModel extends AbstractTableModel {

private String [] columnNames= {"TrainerID","Name","Surname"};
private List <Trainer> data;
    


    public TrainerTableModel(){
    
    }
    
    public TrainerTableModel(List<Trainer> data){
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
    
    public Trainer getTrainer(int x){
        return data.get(x);
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Trainer t=(Trainer)data.get(rowIndex);
        switch (columnIndex){
            case 0:
                return t.getTrainerID();
            case 1:
                return t.getName();
            case 2:
                return t.getSurname();
            default:
                return null;
        }
    }
    
    public void add(List<Trainer> data){
        this.data=data;
    }
    
    public void remove (int row){
        data.remove(row);
    }
    
    
}
