
package bl;

import ejb.TrainerTeamCombo;
import java.util.List;



public interface TrainerTeamComboInterface {
    TrainerTeamCombo create (TrainerTeamCombo ttc)throws AppException;
    void edit (TrainerTeamCombo ttc)throws AppException;
    void remove (TrainerTeamCombo ttc);
    List<TrainerTeamCombo> findAll();
    TrainerTeamCombo findById (String TrainerTeamComboID);
    TrainerTeamCombo findByTrainerTrainerTeam(int TrainerId,int TrainerTeamId);
    List<TrainerTeamCombo> findByTrainerTeam(int trainerTeamId);
}
