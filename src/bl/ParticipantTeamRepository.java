package bl;

import ejb.ParticipantTeam;
import ejb.ParticipatingCompanyMember;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;


public class ParticipantTeamRepository extends EntMngClass implements ParticipantTeamInterface{
    
    public ParticipantTeamRepository(EntityManager tempEm){
        super(tempEm);
    }
    
    @Override
    public ParticipantTeam create(ParticipantTeam pte)throws AppException {
        try{
            em.getTransaction().begin();
            em.persist(pte);
            em.flush();
            em.getTransaction().commit();
            return pte;
        }catch (Throwable thro){
            thro.printStackTrace();
            if (thro.getMessage().contains("2627")){
                if(thro.getMessage().toLowerCase().contains("unique"))
                throw new AppException("Ekziston një ekip e pjesëmarrësve me këto vlera.Secila ekip e pjesëmarrësve duhet të jetë unike.");
                else 
                throw new AppException("Ekziston nje ekip e pjesëmarrësve me këtë çelës primarë.");
            }
            else{
                throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
            }
        }
    }

    @Override
    public void edit(ParticipantTeam pte) throws AppException{
        try{
            em.getTransaction().begin();
            em.merge(pte);
            em.getTransaction().commit();
        }catch (Throwable thro){
            thro.printStackTrace();
            if (thro.getMessage().contains("2627")){
                if(thro.getMessage().toLowerCase().contains("unique"))
                    throw new AppException("Ekziston një ekip e pjesëmarrësve me këto vlera.Secila ekip e pjesëmarrësve duhet të jetë unike.");
                else 
                    throw new AppException("Ekziston nje ekip e pjesëmarrësve me këtë çelës primarë.");
            }
            else{
                throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
            }
        }
    }

    @Override
    public void remove(ParticipantTeam pte) {
        em.getTransaction().begin();
        em.remove(pte);
        em.getTransaction().commit();
    }

    @Override
    public List<ParticipantTeam> findAll() {
        return em.createNamedQuery("ParticipantTeam.findAll").getResultList();
    }

    @Override
    public ParticipantTeam findById(String participantteamId) {
        Query query=em.createQuery("SELECT object(pte) FROM ParticipantTeam pte WHERE pte.participantTeamID = :ParticipantTeamID");
        query.setParameter("ParticipantTeamID", participantteamId);
        return (ParticipantTeam)query.getSingleResult();
    }
    
    @Override
    public List<ParticipantTeam> findByTeamId(int temp){ 
       Query query=em.createQuery("SELECT object(pt) FROM ParticipantTeam pt WHERE pt.teamID.teamID= :teamId");
       query.setParameter("teamId", temp);
       return (List<ParticipantTeam>)query.getResultList();
    }
    
     @Override
    public ParticipantTeam findByEverything(ParticipantTeam pt) {
       Query query=em.createQuery("SELECT object(pte) FROM ParticipantTeam pte WHERE pte.teamID.teamID = :teamId AND pte.pcmId.pcmId= :pcmId");
    
       query.setParameter("teamId", pt.getTeamID().getTeamID());
       query.setParameter("pcmId", pt.getPcmId().getPcmId() );
        return (ParticipantTeam)query.getSingleResult();
    
    }
    
    
            
    @Override
    public ParticipantTeam findByParticipantTP(int participant,String tProcess) {
        Query query=em.createQuery("SELECT object(pt) FROM ParticipantTeam pt ,Team t ,TrainingProcess tp ,ParticipatingCompanyMember pcm ,Participant par WHERE par.participantID= :pId AND tp.tProcessID= :tpId AND pt.teamID.teamID =t.teamID AND tp.teamID.teamID=t.teamID AND pcm.pcmId=pt.pcmId.pcmId AND par.participantID=pcm.participantID.participantID");
        query.setParameter("pId", participant);
        query.setParameter("tpId", tProcess);
        return (ParticipantTeam)query.getSingleResult();
    }   
    
    @Override
    public boolean CheckIfInTraining(long participant,String tProcess){
        
        Query query=em.createQuery("SELECT object(pt) FROM ParticipantTeam pt ,Team t ,TrainingProcess tp ,ParticipatingCompanyMember pcm ,Participant par WHERE par.iDNumber = :pId AND tp.tProcessID= :tpId AND pt.teamID.teamID =t.teamID AND tp.teamID.teamID=t.teamID AND pcm.pcmId=pt.pcmId.pcmId AND par.participantID=pcm.participantID.participantID AND pt.statusi='Active' ");
        query.setParameter("pId", participant);
        query.setParameter("tpId", tProcess);
        return (!query.getResultList().isEmpty());
    }    

    @Override
    public List<ParticipantTeam> findByPCM(ParticipatingCompanyMember pcmtempi) {
        Query query=em.createQuery("SELECT object(pt) FROM ParticipantTeam pt,ParticipatingCompanyMember pcm ,Team t ,TrainingProcess tp WHERE pt.pcmId.pcmId =pcm.pcmId AND t.teamID=pt.teamID.teamID AND tp.teamID.teamID=t.teamID AND pcm.pcmId= :participantCMID AND t.statusi='Active' AND pt.statusi='Active' ");
        query.setParameter("participantCMID", pcmtempi.getPcmId());
        return (List<ParticipantTeam>)query.getResultList();
    }
}

   

  
