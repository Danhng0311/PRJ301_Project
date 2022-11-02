/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Anh
 */
public class ShoppingCart {

    private List<ItemAddToCart> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public ShoppingCart(List<ItemAddToCart> items) {
        this.items = items;
    }

    public List<ItemAddToCart> getItems() {
        return items;
    }

    public void setItems(List<ItemAddToCart> items) {
        this.items = items;
    }

    public ItemAddToCart getItemById(int Id) {
        for (ItemAddToCart item : items) {
            if (item.getProduct().getProductID() == Id) {
                return item;
            }

        }
        return null;
    }

    public int getQuantityById(int Id) {
        return getItemById(Id).getQuantity();
    }

    //add to cart 
    public void addItem(ItemAddToCart t) {
        //already have pro in cart
        if (getItemById(t.getProduct().getProductID()) != null) {
            ItemAddToCart i = getItemById(t.getProduct().getProductID());
            i.setQuantity(i.getQuantity() + t.getQuantity());
        } else {
            items.add(t);
        }
    }

    public void removeItem(int Id) {
        if (getItemById(Id) != null) {
            items.remove(getItemById(Id));
        }
    }

    public double getTotal() {
        double t = 0;
        for (ItemAddToCart item : items) {
            t += (item.getQuantity() * item.getProduct().getUnitPrice());
        }
        return t;
    }

    private Product getProductById(int id, List<Product> list) {
        for (Product p : list) {
            if (p.getProductID() == id) {
                return p;
            }
        }
        return null;
    }

//    public ShoppingCart(String txt, List<Product> list) {
//        items = new ArrayList<>();
//        try {
//            if (txt != null && txt.length() != 0) {
//                String[] s = txt.split(",");
//                for (String i : s) {
//                    String[] n = i.split(":");
//                    int id = Integer.parseInt(n[0]);
//                    int quantity = Integer.parseInt(n[1]);
//                    Product p = getProductById(id, list);
//                    ItemAddToCart t = new ItemAddToCart(p, quantity, p.getUnitPrice());
//                    addItem(t);
//                }
//            }
//        } catch (Exception e) {
//        }
//
//    }
}
