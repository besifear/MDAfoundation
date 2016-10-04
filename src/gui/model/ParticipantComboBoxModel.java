package gui.model;

import ejb.Participant;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class ParticipantComboBoxModel extends AbstractListModel<Participant> implements ComboBoxModel<Participant>{
  List<Participant> date;
    Participant SelectItem;
    
    public ParticipantComboBoxModel (List<Participant>lista){
    
    date = lista;
    }
    
    @Override
    public int getSize() {
     return date.size();
    }

    @Override
    public Participant getElementAt(int i) {
    return date.get(i);
    }

    @Override
    public void setSelectedItem(Object o) {
      SelectItem = (Participant)o;
    }

    @Override
    public Object getSelectedItem() {
      
    return SelectItem;
    }
       
}
