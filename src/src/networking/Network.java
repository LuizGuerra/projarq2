package src.networking;

import src.model.ConnectionStatus;
import src.model.Ecommerce;
import src.model.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Network {

    public static List<Ecommerce> fetchEcommerces() {
        return ecommercesFrom(readFile("C:\\Users\\Fuerras\\Desktop\\ProjArq\\Trab\\src\\src\\networking\\MockedEcommercesData.csv"));
    }

    public static List<User> fetchUsers() {
        return usersFrom(readFile("C:\\Users\\Fuerras\\Desktop\\ProjArq\\Trab\\src\\src\\networking\\MockedUsersData.csv"));
    }

    public static ConnectionStatus tryLogIn(User user) {
        List<User> usersListByEcommerce = fetchUsers().stream()
                .filter(a -> user.ecommerce.equals(a.ecommerce)).collect(Collectors.toList());
        if (usersListByEcommerce.isEmpty()) {
            return ConnectionStatus.badName;
        }

        List<User> usersListByUserName = usersListByEcommerce.stream()
                .filter( a -> user.userName.equals(a.userName)).collect(Collectors.toList());
        if (usersListByUserName.isEmpty()) {
            return ConnectionStatus.badLogin;
        }

        List<User> userListByPassword = usersListByUserName.stream()
                .filter( a -> user.password.equals(a.password)).collect(Collectors.toList());
        if (userListByPassword.isEmpty()) {
            return ConnectionStatus.badPassword;
        }

        return ConnectionStatus.successful;
    }

    private static List<Ecommerce> ecommercesFrom(Stream<String> list) {
        return list == null ? null : list.map(Ecommerce::transform).collect(Collectors.toList());
    }

    private static List<User> usersFrom(Stream<String> list) {
        return list == null ? null : list.map(User::transform).collect(Collectors.toList());
    }

    private static Stream<String> readFile(String path) {
        try {
            return (new BufferedReader(new FileReader(path)).lines());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
