package gui.model;

import ejb.TTrainerEvaluation;
import ejb.TrainingProcess;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TrainerEvaluationTableModelMini extends AbstractTableModel{
    static String [] columnNames = { "Training Code","Trainer","TrainerPreperation","TrainerDiscussion","Presentation" };
     String TrainingProcessId;

    List<TTrainerEvaluation> data;
    
    public TrainerEvaluationTableModelMini() {
    }
    
    public void setTrainingProcessID(String TrainingProcessId){
        this.TrainingProcessId=TrainingProcessId;
    }
    
    
    @Override
   public void setValueAt(Object aValue, int rowIndex, int columnIndex)
   {
       
       TTrainerEvaluation row = data.get(rowIndex);
       
       if(2 == columnIndex) {
           row.setTrainerPreperation(Integer.parseInt(aValue.toString()));
       }
       if(3== columnIndex){
           row.setTrainerDiscussion(Integer.parseInt(aValue.toString()));
       }
       if(4== columnIndex){
           row.setPresentation(Integer.parseInt(aValue.toString()));
       }
   }
    
    
    @Override
    public boolean isCellEditable(int row, int col) {
        return(col!=0 && col!=1);
    }
    
    public TrainerEvaluationTableModelMini(List<TTrainerEvaluation> list) {
        data = list;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }
    
    public void add(List<TTrainerEvaluation> list) {
        if(data==null)
            data=new ArrayList<TTrainerEvaluation>();
        data.addAll(list);
    }
    
    public boolean isEmpty (){
        if(data!=null){
        return data.isEmpty();
        }
        return false;
    }
    
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }
    @Override
    public int getColumnCount() {
       return columnNames.length;
    }
    
    public void inicialize(){
        data=new ArrayList<TTrainerEvaluation>();
    }
    
    public List<TTrainerEvaluation> getData(){
        return data;
    }
    
    public TTrainerEvaluation get(int i){
        return data.get(i);
    }
    
    public int getSize(){
        return data.size();
    }
    
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TTrainerEvaluation te= (TTrainerEvaluation)data.get(rowIndex);
        switch(columnIndex){
            
            case 0:
                return TrainingProcessId;
            case 1:
                return "ID: "+te.getTrainerID().getTrainerID()+" "+te.getTrainerID().getName()+" "+te.getTrainerID().getSurname();
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
     
    public TTrainerEvaluation getTTrainerEvaluation(int rowIndex){
        return data.get(rowIndex);
    }
    
    public void remove(int rowIndex) {
        data.remove(rowIndex);
    }
    
    public void removeAll() {
        if(data!= null){
        while (!data.isEmpty()){
            data.remove(data.size()-1);
        }
        }
    }

    public boolean containsEveryElement(ArrayList<TTrainerEvaluation> trainerev) {
        if(data==null)
                return false;
            
        for (TTrainerEvaluation tte :trainerev){
            if (!data.contains(tte))
                return false;
        }
        return true;
    }
}