/*
 * Name: Sudhangsu Shekhar Bairagi | Roll No: 13071025097 | Date: 28-10-2026
 * Assignment 5: ProductBean - one product with price and discount (Model).
 */
package bean;

public class ProductBean {
    private final String id;
    private final String name;
    private final double price;
    private final int discountPercent;   // 0 = no discount

    public ProductBean(String id, String name, double price, int discountPercent) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discountPercent = discountPercent;
    }

    public String getId()          { return id; }
    public String getName()        { return name; }
    public double getPrice()       { return price; }
    public int getDiscountPercent(){ return discountPercent; }
}
