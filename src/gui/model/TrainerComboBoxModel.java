
package gui.model;

import ejb.Trainer;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;


public class TrainerComboBoxModel extends AbstractListModel <Trainer> implements ComboBoxModel<Trainer>{

    private Trainer selectedItem;   
 private List<Trainer> list;
 
     public TrainerComboBoxModel (List arrayList){
        list=arrayList;
    }

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public Trainer getElementAt(int index) {
        return list.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selectedItem=(Trainer)anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selectedItem;
    }
}
