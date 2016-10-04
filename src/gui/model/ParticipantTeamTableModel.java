
package gui.model;

import ejb.ParticipantTeam;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ParticipantTeamTableModel extends AbstractTableModel{
    static String [] columnNames = { "PartTeamID"};
     

    List<ParticipantTeam> date;
    
    public ParticipantTeamTableModel() {

    }
    
    public ParticipantTeamTableModel(List<ParticipantTeam> list) {
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
        ParticipantTeam pt= (ParticipantTeam)date.get(rowIndex);
         
        switch(columnIndex){
            
            case 0:
                return pt.getPartTeamID();
            
            default:
                 return null;
        }
    }
    public ParticipantTeam getParticipantTeam(int rowIndex){
        return date.get(rowIndex);
    }
    
    public void remove(int rowIndex) {
        date.remove(rowIndex);
    }
}
