
package bl;

import ejb.Client;
import ejb.TrainingProcess;
import ejb.view.AbstractReportEntity;
import ejb.view.ReportMonth;
import java.util.ArrayList;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.swing.JOptionPane;

public class TrainingProcessRepository extends EntMngClass implements TrainingProcessInterface {

    public TrainingProcessRepository(EntityManager tempEm){
        super(tempEm);
    }
    
    @Override
    public TrainingProcess create(TrainingProcess tp)throws AppException {
        try{
        em.getTransaction().begin();
        em.persist(tp);
        em.flush();
        em.getTransaction().commit();
        return tp;
        }catch (Throwable thro){
            if (thro.getMessage().contains("2627")){
                if(thro.getMessage().toLowerCase().contains("unique"))
                throw new AppException("Ekziston një processTrajnimi me këto vlera.Secila processTrajnimi duhet të jetë unike.");
                else 
                throw new AppException("Ekziston nje processTrajnimi me këtë çelës primarë.");
            }
            else{
                throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
            }
        }
    }

    @Override
    public void edit(TrainingProcess tp)throws AppException{
        try{
        em.getTransaction().begin();
        em.merge(tp);
        em.getTransaction().commit();
    }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një processTrajnimi me këto vlera.Secila processTrajnimi duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje processTrajnimi me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }
    }

    @Override
    public void remove(TrainingProcess tp) {
        em.getTransaction().begin();
        em.remove(tp);
        em.getTransaction().commit();
    }

    @Override
    public List<TrainingProcess> findAll() {
        return (List<TrainingProcess>)em.createNamedQuery("TrainingProcess.findAll").getResultList();
    }

    @Override
    public TrainingProcess findById(String TrainingProcessID) {
        Query query=em.createQuery ("SELECT object (tp) FROM TrainingProcess tp WHERE tp.tProcessID = :tpID");
        query.setParameter ("tpID",TrainingProcessID);
        return (TrainingProcess)query.getSingleResult();
    }
    
    @Override
    public TrainingProcess checkIfExists(String TrainingProcessID)throws AppException {
        try{
        Query query=em.createQuery ("SELECT object (tp) FROM TrainingProcess tp WHERE tp.tProcessID = :tpID AND tp.statusi='Active'");
        query.setParameter ("tpID",TrainingProcessID);
        return (TrainingProcess)query.getSingleResult();
        }catch(NoResultException nre){
            throw new AppException("Nuk ekziston Trainimi me kod të trajnimit të tillë");
        }
    }
    
    @Override
    public TrainingProcess findByIdActive(String TrainingProcessID) {
        Query query=em.createQuery ("SELECT object (tp) FROM TrainingProcess tp WHERE tp.tProcessID = :tpID AND tp.statusi='Active'");
        query.setParameter("tpID",TrainingProcessID);
        return (TrainingProcess)query.getSingleResult();
    }
    
    @Override
    public List<TrainingProcess> OrderByVendiAsc() {
        Query query=em.createQuery ("SELECT tp FROM TrainingProcess tp WHERE tp.statusi='Active' ORDER BY tp.place");
       
        return (List<TrainingProcess>)query.getResultList();
       
    }
    
    @Override
    public List<TrainingProcess> OrderByVendiDes() {
        Query query=em.createQuery ("SELECT tp FROM TrainingProcess tp WHERE tp.statusi='Active'  ORDER BY tp.place DESC");
      
        return (List<TrainingProcess>)query.getResultList();
        
    }
    
        
    @Override
    public List<TrainingProcess> findByVendiAsc(String emrII) {
         Query query=em.createQuery ("SELECT tp FROM TrainingProcess tp WHERE tp.place LIKE :emr AND tp.statusi='Active' ORDER BY tp.place");
        query.setParameter("emr","%"+emrII+"%");
        
        return (List<TrainingProcess>)query.getResultList();
        
    }
    
    @Override
    public List<TrainingProcess> findByVendiDes(String emrII) {
        Query query=em.createQuery ("SELECT tp FROM TrainingProcess tp WHERE tp.place LIKE :emr AND tp.statusi='Active'  ORDER BY tp.place DESC");
        query.setParameter("emr","%"+emrII+"%");
       
        return (List<TrainingProcess>)query.getResultList();
    }
    
    @Override
    public List<TrainingProcess> OrderByStartDateAsc() {
        Query query=em.createQuery ("SELECT tp FROM TrainingProcess tp WHERE tp.statusi='Active'  ORDER BY tp.startDate");
       
        return (List<TrainingProcess>)query.getResultList();
       
    }
    
    @Override
    public List<TrainingProcess> OrderByStartDateDes() {
        Query query=em.createQuery ("SELECT tp FROM TrainingProcess tp WHERE tp.statusi='Active' ORDER BY tp.startDate DESC");
      
        return (List<TrainingProcess>)query.getResultList();
        
    }
    
        
    @Override
    public List<TrainingProcess> findByStartDateAsc(Date sd) {
         Query query=em.createQuery ("SELECT tp FROM TrainingProcess tp WHERE tp.startDate= :emr AND tp.statusi='Active' ORDER BY tp.startDate");
        query.setParameter("emr",sd);
        
        return (List<TrainingProcess>)query.getResultList();
        
    }
    
    @Override
    public List<TrainingProcess> findByStartDateDes(Date sd) {
        Query query=em.createQuery ("SELECT tp FROM TrainingProcess tp WHERE tp.startDate= :emr AND tp.statusi='Active' ORDER BY tp.startDate DESC");
        query.setParameter("emr",sd);
       
        return (List<TrainingProcess>)query.getResultList();
        
    }
    
     @Override
    public List<TrainingProcess> OrderByEndDateAsc() {
        Query query=em.createQuery ("SELECT tp FROM TrainingProcess tp WHERE tp.statusi='Active' ORDER BY tp.endDate");
       
        return (List<TrainingProcess>)query.getResultList();
       
    }
    
    @Override
    public List<TrainingProcess> OrderByEndDateDes() {
        Query query=em.createQuery ("SELECT tp FROM TrainingProcess tp WHERE tp.statusi='Active' ORDER BY tp.endDate DESC");
      
        return (List<TrainingProcess>)query.getResultList();
        
    }
    
        
    @Override
    public List<TrainingProcess> findByEndDateAsc(Date sd) {
         Query query=em.createQuery ("SELECT tp FROM TrainingProcess tp WHERE tp.statusi='Active' AND tp.endDate= :emr ORDER BY tp.endDate");
        query.setParameter("emr",sd);
        
        return (List<TrainingProcess>)query.getResultList();
        
    }
    
    @Override
    public List<TrainingProcess> findNumberForID(Date sd) {
         Query query=em.createQuery ("SELECT tp FROM TrainingProcess tp WHERE tp.endDate= :emr ");
        query.setParameter("emr",sd);
        return (List<TrainingProcess>)query.getResultList();
        
    }
    
    @Override
    public List<TrainingProcess> findByEndDateDes(Date sd) {
        Query query=em.createQuery ("SELECT tp FROM TrainingProcess tp WHERE tp.statusi='Active' AND tp.endDate= :emr ORDER BY tp.endDate DESC");
        query.setParameter("emr",sd);
       
        return (List<TrainingProcess>)query.getResultList();
        
    }
    
    @Override
    public List<TrainingProcess> findByYear(int i) {
        Query query=em.createQuery ("SELECT tp FROM TrainingProcess tp WHERE tp.statusi='Active' AND FUNC('YEAR',tp.endDate)= :emr  ORDER BY tp.endDate ");
        query.setParameter("emr",i);
        return (List<TrainingProcess>)query.getResultList();
        
    }
    
    @Override
    public List<TrainingProcess> findBy2013() {
        Query query=em.createQuery ("SELECT tp FROM TrainingProcess tp WHERE tp.statusi='Active' AND FUNC('YEAR',tp.endDate)=2013  ORDER BY tp.endDate ");
        //query.setParameter("emr",sd);
       
        return (List<TrainingProcess>)query.getResultList();
        
    }
    @Override
    public List<TrainingProcess> findBy2014() {
        Query query=em.createQuery ("SELECT tp FROM TrainingProcess tp WHERE tp.statusi='Active' AND FUNC('YEAR',tp.endDate)=2014  ORDER BY tp.endDate ");
        //query.setParameter("emr",sd);
       
        return (List<TrainingProcess>)query.getResultList();
        
    }
    @Override
    public List<TrainingProcess> findBy2015() {
        Query query=em.createQuery ("SELECT tp FROM TrainingProcess tp WHERE tp.statusi='Active' AND FUNC('YEAR',tp.endDate)=2015  ORDER BY tp.endDate ");
        //query.setParameter("emr",sd);
       
        return (List<TrainingProcess>)query.getResultList();
        
    }
    @Override
    public List<TrainingProcess> findBy2016() {
        Query query=em.createQuery ("SELECT tp FROM TrainingProcess tp WHERE tp.statusi='Active' AND FUNC('YEAR',tp.endDate)=2016 ORDER BY tp.endDate ");
        //query.setParameter("emr",sd);
       
        return (List<TrainingProcess>)query.getResultList();
        
    }
    
    @Override
    public List<TrainingProcess> OrderByTrainingIDAsc() {
        Query query=em.createQuery ("SELECT tp FROM TrainingProcess tp WHERE tp.statusi='Active' ORDER BY tp.tProcessID");
        return (List<TrainingProcess>)query.getResultList();
       
    }
    
    @Override
    public List<TrainingProcess> OrderByTrainingIDDes() {
        Query query=em.createQuery ("SELECT tp FROM TrainingProcess tp WHERE tp.statusi='Active' ORDER BY tp.tProcessID DESC");
        return (List<TrainingProcess>)query.getResultList();
        
    }
    
     @Override
    public List<TrainingProcess> findByTrainingIDAsc(String trainingID) {
         Query query=em.createQuery ("SELECT object(tp) FROM TrainingProcess tp WHERE tp.statusi='Active' AND tp.tProcessID LIKE :TrainingProcessID");
        query.setParameter("TrainingProcessID",trainingID+"%");
        return (List<TrainingProcess>)query.getResultList(); 
    }
    
     @Override
    public List<TrainingProcess> findByTrainingIDDes(String trainingID) {
         Query query=em.createQuery ("SELECT object(tp) FROM TrainingProcess tp WHERE tp.statusi='Active' AND tp.tProcessID LIKE :TrainingProcessID DESC");
        query.setParameter("TrainingProcessID",trainingID+"%");
        
        return (List<TrainingProcess>)query.getResultList();
        
    }
    
     @Override
    public List<TrainingProcess> OrderByCompanyAsc() {
        Query query=em.createQuery ("SELECT tp FROM TrainingProcess tp,CTPcombo ctp,Company c WHERE tp.statusi='Active' AND CTP.statusi='Active' AND c.statusi='Active' AND ctp.tProcessID.tProcessID= tp.tProcessID AND ctp.companyID.companyID=c.companyID ORDER BY ctp.companyID.name");
       
        return (List<TrainingProcess>)query.getResultList();
       
    }
    
    @Override
    public List<TrainingProcess> OrderByCompanyDes() {
        Query query=em.createQuery ("SELECT tp FROM TrainingProcess tp,CTPcombo ctp,Company c WHERE tp.statusi='Active' AND CTP.statusi='Active' AND c.statusi='Active' AND ctp.tProcessID.tProcessID= tp.tProcessID AND ctp.companyID.companyID=c.companyID ORDER BY ctp.companyID.name DESC");
      
        return (List<TrainingProcess>)query.getResultList();
        
    }
    
    @Override
    public List<TrainingProcess> findByCompanyAsc(String emriI) {
         Query query=em.createQuery ("SELECT object(tp) FROM TrainingProcess tp,CTPcombo ctp,Company c WHERE tp.statusi='Active' AND CTP.statusi='Active' AND c.statusi='Active' AND ctp.tProcessID.tProcessID= tp.tProcessID AND ctp.companyID.name=c.name AND ctp.companyID.name LIKE :emr ORDER BY ctp.companyID.name");
        query.setParameter("emr","%"+emriI+"%");
        return (List<TrainingProcess>)query.getResultList();
    }
    
     @Override
    public List<TrainingProcess> findByClient(Client clienti) {
        Query query=em.createQuery ("SELECT object(tp) FROM TrainingProcess tp WHERE tp.klientiID.clientID= :cli AND tp.statusi='Active' ");
        query.setParameter("cli",clienti.getClientID());
        return (List<TrainingProcess>)query.getResultList();
    }
    
   
    
     @Override
    public List<TrainingProcess> findByCompanyDes(String emriI) {
         Query query=em.createQuery ("SELECT object(tp) FROM TrainingProcess tp,CTPcombo ctp,Company c WHERE  tp.statusi='Active' AND CTP.statusi='Active' AND c.statusi='Active' AND ctp.tProcessID.tProcessID= tp.tProcessID AND ctp.companyID.name=c.name AND ctp.companyID.name LIKE :emr ORDER BY ctp.companyID.name DESC");
        query.setParameter("emr","%"+emriI+"%");
        return (List<TrainingProcess>)query.getResultList();
        
    }
 
     @Override
    public List<TrainingProcess> OrderByTitleAsc() {
        Query query=em.createQuery ("SELECT tp FROM TrainingProcess tp,TrainingProject tpro,Training t WHERE tp.statusi='Active' AND tpro.statusi='Active' AND t.statusi='Active' AND tp.trainingProjectID.tpId=tpro.tpId AND tpro.trainingID.titleOfTrainingAlbanian=t.titleOfTrainingAlbanian ORDER BY tpro.trainingID.titleOfTrainingAlbanian");
       
        return (List<TrainingProcess>)query.getResultList();
       
    }
    
    @Override
    public List<TrainingProcess> OrderByTitleDes() {
        Query query=em.createQuery ("SELECT tp FROM TrainingProcess tp,TrainingProject tpro,Training t WHERE tp.statusi='Active' AND tpro.statusi='Active' AND t.statusi='Active' AND tp.trainingProjectID.tpId=tpro.tpId AND tpro.trainingID.titleOfTrainingAlbanian=t.titleOfTrainingAlbanian ORDER BY tpro.trainingID.titleOfTrainingAlbanian DESC");
      
        return (List<TrainingProcess>)query.getResultList();
        
    }
    
     @Override
    public List<TrainingProcess> findByTitleAsc(String emriI) {
         Query query=em.createQuery ("SELECT object(tp) FROM TrainingProcess tp,TrainingProject tpro,Training t WHERE tp.statusi='Active' AND tpro.statusi='Active' AND t.statusi='Active' AND  tp.trainingProjectID.tpId=tpro.tpId AND tpro.trainingID.titleOfTrainingAlbanian=t.titleOfTrainingAlbanian AND tpro.trainingID.titleOfTrainingAlbanian LIKE :emr ORDER BY tpro.trainingID.titleOfTrainingAlbanian");
        query.setParameter("emr","%"+emriI+"%");
        return (List<TrainingProcess>)query.getResultList();
        
    }
   
    
     @Override
    public List<TrainingProcess> findByTitleDes(String emriI) {
         Query query=em.createQuery ("SELECT object(tp) FROM TrainingProcess tp,TrainingProject tpro,Training t WHERE tp.statusi='Active' AND tpro.statusi='Active' AND t.statusi='Active' AND  tp.trainingProjectID.tpId=tpro.tpId AND tpro.trainingID.titleOfTrainingAlbanian=t.titleOfTrainingAlbanian AND tpro.trainingID.titleOfTrainingAlbanian LIKE :emr ORDER BY tpro.trainingID.titleOfTrainingAlbanian DESC");
        query.setParameter("emr","%"+emriI+"%");
        return (List<TrainingProcess>)query.getResultList();
        
    }
    
    @Override
     public List<TrainingProcess> OrderByProjectAsc() {
        Query query=em.createQuery ("SELECT tp FROM TrainingProcess tp,TrainingProject tpro,Project p WHERE tp.statusi='Active' AND tpro.statusi='Active' AND p.statusi='Active' AND   tp.trainingProjectID.tpId=tpro.tpId AND tpro.projectID.emri=p.emri ORDER BY tpro.projectID.emri");
       
        return (List<TrainingProcess>)query.getResultList();
       
    }
    
    @Override
    public List<TrainingProcess> OrderByProjectDes() {
        Query query=em.createQuery ("SELECT tp FROM TrainingProcess tp,TrainingProject tpro,Project p WHERE tp.statusi='Active' AND tpro.statusi='Active' AND p.statusi='Active' AND  tp.trainingProjectID.tpId=tpro.tpId AND tpro.projectID.emri=p.emri ORDER BY tpro.projectID.emri DESC");
      
        return (List<TrainingProcess>)query.getResultList();
        
    }
    
     @Override
    public List<TrainingProcess> findByProjectAsc(String emriI) {
         Query query=em.createQuery ("SELECT object(tp) FROM TrainingProcess tp,TrainingProject tpro,Project p WHERE tp.statusi='Active' AND tpro.statusi='Active' AND p.statusi='Active' AND tp.trainingProjectID.tpId=tpro.tpId AND tpro.projectID.emri=p.emri AND tpro.projectID.emri LIKE :emr ORDER BY tpro.projectID.emri");
        query.setParameter("emr","%"+emriI+"%");
        return (List<TrainingProcess>)query.getResultList();
        
    }
   
    
     @Override
    public List<TrainingProcess> findByProjectDes(String emriI) {
         Query query=em.createQuery ("SELECT object(tp) FROM TrainingProcess tp,TrainingProject tpro,Project p WHERE tp.statusi='Active' AND tpro.statusi='Active' AND p.statusi='Active' AND tp.trainingProjectID.tpId=tpro.tpId AND tpro.projectID.emri=p.emri AND tpro.projectID.emri LIKE :emr ORDER BY tpro.projectID.emri DESC");
        query.setParameter("emr","%"+emriI+"%");
        return (List<TrainingProcess>)query.getResultList();
        
    }
    
    @Override
     public List<TrainingProcess> OrderByTopicsAsc() {
        Query query=em.createQuery ("SELECT tp FROM TrainingProcess tp,TopicsCombo tcom,TopicsCovered tc  WHERE tp.statusi='Active' AND tcom.statusi='Active' AND tc.statusi='Active' AND tcom.tProcessID.tProcessID=tp.tProcessID AND tcom.topicID.topicID=tc.topicID ");
       
        return (List<TrainingProcess>)query.getResultList();
       
    }
    
    @Override
    public List<TrainingProcess> OrderByTopicsDes() {
        Query query=em.createQuery ("SELECT tp FROM TrainingProcess tp,TopicsCombo tcom,TopicsCovered tc  WHERE tp.statusi='Active' AND tcom.statusi='Active' AND tc.statusi='Active' AND tcom.tProcessID.tProcessID=tp.tProcessID AND tcom.topicID.topicID=tc.topicID ");
      
        return (List<TrainingProcess>)query.getResultList();
        
    }
    
     @Override
    public List<TrainingProcess> findByTopicsAsc(String emriI) {
        Query query=em.createQuery ("SELECT tp FROM TrainingProcess tp,TopicsCombo tcom,TopicsCovered tc  WHERE tp.statusi='Active' AND tcom.statusi='Active' AND tc.statusi='Active' AND tcom.tProcessID.tProcessID=tp.tProcessID AND tcom.topicID.topicID=tc.topicID AND tc.topicCovered LIKE :emr ");
        query.setParameter("emr",emriI+"%");
        return (List<TrainingProcess>)query.getResultList();
    }
   
    
    @Override
    public List<TrainingProcess> findByTopicsDes(String emriI) {
         Query query=em.createQuery ("SELECT tp FROM TrainingProcess tp,TopicsCombo tcom,TopicsCovered tc  WHERE tp.statusi='Active' AND tcom.statusi='Active' AND tc.statusi='Active' AND tcom.tProcessID.tProcessID=tp.tProcessID AND tcom.topicID.topicID=tc.topicID AND tc.topicCovered LIKE :emr ");
        query.setParameter("emr","%"+emriI+"%");
        return (List<TrainingProcess>)query.getResultList();
    }
    
    @Override
    public TrainingProcess findByEverything(TrainingProcess tp){
        Query query=em.createQuery ("SELECT object(tp) FROM TrainingProcess tp WHERE tp.endDate= :endd AND tp.klientiID= :klio AND tp.place= :pla AND tp.startDate= :strd AND tp.trainersTeamID= :ttid AND tp.trainingProjectID= :traPro AND tp.statusi='Active'");
        query.setParameter("endd",tp.getEndDate());
        query.setParameter("klio",tp.getKlientiID());
        query.setParameter("pla",tp.getPlace());
        query.setParameter("strd",tp.getStartDate());
        query.setParameter("ttid",tp.getTrainersTeamID());
        query.setParameter("traPro",tp.getTrainingProjectID());
        
        return (TrainingProcess)query.getSingleResult();
    }
    
    @Override
    public ArrayList<AbstractReportEntity> gjeneroQuery(String GroupingVariable,ArrayList<String> kolonat,
                                                    int startMonth,int startYear,int endMonth,int endYear){
        ArrayList<AbstractReportEntity>lista=new ArrayList<AbstractReportEntity>();
        switch(GroupingVariable){
            case "Muajve":
                lista.addAll(gjeneroQueryMonth(kolonat,startMonth,startYear,endMonth,endYear));
                break;    
            default:
                break;
        }
        return lista;
    }
        /*Viteve, Vlerësimit të trajerëve, Trainerëve, Training Code, Place*/
    
    public ArrayList<ReportMonth> gjeneroQueryMonth(ArrayList<String> kolonat,
                                                    int startMonth,int startYear,int endMonth,int endYear){
        Iterator<String> iteratori= kolonat.iterator();
        StringBuilder sb= new StringBuilder();
        String s;
        while(iteratori.hasNext()){
            sb.append(iteratori.next());
            if(iteratori.hasNext())
                sb.append(",");
        }/*Duhet me shtu qet sb      SELECT * FROM Report_Month rm WHERE (CONVERT(DATE,CONCAT(CAST(rm.viti AS VARCHAR(4)),'-',CAST(rm.muaji AS VARCHAR(2)),'-',CAST('01' AS VARCHAR (2))))) BETWEEN (CONVERT(DATE,CONCAT(CAST('1995' AS VARCHAR(4)),'-',CAST('04' AS VARCHAR(2)),'-',CAST('15' AS VARCHAR (2))))) AND (CONVERT(DATE,CONCAT(CAST('2016' AS VARCHAR(4)),'-',CAST('09' AS VARCHAR(2)),'-',CAST('01' AS VARCHAR (2)))))*/
        Query query=em.createQuery("SELECT rm FROM ReportMonth rm WHERE FUNCTION('CONVERT',date,CONCAT(CAST(rm.viti AS VARCHAR (4)),'-',CAST(rm.muaji AS VARCHAR(2)),'-',CAST('01' AS VARCHAR (2)))) BETWEEN FUNCTION('CONVERT',date,CONCAT(CAST( :startY AS VARCHAR (4)),'-',CAST( :startM AS VARCHAR(2)),'-',CAST('01' AS VARCHAR (2)))) AND FUNCTION('CONVERT',date,CONCAT(CAST( :endY AS VARCHAR (4)),'-',CAST( :endM AS VARCHAR(2)),'-',CAST('01' AS VARCHAR (2))))"); 
        query.setParameter("startM",startMonth);
        query.setParameter("startY",startYear);
        query.setParameter("endM",endMonth);
        query.setParameter("endY",endYear);
        return (ArrayList<ReportMonth>)query.getResultList();                                   
    }

    @Override
    public List<TrainingProcess> findByTeam(Integer teamID) {
        Query query=em.createQuery("SELECT object(tp) FROM TrainingProcess tp WHERE tp.teamID.teamID= :tID AND tp.statusi='Active'"); 
        query.setParameter("tID",teamID);
        return (List<TrainingProcess>)query.getResultList();     
    }

    @Override
    public List<TrainingProcess> findAllActive() {
        return (List<TrainingProcess>)em.createNamedQuery("TrainingProcess.findAllActive").getResultList();
    }
    
    
    
    }
