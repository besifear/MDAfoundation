
package bl;

import ejb.Trainer;
import java.util.List;

public interface TrainerInterface {
    Trainer create (Trainer t)throws AppException;
    void edit (Trainer t)throws AppException;
    void remove (Trainer t);
    List<Trainer> findAll();
    Trainer findById (String TrainerID);
    List<Trainer> findByTP(String TrainerID);
    Trainer findByNameSurname(Trainer t);
    int findMaxTrainierProjectID();
}
