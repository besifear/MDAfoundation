package bl;

import ejb.Company;
import java.util.List;

public interface CompanyInterface {
    Company create(Company c)throws AppException;
    void edit(Company c)throws AppException;
    void remove(Company c);
    List<Company> findAll();
    Company findById(String CompanyID); 
    List<Company> findByTpId(String TrainingProcess);
    List<Company> OrderByCompanyAsc();
    List<Company> OrderByCompanyDes();
    List<Company> findByCompanyAsc(String emrII);
    List<Company> findByCompanyDes(String emrII);
    Company findByEverything(Company cc);
    List<Company> findByCompanyType(String emrII);
    List<Company> findByParticipant(int idNumber);
    Company findByCompanyName(Company comp);
}
