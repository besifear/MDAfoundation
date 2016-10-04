package bl;

import ejb.Client;
import java.util.List;

public interface ClientInterface {
    Client create(Client c)throws AppException;
    void edit(Client c)throws AppException;
    void remove(Client c);
    List<Client> findAll();
    Client findById(String ClientID); 
    public Client findByName(String ClientId);
}
