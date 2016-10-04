
package bl;

import ejb.TrainerTeamCombo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class TrainerTeamComboRepository extends EntMngClass implements TrainerTeamComboInterface {

    public TrainerTeamComboRepository(EntityManager tempEm){
        super(tempEm);
    }
    
    @Override
    public TrainerTeamCombo create(TrainerTeamCombo ttc)throws AppException {
        try{
        em.getTransaction().begin();
        em.persist(ttc);
        em.flush();
        em.getTransaction().commit();
        return ttc;
        }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një traineriEkipaCombo me këto vlera.Secila traineriEkipaCombo duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje traineriEkipaCombo me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }    
    }

    @Override
    public void edit(TrainerTeamCombo ttc)throws AppException {
        try{
        em.getTransaction().begin();
        em.merge(ttc);
        em.getTransaction().commit();
        }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një traineriEkipaCombo me këto vlera.Secila traineriEkipaCombo duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje traineriEkipaCombo me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }    
    }

    @Override
    public void remove(TrainerTeamCombo ttc) {
          em.getTransaction().begin();
        em.remove(ttc);
        em.getTransaction().commit();
     }

    @Override
    public List<TrainerTeamCombo> findAll() {
        return em.createNamedQuery("TrainerTeamCombo.findAll").getResultList();
     }

    @Override
    public TrainerTeamCombo findById(String TrainerTeamComboID){
         Query query=em.createQuery ("SELECT object (ttc) FROM TrainerTeamCombo ttc WHERE ttc.trainerTeamComboID=: ttcID)");
        query.setParameter ("ttcID",TrainerTeamComboID);
        return (TrainerTeamCombo)query.getSingleResult();
   }

    @Override
    public TrainerTeamCombo findByTrainerTrainerTeam(int trainerId, int trainerTeamId){
         Query query=em.createQuery ("SELECT object (ttc) FROM TrainerTeamCombo ttc WHERE ttc.trainerID.trainerID= :trId AND ttc.trainersTeamID.trainersTeamID= :trTe ");
        query.setParameter ("trId",trainerId);
        query.setParameter ("trTe",trainerTeamId);
        return (TrainerTeamCombo)query.getSingleResult(); 
    }
    
    @Override
    public List<TrainerTeamCombo> findByTrainerTeam(int trainerTeamId){
        Query query=em.createQuery ("SELECT object (ttc) FROM TrainerTeamCombo ttc WHERE ttc.trainersTeamID.trainersTeamID= :trTe ");
        query.setParameter ("trTe",trainerTeamId);
        return (List<TrainerTeamCombo>)query.getResultList();
    }
}
