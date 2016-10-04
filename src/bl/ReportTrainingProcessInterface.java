
package bl;

import ejb.view.ReportTrainingProcess;
import java.util.List;


public interface ReportTrainingProcessInterface {
    ReportTrainingProcess create(ReportTrainingProcess rm)throws AppException;
    void edit(ReportTrainingProcess rm)throws AppException;
    void remove(ReportTrainingProcess rm);
    List<ReportTrainingProcess> findAll();
    ReportTrainingProcess findById(String TrainID);
}
