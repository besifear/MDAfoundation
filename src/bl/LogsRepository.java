package bl;

import ejb.Logs;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;

public class LogsRepository extends EntMngClass implements LogsInterface{
    
    public LogsRepository(EntityManager tempEm){
        super(tempEm);
    }
    
     @Override
    public Logs create(Logs l)throws AppException {
        try{
        em.getTransaction().begin();
        em.persist(l);
        em.flush();
        em.getTransaction().commit();
        return l;
        }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një logs me këto vlera.Secila logs duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje logs me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }
    }

    @Override
    public void edit(Logs l)throws AppException {
       try{
        em.getTransaction().begin();
        em.merge(l);
        em.getTransaction().commit();
        }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një logs me këto vlera.Secila logs duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje logs me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }
       }

    @Override
    public void remove(Logs l) {
        em.getTransaction().begin();
        em.remove(l);
        em.getTransaction().commit();
    }

    @Override
    public List<Logs> findAll() {
        return em.createNamedQuery("Logs.findAll").getResultList();
    }
    
    @Override
    public Date findDate(){
        Query query=em.createNativeQuery("SELECT CURRENT_TIMESTAMP");
        return (Date)query.getSingleResult();
    }
    
    @Override
    public Time findTime(){
        Query query=em.createNativeQuery("SELECT CONVERT (time, SYSDATETIME())");
        return (Time)query.getSingleResult();
    }
    
    @Override
    public List<Logs> OrderByTypeAsc(){
        Query query=em.createQuery ("SELECT l FROM Logs l WHERE l.statusi='Active' ORDER BY l.lloji");
        return (List<Logs>)query.getResultList();
    }
    
    @Override
    public List<Logs> OrderByTypeDes(){
        Query query=em.createQuery ("SELECT l FROM Logs l WHERE l.statusi='Active' ORDER BY l.lloji DESC");
        return (List<Logs>)query.getResultList();
    }
    
    @Override
    public List<Logs> findByTypeAsc(String s) {
         Query query=em.createQuery ("SELECT l FROM Logs l WHERE l.lloji LIKE :emr AND l.statusi='Active' ORDER BY l.lloji");
        query.setParameter("emr","%"+s+"%");
        
        return (List<Logs>)query.getResultList();
        
    }
    
    @Override
    public List<Logs> findByTypeDes(String s) {
         Query query=em.createQuery ("SELECT l FROM Logs l WHERE l.lloji LIKE :emr AND l.statusi='Active' ORDER BY l.lloji DESC");
        query.setParameter("emr","%"+s+"%");
        
        return (List<Logs>)query.getResultList();
        
    }
    
    @Override
    public List<Logs> OrderByDateAsc() {
        Query query=em.createQuery ("SELECT l FROM Logs l WHERE l.statusi='Active'  ORDER BY l.dita");
       
        return (List<Logs>)query.getResultList();
       
    }
    
    @Override
    public List<Logs> OrderByDateDes() {
        Query query=em.createQuery ("SELECT l FROM Logs l WHERE l.statusi='Active' ORDER BY l.dita DESC");
      
        return (List<Logs>)query.getResultList();
        
    }
    
        
    @Override
    public List<Logs> findByDateAsc(Date sd) {
         Query query=em.createQuery ("SELECT l FROM Logs l WHERE l.dita= :emr AND l.statusi='Active' ORDER BY l.dita");
        query.setParameter("emr",sd);
        
        return (List<Logs>)query.getResultList();
        
    }
    
    @Override
    public List<Logs> findByDateDes(Date sd) {
        Query query=em.createQuery ("SELECT l FROM Logs l WHERE l.dita= :emr AND l.statusi='Active' ORDER BY l.dita DESC");
        query.setParameter("emr",sd);
       
        return (List<Logs>)query.getResultList();
        
    }
    
    @Override
    public List<Logs> OrderByUserAsc(){
        Query query=em.createQuery ("SELECT l FROM Logs l WHERE l.statusi='Active' ORDER BY l.username");
        return (List<Logs>)query.getResultList();
    }
    
    @Override
    public List<Logs> OrderByUserDes(){
        Query query=em.createQuery ("SELECT l FROM Logs l WHERE l.statusi='Active' ORDER BY l.username DESC");
        return (List<Logs>)query.getResultList();
    }
    
    @Override
    public List<Logs> findByUserAsc(String s) {
         Query query=em.createQuery ("SELECT l FROM Logs l WHERE l.username.username LIKE :emr ORDER BY l.username");
        query.setParameter("emr","%"+s+"%");
        
        return (List<Logs>)query.getResultList();
        
    }
    
    @Override
    public List<Logs> findByUserDes(String s) {
         Query query=em.createQuery ("SELECT l FROM Logs l WHERE l.username.username LIKE :emr AND l.statusi='Active' ORDER BY l.username DESC");
        query.setParameter("emr","%"+s+"%");
        
        return (List<Logs>)query.getResultList();
    }
}

