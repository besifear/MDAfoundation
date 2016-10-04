
package gui.model;

import ejb.TrainingProcess;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class TrainingProcessTableModelMini extends AbstractTableModel {
    private String [] columnNames= {"TProcessID","TrainingProject","Place","Intervali Trajnimit"};
private List <TrainingProcess> data;

    


    public TrainingProcessTableModelMini(){
    
    }
    
    public TrainingProcessTableModelMini(List<TrainingProcess> data){
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
    
    public TrainingProcess getTrainingProcess(int x){
        return data.get(x);
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TrainingProcess tp=(TrainingProcess)data.get(rowIndex);
        
        Date start = tp.getStartDate();
        Date end = tp.getEndDate();
        
        
        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); 
        String starttime=sdf.format(start);
        String endtime=sdf.format(end);
        
        switch (columnIndex){
            case 0:
                return tp.getTProcessID();
            case 1:
                return tp.getTrainingProjectID().getProjectID().getEmri()+" - "+tp.getTrainingProjectID().getTrainingID().getTitleOfTrainingAlbanian();
            case 2:
                return tp.getPlace();
            case 3:
                return starttime+" - "+endtime;
            default:
                return null;
        }
    }
    
    public void add(List<TrainingProcess> data){
        this.data=data;
    }
    
    public void remove (int row){
        data.remove(row);
    }
    
}