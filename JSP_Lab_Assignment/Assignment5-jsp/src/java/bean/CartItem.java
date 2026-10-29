
package bean;

/**
 *
 * @author sudhangsu-shekhar-bairagi
 */
public class CartItem {

    private final ProductBean product;
    private final int quantity;

    public CartItem(ProductBean product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public ProductBean getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSubtotal() {
        return product.getPrice() * quantity;
    }

    public double getDiscount() {
        return getSubtotal() * product.getDiscountPercent() / 100;
    }

    public double getTotal() {
        return getSubtotal() - getDiscount();
    }
}
