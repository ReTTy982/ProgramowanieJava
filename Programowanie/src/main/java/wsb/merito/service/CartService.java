package wsb.merito.service;

import wsb.merito.model.Cart;
import wsb.merito.model.Product;
import wsb.merito.model.SpecialOffer;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CartService {
    private static CartService INSTANCE;
    private final Cart cart;
    private List<SpecialOffer> selectedOffers;

    private CartService(Cart cart) {
        this.cart = cart;
    }

    public static CartService getInstance(Cart cart) {
        if (INSTANCE == null) {
            INSTANCE = new CartService(cart);
        }
        return INSTANCE;
    }

    public void addProduct(Product product) {
        cart.addProduct(product);
    }

    public void removeProduct(Product product) {
        cart.removeProduct(product);
    }

    public void addSpecialOffer(SpecialOffer specialoffer) {
        if (specialoffer == null || !specialoffer.checkActive()) {
            throw new IllegalArgumentException("Invalid or inactive promotion.");
        }
        selectedOffers.add(specialoffer);
    }

    public void removeSpecialOffer(SpecialOffer specialoffer) {
        selectedOffers.remove(specialoffer);
    }

    public void removeOfferByName(String name) {
        selectedOffers.removeIf(offer -> offer.getName().equalsIgnoreCase(name));
    }

    public List<String> getProductSummaries() {
        Map<Product, Integer> items = cart.getItems();
        return items.entrySet().stream()
                .map(entry -> entry.getKey().getName() + ", " + entry.getValue() + " szt.")
                .collect(Collectors.toList());
    }

    public float getTotalPrice() {
        return (float) cart.getItems().entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public boolean isEmpty() {
        return cart.isEmpty();
    }

    public void clearCart() {
        cart.clear();
    }

}
