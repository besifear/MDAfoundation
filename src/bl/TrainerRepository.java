

package bl;
import ejb.Trainer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class TrainerRepository extends EntMngClass implements TrainerInterface{

    public TrainerRepository(EntityManager tempEm){
        super(tempEm);
    }
    
    @Override
    public Trainer create(Trainer t)throws AppException {
        try{
        em.getTransaction().begin();
        em.persist(t);
        em.flush();
        em.getTransaction().commit();
        return t;
        }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një trainer me këto vlera.Secila trainer duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje trainer me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }
   }

    @Override
    public void edit(Trainer t)throws AppException {
        try{
        em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();
        }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një trainer me këto vlera.Secila trainer duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje trainer me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }    
    }

    @Override
    public void remove(Trainer t) {
         em.getTransaction().begin();
        em.remove(t);
        em.getTransaction().commit();
     }

    @Override
    public List<Trainer> findAll() {
       return em.createNamedQuery("Trainer.findAll").getResultList();
    }

    @Override
    public Trainer findById(String TrainerID) {
          Query query=em.createQuery ("SELECT object (t) FROM Trainer t WHERE t.trainerID: tID");
        query.setParameter ("tID",TrainerID);
        return (Trainer)query.getSingleResult();
   }
    
    @Override
    public List <Trainer> findByTP(String TrainingProcessID) {
          Query query=em.createQuery ("SELECT object (t) FROM Trainer t,TrainerTeamCombo ttc,TrainersTeam tt,TrainingProcess tp WHERE t.trainerID =ttc.trainerID.trainerID  AND tt.trainersTeamID =ttc.trainersTeamID.trainersTeamID AND tp.trainersTeamID.trainersTeamID =tt.trainersTeamID AND tp.tProcessID = :tpID AND t.statusi='Active' AND tt.statusi='Active' AND tp.statusi='Active' AND ttc.statusi='Active'");
        query.setParameter ("tpID",TrainingProcessID);
        return (List <Trainer>)query.getResultList();
   }
    
    @Override
    public Trainer findByNameSurname(Trainer t){
        Query query=em.createQuery ("SELECT object (t) FROM Trainer t WHERE t.name= :nam AND t.surname= :sur ");
        query.setParameter ("nam",t.getName());
        query.setParameter ("sur",t.getSurname());
        return (Trainer)query.getSingleResult();
   
    }

    @Override
    public int findMaxTrainierProjectID() {
         Query query=em.createQuery("SELECT p.trainerID FROM Trainer p WHERE p.trainerID >=All(SELECT pp.trainerID FROM Trainer pp)");
        return (int)query.getSingleResult();
    }
    
    
    
}
