package bl;
import ejb.ParticipantTeam;
import ejb.ParticipatingCompanyMember;
import ejb.Team;
import java.util.ArrayList;
import java.util.List;

public interface TeamInterface {
    Team create(Team te)throws AppException;
    void edit(Team te)throws AppException;
    void remove(Team te);
    List<Team> findAll();
    Team findById(String CompanyID);
    Team findByTpId(String TpID);
    Team findByParticipants(List<ParticipatingCompanyMember> pcms);
}
