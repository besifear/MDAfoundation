
package bl;

import java.util.List;
import ejb.view.ReportMonth;


public interface ReportMonthViewInterface {
    ReportMonth create(ReportMonth rm)throws AppException;
    void edit(ReportMonth rm)throws AppException;
    void remove(ReportMonth rm);
    List<ReportMonth> findAll();
    ReportMonth findById(String TrainID);
}
