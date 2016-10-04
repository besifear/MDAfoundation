
package bl;

import java.util.List;
import ejb.TrainingView;

public interface TrainingViewInterface {
    TrainingView create(TrainingView tv)throws AppException;
    void edit(TrainingView tv)throws AppException;
    void remove(TrainingView tv);
    List<TrainingView> findAll();
    TrainingView findById(String TrainID); 
}
