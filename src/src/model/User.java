package src.model;

public class User {
    public final String ecommerce, userName, password;

    public User(String ecommerce, String userName, String password) {
        this.ecommerce = ecommerce;
        this.userName = userName;
        this.password = password;
    }

    public static User transform(String userString) {
        String [] str = userString.split(";");
        return (new User(str[0], str[1], str[2]));
    }

}
