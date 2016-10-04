
package bl;

import java.util.List;
import ejb.ParticipantView;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ParticipantViewRepository extends EntMngClass implements ParticipantViewInterface{
     
    public ParticipantViewRepository(EntityManager tempEm){
        super(tempEm);
    }
    
    @Override
    public ParticipantView create(ParticipantView pv) throws AppException{
        try{
        em.getTransaction().begin();
        em.persist(pv);
        em.flush();
        em.getTransaction().commit();
        return pv;
        }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një klient me këto vlera.Secila klient duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje klient me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }
    }

    @Override
    public void edit(ParticipantView pv) throws AppException{
        try{
        em.getTransaction().begin();
        em.merge(pv);
        em.getTransaction().commit();
        }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një klient me këto vlera.Secila klient duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje klient me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }
    }

    @Override
    public void remove(ParticipantView pv) {
        em.getTransaction().begin();
        em.remove(pv);
        em.getTransaction().commit();
    }

    @Override
    public List<ParticipantView> findAll() {
        return em.createNamedQuery("ParticipantView.findAll").getResultList();
    }

    @Override
    public ParticipantView findById(String PartId) {
        Query query=em.createQuery("SELECT object(pv) FROM ParticipantView pv WHERE pv.participantID = :id ");
        query.setParameter("id", PartId);
        return (ParticipantView)query.getSingleResult();
    }
}
