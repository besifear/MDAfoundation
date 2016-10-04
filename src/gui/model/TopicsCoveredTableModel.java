
package gui.model;

import ejb.TopicsCovered;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TopicsCoveredTableModel extends AbstractTableModel{
    private String [] columnNames= {"TopicID","Shqip","Anglisht","Serbisht","TProcessID"};
private List <TopicsCovered> data;


    public TopicsCoveredTableModel(){
    
    }
    
    public TopicsCoveredTableModel(List<TopicsCovered> data){
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
    
    public TopicsCovered getTopicsCovered(int x){
        return data.get(x);
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TopicsCovered tc=(TopicsCovered)data.get(rowIndex);
        switch (columnIndex){
            case 0:
                return tc.getTopicID();
            case 1:
                return tc.getTopicCovered();
            default:
                return null;
        }
    }
    
    public void add(List<TopicsCovered> data){
        this.data=data;
    }
    
    public void remove (int row){
        data.remove(row);
    }
}
