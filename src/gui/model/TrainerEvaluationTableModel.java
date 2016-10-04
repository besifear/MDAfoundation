package gui.model;

import ejb.TTrainerEvaluation;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TrainerEvaluationTableModel extends AbstractTableModel{
    static String [] columnNames = { "EvaluationID","TrainerID","TrainerPreperation","TrainerDiscussion","Presentation" };
     

    List<TTrainerEvaluation> date;
    
    public TrainerEvaluationTableModel() {

    }
    
    public TrainerEvaluationTableModel(List<TTrainerEvaluation> list) {
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
        TTrainerEvaluation te= (TTrainerEvaluation)date.get(rowIndex);
         
        switch(columnIndex){
            
            case 0:
                return te.getEvaluationID();
            case 1:
                return te.getTrainerID();
            case 2:
                return te.getTrainerPreperation();
            case 3:
                return te.getTrainerDiscussion();
            case 4:
                return te.getPresentation();
            default:
                 return null;
        }
    }
     public void add(List<TTrainerEvaluation> data){
        date=data;
    }
    public TTrainerEvaluation getTTrainerEvaluation(int rowIndex){
        return date.get(rowIndex);
    }
    
    public void remove(int rowIndex) {
        date.remove(rowIndex);
    }
}
