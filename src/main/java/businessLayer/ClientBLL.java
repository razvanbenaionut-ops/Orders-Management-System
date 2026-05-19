package businessLayer;

import Model.Client;
import businessLayer.validators.ClientNameValidator;
import businessLayer.validators.Validator;
import dataAccessLayer.ClientDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ClientBLL {

    private List<Validator<Client>> validators;
    private ClientDAO clientDAO;
    public ClientBLL(){
        clientDAO=new ClientDAO();
        validators=new ArrayList<Validator<Client>>();
        validators.add(new ClientNameValidator());
    }
    public List<Client> getClients(){
        return clientDAO.findAll();
    }

    public Client findClient(int id)
    {
        //Client c=clientDAO.findById(id);
        //if(c==null)
        //{
          //  throw new NoSuchElementException("Client with id "+id+" doesnt exist");
        //}
        //return c;
        List<Client> clients=clientDAO.findAll();
        return clients.stream().
                       filter(c->c.getId()==id).
                       findFirst().
                       orElseThrow(()->new NoSuchElementException("Client with id "+id+" doesnt exist"));
    }

    public Client addClient(Client client)
    {
        //for(Validator<Client> c:validators)
        //{
          //  c.validate(client);
        //}
        //return clientDAO.insert(client);
        validators.stream().
                  forEach(v->v.validate(client));
        return clientDAO.insert(client);
    }

    public void editClient(Client client)
    {
       // for(Validator<Client> c:validators)
        //{
          //  c.validate(client);
        //}
        //clientDAO.update(client);
        validators.stream().
                forEach(v->v.validate(client));
        clientDAO.update(client);
    }

    public void deleteClient(Client client)
    {
        clientDAO.delete(client);
    }



}
