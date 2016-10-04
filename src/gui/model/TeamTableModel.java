package gui.model;

import ejb.Team;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TeamTableModel extends AbstractTableModel{
    static String [] columnNames = { "TeamID"};
     

    List<Team> date;
    
    public TeamTableModel() {

    }
    
    public TeamTableModel(List<Team> list) {
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
    public int getColumnCount() {
       return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Team t= (Team)date.get(rowIndex);
         
        switch(columnIndex){
            
            case 0:
                return t.getTeamID();
            
            
            default:
                 return null;
        }
    }
    public Team getTeam(int rowIndex){
        return date.get(rowIndex);
    }
    
    public void remove(int rowIndex) {
        date.remove(rowIndex);
    }
}
