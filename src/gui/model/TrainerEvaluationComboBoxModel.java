package gui.model;

import ejb.TTrainerEvaluation;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

public class TrainerEvaluationComboBoxModel extends AbstractListModel<TTrainerEvaluation> implements ComboBoxModel<TTrainerEvaluation>{
    List<TTrainerEvaluation> date;
    TTrainerEvaluation SelectItem;
    public TrainerEvaluationComboBoxModel (List<TTrainerEvaluation>lista){
    
    date = lista;
    }

    public TrainerEvaluationComboBoxModel() {
        
    }
    
    @Override
    public int getSize() {
     return date.size();
    }

    @Override
    public TTrainerEvaluation getElementAt(int i) {
    return date.get(i);
    }

    @Override
    public void setSelectedItem(Object o) {
      SelectItem = (TTrainerEvaluation)o;
    }

    @Override
    public Object getSelectedItem() {
      
    return SelectItem;
    }
}
