
package gui.model;

import ejb.view.ReportYear;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class ReportYearTableModel extends AbstractTableModel{
    static String [] columnNames = { "Viti","Numri i Trajnimeve","Numri i Vlerësimeve","Numri i Pjesmarrësve"};
     

    List<ReportYear> date;
    
    public ReportYearTableModel() {

    }
    
    public ReportYearTableModel(List<ReportYear> list) {
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
        ReportYear rm= (ReportYear)date.get(rowIndex);
         
        switch(columnIndex){
            
            case 0:
                
                return rm.getViti();
                
            case 1:
                
                return rm.getNumriiTrainimeve();
            
            case 2:
                
                return rm.getNumriiVleresimeveteTrainereve();
            
            case 3:
                
                return rm.getNumriiPjesemarresve();
                
            default:
                 return null;
        }
    }
    public ReportYear getReportYear(int rowIndex){
        return date.get(rowIndex);
    }
    
    public void remove(int rowIndex) {
        date.remove(rowIndex);
    }
    
    public void add(List<ReportYear> date){
        
        this.date=date;
        
    }
}
