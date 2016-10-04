
package bl;

import ejb.TopicsCovered;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;

public class TopicsCoveredRepository extends EntMngClass implements TopicsCoveredInterface{

    public TopicsCoveredRepository(EntityManager tempEm){
        super(tempEm);
    }
    
    @Override
    public TopicsCovered create(TopicsCovered tc)throws AppException {
        try{
        em.getTransaction().begin();
        em.persist(tc);
        em.flush();
        em.getTransaction().commit();
        return tc;
        }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një temat e trajnimit me këto vlera.Secila temat e trajnimit duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje temat e trajnimit me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }
    }

    @Override
    public void edit(TopicsCovered tc)throws AppException {
        try{
        em.getTransaction().begin();
        em.merge(tc);
        em.getTransaction().commit();
        }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një temat e trajnimit me këto vlera.Secila temat e trajnimit duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje temat e trajnimit me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }
     }

    @Override
    public void remove(TopicsCovered tc) {
        em.getTransaction().begin();
        em.remove(tc);
        em.getTransaction().commit();
    }

    @Override
    public List<TopicsCovered> findAll() {
         return em.createNamedQuery("TopicsCovered.findAll").getResultList();
     }

    @Override
    public TopicsCovered findById(String TopicsCoveredID) {
         Query query=em.createQuery ("SELECT object (tp) FROM TopicsCovered tp WHERE tp.topicID= :tpID");
        query.setParameter ("tpID",TopicsCoveredID);
        return (TopicsCovered)query.getSingleResult();
   }
    
    @Override
    public TopicsCovered findByTopicsCovered (TopicsCovered tc)
    {
         Query query=em.createQuery ("SELECT object (tc) FROM TopicsCovered tc WHERE tc.topicCovered=:top AND tc.language=:lan ");
        query.setParameter ("top",tc.getTopicCovered());
        query.setParameter ("lan",tc.getLanguage());
        return (TopicsCovered)query.getSingleResult();
   }
    
    @Override
    public List<TopicsCovered> findByTpLangId (String TrainingPID,String Language)
    {
        Query query=em.createQuery ("SELECT object (tc) FROM TopicsCovered tc,TopicsCombo toc WHERE tc.topicID=toc.topicID.topicID AND toc.tProcessID.tProcessID= :tpID AND tc.language= :lan AND tc.statusi='Active' AND toc.statusi='Active' ");
        query.setParameter ("tpID",TrainingPID);
        query.setParameter ("lan",Language);
        return (List<TopicsCovered>)query.getResultList();
   }

    @Override
    public List<TopicsCovered> findByTpId(String TrainingPID) {
        Query query=em.createQuery ("SELECT object (tc) FROM TopicsCovered tc,TopicsCombo toc WHERE tc.topicID=toc.topicID.topicID AND toc.tProcessID.tProcessID= :tpID AND tc.statusi='Active' AND toc.statusi='Active' ");
        query.setParameter ("tpID",TrainingPID);
        return (List<TopicsCovered>)query.getResultList();
    }
    
    @Override
    public List<Object> findByLang(String Lang) {
        Query query=em.createQuery ("SELECT object (tc) FROM TopicsCovered tc WHERE tc.language= :l AND tc.statusi='Active' ");
        query.setParameter ("l",Lang);
        return (List<Object>)query.getResultList();
    }
    
    @Override
    public List<TopicsCovered> findByTpIdAndLang(String TrainingPID,String Lang) {
        Query query=em.createQuery ("SELECT object (tc) FROM TopicsCovered tc,TopicsCombo toc WHERE tc.topicID=toc.topicID.topicID AND toc.tProcessID.tProcessID= :tpID AND tc.language = :language AND tc.statusi='Active' AND toc.statusi='Active' ");
        query.setParameter ("tpID",TrainingPID);
        query.setParameter ("language",Lang);
        return (List<TopicsCovered>)query.getResultList();
    }

    @Override
    public boolean isUsedInATrainingProcess(TopicsCovered tc) {
        Query query=em.createQuery ("SELECT object (tc) FROM TopicsCovered tc,TopicsCombo tcomb WHERE tcomb.topicID.topicID=tc.topicID and tcomb.statusi='Active' and tc.topicCovered LIKE :tcName ");
        query.setParameter ("tcName",tc.getTopicCovered());
        return(!query.getResultList().isEmpty());
    }
}
