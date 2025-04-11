package org.example.grokking_simplicity;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;

public class Chapter4 {

    record Pair(String name, int price){}

    List<Pair> shopping_cart = new ArrayList<>();
    int shopping_cart_total = 0;

    void add_item_to_cart(String name, int price) {
        shopping_cart.add(new Pair(name, price));
        calc_cart_total();
    }

    void calc_cart_total() {
        shopping_cart_total = 0;
        for (Pair item : shopping_cart) {
            shopping_cart_total += item.price();
        }
        // обновить DOM
    }

}
