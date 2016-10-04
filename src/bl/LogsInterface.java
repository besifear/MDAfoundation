package bl;

import ejb.Logs;
import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface LogsInterface {
    
    Logs create (Logs l)throws AppException;
    void edit (Logs l)throws AppException;
    void remove (Logs l);
    List<Logs> findAll();
    Date findDate();
    Time findTime();
    List<Logs> OrderByTypeAsc();
    List<Logs> OrderByTypeDes();
    List<Logs> findByTypeAsc(String s);
    List<Logs> findByTypeDes(String s);
    List<Logs> OrderByDateAsc();
    List<Logs> OrderByDateDes();
    List<Logs> findByDateAsc(Date sd);
    List<Logs> findByDateDes(Date sd);
    List<Logs> OrderByUserAsc();
    List<Logs> OrderByUserDes();
    List<Logs> findByUserAsc(String s);
    List<Logs> findByUserDes(String s);
    
}
