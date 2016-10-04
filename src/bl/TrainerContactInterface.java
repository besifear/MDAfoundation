
package bl;

import ejb.TrainerContact;
import java.util.List;

public interface TrainerContactInterface {
    void create (TrainerContact tp)throws AppException;
    void edit (TrainerContact tp)throws AppException;
    void remove (TrainerContact tp);
    List<TrainerContact> findAll();
    TrainerContact findById (String TrainerContactID);

}
