
package gui.model;


import ejb.Users;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

public class UserTableModel extends AbstractTableModel{
    static String [] columnNames = {"Username","Emri","Mbiemri","Pozita"};

    List<Users> date;
    
    public UserTableModel() {

    }
    
    public UserTableModel(List<Users> list) {
        date = list;
    }

    @Override
    public int getRowCount() {
        return date.size();
    }
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
    
        
    public List<Users> getData() {
    return date;
    }
    
    public TableColumn getColumn(JTable t,int i){
        return t.getColumnModel().getColumn(i);
    }
    
    @Override
    public int getColumnCount() {
       return columnNames.length;
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {
    return false;
    }
   
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Users v= (Users)date.get(rowIndex);
         
        switch(columnIndex){
            
            case 0:
                return  v.getUsername();
            case 1:        
                return v.getName();
            case 2:
                return v.getSurname();
            case 3:
                return v.getPozita();
            default:
                 return null;
        }
    }
    
    public Users getUsers(int rowIndex){
        return date.get(rowIndex);
    }
    
    public void remove(int rowIndex) {
        date.remove(rowIndex);
    }
    
     public void add(List<Users> data){
        this.date=data;
    }
}
