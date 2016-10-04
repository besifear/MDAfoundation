package bl;

import ejb.CTPcombo;
import ejb.Company;
import ejb.Participant;
import ejb.TrainingProcess;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class CTPcomboRepository extends EntMngClass implements CTPcomboInterface{
       
    public CTPcomboRepository(EntityManager tempEm){
        super(tempEm);
    }
    
    @Override
    public CTPcombo create (CTPcombo c)throws AppException {
    try{
        em.getTransaction().begin();
        em.persist(c);
        em.flush();
        em.getTransaction().commit();
        return c;
    }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një kombinimCompaniTp me këto vlera.Secila kombinimCompaniTp duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje kombinimCompaniTp me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }
    }

    @Override
    public void edit(CTPcombo c)throws AppException{
        try{
        em.getTransaction().begin();
        em.merge(c);
        em.getTransaction().commit();
        }catch(Throwable thro){
            if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një kombinimCompaniTp me këto vlera.Secila kombinimCompaniTp duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje kombinimCompaniTp me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
        }
    }

    @Override
    public void remove(CTPcombo c) {
        em.getTransaction().begin();
        em.remove(c);
        em.getTransaction().commit();
    }

    @Override
    public List<CTPcombo> findAll() {
        return em.createNamedQuery("CTPcombo.findAll").getResultList();
    }

    @Override
    public CTPcombo findById(String CTPcomboId) {
        Query query=em.createQuery("SELECT object(c) FROM CTPcombo c WHERE c.ctpcomboID = :CTPcomboID");
        query.setParameter("CTPcomboID", CTPcomboId);
        return (CTPcombo)query.getSingleResult();
    }

    @Override
    public CTPcombo findByEverything(CTPcombo ctpc) {
        Query query=em.createQuery("SELECT object(c) FROM CTPcombo c WHERE c.companyID.companyID= :comp AND c.tProcessID.tProcessID = :tpro ");
        query.setParameter("comp", ctpc.getCompanyID().getCompanyID());
        query.setParameter("tpro", ctpc.getTProcessID().getTProcessID());
        return (CTPcombo)query.getSingleResult();
    }

    @Override
    public CTPcombo findCTPcomboTpParticipant(TrainingProcess tptemp, Participant partemp) {
        Query query=em.createQuery("SELECT object(ctpc) FROM CTPcombo ctpc,Company c, ParticipatingCompanyMember pcm,TrainingProcess tp WHERE ctpc.companyID.companyID=c.companyID AND pcm.companyID.companyID =c.companyID AND pcm.participantID.participantID = :parti AND tp.tProcessID like :tpro AND ctpc.tProcessID.tProcessID like tp.tProcessID AND c.statusi='Active' AND pcm.statusi='Active' AND pcm.participantID.statusi='Active' AND tp.statusi='Active' ");
        query.setParameter("tpro", tptemp.getTProcessID());
        query.setParameter("parti", partemp.getParticipantID());
        return (CTPcombo)query.getSingleResult();
    }
    
    @Override
    public CTPcombo findCTPcomboTpParticipantForDelete(TrainingProcess tptemp, Participant partemp) {
        Query query=em.createQuery("SELECT object(ctpc) FROM CTPcombo ctpc,Company c, ParticipatingCompanyMember pcm,TrainingProcess tp WHERE ctpc.companyID.companyID=c.companyID AND pcm.companyID.companyID =c.companyID AND pcm.participantID.participantID = :parti AND tp.tProcessID like :tpro AND ctpc.tProcessID.tProcessID like tp.tProcessID");
        query.setParameter("tpro", tptemp.getTProcessID());
        query.setParameter("parti", partemp.getParticipantID());
        return (CTPcombo)query.getSingleResult();
    }

    @Override
    public ArrayList<CTPcombo> findByCompany(Company companyID) {
     Query query=em.createQuery("SELECT object(c) FROM CTPcombo c WHERE c.companyID= :comp ");
     query.setParameter("comp", companyID);   
     return (ArrayList<CTPcombo> )query.getResultList();
    }
    
    @Override
    public CTPcombo findByTPCompany(TrainingProcess tptemp, Company companyID) {
        Query query=em.createQuery("SELECT object(c) FROM CTPcombo c WHERE c.companyID.companyID = :comp AND c.tProcessID.tProcessID =:tpId");
        query.setParameter("comp", companyID.getCompanyID());
        query.setParameter("tpId", tptemp.getTProcessID());   
        return (CTPcombo)query.getSingleResult();
    }

    @Override
    public List<CTPcombo> findByCompanyActive(Company companyID) {
        Query query=em.createQuery("SELECT object(c) FROM CTPcombo c WHERE c.companyID.companyID = :comp AND c.statusi='Active'");
        query.setParameter("comp", companyID.getCompanyID());   
        return (List<CTPcombo>)query.getResultList();
    }

    @Override
    public List<CTPcombo> findByTP(TrainingProcess tp) {
        Query query=em.createQuery("SELECT object(c) FROM CTPcombo c WHERE c.tProcessID.tProcessID LIKE :tpId AND c.statusi='Active' ");
        query.setParameter("tpId", tp.getTProcessID());   
        return (List<CTPcombo>)query.getResultList();
    }
}