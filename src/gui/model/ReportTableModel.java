
package gui.model;

import ejb.view.AbstractReportEntity;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

public class ReportTableModel extends AbstractTableModel {
   protected static ArrayList<String> columnNames;
   protected List<AbstractReportEntity> date;
   
public ReportTableModel() {
}
    
public ReportTableModel(List<AbstractReportEntity> list,ArrayList<String> Titujt) {
    columnNames=Titujt;
    date = list;
}

@Override
public int getRowCount() {
    return date.size();
}

@Override
public String getColumnName(int col) {
    return columnNames.get(col);
}
    
@Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
}

public List<AbstractReportEntity> getData() {
    return date;
}
    
public TableColumn getColumn(JTable t,int i){
    return t.getColumnModel().getColumn(i);
}
    
@Override
public int getColumnCount() {
    return columnNames.size();
}
    
@Override
public boolean isCellEditable(int row, int col) {
    return false;
}
    
@Override
public AbstractReportEntity getValueAt(int rowIndex, int columnIndex) {
    AbstractReportEntity v= (AbstractReportEntity)date.get(rowIndex);
        return date.get(rowIndex);
    }
}
