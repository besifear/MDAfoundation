package gui.model;

import ejb.Company;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class CompanyComboBoxModel extends AbstractListModel<Company> implements ComboBoxModel<Company>{
    List<Company> date;
    Company SelectItem;
    public CompanyComboBoxModel (List<Company>lista){
    
    date = lista;
    }

    public CompanyComboBoxModel() {
       
    }
    
    @Override
    public int getSize() {
     return date.size();
    }

    @Override
    public Company getElementAt(int i) {
    return date.get(i);
    }

    @Override
    public void setSelectedItem(Object o) {
      SelectItem = (Company)o;
    }

    @Override
    public Object getSelectedItem() {
      
    return SelectItem;
    }
}
