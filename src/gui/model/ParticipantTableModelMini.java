

package gui.model;

import ejb.Participant;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ParticipantTableModelMini extends AbstractTableModel{
  static String [] columnNames = {"ID", "Participant","City","Personal Number"};
    List<Participant> data;
    
    public ParticipantTableModelMini() {

    }
    
    public ParticipantTableModelMini(List<Participant> list) {
        data = list;
    }

    public void add(List<Participant> list) {
        data = list;
    }
    
    @Override
    public int getRowCount() {
        return data.size();
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
         Participant p= (Participant)data.get(rowIndex);
         
        switch(columnIndex){
            
            case 0:
                return "ID: "+p.getParticipantID();
            case 1:
                return p.getName()+" "+p.getSurname();
            case 2:
                return p.getCity()+" ("+p.getParticipantState()+")";
            case 3:
                return p.getIDNumber();    
            default:
                 return null;
        }
    }
    
     public Object getStringValueAt(int rowIndex, int columnIndex) {
         Participant p= (Participant)data.get(rowIndex);
         
        switch(columnIndex){
            
            case 0:
                return "ID: "+p.getParticipantID();
            case 1:
                return p.getName()+" "+p.getSurname();
            case 2:
                return p.getCity()+" ("+p.getParticipantState()+")";
            case 3:
                return p.getIDNumber()+"";    
            default:
                 return null;
        }
    }
    
    public Participant getParticipant(int rowIndex){
        return data.get(rowIndex);
    }
    
    public void remove(int rowIndex) {
        data.remove(rowIndex);
    }  
}
