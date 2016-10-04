
package bl;

import ejb.Project;
import java.util.List;

public interface ProjectInterface {
    
    Project create (Project p)throws AppException;
    void edit (Project p)throws AppException;
    void remove (Project p);
    List<Project> findAll();
    Project findById (String ProjectID);
    Project findByName (String Name);
    List<Project> findByTpId(String trainingpId);
}
