
package gui.model;

import ejb.TrainerContact;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;



public class TrainerContactComboBoxModel extends AbstractListModel<TrainerContact> implements ComboBoxModel<TrainerContact> {
private TrainerContact selectedItem;
private List<TrainerContact> list;

    public TrainerContactComboBoxModel (List arrayList){
        list=arrayList;
    }

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public TrainerContact getElementAt(int index) {
        return list.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selectedItem=(TrainerContact)anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selectedItem;
    }
}
