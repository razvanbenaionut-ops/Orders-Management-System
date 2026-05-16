package businessLayer;

import Model.Client;
import businessLayer.validators.ClientNameValidator;
import businessLayer.validators.Validator;
import dataAccessLayer.ClientDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ClientBLL {

    private List<Validator<Client>> clients;
    private ClientDAO clientDAO;
    public ClientBLL(){
        clientDAO=new ClientDAO();
        clients=new ArrayList<Validator<Client>>();
        clients.add(new ClientNameValidator());
    }
    public List<Client> getClients(){
        return clientDAO.findAll();
    }

    public Client findClient(int id)
    {
        Client c=clientDAO.findById(id);
        if(c==null)
        {
            throw new NoSuchElementException("Client with id "+id+" doesnt exist");
        }
        return c;
    }

    public Client addClient(Client client)
    {
        for(Validator<Client> c :clients)
        {
            c.validate(client);
        }
        return clientDAO.insert(client);
    }

    public void editClient(Client client)
    {
        for(Validator<Client> c :clients)
        {
            c.validate(client);
        }
        clientDAO.update(client);
    }

    public void deleteClient(Client client)
    {
        clientDAO.delete(client);
    }



}
