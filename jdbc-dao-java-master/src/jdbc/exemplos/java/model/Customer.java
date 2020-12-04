package jdbc.exemplos.java.model;

/**
 *
 * @author Rafael Vargas Mesquita
 */
public class Customer {

    private Integer id;
    private String Username;
    private String password;
    private int ecommerceID;

    public Customer() {

    }

    public Customer(int ecommerceID, String Username, String password) {
        this.Username = Username;
        this.password = password;
        this.ecommerceID = ecommerceID;
    }

    public Customer(Integer id,int ecommerceID, String Username, String password ) {
        this.id = id;
        this.Username = Username;
        this.password = password;
        this.ecommerceID = ecommerceID;
    }

    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEcommerceID() {
        return ecommerceID;
    }

    public void setEcommerceID(int ecommerceID) {
        this.ecommerceID = ecommerceID;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", Username=" + Username + ", password=" + password + ", ecommerceID=" + ecommerceID + '}';
    }

}
