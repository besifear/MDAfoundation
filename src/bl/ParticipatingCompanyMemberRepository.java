package bl;

import ejb.CTPcombo;
import ejb.Company;
import ejb.Participant;
import ejb.ParticipatingCompanyMember;
import ejb.TrainingProcess;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ParticipatingCompanyMemberRepository extends EntMngClass implements ParticipatingCompanyMemberInterface{
    
    public ParticipatingCompanyMemberRepository(EntityManager tempEm){
        super(tempEm);
    }
    
    @Override
    public ParticipatingCompanyMember create(ParticipatingCompanyMember pcm) throws AppException {
        try{
        em.getTransaction().begin();
        em.persist(pcm);
        em.flush();
        em.getTransaction().commit();
        return pcm;
        }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një kombinimPjesmarrësKompani me këto vlera.Secila kombinimPjesmarrësKompani duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje kombinimPjesmarrësKompani me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }
    }

    @Override
    public void edit(ParticipatingCompanyMember pcm) throws AppException {
        try{
        em.getTransaction().begin();
        em.merge(pcm);
        em.getTransaction().commit();
        }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një kombinimPjesmarrësKompani me këto vlera.Secila kombinimPjesmarrësKompani duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje kombinimPjesmarrësKompani me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }
    }

    @Override
    public void remove(ParticipatingCompanyMember pcm) {
        em.getTransaction().begin();
        em.remove(pcm);
        em.getTransaction().commit();
    }

    @Override
    public List<ParticipatingCompanyMember> findAll() {
        return em.createNamedQuery("ParticipatingCompanyMember.findAll").getResultList();
    }

    @Override
    public ParticipatingCompanyMember findById(String participantId) {
        Query query=em.createQuery("SELECT object(pcm) FROM Participant pcm WHERE pcm.PCMID = :PCMID");
        query.setParameter("PCMID", participantId);
        return (ParticipatingCompanyMember)query.getSingleResult();
    }

    @Override
    public ParticipatingCompanyMember findByEverything(ParticipatingCompanyMember c) {
        Query query=em.createQuery("SELECT object(pcm) FROM ParticipatingCompanyMember pcm WHERE pcm.companyID.companyID = :pcmcid AND pcm.participantID.participantID= :pcmp ");
        query.setParameter("pcmcid", c.getCompanyID().getCompanyID());
        query.setParameter("pcmp", c.getParticipantID().getParticipantID());
        return (ParticipatingCompanyMember)query.getSingleResult();
    }

    @Override
    public List<ParticipatingCompanyMember> findByTeam(int teamId) {
        Query query=em.createQuery("SELECT object(pcm) FROM ParticipatingCompanyMember pcm, Team t, ParticipantTeam pt WHERE pt.pcmId.participantID =pcm.participantID AND t.teamID=pt.teamID.teamID AND t.teamID= :teaam AND pt.statusi='Active' AND t.statusi='Active' AND pcm.statusi='Active'");
        query.setParameter("teaam",teamId);
        return (List<ParticipatingCompanyMember>)query.getResultList();
    }

    @Override
    public List<ParticipatingCompanyMember> findByCTPcombo(CTPcombo ctptemp) {
        Query query=em.createQuery("SELECT object(pcm) FROM ParticipatingCompanyMember pcm,Company c,CTPcombo ctpc WHERE pcm.companyID.companyID=c.companyID AND ctpc.companyID.companyID=c.companyID AND ctpc= :ctpTemp AND c.statusi='Active' AND pcm.statusi='Active' AND ctpc.statusi='Active' ");
        query.setParameter("ctpTemp",ctptemp);
        return (List<ParticipatingCompanyMember>)query.getResultList();
    }
    
    @Override
    public List<ParticipatingCompanyMember> findByCTPcomboForDelete(CTPcombo ctptemp) {
        Query query=em.createQuery("SELECT object(pcm) FROM ParticipatingCompanyMember pcm,Company c,CTPcombo ctpc WHERE pcm.companyID.companyID=c.companyID AND ctpc.companyID.companyID=c.companyID AND ctpc= :ctpTemp");
        query.setParameter("ctpTemp",ctptemp);
        return (List<ParticipatingCompanyMember>)query.getResultList();
    }

    @Override
    public List<ParticipatingCompanyMember> findByCompany(Company companyID) {
        Query query=em.createQuery("SELECT object(pcm) FROM ParticipatingCompanyMember pcm,ParticipantTeam pt,Team t,TrainingProcess tp WHERE pcm.pcmId=pt.pcmId.pcmId AND t.teamID=pt.teamID.teamID AND tp.teamID.teamID=t.teamID AND pcm.companyID.companyID= :comID AND t.statusi='Active' ");
        query.setParameter("comID",companyID.getCompanyID());
        return (List<ParticipatingCompanyMember>)query.getResultList();
    }

    @Override
    public ParticipatingCompanyMember findBytTpParticipant(TrainingProcess tptemp, Participant partemp) {
        Query query=em.createQuery("SELECT object(pcm) FROM ParticipatingCompanyMember pcm ,ParticipantTeam pt,Team t,TrainingProcess tp WHERE pcm.participantID.participantID= :part AND pcm.pcmId=pt.pcmId.pcmId AND pt.teamID.teamID=t.teamID AND tp.teamID.teamID=t.teamID AND tp.tProcessID LIKE :tpro AND pt.statusi='Active'");
        query.setParameter("part",partemp.getParticipantID());
        query.setParameter("tpro",tptemp.getTProcessID());
        return (ParticipatingCompanyMember)query.getSingleResult();
    }

    @Override
    public ParticipatingCompanyMember findBytTpParticipantForDelete(TrainingProcess tptemp, Participant partemp) {
        Query query=em.createQuery("SELECT object(pcm) FROM ParticipatingCompanyMember pcm ,Participant p ,ParticipantTeam pt ,Team t ,TrainingProcess tp  WHERE  p.iDNumber= :part AND tp.tProcessID LIKE :tpro AND pcm.participantID.participantID=p.participantID AND pt.pcmId.pcmId=pcm.pcmId AND t.teamID =pt.teamID.teamID AND tp.teamID.teamID =t.teamID");
        query.setParameter("part",partemp.getIDNumber());
        query.setParameter("tpro",tptemp.getTProcessID());
        return (ParticipatingCompanyMember)query.getSingleResult();
    }
    
    @Override
    public List<ParticipatingCompanyMember> findByParticipant(Participant partemp) {
        Query query=em.createQuery("SELECT object(pcm) FROM ParticipatingCompanyMember pcm WHERE pcm.participantID.participantID = :part AND pcm.statusi='Active'");
        query.setParameter("part",partemp.getParticipantID());
        return (List<ParticipatingCompanyMember>)query.getResultList();
    }
}
