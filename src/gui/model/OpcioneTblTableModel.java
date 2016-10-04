
package gui.model;

import Utility.Variables;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

public class OpcioneTblTableModel extends AbstractTableModel{
    static String [] columnNames = {"Variabla","Check"};

    List<Variables> date;
    
    public OpcioneTblTableModel() {

    }
    
    public OpcioneTblTableModel(List<Variables> list) {
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
    
    @Override
        public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex==1)
            return Boolean.class;
        return String.class;
    }
        
        
    public List<Variables> getData() {
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
    return (col==1);
    }
    
    @Override
   public void setValueAt(Object aValue, int rowIndex, int columnIndex)
   {
        Variables row = date.get(rowIndex);
        if(aValue!=null){
            if(1== columnIndex){
                Boolean b=(Boolean)aValue;
                row.setChecked(b);
            }
        }
       
   }
   
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Variables v= (Variables)date.get(rowIndex);
         
        switch(columnIndex){
            
            case 0:
                return v.getName();
            case 1:        
                return v.isChecked();
            default:
                 return null;
        }
    }
    
    public Variables getVariables(int rowIndex){
        return date.get(rowIndex);
    }
    
    public void remove(int rowIndex) {
        date.remove(rowIndex);
    }
    
     public void add(List<Variables> data){
        this.date=data;
    }
}
