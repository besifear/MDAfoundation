
package Utility;

import java.util.List;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class PersonalTableModel extends AbstractTableModel {
    
    static String [] columns;
    List<Object> data;
    
    public PersonalTableModel(){
    
    }
    
    public PersonalTableModel(String []columns,List<Object> data){
        this.columns=columns;
        this.data=data;
    }
    
   @Override
    public int getRowCount() {
        return data.size();
    }
    
    @Override
    public String getColumnName(int col) {
        return columns[col];
    }
    @Override
    public int getColumnCount() {
       return columns.length;
    }
/*
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object o= (Object)data.get(rowIndex);
         
        for (int i=0;i<columns.length;i++){
            
            return data.get(i);
        }
        
        
        
        switch(columnIndex){
            
            case 0:
                return data.get(0);
            case 1:
                return t.getTitleOfTrainingAlbanian();
            
            case 2:
                return t.getTitleOfTrainingEnglish();
            
            case 3:
                return t.getTitleOfTrainingSerbian();
        
            default:
                 return null;
        
        }
    }*/
    public Object getObject(int rowIndex){
        return data.get(rowIndex);
    }
    
    public void remove(int rowIndex) {
        data.remove(rowIndex);
    }
    
    public void add(List<Object> data){
        this.data=data;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
