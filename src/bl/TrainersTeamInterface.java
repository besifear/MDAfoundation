
package bl;

import ejb.Trainer;
import ejb.TrainersTeam;
import java.util.ArrayList;
import java.util.List;



public interface TrainersTeamInterface {
    TrainersTeam create (TrainersTeam tt)throws AppException;
    void edit (TrainersTeam tt)throws AppException;
    void remove (TrainersTeam tt);
    List<TrainersTeam> findAll();
    TrainersTeam findById (String TrainersTeamID);
    TrainersTeam findByParticipants(ArrayList<Trainer> Trainers);
   
}
