package bl;

import ejb.TopicsCombo;
import ejb.TrainingProcess;
import java.util.List;

public interface TopicsComboInterface {
    TopicsCombo create(TopicsCombo c)throws AppException;
    void edit(TopicsCombo c)throws AppException;
    void remove(TopicsCombo c);
    List<TopicsCombo> findAll();
    TopicsCombo findById(String TopicsComboID);
    TopicsCombo findByTpTc(TopicsCombo tc);
    List<TopicsCombo> findByTrainingProcess(TrainingProcess tp);
}
