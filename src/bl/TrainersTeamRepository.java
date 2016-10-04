

package bl;

import ejb.Trainer;
import ejb.TrainersTeam;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class TrainersTeamRepository extends EntMngClass implements TrainersTeamInterface {

    public TrainersTeamRepository(EntityManager tempEm){
        super(tempEm);
    }
    
    @Override
    public TrainersTeam create(TrainersTeam tt) throws AppException {
        try{
       em.getTransaction().begin();
        em.persist(tt);
        em.flush();
        em.getTransaction().commit();
        return tt;
        }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një ekipëTrainerëve me këto vlera.Secila ekipëTrainerëve duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje ekipëTrainerëve me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }    
     }

    @Override
    public void edit(TrainersTeam tt) throws AppException {
        try{ 
        em.getTransaction().begin();
        em.merge(tt);
        em.getTransaction().commit();
        }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një ekipëTrainerëve me këto vlera.Secila ekipëTrainerëve duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje ekipëTrainerëve me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }    
        }

    @Override
    public void remove(TrainersTeam tt) {
        em.getTransaction().begin();
        em.remove(tt);
        em.getTransaction().commit();
    }

    @Override
    public List<TrainersTeam> findAll() {
       return em.createNamedQuery("TrainersTeam.findAll").getResultList();
   }

    @Override
    public TrainersTeam findById(String TrainersTeamID) {
       Query query=em.createQuery ("SELECT object (tt) FROM TrainersTeam tt WHERE tt.trainersTeamID= :ttID)");
        query.setParameter ("ttID",TrainersTeamID);
        return (TrainersTeam)query.getSingleResult();
    }

    @Override
    public TrainersTeam findByParticipants(ArrayList<Trainer> Trainers) {
        Query query=em.createQuery(prepareParticipants(Trainers));                                              
        return (TrainersTeam)query.getSingleResult();
    }
    
    
    
    public String prepareParticipants(List<Trainer> Trainers){
        String s="";
        for (int i=0;i<Trainers.size();i++){
            if (i+1==Trainers.size())
                s+="SELECT object(tt) FROM TrainersTeam tt,Trainer t, TrainerTeamCombo ttc WHERE tt.trainersTeamID=ttc.trainersTeamID.trainersTeamID AND ttc.trainerID.trainerID=t.trainerID AND t.trainerID ="+Trainers.get(i).getTrainerID()+" AND EXISTS (SELECT count(tr.trainerID) FROM Trainer tr,TrainersTeam trt,TrainerTeamCombo ttcc WHERE tr.trainerID=ttcc.trainerID.trainerID AND ttcc.trainersTeamID.trainersTeamID=trt.trainersTeamID AND trt.trainersTeamID =tt.trainersTeamID HAVING count(tr.trainerID)="+Trainers.size()+") ";
            else
            s+="SELECT object(tt) FROM TrainersTeam tt,Trainer t, TrainerTeamCombo ttc WHERE tt.trainersTeamID=ttc.trainersTeamID.trainersTeamID AND ttc.trainerID.trainerID=t.trainerID AND t.trainerID ="+Trainers.get(i).getTrainerID()+" INTERSECT ";
        }
        return s;
    }
}
