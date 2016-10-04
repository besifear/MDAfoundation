

package bl;

import ejb.Project;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class ProjectRepository extends EntMngClass implements ProjectInterface {

    public ProjectRepository(EntityManager tempEm){
        super(tempEm);
    }
    
    @Override
    public Project create(Project p)throws AppException {
        try{
        em.getTransaction().begin();
        em.persist(p);
        em.flush();
        em.getTransaction().commit();
        return p;
        }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një projekt me këto vlera.Secila projekt duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje projekt me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }
    }

    @Override
    public void edit(Project p)throws AppException {
        try{
       em.getTransaction().begin();
        em.merge(p);
        em.getTransaction().commit();
        }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një projekt me këto vlera.Secila projekt duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje projekt me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }
        }

    @Override
    public void remove(Project p) {
    em.getTransaction().begin();
        em.remove(p);
        em.getTransaction().commit();
    }

    @Override
    public List<Project> findAll() {
         return em.createNamedQuery("Project.findAll").getResultList();
    }

    @Override
    public Project findById(String ProjectID) {
           Query query=em.createQuery ("SELECT object (p) FROM Project p WHERE p.projectID= :pID");
        query.setParameter ("pID",ProjectID);
        return (Project)query.getSingleResult();
    }
    
    @Override
    public Project findByName(String Name) {
           Query query=em.createQuery ("SELECT p FROM Project p WHERE p.emri= :Pemri");
        query.setParameter ("Pemri",Name);
        return (Project)query.getSingleResult();
    }
    
    @Override
    public List<Project> findByTpId(String trainingpId) {
        Query query=em.createQuery("SELECT object(p) FROM Project p,TrainingProject tpro,TrainingProcess tp WHERE p.projectID=tpro.projectID.projectID AND tpro.tpId=tp.trainingProjectID.tpId AND tp.tProcessID= :tpID AND p.statusi='Active' AND tp.statusi='Active' AND tpro.statusi='Active' ");
        query.setParameter("tpID", trainingpId);
        return (List<Project>)query.getResultList();
    }
    
}
