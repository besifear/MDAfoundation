
package gui.model;

import ejb.Team;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class TeamComboBoxModel extends AbstractListModel<Team> implements ComboBoxModel<Team>{
    List<Team> date;
    Team SelectItem;
    public TeamComboBoxModel (List<Team>lista){
    
    date = lista;
    }

    public TeamComboBoxModel() {
    }
    
    @Override
    public int getSize() {
     return date.size();
    }

    @Override
    public Team getElementAt(int i) {
    return date.get(i);
    }

    @Override
    public void setSelectedItem(Object o) {
      SelectItem = (Team)o;
    }

    @Override
    public Object getSelectedItem() {
      
    return SelectItem;
    }
}
