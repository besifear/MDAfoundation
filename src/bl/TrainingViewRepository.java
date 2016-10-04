
package bl;

import ejb.TrainingView;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;

public class TrainingViewRepository extends EntMngClass implements TrainingViewInterface{
    
    public TrainingViewRepository(EntityManager tempEm){
        super(tempEm);
    }
     
    
    @Override
    public TrainingView create(TrainingView tv) throws AppException{
        try{
        em.getTransaction().begin();
        em.persist(tv);
        em.flush();
        em.getTransaction().commit();
        return tv;
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
    public void edit(TrainingView tv) throws AppException{
        try{
        em.getTransaction().begin();
        em.merge(tv);
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
    public void remove(TrainingView tv) {
        em.getTransaction().begin();
        em.remove(tv);
        em.getTransaction().commit();
    }

    @Override
    public List<TrainingView> findAll() {
        Query query=em.createQuery("SELECT tv FROM TrainingView tv ORDER BY tv.tProcessID ");
        return (List<TrainingView>)query.getResultList();
    }

    @Override
    public TrainingView findById(String TrainId) {
        Query query=em.createQuery("SELECT object(tv) FROM TrainingView tv WHERE tv.tProcessID = :id ");
        query.setParameter("id", TrainId);
        return (TrainingView)query.getSingleResult();
    }
}
