
package gui.model;

import ejb.TrainingProcess;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;



public class TrainingProcessComboBox extends AbstractListModel<TrainingProcess> implements ComboBoxModel<TrainingProcess> {
private TrainingProcess selectedItem;   
private List<TrainingProcess> list;
 
     public TrainingProcessComboBox (List arrayList){
        list=arrayList;
    }
     
        @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public TrainingProcess getElementAt(int index) {
        return list.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selectedItem=(TrainingProcess)anItem;
    }
    
     @Override
    public Object getSelectedItem() {
        return selectedItem;
    }
}
