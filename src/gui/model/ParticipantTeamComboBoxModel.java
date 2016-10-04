
package gui.model;

import ejb.ParticipantTeam;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class ParticipantTeamComboBoxModel extends AbstractListModel<ParticipantTeam> implements ComboBoxModel<ParticipantTeam>{
    List<ParticipantTeam> date;
    ParticipantTeam SelectItem;
    public ParticipantTeamComboBoxModel (List<ParticipantTeam>lista){
    
    date = lista;
    }

    public ParticipantTeamComboBoxModel() {
            }
    
    @Override
    public int getSize() {
     return date.size();
    }

    @Override
    public ParticipantTeam getElementAt(int i) {
    return date.get(i);
    }

    @Override
    public void setSelectedItem(Object o) {
      SelectItem = (ParticipantTeam)o;
    }

    @Override
    public Object getSelectedItem() {
      
    return SelectItem;
    }
}
