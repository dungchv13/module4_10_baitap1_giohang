package entities;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "products")
public class Product implements Cloneable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String name;

    private String provider;

    @Min(0)
    private int quantity;

    private String img;

    private double price;

    public Product() {
    }

    public Product(String name, String provider, @Min(0) int quantity, String img, double price) {
        this.name = name;
        this.provider = provider;
        this.quantity = quantity;
        this.img = img;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public Product clone(){
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setProvider(provider);
        product.setQuantity(quantity);
        product.setImg(img);
        return product;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", provider='" + provider + '\'' +
                ", quantity=" + quantity +
                ", img='" + img + '\'' +
                '}';
    }
}
