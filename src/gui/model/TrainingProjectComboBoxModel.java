package gui.model;
import ejb.TrainingProject;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;


public class TrainingProjectComboBoxModel extends AbstractListModel <TrainingProject> implements ComboBoxModel<TrainingProject> {
 private TrainingProject selectedItem;   
 private List<TrainingProject> list;

 public TrainingProjectComboBoxModel (List arrayList){
        list=arrayList;
    }

    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public TrainingProject getElementAt(int index) {
        return list.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selectedItem=(TrainingProject)anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selectedItem;
    }
    
}
