
package gui.model;

import ejb.Project;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class ProjectTableModel extends AbstractTableModel{
private String [] columnNames= {"ID","Name"};
private List <Project> data;
    


    public ProjectTableModel(){
    
    }
    
    public ProjectTableModel(List<Project> data){
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
    
    public Project getProject(int x){
        return data.get(x);
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Project p=(Project)data.get(rowIndex);
        switch (columnIndex){
            case 0:
                return p.getProjectID();
            case 1:
                return p.getEmri();
            default:
                return null;
        }
    }
    
    public void add(List<Project> data){
        this.data=data;
    }
    
    public void remove (int row){
        data.remove(row);
    }
    
}
