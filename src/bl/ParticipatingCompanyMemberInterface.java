package bl;
import ejb.CTPcombo;
import ejb.Company;
import ejb.Participant;
import ejb.ParticipatingCompanyMember;
import ejb.TrainingProcess;
import java.util.ArrayList;
import java.util.List;

public interface ParticipatingCompanyMemberInterface {
    
    ParticipatingCompanyMember create(ParticipatingCompanyMember pcm)throws AppException;
    void edit(ParticipatingCompanyMember pcm)throws AppException;
    void remove(ParticipatingCompanyMember pcm);
    List<ParticipatingCompanyMember> findAll();
    ParticipatingCompanyMember findById(String PCMID); 
    ParticipatingCompanyMember findByEverything (ParticipatingCompanyMember c);
    List<ParticipatingCompanyMember> findByTeam(int teamId);
    List<ParticipatingCompanyMember> findByCTPcombo(CTPcombo ctptemp);
    List<ParticipatingCompanyMember> findByCTPcomboForDelete(CTPcombo ctptemp);
    List<ParticipatingCompanyMember> findByCompany(Company companyID);
    ParticipatingCompanyMember findBytTpParticipant(TrainingProcess tptemp, Participant partemp);
    ParticipatingCompanyMember findBytTpParticipantForDelete(TrainingProcess tptemp, Participant partemp);

    List<ParticipatingCompanyMember> findByParticipant(Participant partemp);
}
