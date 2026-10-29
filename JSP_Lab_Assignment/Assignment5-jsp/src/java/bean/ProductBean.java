package bean;

/**
 *
 * @author sudhangsu-shekhar-bairagi
 */
public class ProductBean {

    private final String id;
    private final String name;
    private final double price;
    private final int discountPercent;  

    public ProductBean(String id, String name, double price, int discountPercent) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discountPercent = discountPercent;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }
}
