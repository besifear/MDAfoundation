
package bl;

import ejb.view.ReportYear;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class ReportYearRepository extends EntMngClass implements ReportYearInterface{
    
    public ReportYearRepository(EntityManager tempEm){
        super(tempEm);
    }
    
    
    @Override
    public ReportYear create(ReportYear rm) throws AppException{
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
    public void edit(ReportYear rm) throws AppException{
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
    public void remove(ReportYear rm) {
        em.getTransaction().begin();
        em.remove(rm);
        em.getTransaction().commit();
    }

    @Override
    public List<ReportYear> findAll() {
        return em.createNamedQuery("ReportYear.findAll").getResultList();
        /*Query query=em.createQuery("SELECT object(tv) FROM TrainingView tv");
        List<TrainingView> ts=query.getResultList();
        for(int i=0;i<ts.size();i++){
        JOptionPane.showMessageDialog(null, ts.get(i).getTopicCovered());
        }
        return query.getResultList();*/
        
    }

    @Override
    public ReportYear findById(String muaji) {
        Query query=em.createQuery("SELECT object(tv) FROM ReportYear tv WHERE tv.viti = :muaj ");
        query.setParameter("muaj", muaji);
        return (ReportYear)query.getSingleResult();
    }
}
