

package bl;
import ejb.Users;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UserRepository extends EntMngClass implements UserInterface{

    public UserRepository(EntityManager tempEm){
        super(tempEm);
    }
    
    @Override
    public Users create(Users t)throws AppException{
        try{
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        return t;
        }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një User me këto vlera.Secila User duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje User me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }
   }

    @Override
    public void edit(Users t)throws AppException{
        try{
        em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();
        }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një User me këto vlera.Secila User duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje User me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }
    }

    @Override
    public void remove(Users t) {
         em.getTransaction().begin();
        em.remove(t);
        em.getTransaction().commit();
     }

    @Override
    public List<Users> findAll() {
       return em.createNamedQuery("Users.findAll").getResultList();
    }

    
    @Override
    public int CheckAdminExists() {
       Query query=em.createQuery("SELECT t.numriUserave FROM TestAdmin t ");
       
       return (int)query.getSingleResult();
    }
    
    @Override
    public Users findByNameSurname(Users t){
        Query query=em.createQuery ("SELECT object (us) FROM Users us WHERE us.name= :nam AND us.surname= :sur AND t.statusi='Active' ");
        query.setParameter ("nam",t.getName());
        query.setParameter ("sur",t.getSurname());
        return (Users)query.getSingleResult();
    }

    @Override
    public boolean findByUsername(String us) {
        Query query=em.createNativeQuery ("SELECT CASE WHEN EXISTS (SELECT * FROM Users u\n" +
                                          "WHERE u.Username = '"+us+"' AND u.statusi='Active') then 1 ELSE 0 END");
        if((int)query.getSingleResult()==0)
            return false;
        else                    
            return true;
    }
    
    @Override
    public Users findByUsernamePassword(String us,byte[] pass) {
        Query query=em.createQuery ("SELECT object (us) FROM Users us WHERE us.username = :usern AND  us.passwordi = :passw AND us.statusi='Active'");
        query.setParameter ("usern",us);
        query.setParameter ("passw",pass);
        return (Users)query.getSingleResult();
    }

    @Override
    public byte[] kripto(String pass) {
         Query query=em.createNativeQuery("SELECT HASHBYTES('SHA2_512','"+pass+"')");
         return (byte[])query.getSingleResult();
    }

    @Override
    public String findSalt(String username) {
        Query query=em.createQuery ("SELECT us.salt FROM Users us WHERE us.username = :usern AND us.statusi='Active'");
        query.setParameter ("usern",username);
        return (String)query.getSingleResult();
    }
    
    
    @Override
    public Users findByUser(String u){
        Query query=em.createQuery ("SELECT object (us) FROM Users us WHERE us.username= :user AND us.statusi='Active'");
        query.setParameter ("user",u);
        return (Users)query.getSingleResult();
    }
    
    
    @Override
    public void createSqlLoginFirstAdminAdministrator(String username,String password){
        
        em.createNativeQuery("USE MDAfondation;\n "+
                    "CREATE LOGIN "+username+" WITH password= '"+password+"',DEFAULT_DATABASE = MDAfondation,DEFAULT_LANGUAGE ="+ 
                    " english,CHECK_EXPIRATION = OFF,CHECK_POLICY=OFF;\n"+
                    "SELECT 1;\n").getSingleResult();
        em.createNativeQuery("USE MDAfondation;\n" +
                    "CREATE USER "+username+" FOR LOGIN "+username+";\n"+
                    "SELECT 1;\n").getSingleResult();
        em.createNativeQuery("USE MDAfondation;\n" +
                    "ALTER ROLE  Administrator\n"+
                    "ADD MEMBER "+username+";\n"+
                    "SELECT 1;\n").getSingleResult();
        
        /*
        em.createNativeQuery("USE MDAfondation;\n" +
                    "ALTER ROLE  db_accessadmin\n"+
                    "ADD MEMBER "+username+";\n"+
                    "SELECT 1;\n").getSingleResult();
        */
        em.createNativeQuery("USE MDAfondation;\n" +
                    "ALTER ROLE  db_securityadmin\n"+
                    "ADD MEMBER "+username+";\n"+
                    "SELECT 1;\n").getSingleResult();
        em.createNativeQuery("USE master;\n" +
                    "GRANT ALTER ANY LOGIN TO "+username+"; \n"+
                    "SELECT 1;\n").getSingleResult();
        em.createNativeQuery("USE MDAfondation;\n" +
                    "GRANT ALTER ANY USER TO "+username+"; \n"+
                    "SELECT 1;\n").getSingleResult();
        em.createNativeQuery("USE MDAfondation;\n" +
                    "ALTER AUTHORIZATION    \n" +
                    "ON ROLE :: Administrator\n" +
                    "TO Administrator;\n"+
                    "SELECT 1;\n").getResultList();
        }
    
    @Override
    public void createSqlLoginAdministrator(String username, String password){
    em.createNativeQuery("USE MDAfondation;\n "+
                    "CREATE LOGIN "+username+" WITH password= '"+password+"',DEFAULT_DATABASE = MDAfondation,DEFAULT_LANGUAGE ="+ 
                    " english,CHECK_EXPIRATION = OFF,CHECK_POLICY=OFF;\n"+
                    "SELECT 1;\n").getSingleResult();
        em.createNativeQuery("USE MDAfondation;\n" +
                    "CREATE USER "+username+" FOR LOGIN "+username+";\n"+
                    "SELECT 1;\n").getSingleResult();
        em.createNativeQuery("USE MDAfondation;\n" +
                    "ALTER ROLE  Administrator\n"+
                    "ADD MEMBER "+username+";\n"+
                    "SELECT 1;\n").getSingleResult();
        em.createNativeQuery("USE MDAfondation;\n" +
                    "ALTER ROLE  db_securityadmin\n"+
                    "ADD MEMBER "+username+";\n"+
                    "SELECT 1;\n").getSingleResult();
        em.createNativeQuery("USE master;\n" +
                    "GRANT ALTER ANY LOGIN TO "+username+"; \n"+
                    "SELECT 1;\n").getSingleResult();
        em.createNativeQuery("USE MDAfondation;\n" +
                    "GRANT ALTER ANY USER TO "+username+"; \n"+
                    "SELECT 1;\n").getSingleResult();
    }
    
    @Override
    public void createSqlLogin(String username,String password){
        
        em.createNativeQuery("USE MDAfondation;\n "+
                    "CREATE LOGIN "+username+" WITH password= '"+password+"',DEFAULT_DATABASE = MDAfondation,DEFAULT_LANGUAGE ="+ 
                    " english,CHECK_EXPIRATION = OFF,CHECK_POLICY=OFF;\n"+
                    "SELECT 1;\n").getSingleResult();
        em.createNativeQuery("USE MDAfondation;\n" +
                    "CREATE USER "+username+" FOR LOGIN "+username+";\n"+
                    "SELECT 1;\n").getSingleResult();
        em.createNativeQuery("USE MDAfondation;\n" +
                    "ALTER ROLE  Stafi\n"+
                    "ADD MEMBER "+username+";\n"+
                    "SELECT 1;\n").getSingleResult();
    }

    @Override
    public void changeLoginPassword(Users user,String password) {
        em.createNativeQuery("ALTER LOGIN ["+user.getUsername()+"] WITH DEFAULT_DATABASE=[MDAfondation];\n"
                           + "USE [MDAfondation];\n"
                           + "ALTER LOGIN ["+user.getUsername()+"] WITH PASSWORD='"+password+"';\n"
                           + "SELECT 1;\n").getSingleResult();
        
    }

    @Override
    public int getNumOfLogins(Users user) {
        Query query=em.createQuery ("SELECT us.numOfLogins FROM Users us WHERE us.username = :user AND us.statusi='Active'");
        query.setParameter ("user",user.getUsername());
        return Integer.parseInt(query.getSingleResult().toString());
    }

    @Override
    public void deleteUser(Users user){
        em.createNativeQuery("DECLARE @loginNameToDrop sysname\n" +
            "SET @loginNameToDrop = '"+user.getUsername()+"';\n" +
            "\n" +
            "DECLARE sessionsToKill CURSOR FAST_FORWARD FOR\n" +
            "SELECT session_id\n" +
            "FROM sys.dm_exec_sessions\n" +
            "WHERE login_name = @loginNameToDrop\n" +
            "OPEN sessionsToKill\n" +
            "\n" +
            "DECLARE @sessionId INT\n" +
            "DECLARE @statement NVARCHAR(200)\n" +
            "\n"+
            "FETCH NEXT FROM sessionsToKill INTO @sessionId\n" +
            "\n" +
            "WHILE @@FETCH_STATUS = 0\n" +
            "BEGIN\n" +
            "PRINT 'Killing session ' + CAST(@sessionId AS NVARCHAR(20)) + ' for login ' + @loginNameToDrop\n" +
            "\n" +
            "SET @statement = 'KILL ' + CAST(@sessionId AS NVARCHAR(20))\n" +
            "EXEC sp_executesql @statement\n" +
            "\n" +
            "FETCH NEXT FROM sessionsToKill INTO @sessionId\n" +
            "END\n" +
            "\n" +
            "CLOSE sessionsToKill\n" +
            "DEALLOCATE sessionsToKill\n" +
            "\n" +
            "PRINT 'Dropping login ' + @loginNameToDrop\n" +
            "SET @statement = 'DROP LOGIN [' + @loginNameToDrop + ']'\n" +
            "EXEC sp_executesql @statement\n"+
            "SELECT 1;\n").getSingleResult();
        
        em.createNativeQuery("USE MDAfondation;"
            + "DROP USER "+user.getUsername()+";\n"
            + "SELECT 1;\n").getSingleResult();
    }
}
