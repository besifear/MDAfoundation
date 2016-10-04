
package bl;

import ejb.Training;
import java.util.List;

public interface TrainingInterface {
    Training create (Training t)throws AppException;
    void edit (Training t)throws AppException;
    void remove (Training t);
    List<Training> findAll();
    Training findById (String TrainingID);
    int findByTOT(String TOT);
    Training findByTpId (String TrainingPID);
    List<Training> OrderByTrainingAsc();
    List<Training> OrderByTrainingDes();
    List<Training> findByTrainingAsc(String emrII);
    List<Training> findByTrainingDes(String emrII);
    Training findByTOTs(String topA,String topE,String topS);
    
}
