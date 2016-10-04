

package bl;

import ejb.ParticipantTeam;
import ejb.ParticipatingCompanyMember;
import ejb.Team;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;

public class TeamRepository extends EntMngClass implements TeamInterface{
     
    public TeamRepository(EntityManager tempEm){
        super(tempEm);
    }
    
    @Override
    public Team create(Team te) throws AppException{
        try{
        em.getTransaction().begin();
        em.persist(te);
        em.flush();
        em.getTransaction().commit();
        return te;
        }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një ekip e trainerëve e pjesëmarrësve me këto vlera.Secila ekip e trainerëve e pjesëmarrësve duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje ekip e trainerëve e pjesëmarrësve me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }
    }

    @Override
    public void edit(Team te) throws AppException{
        try{
        em.getTransaction().begin();
        em.merge(te);
        em.getTransaction().commit();
        }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një ekip e trainerëve e pjesëmarrësve me këto vlera.Secila ekip e trainerëve e pjesëmarrësve duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje ekip e trainerëve e pjesëmarrësve me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }
        }

    @Override
    public void remove(Team te) {
        em.getTransaction().begin();
        em.remove(te);
        em.getTransaction().commit();
    }

    @Override
    public List<Team> findAll() {
        return em.createNamedQuery("Team.findAll").getResultList();
    }

    @Override
    public Team findById(String teamId) {
        Query query=em.createQuery("SELECT object(te) FROM Participant te WHERE te.TeamID = :TeamID");
        query.setParameter("TeamID", teamId);
        return (Team)query.getSingleResult();
    }
    
    
    @Override
    public Team findByTpId(String TpID) {
        Query query=em.createQuery("SELECT object(te) FROM Team te WHERE te.tProcessID.tProcessID = :trainp");
        query.setParameter("trainp", TpID);
        return (Team)query.getSingleResult();
    }

    public String prepareParticipants(List<ParticipatingCompanyMember> pcms){
        String s="";
        for (int i=0;i<pcms.size();i++){
            if (i+1==pcms.size())
                s+="SELECT object(t) FROM Team t,ParticipatingCompanyMember pcm,ParticipantTeam pt WHERE t.teamID =pt.teamID.teamID AND pt.pcmId.pcmId =pcm.pcmId AND pt.pcmId.pcmId ="+pcms.get(i).getPcmId()+" AND EXISTS (SELECT count(pcm.participantID.participantID) FROM ParticipatingCompanyMember pcm,ParticipantTeam pt,Team ttt WHERE pt.pcmId.pcmId=pcm.pcmId AND pt.teamID.teamID=ttt.teamID AND t.teamID=ttt.teamID HAVING count(pcm.participantID.participantID)="+pcms.size()+")";
            else
            s+="SELECT object (t) FROM Team t,ParticipatingCompanyMember pcm, ParticipantTeam pt WHERE t.teamID =pt.teamID.teamID AND pt.pcmId.pcmId=pcm.pcmId and pt.pcmId.pcmId = "+pcms.get(i).getPcmId()+ " INTERSECT ";
        }
        return s;
    }
    
    /*public Team TestiBudallem(List<ParticipatingCompanyMember> pcms) {
        Query query=em.createQuery("SELECT object (t) FROM Team t,ParticipatingCompanyMember pcm,ParticipantTeam pt WHERE t.teamID=pt.teamID.teamID AND pt.pcmId.pcmId=pcm.pcmId AND pt.pcmId.pcmId=12 AND EXISTS (SELECT count(pcm.participantID.participantID) FROM ParticipatingCompanyMember pcm,ParticipantTeam pt,Team ttt WHERE pt.pcmId.pcmId=pcm.pcmId AND pt.teamID.teamID=ttt.teamID AND t.teamID=ttt.teamID HAVING COUNT(pcm.participantID.participantID) ");
        return (Team)query.getSingleResult();
    }*/
    
    @Override
    public Team findByParticipants(List<ParticipatingCompanyMember> pcms) {
        Query query=em.createQuery(prepareParticipants(pcms));
        return (Team)query.getSingleResult();
    }

}
