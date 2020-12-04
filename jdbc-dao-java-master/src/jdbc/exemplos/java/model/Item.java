
package jdbc.exemplos.java.model;

/**
 *
 * @author AlbertoPinalli
 */
public class Item {
    
    private int id;
    private String named;
    private double price;

    public Item() {
    }

    public Item(int id, String nemed, double price) {
        this.id = id;
        this.named = nemed;
        this.price = price;
    }

    public Item(String named, double price) {
        this.named = named;
        this.price = price;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNemed() {
        return named;
    }

    public void setNemed(String nemed) {
        this.named = nemed;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", nemed=" + named + ", price=" + price + '}';
    }

    
}
