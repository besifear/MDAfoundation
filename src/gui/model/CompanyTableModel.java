
package gui.model;

import ejb.Company;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class CompanyTableModel extends AbstractTableModel{
    static String [] columnNames = {"Name", "Companytype"};
     

    List<Company> date;
    
    public CompanyTableModel() {

    }
    
    public CompanyTableModel(List<Company> list) {
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
        Company c= (Company)date.get(rowIndex);
         
        switch(columnIndex){
            
            case 0:
                return c.getName();
            case 1:
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
