
package gui.model;

import ejb.view.ReportTrainer;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class ReportTrainerTableModel extends AbstractTableModel{
    static String [] columnNames = { "Trajner","Mesatarje","Prezantimi","Diskutimi","Përgaditja","Numri i Trajnimeve të Mbajtura","Numri i Pjesmarrësve të Trajnuar","Numri i Vlersimeve",};
     

    List<ReportTrainer> date;
    
    public ReportTrainerTableModel() {

    }
    
    public ReportTrainerTableModel(List<ReportTrainer> list) {
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
        ReportTrainer rm= (ReportTrainer)date.get(rowIndex);
         
        switch(columnIndex){
            
            case 0:
                
                return rm.getTrainer();
                
            case 1:
                
                return getRoundDouble(rm.getMesatarja()) ;
            
            case 2:
                
                return  getRoundDouble(rm.getPrezantimi());
            
            case 3:
                
                return getRoundDouble(rm.getDiskutimi());
                
            case 4:
            
                return getRoundDouble(rm.getPergaditja());
                
           case 5:
               
                return rm.getNumriiTrainimevetembajtura();
                
            case 6:
            
                return rm.getNumriiPersonavetetrainuar();
             
            case 7:
                
                return rm.getNumriVlersimeve();
            default:
                 return null;
        }
    }
    
    public double getRoundDouble(double numri){
        double d;
        BigDecimal bd = new BigDecimal(numri).setScale(2, RoundingMode.HALF_EVEN);
            d = bd.doubleValue();
            return d;
    }
    
    public ReportTrainer getReportTrainer(int rowIndex){
        return date.get(rowIndex);
    }
    
    public void remove(int rowIndex) {
        date.remove(rowIndex);
    }
    
    public void add(List<ReportTrainer> date){
        
        this.date=date;
        
    }
}
