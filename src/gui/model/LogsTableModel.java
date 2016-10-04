

package gui.model;

import ejb.Logs;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import bl.LogsInterface;
import bl.LogsRepository;
import javax.persistence.EntityManager;


public class LogsTableModel extends AbstractTableModel {
    private String [] columnNames= {"User","Data","Koha","Lloji","Mesazhi"};
    private List <Logs> data;
    LogsInterface logsir;

    AbstractTableModel dtm=this;
    
    
    public LogsTableModel(EntityManager em){
       logsir=new LogsRepository(em);
    }
    
    
    
    public LogsTableModel(List<Logs> data){
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
    
    public Logs getLogs(int x){
        return data.get(x);
    }
    
    @Override
            public boolean isCellEditable(int row, int col) {
                // return your actual criteria
                return true;
            }
    
   
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Logs l=(Logs)data.get(rowIndex);
        
        Date date = l.getDita();
        Date time=l.getKoha();
        Date d1;
        Date d2;
        DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat sdf2 = new SimpleDateFormat("HH:mm");
        String today=sdf.format(date);
        String koha=sdf2.format(time);
        switch (columnIndex){
            case 0:
                return l.getUseri();
            case 1:
                return today;
            case 2:
                return koha;
            case 3:
                return l.getLloji();
            case 4:
                return l.getMesazhi();
                
            default:
                return null;
        }
    }
    
    public void add(List<Logs> data){
        this.data=data;
    }
    
    public void remove (int row){
        data.remove(row);
    }
    
}
