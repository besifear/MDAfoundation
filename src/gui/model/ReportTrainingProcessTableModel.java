
package gui.model;

import ejb.view.ReportTrainingProcess;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class ReportTrainingProcessTableModel extends AbstractTableModel{
    static String [] columnNames = { "Place","Trajnimi","Titujt e Trajnimeve","Numri i Trajnerëve","Numri i Pjesmarrësve","Numri i Vlerësimeve"};
     

    List<ReportTrainingProcess> date;
    
    public ReportTrainingProcessTableModel() {

    }
    
    public ReportTrainingProcessTableModel(List<ReportTrainingProcess> list) {
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
        ReportTrainingProcess rm= (ReportTrainingProcess)date.get(rowIndex);
         
        switch(columnIndex){
            
            case 0:
                
                return rm.getPlace();
                
            case 1:
                
                return rm.getTrainimi();
            
            case 2:
                
                return rm.getTitujteTrainimit();
            
            case 3:
                
                return rm.getNumriiTrainereve();
            
            case 4:
                
                return rm.getNumriiPjesmarresve();
               
            case 5:
                
                return rm.getNumriiVleresimeve();
                   
            default:
                 return null;
        }
    }
    public ReportTrainingProcess getReportTrainingProcess(int rowIndex){
        return date.get(rowIndex);
    }
    
    public void remove(int rowIndex) {
        date.remove(rowIndex);
    }
    
    public void add(List<ReportTrainingProcess> date){
        
        this.date=date;
        
    }
}
