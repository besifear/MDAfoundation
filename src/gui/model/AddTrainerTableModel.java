

package gui.model;

import ejb.Trainer;
import java.awt.Component;
import java.util.Iterator;
import java.util.List;
import javax.swing.Icon;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;


public class AddTrainerTableModel extends AbstractTableModel {

private String [] columnNames= {"Name","Surname"};
private List <Trainer> data;
    


    public AddTrainerTableModel(){
    
    }
    
    public AddTrainerTableModel(List<Trainer> data){
        this.data=data;
    }
    
    public List<Trainer> getData(){
    return data;
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
   public void setValueAt(Object aValue, int rowIndex, int columnIndex)
   {
       Trainer row = data.get(rowIndex);
       
       if(0== columnIndex){
           row.setName(aValue.toString());
       }
       if(1== columnIndex){
           row.setSurname(aValue.toString());
       }
   }
    
    
    @Override
            public boolean isCellEditable(int row, int col) {
                // return your actual criteria
                return true;
            }
    
    
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Trainer t=(Trainer)data.get(rowIndex);
        switch (columnIndex){
            
            case 0:
                return t.getName();
            case 1:
                return t.getSurname();
            default:
                return null;
        }
    }
    
    public Iterator<Trainer> Iteratori(){
        return new Iterator<Trainer>(){
            int i=0;
            Trainer t;
            
            
            @Override
            public boolean hasNext() {
                int j=0;
                for (Trainer t: data){
                    if(t.getName()!=null && t.getSurname()!=null &&
                            !t.getName().trim().isEmpty() && !t.getSurname().trim().isEmpty())
                        j++;
                }
                return (j!=0);
            }

            @Override
            public Trainer next() {
                    return data.get(i++);
            }
           
            
        };
    }
    
    
    
    public void add(List<Trainer> data){
        this.data=data;
    }
    
    public void remove (int row){
        data.remove(row);
    }
    
    public int size(){
        return data.size();
    }
    
    
}
