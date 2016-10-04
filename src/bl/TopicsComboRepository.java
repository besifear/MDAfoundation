package bl;

import ejb.TopicsCombo;
import ejb.TrainingProcess;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class TopicsComboRepository extends EntMngClass implements TopicsComboInterface{
    
    public TopicsComboRepository(EntityManager tempEm){
        super(tempEm);
    }
    
    @Override
    public TopicsCombo create(TopicsCombo c) throws AppException{
        try{
        em.getTransaction().begin();
        em.persist(c);
        em.flush();
        em.getTransaction().commit();
        return c;
        }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një temat e trajnimit tp combo me këto vlera.Secila temat e trajnimit tp combo duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje temat e trajnimit tp combo me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }
    }

    @Override
    public void edit(TopicsCombo c) throws AppException{
        try{
        em.getTransaction().begin();
        em.merge(c);
        em.getTransaction().commit();
        }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një temat e trajnimit tp combo me këto vlera.Secila temat e trajnimit tp combo duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje temat e trajnimit tp combo me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }
    }

    @Override
    public void remove(TopicsCombo c) {
        em.getTransaction().begin();
        em.remove(c);
        em.getTransaction().commit();
    }

    @Override
    public List<TopicsCombo> findAll() {
        return em.createNamedQuery("TopicsCombo.findAll").getResultList();
    }

    @Override
    public TopicsCombo findById(String TopicsComboId) {
        Query query=em.createQuery("SELECT object(c) FROM TopicsCombo c WHERE c.topicComboID = :TopicsComboID");
        query.setParameter("TopicsComboID", TopicsComboId);
        return (TopicsCombo)query.getSingleResult();
    }

    @Override
    public TopicsCombo findByTpTc(TopicsCombo tc) {
        Query query=em.createQuery("SELECT object(c) FROM TopicsCombo c WHERE c.tProcessID.tProcessID = :tpro AND c.topicID.topicID= :tcombo");
        query.setParameter("tpro", tc.getTProcessID().getTProcessID());
        query.setParameter("tcombo", tc.getTopicID().getTopicID());
        return (TopicsCombo)query.getSingleResult();
    
    }

    @Override
    public List<TopicsCombo> findByTrainingProcess(TrainingProcess tp) {
        Query query=em.createQuery("SELECT object(c) FROM TopicsCombo c WHERE c.tProcessID.tProcessID LIKE :tpId ");
        query.setParameter("tpId", tp.getTProcessID());
        return (List<TopicsCombo>)query.getResultList();
    }
    
}
