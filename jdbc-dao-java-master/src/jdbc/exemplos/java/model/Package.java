
package jdbc.exemplos.java.model;

/**
 *
 * @author AlbertoPinalli
 */
public class Package {
    
    private int id;
    private int quantidade;

    public Package() {
    }

    public Package(int id, int quantidade) {
        this.id = id;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Package{" + "id=" + id + ", quantidade=" + quantidade + '}';
    }

}