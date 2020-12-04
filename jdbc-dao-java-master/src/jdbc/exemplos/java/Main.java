package jdbc.exemplos.java;

import jdbc.exemplos.java.dao.CustomerDAO;

/**
 *
 * @author AlbertoPinalli
 */
public class Main {

    public static void main(String[] args) {

        CustomerDAO c = new CustomerDAO();
        String nome;
        
        System.out.println("Inform");
             
     ///   c.inserir("","","");
        c.listar();
    }
}
