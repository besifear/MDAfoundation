

package gui.model;

import ejb.Participant;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ParticipantTableModel extends AbstractTableModel{
  static String [] columnNames = {"ParticipantId", "Name", "Surname","ParticipantAddress","City","ParticipantState","Sex","ZipCode","DOB","IdNumber"};
    List<Participant> data;
    
    public ParticipantTableModel() {

    }
    
    public ParticipantTableModel(List<Participant> list) {
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
    
    public List<Participant> getData(){
        return data;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
         Participant p= (Participant)data.get(rowIndex);
         DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
        switch(columnIndex){
            
            case 0:
                return p.getParticipantID();
            case 1:
                return p.getName();
            case 2:
                return p.getSurname();
            case 3:
                return p.getParticipantAddress();
            case 4:
                return p.getCity();
            case 5:
                return p.getParticipantState();
            case 6:
                return p.getSex();
            case 7:
                return p.getZipCode();
            case 8:
                return dateFormat.format(p.getDob());
            case 9:
                return p.getIDNumber();    
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
