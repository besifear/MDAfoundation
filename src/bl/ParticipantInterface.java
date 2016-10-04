package bl;
import ejb.Participant;
import java.util.List;


public interface ParticipantInterface {
  Participant create(Participant p)throws AppException;
    void edit(Participant p)throws AppException;
    void remove(Participant p);
    List<Participant> findAll();
    List<Participant> findAllActive();
    Participant findById(String ParticipantID); 
    Participant findByPersonalID(long personalID);
    List<Participant> findByPersonalIDAsc(long personalID);
    List<Participant> findByPersonalIDDes(long personalID);
    List<Participant> OrderByPersonalIDAsc();
    List<Participant> OrderByPersonalIDDes();
    List<Participant> findByPersonalIDAndTPId(long personalID,String tpId);
     List<Participant> findByTpId(String trainingpId);
     List<Participant> findByTpIdActive(String trainingpId);
     List<Participant> findByGjiniaTpIdActive(String trainingpId,String gjinia);
     List<Participant> findByEmriAsc(String emrII);
     List<Participant> findByEmriDes(String emrII);
     List<Participant> OrderByEmriAsc();
     List<Participant> OrderByEmriDes();
     List<Participant> findByGjiniaAsc(String emrII);
     List<Participant> findByGjiniaDes(String emrII);
     List<Participant> OrderByGjiniaAsc();
     List<Participant> OrderByGjiniaDes();
     List<Participant> findByQytetiAsc(String emrII);
     List<Participant> findByQytetiDes(String emrII);
     List<Participant> OrderByQytetiAsc();
     List<Participant> OrderByQytetiDes();
     Participant findByEverything(Participant p);
     List<Participant> findByTPDate(int i);
     long findByEvaluation(int participant);
     Participant checkIfExists(String ParticipantID)throws AppException; 
}
