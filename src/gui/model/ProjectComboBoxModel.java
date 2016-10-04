
package gui.model;

import ejb.Project;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class ProjectComboBoxModel extends AbstractListModel <Project> implements ComboBoxModel<Project> {
 private Project selectedItem;   
 private List<Project> list;
 
     public ProjectComboBoxModel (List arrayList){
        list=arrayList;
    }

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public Project getElementAt(int index) {
        return list.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selectedItem=(Project)anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selectedItem;
    }
}
