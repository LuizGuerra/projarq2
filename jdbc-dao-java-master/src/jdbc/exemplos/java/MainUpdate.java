package jdbc.exemplos.java;

import jdbc.exemplos.java.dao.CustomerDAO;
import jdbc.exemplos.java.model.Customer;


public class MainUpdate {
    public static void main(String[] args) {
        Customer customer = new Customer(1 ,003,"Cardoso","999");
        CustomerDAO clienteDAO = new  CustomerDAO();
        clienteDAO.alterar(customer);
    }
}
