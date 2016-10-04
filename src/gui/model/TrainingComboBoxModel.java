
package gui.model;

import ejb.Training;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
public class TrainingComboBoxModel extends AbstractListModel<Training> implements ComboBoxModel<Training>{
    List<Training> date;
    Training SelectItem;
    public TrainingComboBoxModel (List<Training>lista){
    
    date = lista;
    }

    public TrainingComboBoxModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int getSize() {
     return date.size();
    }

    @Override
    public Training getElementAt(int i) {
    return date.get(i);
    }

    @Override
    public void setSelectedItem(Object o) {
      SelectItem = (Training)o;
    }

    @Override
    public Object getSelectedItem() {
      
    return SelectItem;
    }
}
