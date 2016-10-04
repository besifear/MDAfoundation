
package bl;

import java.util.List;
import ejb.TopicsCovered;
import java.util.ArrayList;

public interface TopicsCoveredInterface {
    TopicsCovered create (TopicsCovered tc)throws AppException;
    void edit (TopicsCovered tc)throws AppException;
    void remove (TopicsCovered tc);
    List<TopicsCovered> findAll();
    TopicsCovered findById (String TopicsCoveredID);
    List<TopicsCovered> findByTpId (String TrainingPID);
    List<TopicsCovered> findByTpLangId (String TrainingPID,String Language);
    TopicsCovered findByTopicsCovered (TopicsCovered tc);
    List<Object> findByLang(String Lang);
    List<TopicsCovered> findByTpIdAndLang(String TrainingPID,String Lang);
    boolean isUsedInATrainingProcess(TopicsCovered tc);
    
}
