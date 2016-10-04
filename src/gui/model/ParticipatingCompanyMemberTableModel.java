package gui.model;

import ejb.ParticipatingCompanyMember;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ParticipatingCompanyMemberTableModel extends AbstractTableModel{
    static String [] columnNames = { "PcmId"};
     

    List<ParticipatingCompanyMember> date;
    
    public ParticipatingCompanyMemberTableModel() {

    }
    
    public ParticipatingCompanyMemberTableModel(List<ParticipatingCompanyMember> list) {
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
        ParticipatingCompanyMember pcm= (ParticipatingCompanyMember)date.get(rowIndex);
         
        switch(columnIndex){
            
            case 0:
                return pcm.getPcmId();
            case 1:
                return pcm.getCompanyID();
            case 2:
                return pcm.getParticipantID();
            default:
                 return null;
        }
    }
    public ParticipatingCompanyMember getParticipatingCompanyMember(int rowIndex){
        return date.get(rowIndex);
    }
    
    public void remove(int rowIndex) {
        date.remove(rowIndex);
    }
}