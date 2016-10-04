
package gui.model;

import ejb.TrainerContact;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class TrainerContactTableModel extends AbstractTableModel  {
    
    private String [] columnNames= {"ContactID","ContactType","ContactValue","TrainerID"};
    private List <TrainerContact> data;
    


    public TrainerContactTableModel(){
    
    }
    
    public TrainerContactTableModel(List<TrainerContact> data){
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
    
    public TrainerContact getTrainerContact(int x){
        return data.get(x);
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TrainerContact tc=(TrainerContact)data.get(rowIndex);
        switch (columnIndex){
            case 0:
                return tc.getContactID();
            case 1:
                return tc.getContactType();
            case 2:
                return tc.getContactValue();
            case 3:
                return tc.getTrainerID();
            default:
                return null;
        }
    }
    
    public void add(List<TrainerContact> data){
        this.data=data;
    }
    
    public void remove (int row){
        data.remove(row);
    }
    
}
