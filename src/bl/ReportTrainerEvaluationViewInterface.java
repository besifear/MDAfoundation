
package bl;

import ejb.view.ReportTrainerEvaluation;
import java.util.List;




public interface ReportTrainerEvaluationViewInterface {
    ReportTrainerEvaluation create(ReportTrainerEvaluation rte)throws AppException;
    void edit(ReportTrainerEvaluation rte)throws AppException;
    void remove(ReportTrainerEvaluation rte);
    List<ReportTrainerEvaluation> findAll();
    ReportTrainerEvaluation findById(String TrainID);
}
