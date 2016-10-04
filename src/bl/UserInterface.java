
package bl;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import ejb.Users;
import java.util.List;

public interface UserInterface {
    Users create (Users t)throws AppException;
    void edit (Users t)throws AppException;
    void remove (Users t);
    List<Users> findAll();
    Users findByNameSurname(Users us);
    boolean findByUsername(String us);
    Users findByUsernamePassword(String us,byte[] pass);
    byte[] kripto(String pass);
    String findSalt(String username);
    Users findByUser(String u);
    void createSqlLogin(String username,String password);
    void createSqlLoginAdministrator(String username,String password);
    void createSqlLoginFirstAdminAdministrator(String username,String password);
    int CheckAdminExists();
    void changeLoginPassword(Users user,String password);
    int getNumOfLogins(Users user);
    void deleteUser(Users user);
}
