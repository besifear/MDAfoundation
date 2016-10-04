
package gui.model;

import ejb.view.ReportMonth;
import ejb.view.ReportTrainerEvaluation;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
 
public class ReportTrainerEvaluationViewTableModel extends AbstractTableModel{
    static String [] columnNames = { "Pjesmarrësit e Trajnuar","Kodi i Trajnimit","Trajneri","Prezantimi","Diskutimi","Përgaditja"};
     

    List<ReportTrainerEvaluation> date;
    
    public String getTitle(int i){
    return columnNames[i];
    
    }
    
    public ReportTrainerEvaluationViewTableModel() {

    }
    
    public ReportTrainerEvaluationViewTableModel(List<ReportTrainerEvaluation> list) {
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
        ReportTrainerEvaluation rte= (ReportTrainerEvaluation)date.get(rowIndex);
         
        switch(columnIndex){
            
            case 0:
                return rte.getPjesemaresitetrajnuar();
                
            case 1:
                return rte.getKodiTrainimit();
            
            case 2:
                return rte.getTrainer();
            
            case 3:
                return rte.getPrezantimi();
                
            case 4:
            
                return rte.getDiskutimi();
            case 5:
            
                return rte.getPergaditja();
                
            default:
                 return null;
        }
    }
    public ReportTrainerEvaluation getTraining(int rowIndex){
        return date.get(rowIndex);
    }
    
    public void remove(int rowIndex) {
        date.remove(rowIndex);
    }
    
    public void add(List<ReportTrainerEvaluation> date){
        
        this.date=date;
        
    }
}
