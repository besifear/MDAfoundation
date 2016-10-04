package bl;

import ejb.Participant;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.swing.JOptionPane;

public class ParticipantRepository extends EntMngClass implements ParticipantInterface {
 
    public ParticipantRepository(EntityManager tempEm){
        super(tempEm);
    }
    
     @Override
    public Participant create(Participant p) throws AppException {
        try{
        em.getTransaction().begin();
        em.persist(p);
        em.flush();
        em.getTransaction().commit();
        return p;
        }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një pjesëmarrës me këto vlera.Secila pjesëmarrës duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje pjesëmarrës me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }
    }

    @Override
    public void edit(Participant p) throws AppException {
        try{
        em.getTransaction().begin();
        em.merge(p);
        em.getTransaction().commit();
        }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një pjesëmarrës me këto vlera.Secila pjesëmarrës duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje pjesëmarrës me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }
    }

    @Override
    public void remove(Participant p) {
        em.getTransaction().begin();
        em.remove(p);
        em.getTransaction().commit();
    }

    @Override
    public List<Participant> findAll() {
        return em.createNamedQuery("Participant.findAll").getResultList();
    }
    
    public List<Participant> findAllActive() {
        return em.createNamedQuery("Participant.findAllActive").getResultList();
    }

    @Override
    public Participant findById(String participantId) {
        Query query=em.createQuery("SELECT object(p) FROM Participant p WHERE p.ParticipantID = :ParticipantID");
        query.setParameter("ParticipantID", participantId);
        return (Participant)query.getSingleResult();
    }
    
    @Override
    public List<Participant> findByTpId(String trainingpId) {
        Query query=em.createQuery("SELECT DISTINCT  object(p) FROM Participant p,ParticipatingCompanyMember pcm,ParticipantTeam pt,Team t,TrainingProcess tp WHERE p.participantID=pcm.participantID.participantID AND pcm.pcmId=pt.pcmId.pcmId AND t.teamID=pt.teamID.teamID AND tp.teamID.teamID=t.teamID AND tp.tProcessID= :tpID");
        query.setParameter("tpID", trainingpId);
        return (List<Participant>)query.getResultList();
    }
    
    @Override
    public List<Participant> findByTpIdActive(String trainingpId) {
        Query query=em.createQuery("SELECT DISTINCT object(p) FROM Participant p,ParticipatingCompanyMember pcm,ParticipantTeam pt,Team t,TrainingProcess tp WHERE p.participantID=pcm.participantID.participantID AND pcm.pcmId=pt.pcmId.pcmId AND t.teamID=pt.teamID.teamID AND tp.teamID.teamID=t.teamID AND tp.tProcessID= :tpID AND p.statusi='Active' AND pcm.statusi='Active' AND pt.statusi='Active' AND t.statusi='Active' AND tp.statusi='Active'");
        query.setParameter("tpID", trainingpId);
        return (List<Participant>)query.getResultList();
    }
    
    @Override
    public List<Participant> findByGjiniaTpIdActive(String trainingpId,String gjinia) {
        Query query=em.createQuery("SELECT object(p) FROM Participant p,ParticipatingCompanyMember pcm,ParticipantTeam pt,Team t,TrainingProcess tp WHERE p.participantID=pcm.participantID.participantID AND pcm.pcmId=pt.pcmId.pcmId AND t.teamID=pt.teamID.teamID AND tp.teamID.teamID=t.teamID AND tp.tProcessID= :tpID AND p.sex= :Gjinia AND p.statusi='Active' AND pcm.statusi='Active' AND pt.statusi='Active' AND t.statusi='Active' AND tp.statusi='Active'");
        query.setParameter("tpID", trainingpId);
        query.setParameter("Gjinia", gjinia);
        return (List<Participant>)query.getResultList();
    }
    
    @Override
    public Participant findByPersonalID(long personalID) {
         Query query=em.createQuery ("SELECT object(p) FROM Participant p WHERE p.iDNumber = :PersonID");
        query.setParameter("PersonID",personalID);
        
        return (Participant)query.getSingleResult();
        
    }
    
     @Override
    public List<Participant> findByPersonalIDAsc(long personalID) {
        Query query=em.createQuery ("SELECT object(p) FROM Participant p WHERE CAST(p.iDNumber AS VARCHAR(10)) LIKE :PersonID AND p.statusi='Active' ORDER BY p.iDNumber ");
        query.setParameter("PersonID",personalID+"%");
        
        return (List<Participant>)query.getResultList();
        
    }
    
     @Override
    public List<Participant> findByPersonalIDDes(long personalID) {
        Query query=em.createQuery ("SELECT object(p) FROM Participant p WHERE CAST(p.iDNumber AS VARCHAR(10)) LIKE :PersonID AND p.statusi='Active' ORDER BY p.iDNumber DESC");
        query.setParameter("PersonID",personalID+"%");
        return (List<Participant>)query.getResultList();
    }
    
    @Override
    public List<Participant> OrderByPersonalIDAsc() {
        Query query=em.createQuery ("SELECT p FROM Participant p WHERE p.statusi='Active' ORDER BY p.iDNumber");
       
        return (List<Participant>)query.getResultList();
       
    }
    
    @Override
    public List<Participant> OrderByPersonalIDDes() {
        Query query=em.createQuery ("SELECT p FROM Participant p WHERE p.statusi='Active' ORDER BY p.iDNumber DESC");
      
        return (List<Participant>)query.getResultList();
        
    }
    
     @Override
    public List<Participant> findByPersonalIDAndTPId(long personalID,String tpId) {
        Query query=em.createQuery ("SELECT DISTINCT object(p)  FROM Participant p,ParticipatingCompanyMember pcm,ParticipantTeam pt,Team t,TrainingProcess tp WHERE CAST(p.iDNumber AS VARCHAR(10)) LIKE :PersonID AND  p.participantID=pcm.participantID.participantID AND pcm.pcmId=pt.pcmId.pcmId AND t.teamID=pt.teamID.teamID AND tp.teamID.teamID=t.teamID AND tp.tProcessID= :TrainingID AND p.statusi='Active' AND pcm.statusi='Active' AND pt.statusi='Active' AND t.statusi='Active' AND tp.statusi='Active' ");
        query.setParameter("PersonID",personalID+"%");
        query.setParameter("TrainingID",tpId);
        return (List<Participant>)query.getResultList();
        
    }
    
    @Override
    public List<Participant> OrderByEmriAsc() {
        Query query=em.createQuery ("SELECT p FROM Participant p WHERE p.statusi='Active' ORDER BY p.name");
       
        return (List<Participant>)query.getResultList();
       
    }
    
    @Override
    public List<Participant> OrderByEmriDes() {
        Query query=em.createQuery ("SELECT p FROM Participant p WHERE p.statusi='Active' ORDER BY p.name DESC");
      
        return (List<Participant>)query.getResultList();
        
    }
    
        
    @Override
    public List<Participant> findByEmriAsc(String emrII) {
         Query query=em.createQuery ("SELECT p FROM Participant p WHERE CONCAT  (' ',p.name,' ',p.surname) LIKE :emr AND p.statusi='Active' ORDER BY p.name");
        query.setParameter("emr","%"+emrII+"%");
        
        return (List<Participant>)query.getResultList();
        
    }
    
    @Override
    public List<Participant> findByEmriDes(String emrII) {
        Query query=em.createQuery ("SELECT p FROM Participant p WHERE CONCAT(' ',p.name,' ',p.surname) LIKE :emr AND p.statusi='Active' ORDER BY p.name DESC");
        query.setParameter("emr","%"+emrII+"%");
       
        return (List<Participant>)query.getResultList();
        
    }
    
    @Override
    public List<Participant> OrderByGjiniaAsc() {
        Query query=em.createQuery ("SELECT p FROM Participant p WHERE p.statusi='Active' ORDER BY p.sex");
       
        return (List<Participant>)query.getResultList();
       
    }
    
    @Override
    public List<Participant> OrderByGjiniaDes() {
        Query query=em.createQuery ("SELECT p FROM Participant p WHERE p.statusi='Active' ORDER BY p.sex DESC");
      
        return (List<Participant>)query.getResultList();
        
    }
                                    
        
    @Override
    public List<Participant> findByGjiniaAsc(String gjinia) {
         Query query=em.createQuery ("SELECT p FROM Participant p WHERE p.sex= :emr AND p.statusi='Active' ORDER BY p.sex");
        query.setParameter("emr",gjinia);
        return (List<Participant>)query.getResultList();
    }
    
    @Override
    public List<Participant> findByGjiniaDes(String emrII) {
        Query query=em.createQuery ("SELECT p FROM Participant p WHERE p.sex= :emr AND p.statusi='Active' ORDER BY p.sex DESC");
        query.setParameter("emr",emrII);
        return (List<Participant>)query.getResultList();
    }
    
    @Override
    public List<Participant> OrderByQytetiAsc() {
        Query query=em.createQuery ("SELECT p FROM Participant p WHERE p.statusi='Active' ORDER BY p.city");
       
        return (List<Participant>)query.getResultList();
       
    }
    
    @Override
    public List<Participant> OrderByQytetiDes() {
        Query query=em.createQuery ("SELECT p FROM Participant p WHERE p.statusi='Active' ORDER BY p.city DESC");
      
        return (List<Participant>)query.getResultList();
        
    }
    
        
    @Override
    public List<Participant> findByQytetiAsc(String emrII) {
         Query query=em.createQuery ("SELECT p FROM Participant p  WHERE p.city LIKE :emr AND p.statusi='Active' ORDER BY p.city");
        query.setParameter("emr","%"+emrII+"%");
        
        return (List<Participant>)query.getResultList();
        
    }
    
    @Override
    public List<Participant> findByQytetiDes(String emrII) {
        Query query=em.createQuery ("SELECT p FROM Participant p WHERE p.city LIKE :emr AND p.statusi='Active' ORDER BY p.city DESC");
        query.setParameter("emr","%"+emrII+"%");
       
        return (List<Participant>)query.getResultList();
        
    }

    @Override
    public Participant findByEverything(Participant p) {
        Query query=em.createQuery ("SELECT p FROM Participant p WHERE p.name= :nam AND p.surname= :sur AND p.city= :cit AND p.participantState= :stat AND p.email= :ema AND p.participantAddress= :paddres AND p.sex= :sex AND p.zipCode= :zip AND p.pozita= :poz  AND p.phone= :pho  AND p.dob= :pdob AND p.iDNumber= :pid");
        query.setParameter("nam",p.getName());
        query.setParameter("sur",p.getSurname());
        query.setParameter("cit",p.getCity());
        query.setParameter("stat",p.getParticipantState());
        query.setParameter("ema",p.getEmail());
        query.setParameter("paddres",p.getParticipantAddress());
        query.setParameter("sex",p.getSex());
        query.setParameter("zip",p.getZipCode());
        query.setParameter("poz",p.getPozita());
        query.setParameter("pho",p.getPhone());
        query.setParameter("pid",p.getIDNumber());
        query.setParameter("pdob",p.getDob());
        return (Participant)query.getSingleResult();
    }
    
    @Override
    public List<Participant> findByTPDate(int i){
      Query query=em.createQuery("SELECT object(p) FROM Participant p,ParticipatingCompanyMember pcm,ParticipantTeam pt,Team t,TrainingProcess tp WHERE p.participantID=pcm.participantID.participantID AND pcm.pcmId=pt.pcmId.pcmId AND t.teamID=pt.teamID.teamID AND tp.teamID.teamID=t.teamID AND FUNC('YEAR',tp.endDate) = :data");
        query.setParameter("data", i);
        return (List<Participant>)query.getResultList();
    }
    
    @Override
    public long findByEvaluation(int participant){
      Query query=em.createQuery("SELECT COUNT(tte.evaluationID)  FROM TTrainerEvaluation tte, TrainingProcess tp , ParticipatingCompanyMember pcm WHERE tte.tProcessID.tProcessID =tp.tProcessID AND tte.pcmId.pcmId=pcm.pcmId AND pcm.participantID.participantID= :partid  AND tte.statusi='Active' AND tp.statusi='Active' AND pcm.statusi='Active' AND pcm.participantID.statusi='Active'");
        query.setParameter("partid", participant);
        return (long)query.getSingleResult();
        
    }

    @Override
    public Participant checkIfExists(String ParticipantID)throws AppException {
        try{
        Query query=em.createQuery("SELECT object(p) FROM Participant p WHERE p.ParticipantID = :ParticipantID AND p.statusi='Active'");
        query.setParameter("ParticipantID", ParticipantID);
        return (Participant)query.getSingleResult();
        }catch(NoResultException nre){
            throw new AppException("Ky pjesëmarrës ekzistonë.");
        }
    }
    
}
