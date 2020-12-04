package jdbc.exemplos.java;

import jdbc.exemplos.java.dao.CustomerDAO;
import jdbc.exemplos.java.dao.ItemDAO;
import jdbc.exemplos.java.model.Customer;
import jdbc.exemplos.java.model.Item;

/**
 *
 * @author Rafael Vargas Mesquita
 */
public class MainInsert {
    public static void main(String[] args) {
        Customer customer = new Customer(005, "Bacana", "(666");
        Item i = new Item("Notebook Dell",2368.00);
        CustomerDAO clienteDAO = new CustomerDAO();
        ItemDAO itemDao = new ItemDAO();
        clienteDAO.inserir(customer);
        itemDao.inserir(i);
    }
}
