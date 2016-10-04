
package bl;

import ejb.TrainerContact;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class TrainerContactRepository extends EntMngClass implements TrainerContactInterface {

    public TrainerContactRepository(EntityManager tempEm){
        super(tempEm);
    }
    
    @Override
    public void create(TrainerContact tc) throws AppException {
       try{
        em.getTransaction().begin();
        em.persist(tc);
        em.getTransaction().commit();
        }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një kontakt i trainerit me këto vlera.Secila kontakt i trainerit duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje kontakt i trainerit me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }
     }

    @Override
    public void edit(TrainerContact tc) throws AppException {
        try{
        em.getTransaction().begin();
        em.merge(tc);
        em.getTransaction().commit();
        }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një kontakt i trainerit me këto vlera.Secila kontakt i trainerit duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje kontakt i trainerit me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }
    }

    @Override
    public void remove(TrainerContact tc) {
          em.getTransaction().begin();
        em.remove(tc);
        em.getTransaction().commit();
   }

    @Override
    public List<TrainerContact> findAll() {
         return em.createNamedQuery("TrainerContact.findAll").getResultList();
    }

    @Override
    public TrainerContact findById(String TrainerContactID) {
          Query query=em.createQuery ("SELECT object (tc) FROM TrainerContact tc WHERE tc.contactID: tcID)");
        query.setParameter ("tcID",TrainerContactID);
        return (TrainerContact)query.getSingleResult();
   }
    
}
