package jdbc.exemplos.java;

import jdbc.exemplos.java.dao.CustomerDAO;

/**
 *
 * @author Rafael Vargas Mesquita
 */
public class MainDelete {
    public static void main(String[] args) {
        CustomerDAO clienteDAO = new  CustomerDAO();
        clienteDAO.remover(1);
    }
}
