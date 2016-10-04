package bl;

import ejb.CTPcombo;
import ejb.Company;
import ejb.Participant;
import ejb.TrainingProcess;
import java.util.ArrayList;
import java.util.List;

public interface CTPcomboInterface {
    CTPcombo create(CTPcombo c)throws AppException;
    void edit(CTPcombo c)throws AppException;
    void remove(CTPcombo c);
    List<CTPcombo> findAll();
    CTPcombo findById(String CompanyID); 
    CTPcombo findByEverything(CTPcombo ctpc);
    CTPcombo findCTPcomboTpParticipant(TrainingProcess tptemp, Participant partemp);
    CTPcombo findCTPcomboTpParticipantForDelete(TrainingProcess tptemp, Participant partemp);
    ArrayList<CTPcombo> findByCompany(Company companyID);
    CTPcombo findByTPCompany(TrainingProcess tptemp, Company companyID);
    List<CTPcombo> findByCompanyActive(Company companyID);
    List<CTPcombo> findByTP(TrainingProcess tp);
}
