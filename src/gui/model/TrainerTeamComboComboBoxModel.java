
package gui.model;

import ejb.TrainerTeamCombo;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class TrainerTeamComboComboBoxModel extends AbstractListModel <TrainerTeamCombo> implements ComboBoxModel<TrainerTeamCombo>  {
    private TrainerTeamCombo selectedItem;   
 private List<TrainerTeamCombo> list;
 
     public TrainerTeamComboComboBoxModel (List arrayList){
        list=arrayList;
    }

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public TrainerTeamCombo getElementAt(int index) {
        return list.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selectedItem=(TrainerTeamCombo)anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selectedItem;
    }
}
