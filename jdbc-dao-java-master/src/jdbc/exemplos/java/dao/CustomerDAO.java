package jdbc.exemplos.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.exemplos.java.model.Customer;

public class CustomerDAO {

    private Connection connection;

    public CustomerDAO() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String DATABASE_URL = "jdbc:derby://localhost:1527/dbecommerce";
            String usuario = "root";
            String senha = "123";
            this.connection = DriverManager.getConnection(DATABASE_URL, usuario, senha);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Customer> listar() {
        String sql = "SELECT * FROM Customer";
        List<Customer> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Customer customer = new Customer();
                customer.setId(resultado.getInt("id"));
                customer.setEcommerceID(resultado.getInt("ecommerceID"));
                customer.setUsername(resultado.getString("userName"));
                retorno.add(customer);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public boolean inserir(Customer customer) {
        String sql = "INSERT INTO customer(ecommerceID, userName, password ) VALUES(?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, customer.getEcommerceID());
            stmt.setString(2, customer.getUsername());
            stmt.setString(3, customer.getPassword());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(Customer customer) {
        String sql = "UPDATE customer SET ecommerceID =?, userName=?,  password =?  WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, customer.getEcommerceID());
            stmt.setString(2, customer.getUsername());
            stmt.setString(3, customer.getPassword());
            stmt.setInt(4, customer.getId());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean remover(Integer id) {
        String sql = "DELETE FROM customer WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
