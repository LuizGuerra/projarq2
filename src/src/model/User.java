package src.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return ecommerce.equals(user.ecommerce) &&
                userName.equals(user.userName) &&
                password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ecommerce, userName, password);
    }
}
