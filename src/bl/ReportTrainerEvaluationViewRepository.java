
package bl;

import ejb.view.ReportTrainerEvaluation;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;


public class ReportTrainerEvaluationViewRepository extends EntMngClass implements ReportTrainerEvaluationViewInterface{
    
    public ReportTrainerEvaluationViewRepository(EntityManager tempEm){
        super(tempEm);
    }
    
    @Override
    public ReportTrainerEvaluation create(ReportTrainerEvaluation rm) throws AppException{
        try{
        em.getTransaction().begin();
        em.persist(rm);
        em.flush();
        em.getTransaction().commit();
        return rm;
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
    public void edit(ReportTrainerEvaluation rm) throws AppException{
        try{
        em.getTransaction().begin();
        em.merge(rm);
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
    public void remove(ReportTrainerEvaluation rm) {
        em.getTransaction().begin();
        em.remove(rm);
        em.getTransaction().commit();
    }

    @Override
    public List<ReportTrainerEvaluation> findAll() {
        return em.createNamedQuery("ReportTrainerEvaluation.findAll").getResultList();
        /*Query query=em.createQuery("SELECT object(tv) FROM TrainingView tv");
        List<TrainingView> ts=query.getResultList();
        for(int i=0;i<ts.size();i++){
        JOptionPane.showMessageDialog(null, ts.get(i).getTopicCovered());
        }
        return query.getResultList();*/
        
    }

    @Override
    public ReportTrainerEvaluation findById(String pjemarresi) {
        Query query=em.createQuery("SELECT object(tv) FROM ReportTrainerEvaluation rte WHERE rte.pjesemaresitetrajnuar = :pjes ");
        query.setParameter("pjes", pjemarresi);
        return (ReportTrainerEvaluation)query.getSingleResult();
    }
}
