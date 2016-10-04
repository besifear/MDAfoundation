
package bl;

import ejb.TrainingProject;
import java.util.List;


public interface TrainingProjectInterface {
    TrainingProject create (TrainingProject tp)throws AppException;
    void edit (TrainingProject tp)throws AppException;
    void remove (TrainingProject tp);
    List<TrainingProject> findAll();
    TrainingProject findById (String TrainingProjectID);
    TrainingProject findByTrainingProject (int TrainingId,int ProjectId);
    int findMaxTrainingProjectID();
}
