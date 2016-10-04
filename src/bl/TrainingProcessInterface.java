
package bl;

import ejb.Client;
import ejb.TrainingProcess;
import ejb.view.AbstractReportEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public interface TrainingProcessInterface {
    TrainingProcess create (TrainingProcess tp)throws AppException;
    void edit (TrainingProcess tp)throws AppException;
    void remove (TrainingProcess tp);
    List<TrainingProcess> findAll();
    List<TrainingProcess> findAllActive();
    TrainingProcess findById (String TrainingProcessID);
    TrainingProcess findByIdActive(String TrainingProcessID);
    List<TrainingProcess> OrderByVendiAsc();
    List<TrainingProcess> OrderByVendiDes();
    List<TrainingProcess> findByVendiAsc(String emrII);
    List<TrainingProcess> findByVendiDes(String emrII);
    List<TrainingProcess> OrderByStartDateAsc();
     List<TrainingProcess> OrderByStartDateDes();
     List<TrainingProcess> findByStartDateAsc(Date sd);
     List<TrainingProcess> findByStartDateDes(Date sd);
    List<TrainingProcess> OrderByEndDateAsc();
     List<TrainingProcess> OrderByEndDateDes();
     List<TrainingProcess> findNumberForID(Date sd);
     List<TrainingProcess> findByEndDateAsc(Date sd);
     List<TrainingProcess> findByEndDateDes(Date sd);
     List<TrainingProcess> OrderByTrainingIDAsc();
     List<TrainingProcess> OrderByTrainingIDDes();
     List<TrainingProcess> findByTrainingIDAsc(String trainingID);
     List<TrainingProcess> findByTrainingIDDes(String trainingID);
     List<TrainingProcess> OrderByCompanyAsc();
     List<TrainingProcess> OrderByCompanyDes();
     List<TrainingProcess> findByCompanyAsc(String emri);
     List<TrainingProcess> findByClient(Client clienti);
     List<TrainingProcess> findByCompanyDes(String emri);
     List<TrainingProcess> OrderByTitleAsc();
     List<TrainingProcess> OrderByTitleDes();
     List<TrainingProcess> findByTitleAsc(String emriI);
     List<TrainingProcess> findByTitleDes(String emriI);
     List<TrainingProcess> OrderByProjectAsc();
     List<TrainingProcess> OrderByProjectDes();
     List<TrainingProcess> findByProjectAsc(String emriI);
     List<TrainingProcess> findByProjectDes(String emriI);
     List<TrainingProcess> OrderByTopicsAsc();
     List<TrainingProcess> OrderByTopicsDes();
     List<TrainingProcess> findByTopicsAsc(String emriI);
     List<TrainingProcess> findByTopicsDes(String emriI);
     TrainingProcess findByEverything(TrainingProcess tp);
     List<TrainingProcess> findBy2013();
     List<TrainingProcess> findBy2014();
     List<TrainingProcess> findBy2015();
     List<TrainingProcess> findBy2016();
     ArrayList<AbstractReportEntity> gjeneroQuery(String GroupingVariable,ArrayList<String> kolonat,
             int startMonth,int startYear,int endMonth,int endYear);
     List<TrainingProcess> findByTeam(Integer teamID);
     TrainingProcess checkIfExists(String TrainingProcessID)throws AppException ;
}
