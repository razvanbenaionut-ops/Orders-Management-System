package businessLayer.validators;

import Model.Client;

public class ClientNameValidator implements Validator<Client> {
    public void validate(Client c) {
        if(c.getName().length()==0)
            throw new IllegalArgumentException("Client name cannot be empty");
    }
}
