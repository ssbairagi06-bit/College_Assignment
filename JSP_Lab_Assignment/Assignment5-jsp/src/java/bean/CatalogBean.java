/*
 * Name: Sudhangsu Shekhar Bairagi | Roll No: 13071025097 | Date: 28-10-2026
 * Assignment 5: CatalogBean - the list of products on sale (Model).
 */
package bean;

import java.util.*;

public class CatalogBean {
    private final List<ProductBean> products = new ArrayList<>();

    public CatalogBean() {
        products.add(new ProductBean("P1", "Java Programming Book", 450, 10));
        products.add(new ProductBean("P2", "Wireless Mouse", 700, 0));
        products.add(new ProductBean("P3", "Cotton T-Shirt", 400, 20));
        products.add(new ProductBean("P4", "Headphones", 1500, 15));
        products.add(new ProductBean("P5", "Notebook Pack", 250, 0));
    }

    public List<ProductBean> getProducts() { return products; }
}
