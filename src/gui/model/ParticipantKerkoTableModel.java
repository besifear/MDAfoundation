package gui.model;

import ejb.Participant;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ParticipantKerkoTableModel extends AbstractTableModel{
  static String [] columnNames = {"Name", "Surname", "Data e Lindjes","Numri Personal","Email","Telefoni","Qyteti"};
    List<Participant> data;
    
    public ParticipantKerkoTableModel() {

    }
    
    public ParticipantKerkoTableModel(List<Participant> list) {
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
         DateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
        switch(columnIndex){
            
            
            case 0:
                return p.getName();
            case 1:
                return p.getSurname();
            case 2:
                return dateFormat.format(p.getDob());    
            case 3:
                return p.getIDNumber(); 
            case 4:
                return p.getEmail();    
            case 5:
                return p.getPhone();     
            case 6:
                return p.getCity();
            
             
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

