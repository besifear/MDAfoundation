
package bl;

import ejb.TrainingProject;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class TrainingProjectRepository extends EntMngClass implements TrainingProjectInterface {

    public TrainingProjectRepository(EntityManager tempEm){
        super(tempEm);
    }
    
    @Override
    public TrainingProject create(TrainingProject tp)throws AppException {
        try{
        em.getTransaction().begin();
        em.persist(tp);
        em.flush();
        em.getTransaction().commit();
        return tp;
        }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një projektTrajnimi me këto vlera.Secila projektTrajnimi duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje projektTrajnimi me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }
   }

    @Override
    public void edit(TrainingProject tp) throws AppException{
        try{
         em.getTransaction().begin();
        em.merge(tp);
        em.getTransaction().commit();
        }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një projektTrajnimi me këto vlera.Secila projektTrajnimi duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje projektTrajnimi me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }
    }

    @Override
    public void remove(TrainingProject tp) {
        em.getTransaction().begin();
        em.remove(tp);
        em.getTransaction().commit();
    }

    @Override
    public List<TrainingProject> findAll() {
         return em.createNamedQuery("TrainingProject.findAll").getResultList();
    }

    @Override
    public TrainingProject findById(String TrainingProjectID) {
         Query query=em.createQuery ("SELECT object (tp) FROM TrainingProject tp WHERE tp.tpId: tpID)");
        query.setParameter ("tpID",TrainingProjectID);
        return (TrainingProject)query.getSingleResult();
   }
    
    @Override
    public TrainingProject findByTrainingProject(int TrainingId, int ProjectId) {
        Query query=em.createQuery ("SELECT object (tp) FROM TrainingProject tp WHERE tp.trainingID.trainingID =:tId AND tp.projectID.projectID = :pId");
        query.setParameter ("tId",TrainingId);
        query.setParameter ("pId",ProjectId);
        return (TrainingProject)query.getSingleResult();
   
    }
    
    @Override
    public int findMaxTrainingProjectID(){
        Query query=em.createQuery("SELECT p.tpId FROM TrainingProject p WHERE p.tpId >=All(SELECT pp.tpId FROM TrainingProject pp)");
        return (int)query.getSingleResult();
    }
    
    
}
