package bl;
import ejb.ParticipantTeam;
import ejb.ParticipatingCompanyMember;
import java.util.ArrayList;
import java.util.List;

public interface ParticipantTeamInterface {
      ParticipantTeam create(ParticipantTeam pte)throws AppException;
    void edit(ParticipantTeam pte)throws AppException;
    void remove(ParticipantTeam pte);
    List<ParticipantTeam> findAll();
    ParticipantTeam findById(String CompanyID);
    List<ParticipantTeam> findByTeamId(int temp);
    ParticipantTeam findByEverything(ParticipantTeam pt);
    ParticipantTeam findByParticipantTP(int participantId,String TrainingProcessId);
    boolean CheckIfInTraining(long participant,String tProcess);
    
    List<ParticipantTeam>findByPCM(ParticipatingCompanyMember pcmtempi);
}
