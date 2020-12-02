package src.facade;

import src.model.ConnectionStatus;
import src.model.Ecommerce;
import src.model.User;
import src.networking.Network;

import java.util.List;

public class NetworkFacade {

    public ConnectionStatus tryLogIn(User user) {
        return Network.tryLogIn(user);
    }

    public List<Ecommerce> fetchEcommerces() {
        return Network.fetchEcommerces();
    }

    public List<User> fetchUsers() {
        return Network.fetchUsers();
    }

}
