
package gui.model;

import ejb.TopicsCovered;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class TopicsCoveredComboBoxModel extends AbstractListModel <TopicsCovered> implements ComboBoxModel<TopicsCovered>{
    
    private TopicsCovered selectedItem;   
 private List<TopicsCovered> list;
 
     public TopicsCoveredComboBoxModel (List arrayList){
        list=arrayList;
    }

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public TopicsCovered getElementAt(int index) {
        return list.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selectedItem=(TopicsCovered)anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selectedItem;
    }
}
