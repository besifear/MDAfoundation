package gui.model;

import ejb.ParticipatingCompanyMember;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class ParticipatingCompanyMemberComboBoxModel extends AbstractListModel<ParticipatingCompanyMember> implements ComboBoxModel<ParticipatingCompanyMember>{
    List<ParticipatingCompanyMember> date;
    ParticipatingCompanyMember SelectItem;
    public ParticipatingCompanyMemberComboBoxModel (List<ParticipatingCompanyMember>lista){
    
    date = lista;
    }

    public ParticipatingCompanyMemberComboBoxModel() {
        
    }
    
    @Override
    public int getSize() {
     return date.size();
    }

    @Override
    public ParticipatingCompanyMember getElementAt(int i) {
    return date.get(i);
    }

    @Override
    public void setSelectedItem(Object o) {
      SelectItem = (ParticipatingCompanyMember)o;
    }

    @Override
    public Object getSelectedItem() {
      
    return SelectItem;
    }
}
