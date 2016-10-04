package gui.model;

import ejb.Participant;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ParticipantKerkoCustomTableModel extends AbstractTableModel{
  static String [] columnNames;
    List<Participant> data;
    
    public ParticipantKerkoCustomTableModel(String[] colNames) {
        columnNames=colNames;
        /*"Name", "Surname", "Data e Lindjes","Numri Personal","Email","Telefoni","Qyteti"*/
    }
    
    public void setColumnNames(String[] colNames){
            columnNames=colNames;
    }

    public static String[] getColumnNames() {
        return columnNames;
    }
    
    
    public ParticipantKerkoCustomTableModel(List<Participant> list) {
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
        switch(columnNames[columnIndex]){
            
            
            case "Name":
                return p.getName();
            case "Surname":
                return p.getSurname();
            case "Data e Lindjes":
                return dateFormat.format(p.getDob());    
            case "Numri Personal":
                return p.getIDNumber(); 
            case "Email":
                return p.getEmail();    
            case "Telefoni":
                return p.getPhone();     
            case "Qyteti":
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

