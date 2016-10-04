
package bl;

import ejb.view.ReportYear;
import java.util.List;



public interface ReportYearInterface {
    ReportYear create(ReportYear rm)throws AppException;
    void edit(ReportYear rm)throws AppException;
    void remove(ReportYear rm);
    List<ReportYear> findAll();
    ReportYear findById(String TrainID);
}
