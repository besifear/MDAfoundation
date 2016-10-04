package bl;

import ejb.Company;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;

public class CompanyRepository extends EntMngClass implements CompanyInterface{
     
    public CompanyRepository(EntityManager tempEm){
        super(tempEm);
    }
    
    @Override
    public Company create(Company c)throws AppException {
        try{
        em.getTransaction().begin();
        em.persist(c);
        em.flush();
        em.getTransaction().commit();
        return c;
        }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një kompani me këto vlera.Secila kompani duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje kompani me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }
    }

    @Override
    public void edit(Company c)throws AppException {
        try{
        em.getTransaction().begin();
        em.merge(c);
        em.getTransaction().commit();
        }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një kompani me këto vlera.Secila kompani duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje kompani me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }
    }

    @Override
    public void remove(Company c) {
        em.getTransaction().begin();
        em.remove(c);
        em.getTransaction().commit();
    }

    @Override
    public List<Company> findAll() {
        return em.createNamedQuery("Company.findAll").getResultList();
    }

    @Override
    public Company findById(String companyId) {
        Query query=em.createQuery("SELECT object(c) FROM Company c WHERE c.CompanyID = :CompanyID");
        query.setParameter("CompanyID", companyId);
        return (Company)query.getSingleResult();
    }
    
    
    @Override
    public List<Company> findByTpId(String trainingpId) { 
        Query query=em.createQuery("SELECT object(c) FROM Company c,CTPcombo ctp,TrainingProcess tp WHERE c.companyID=ctp.companyID.companyID AND ctp.tProcessID.tProcessID=tp.tProcessID AND tp.tProcessID= :tpID AND c.statusi='Active' AND ctp.statusi='Active' AND tp.statusi='Active' ");
        query.setParameter("tpID", trainingpId);
        return (List<Company>)query.getResultList();
    }
    
    @Override
    public List<Company> OrderByCompanyAsc() {
        Query query=em.createQuery ("SELECT c FROM Company c WHERE c.statusi='Active' ORDER BY c.name ");
        return (List<Company>)query.getResultList();
    }
    
    @Override
    public List<Company> OrderByCompanyDes() {
        Query query=em.createQuery ("SELECT c FROM Company c WHERE c.statusi='Active' ORDER BY c.name DESC");
      
        return (List<Company>)query.getResultList();
        
    }
    
        
    @Override
    public List<Company> findByCompanyAsc(String emrII) {
         Query query=em.createQuery ("SELECT c FROM Company c WHERE c.statusi='Active' AND c.name LIKE :emr ORDER BY c.name");
        query.setParameter("emr","%"+emrII+"%");
        
        return (List<Company>)query.getResultList();
        
    }
    
    @Override
    public List<Company> findByCompanyDes(String emrII) {
        Query query=em.createQuery ("SELECT c FROM Company c WHERE c.statusi='Active' AND c.name LIKE :emr ORDER BY c.name DESC");
        query.setParameter("emr","%"+emrII+"%");
       
        return (List<Company>)query.getResultList();
    }

    @Override
    public Company findByEverything(Company cc) {
        Query query=em.createQuery ("SELECT c FROM Company c WHERE c.name = :emr AND c.companytype= :toc ");
        query.setParameter("emr",cc.getName());
        query.setParameter("toc",cc.getCompanytype());
        return (Company)query.getSingleResult();
    }
    
    @Override
    public List<Company> findByCompanyType(String emrII) {
         Query query=em.createQuery ("SELECT c FROM Company c WHERE c.companytype= :emr ");
        query.setParameter("emr",emrII);
        return (List<Company>)query.getResultList();
    }
    
    
    @Override
    public List<Company> findByParticipant(int idNumber) {
        Query query=em.createQuery ("SELECT c FROM Company c ,Participant p,ParticipatingCompanyMember pcm WHERE p.iDNumber=pcm.participantID.iDNumber AND c.companyID=pcm.companyID.companyID AND p.iDNumber= :id AND p.statusi='Active' AND pcm.statusi='Active' AND c.statusi='Active'");
        query.setParameter("id",idNumber);
        return (List<Company>)query.getResultList();
        
    }

    @Override
    public Company findByCompanyName(Company comp) {
        Query query=em.createQuery ("SELECT c FROM Company c WHERE c.name= :emr ");
        query.setParameter("emr",comp.getName());
        return (Company)query.getSingleResult();
    }
    
}
