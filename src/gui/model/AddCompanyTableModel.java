
package gui.model;

import ejb.Company;
import java.util.List;
import javafx.scene.control.ComboBox;
import javax.swing.JComboBox;
import javax.swing.table.AbstractTableModel;

public class AddCompanyTableModel extends AbstractTableModel{
    static String [] columnNames = {"ID","Name", "Companytype"};
    String[] TOCarray = {"Public Institutions", "International Organizations", "Non-Govermental", "Private Businesses", "Individual"};
     

    List<Company> date;
    
    public AddCompanyTableModel() {

    }
    
    public AddCompanyTableModel(List<Company> list) {
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
   public void setValueAt(Object aValue, int rowIndex, int columnIndex)
   {
       Company row = date.get(rowIndex);
       if(aValue!=null){
       if(0 == columnIndex) {
           row.setCompanyID(Integer.parseInt(aValue.toString()));
       }
       if(1== columnIndex){
           row.setName(aValue.toString());
       }
       if(2== columnIndex){
           String s=aValue.toString();
           for (int i=0;i<TOCarray.length;i++)
           {
               if(s.equals(TOCarray[i]))
                   row.setCompanytype(s);
           }
          /* row.setCompanytype();*/
       }
       }
   }
    
    
    @Override
            public boolean isCellEditable(int row, int col) {
                // return your actual criteria
                return true;
            }
    
    
    
    
    
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Company c= (Company)date.get(rowIndex);
         
        switch(columnIndex){
            
            case 0:
                return c.getCompanyID();
            case 1:        
                return c.getName();
            case 2:
                return c.getCompanytype();
            default:
                 return null;
        }
    }
    public Company getCompany(int rowIndex){
        return date.get(rowIndex);
    }
    
    public void remove(int rowIndex) {
        date.remove(rowIndex);
    }
    
     public void add(List<Company> data){
        this.date=data;
    }
}
