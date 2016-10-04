
package bl;

import ejb.view.ReportTrainer;
import java.util.List;


public interface ReportTrainerInterface {
    ReportTrainer create(ReportTrainer rm)throws AppException;
    void edit(ReportTrainer rm)throws AppException;
    void remove(ReportTrainer rm);
    List<ReportTrainer> findAll();
    ReportTrainer findById(String TrainID);
}
