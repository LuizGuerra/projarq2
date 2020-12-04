package jdbc.exemplos.java;

import java.util.List;
import jdbc.exemplos.java.dao.CustomerDAO;
import jdbc.exemplos.java.dao.ItemDAO;
import jdbc.exemplos.java.model.Customer;
import jdbc.exemplos.java.model.Item;


public class MainSelect {
    public static void main(String[] args) {
       
      CustomerDAO customerDAO = new CustomerDAO();
      ItemDAO item = new ItemDAO();
      
        List<Customer> lista = customerDAO.listar();
        for (Customer customer : lista)
             System.out.println(customer);
        
        
        List<Item> lista2 = item.listar();
        for (Item i : lista2)
             System.out.println(i);
    }
}
