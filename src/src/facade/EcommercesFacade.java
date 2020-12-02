package src.facade;

import src.model.Ecommerce;
import src.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EcommercesFacade {
    private Set<Ecommerce> ecommerces;
    private Set<User> connections;

    public EcommercesFacade() {
        ecommerces = new HashSet<>();
        connections = new HashSet<>();
    }

    public boolean asAny(Ecommerce ecommerce) {
        return ecommerces.stream().anyMatch(e -> e.equals(ecommerce));
    }

    public boolean asAny(User user) {
        return connections.stream().anyMatch(u -> u.equals(user));
    }

    public boolean addConnection(User user) {
        if (user == null) { return false; }
        return connections.add(user);
    }

    public boolean addEcommerce(Ecommerce ecommerce) {
        if (ecommerce == null) { return false; }
        return ecommerces.add(ecommerce);
    }

    public Ecommerce removeEcommerce(String store) {
        for (Ecommerce e : ecommerces) {
            if (e.getName().equals(store)) {
                if (ecommerces.remove(e)) {
                    return e;
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    public User removeLogIn(String store) {
        for (User user : connections) {
            if (user.ecommerce.equals(store)) {
                if (connections.remove(user)) {
                    return user;
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    public void update(List<Ecommerce> list) {
        ecommerces.addAll(connectedStores(list));
    }

    public List<Ecommerce> connectedStores(List<Ecommerce> list) {
        return list.stream()
                .filter( ecommerce -> connections.stream()
                        .anyMatch( user -> ecommerce.getName().equals(user.ecommerce)))
                .collect(Collectors.toList());
    }

    public Ecommerce fromString(String named) {
        for (Ecommerce e : ecommerces) {
            if (e.getName().equals(named)) {
                return e;
            }
        }
        return null;
    }

    public Object[] ecommercesNamesArray() {
        return ecommerces.stream().map(Ecommerce::getName).toArray();
    }

    public List<String> ecommercesNamesList() {
        return ecommerces.stream().map(Ecommerce::getName).collect(Collectors.toList());
    }

    public Set<Ecommerce> getEcommerces() {
        return ecommerces;
    }

    public Set<User> getConnections() {
        return connections;
    }
}
