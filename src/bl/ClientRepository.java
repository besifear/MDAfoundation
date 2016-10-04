package bl;

import ejb.Client;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ClientRepository extends EntMngClass implements ClientInterface{
    
    public ClientRepository(EntityManager tempEm){
        super(tempEm);
    }
    
     @Override
    public Client create(Client c) throws AppException{
        try{
        em.getTransaction().begin();
        em.persist(c);
        em.flush();
        em.getTransaction().commit();
        return c;
        }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një klient me këto vlera.Secila klient duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje klient me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }
    }

    @Override
    public void edit(Client c) throws AppException{
        try{
        em.getTransaction().begin();
        em.merge(c);
        em.getTransaction().commit();
        }catch (Throwable thro){
        if (thro.getMessage().contains("2627")){
            if(thro.getMessage().toLowerCase().contains("unique"))
            throw new AppException("Ekziston një klient me këto vlera.Secila klient duhet të jetë unike.");
            else 
            throw new AppException("Ekziston nje klient me këtë çelës primarë.");
        }
        else{
            throw new AppException("Create : "+thro.getClass()+" - "+thro.getMessage());
    }
    }
    }

    @Override
    public void remove(Client c) {
        em.getTransaction().begin();
        em.remove(c);
        em.getTransaction().commit();
    }

    @Override
    public List<Client> findAll() {
        return em.createNamedQuery("Client.findAll").getResultList();
    }

    @Override
    public Client findById(String ClientId) {
        Query query=em.createQuery("SELECT object(c) FROM Client c WHERE c.clientID = :klienti ");
        query.setParameter("klienti", ClientId);
        return (Client)query.getSingleResult();
    }
    
    @Override
    public Client findByName(String ClientId) {
        Query query=em.createQuery("SELECT object(c) FROM Client c WHERE c.emri= :klienti ");
        query.setParameter("klienti", ClientId);
        return (Client)query.getSingleResult();
    }
    
}
