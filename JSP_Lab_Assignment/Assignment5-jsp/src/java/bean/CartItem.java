/*
 * Name: Sudhangsu Shekhar Bairagi | Roll No: 13071025097 | Date: 28-10-2026
 * Assignment 5: CartItem - one line in the cart (product + quantity).
 */
package bean;

public class CartItem {
    private final ProductBean product;
    private final int quantity;

    public CartItem(ProductBean product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public ProductBean getProduct() { return product; }
    public int getQuantity()        { return quantity; }

    public double getSubtotal() { return product.getPrice() * quantity; }
    public double getDiscount() { return getSubtotal() * product.getDiscountPercent() / 100; }
    public double getTotal()    { return getSubtotal() - getDiscount(); }
}
