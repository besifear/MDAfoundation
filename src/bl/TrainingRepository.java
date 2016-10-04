
package bl;

import ejb.Training;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;




public class TrainingRepository extends EntMngClass implements TrainingInterface{

    public TrainingRepository(EntityManager tempEm){
        super(tempEm);
    }
    
    @Override
    public Training create(Training t)throws AppException {
        try{
         em.getTransaction().begin();
        em.persist(t);
        em.flush();
        em.getTransaction().commit();
        return t;
        }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një trajnim me këto vlera.Secila trajnim duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje trajnim me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }
     }

    @Override
    public void edit(Training t) throws AppException{
        try{
        em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();
        }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një trajnim me këto vlera.Secila trajnim duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje trajnim me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }
    }

    @Override
    public void remove(Training t) {
         em.getTransaction().begin();
        em.remove(t);
        em.getTransaction().commit();
     }

    @Override
    public List<Training> findAll() {
        return em.createNamedQuery("Training.findAll").getResultList();
     }

    @Override
    public Training findById(String TrainingID) {
         Query query=em.createQuery ("SELECT object (t) FROM Training t WHERE t.trainingID: tID)");
        query.setParameter ("tID",TrainingID);
        return (Training)query.getSingleResult();
     }
    
    
     @Override
    public int findByTOT(String TOT) {
         Query query=em.createQuery ("SELECT t.trainingID FROM Training t WHERE t.titleOfTraining : titleOfTraining)");
        query.setParameter ("titleOfTraining",TOT);
        return (int)query.getSingleResult();
     }
  
    @Override
    public Training findByTpId(String trainingpId) {
        Query query=em.createQuery("SELECT object(t) FROM Training t,TrainingProject tpro,TrainingProcess tp WHERE t.trainingID=tpro.trainingID.trainingID AND tpro.tpId=tp.trainingProjectID.tpId AND tp.tProcessID= :tpID AND t.statusi='Active' AND tpro.statusi='Active' AND tp.statusi='Active' ");
        query.setParameter("tpID", trainingpId);
        return (Training)query.getSingleResult();
        }
    
     @Override
    public List<Training> OrderByTrainingAsc() {
        Query query=em.createQuery ("SELECT t FROM Training t ORDER BY t.titleOfTrainingAlbanian");
       
        return (List<Training>)query.getResultList();
       
    }
    
    @Override
    public List<Training> OrderByTrainingDes() {
        Query query=em.createQuery ("SELECT t FROM Training t ORDER BY t.titleOfTrainingAlbanian DESC");
      
        return (List<Training>)query.getResultList();
        
    }
    
        
    @Override
    public List<Training> findByTrainingAsc(String emrII) {
         Query query=em.createQuery ("SELECT t FROM Training t WHERE t.titleOfTrainingAlbanian LIKE :emr ORDER BY t.titleOfTrainingAlbanian");
        query.setParameter("emr","%"+emrII+"%");
        
        return (List<Training>)query.getResultList();
        
    }
    
    @Override
    public List<Training> findByTrainingDes(String emrII) {
        Query query=em.createQuery ("SELECT t FROM Training t WHERE t.titleOfTrainingAlbanian LIKE :emr ORDER BY t.titleOfTrainingAlbanian DESC");
        query.setParameter("emr","%"+emrII+"%");
       
        return (List<Training>)query.getResultList();
    }
    
    @Override
    public Training findByTOTs(String topA,String topE,String topS){
         Query query=em.createQuery ("SELECT object (t) FROM Training t WHERE t.titleOfTrainingAlbanian= :titleA AND t.titleOfTrainingEnglish= :titleE AND t.titleOfTrainingSerbian= :titleS");
        query.setParameter ("titleA",topA);
        query.setParameter ("titleE",topE);
        query.setParameter ("titleS",topS);
        return (Training)query.getSingleResult();
    }
    
}
