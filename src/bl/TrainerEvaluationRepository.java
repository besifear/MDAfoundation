
package bl;

import ejb.Participant;
import ejb.ParticipatingCompanyMember;
import ejb.TTrainerEvaluation;
import ejb.TrainingProcess;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class TrainerEvaluationRepository extends EntMngClass implements TrainerEvaluationInterface {
     
    public TrainerEvaluationRepository(EntityManager tempEm){
        super(tempEm);
    }
    
    @Override
    public void create(TTrainerEvaluation tev) throws AppException {
        try{
        em.getTransaction().begin();
        em.persist(tev);
        em.getTransaction().commit();
        }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një vlerësim i trainerit me këto vlera.Secila vlerësim i trainerit duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje vlerësim i trainerit me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }
    }

    @Override
    public void edit(TTrainerEvaluation tev) throws AppException {
        try{
        em.getTransaction().begin();
        em.merge(tev);
        em.getTransaction().commit();
        }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një vlerësim i trainerit me këto vlera.Secila vlerësim i trainerit duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje vlerësim i trainerit me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }
    }

    @Override
    public void remove(TTrainerEvaluation tev) {
        em.getTransaction().begin();
        em.remove(tev);
        em.getTransaction().commit();
    }

    @Override
    public List<TTrainerEvaluation> findAll() {
        return em.createNamedQuery("TTrainerEvaluation.findAll").getResultList();
    }
    
    @Override
    public List<TTrainerEvaluation> findAllActive() {
        return em.createNamedQuery("TTrainerEvaluation.findAllActive").getResultList();
    }
    
    @Override
    public TTrainerEvaluation findById(String evaluationId) {
        Query query=em.createQuery("SELECT object(tev) FROM TTrainerEvaluation tev WHERE tev.evaluationID = :evaluationID");
        query.setParameter("EvaluationID", evaluationId);
        return (TTrainerEvaluation)query.getSingleResult();
    }
    
    @Override
    public List<TTrainerEvaluation> findByTrainerID(int evaluationId) {
        Query query=em.createQuery("SELECT object(tev) FROM TTrainerEvaluation tev WHERE tev.trainerID.trainerID= :evaluationID");
        query.setParameter("evaluationID", evaluationId);
        return (List<TTrainerEvaluation>)query.getResultList();
    }

    @Override
    public TTrainerEvaluation findByEverything(TTrainerEvaluation tte) {
        Query query=em.createQuery("SELECT object(tev) FROM TTrainerEvaluation tev WHERE tev.pcmId.pcmId= :tevPT AND tev.tProcessID.tProcessID = :tpro AND tev.trainerID.trainerID = :train ");
        query.setParameter("tevPT", tte.getPcmId().getPcmId());
        query.setParameter("tpro", tte.getTProcessID().getTProcessID());
        query.setParameter("train", tte.getTrainerID().getTrainerID());
        return (TTrainerEvaluation)query.getSingleResult();
    }
    
    
    
    @Override
    public List<TTrainerEvaluation> findByTrainingProcessParticipantForDelete(String tp,int par) {
        Query query=em.createQuery("Select object(tev) FROM TTrainerEvaluation tev,ParticipatingCompanyMember pcm,Participant p WHERE tev.pcmId.pcmId=pcm.pcmId AND pcm.participantID.participantID =p.participantID AND p.participantID = :parId AND  tev.tProcessID.tProcessID = :tpro ");
        query.setParameter("parId",par);
        query.setParameter("tpro",tp);
        return (List<TTrainerEvaluation>)query.getResultList();
    }
    
    @Override
    public List<TTrainerEvaluation> findByTPDate(int i){
      Query query=em.createQuery("SELECT object(te) FROM TTrainerEvaluation te,TrainingProcess tp WHERE  te.tProcessID.tProcessID=tp.tProcessID AND FUNC('YEAR',tp.endDate) = :data");
        query.setParameter("data", i);
        return (List<TTrainerEvaluation>)query.getResultList();
    }

    @Override
    public List<TTrainerEvaluation> findByTrainingProcessParticipant(TrainingProcess tptemp, Participant partemp) {
        Query query=em.createQuery("SELECT object(tte) FROM TTrainerEvaluation tte,ParticipatingCompanyMember pcm,TrainingProcess tp,Participant p where tte.pcmId.pcmId =pcm.pcmId AND tte.pcmId.pcmId=pcm.pcmId AND tp.tProcessID=tte.tProcessID.tProcessID AND pcm.participantID.participantID = p.participantID AND p.iDNumber = :idNum AND tp.tProcessID LIKE :tpID  ");
        query.setParameter("idNum", partemp.getIDNumber());
        query.setParameter("tpID", tptemp.getTProcessID());
        return (List<TTrainerEvaluation>)query.getResultList();
    }

    @Override
    public List<TTrainerEvaluation> findByPCM(ParticipatingCompanyMember pcm) {
        Query query=em.createQuery("SELECT object(tte) FROM TTrainerEvaluation tte WHERE tte.pcmId.pcmId= :pcm AND tte.statusi='Active' ");
        query.setParameter("pcm", pcm.getPcmId());
        return (List<TTrainerEvaluation>)query.getResultList();
    }

    @Override
    public List<TTrainerEvaluation> findByTrainingProcess(TrainingProcess tp) {
        Query query=em.createQuery("SELECT object(tte) FROM TTrainerEvaluation tte WHERE tte.tProcessID.tProcessID LIKE :tpId ");
        query.setParameter("tpId", tp.getTProcessID());
        return (List<TTrainerEvaluation>)query.getResultList();
    }
    
}
