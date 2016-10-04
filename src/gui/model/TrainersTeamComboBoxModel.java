
package gui.model;

import ejb.TrainersTeam;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class TrainersTeamComboBoxModel extends AbstractListModel <TrainersTeam> implements ComboBoxModel<TrainersTeam> {
   private TrainersTeam selectedItem;   
 private List<TrainersTeam> list;
 
     public TrainersTeamComboBoxModel (List arrayList){
        list=arrayList;
    }

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public TrainersTeam getElementAt(int index) {
        return list.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selectedItem=(TrainersTeam)anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selectedItem;
    } 
}
