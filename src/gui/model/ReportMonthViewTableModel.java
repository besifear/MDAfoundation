
package gui.model;

import ejb.view.ReportMonth;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class ReportMonthViewTableModel extends AbstractTableModel{
    static String [] columnNames = { "Viti","Muaji","Numri i Trajnimeve","Numri i Vlerësimeve të Trajnimeve","Numri i Pjesmarrësve"};
     

    List<ReportMonth> date;
    
    public ReportMonthViewTableModel() {

    }
    
    public ReportMonthViewTableModel(List<ReportMonth> list) {
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
        ReportMonth rm= (ReportMonth)date.get(rowIndex);
         
        switch(columnIndex){
            
            case 0:
                return rm.getViti();
                
            case 1:
                return getMuaji(rm.getMuaji());
            
            case 2:
                return rm.getNumriiTrainimeve();
            
            case 3:
                return rm.getNumriiVleresimeveteTrainereve();
                
            case 4:
            
                return rm.getNumriiPjesemarresve();
           
            default:
                 return null;
        }
    }
    public ReportMonth getReportMonth(int rowIndex){
        return date.get(rowIndex);
    }
    
    public void remove(int rowIndex) {
        date.remove(rowIndex);
    }
    
    public void add(List<ReportMonth> date){
        
        this.date=date;
        
    }
    
    public String getMuaji(int muaji){
        switch(muaji){
            case 1:
                return "Janar";
            
            case 2:
                return "Shkurt";
                
            case 3:
                return "Mars";    
                
             case 4:
                return "Prill";
            
            case 5:
                return "Maj";
                
            case 6:
                return "Qershor";    
                
             case 7:
                return "Korrik";
            
            case 8:
                return "Gusht";
                
            case 9:
                return "Shtator";    
                
             case 10:
                return "Tetor";
            
            case 11:
                return "Nëntor";
                
            case 12:
                return "Dhjetor";    
                
            default: 
                return "";    
        }        
                
    }
}
