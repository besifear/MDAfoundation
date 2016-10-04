
package bl;

import ejb.ParticipantView;
import java.util.List;


public interface ParticipantViewInterface {
    ParticipantView create(ParticipantView pv)throws AppException;
    void edit(ParticipantView pv)throws AppException;
    void remove(ParticipantView pv);
    List<ParticipantView> findAll();
    ParticipantView findById(String TrainID); 
}
