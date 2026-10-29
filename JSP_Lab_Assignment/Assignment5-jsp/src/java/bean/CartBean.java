package bean;

import java.util.*;

/**
 *
 * @author sudhangsu-shekhar-bairagi
 */
public class CartBean {

    private final Map<String, CartItem> items = new LinkedHashMap<>();

    public boolean updateItem(ProductBean product, int quantity) {
        if (quantity > 0) {
            items.put(product.getId(), new CartItem(product, quantity));
        } else {
            items.remove(product.getId());
        }
        return true;
    }

    public boolean clear() {
        items.clear();
        return true;
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items.values());
    }

    public double getSubtotal() {
        double sum = 0;
        for (CartItem i : items.values()) {
            sum += i.getSubtotal();
        }
        return sum;
    }

    public double getTotalDiscount() {
        double sum = 0;
        for (CartItem i : items.values()) {
            sum += i.getDiscount();
        }
        return sum;
    }

    public double getGrandTotal() {
        return getSubtotal() - getTotalDiscount();
    }
}
