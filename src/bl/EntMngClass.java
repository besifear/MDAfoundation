package bl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
 

public class EntMngClass {
    protected EntityManager em;
     
    public EntMngClass(EntityManager em){
         this.em=em;
    }
     
    public EntMngClass(String username,String password,String serverIp) throws AppException{
        if(username.trim().isEmpty())
            throw new AppException("Usernami mungon.");
        if(password.trim().isEmpty())
            throw new AppException("Passwordi mungon.");
        try{
        EntityManagerFactory emf = null;
        Map properties = new HashMap();
        properties.put("javax.persistence.jdbc.driver", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put("javax.persistence.jdbc.url", "jdbc:sqlserver://"+serverIp+":1433;databaseName=MDAfondation");
        properties.put("javax.persistence.jdbc.user", username);
        properties.put("javax.persistence.jdbc.password", password);
        emf = Persistence.createEntityManagerFactory("MDAfondationPU", properties);
        
        em= (EntityManager) emf.createEntityManager();
        }catch(Throwable thro){
            if(thro.getMessage().contains("18456"))
                throw new AppException("Keni gabuar Userin apo Passwordin.");
            else if
                 (thro.getMessage().contains("0"))
                throw new AppException("Nuk mund të krijohet lidhja me server.Kontrollo kabllat ose kontakto administratën");
            else
                throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
        }}
        
    
    public EntityManager getEntityManager(){
        return em;
    }
}