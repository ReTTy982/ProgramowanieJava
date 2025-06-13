package wsb.merito.service;

import wsb.merito.model.Cart;
import wsb.merito.model.DiscountType;
import wsb.merito.model.Product;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CartService {
    private static CartService INSTANCE;
    private final Cart cart;

    private DiscountType discountType;

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

    public void addProduct(Product product, int quantity) {
        cart.addProduct(product, quantity);
    }

    public void removeProduct(Product product) {
        cart.removeProduct(product);
    }

    public void removeProduct(Product product, int quantity) {
        cart.removeProduct(product, quantity);
    }

    public List<String> getProductSummaries() {
        Map<Product, Integer> items = cart.getItems();
        return items.entrySet().stream()
                .map(entry -> entry.getKey().getName() + ", " + entry.getValue() + " szt." + " " + entry.getKey()
                        .getPrice() + "zł").collect(Collectors.toList());
    }

    public List<String> getProductSummariesWithId() {
        Map<Product, Integer> items = cart.getItems();
        return items.entrySet().stream().map(entry -> entry.getKey().getId() + "|" + entry.getKey()
                        .getName() + ", " + entry.getValue() + " szt." + " " + entry.getKey().getPrice() + "zł")
                .collect(Collectors.toList());
    }


    public float getFinalPrice() {
        float fullPrice = (float) cart.getItems().entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue()).sum();

        if (discountType == null) {
            return fullPrice;
        }

        return switch (discountType) {
            case DiscountType.PERCENTAGE_TOTAL -> applyPercentageDiscount(fullPrice);
            case DiscountType.CHEAPEST_FOR_PENNY -> applyCheapestForPennyDiscount();
            case DiscountType.TWO_FOR_ONE -> applyTwoForOneDiscount();
            default -> fullPrice;
        };
    }

    private float applyPercentageDiscount(float fullPrice) {
        return fullPrice * 0.9f;
    }

    private float applyCheapestForPennyDiscount() {
        List<Product> productsSorted = cart.getItems().entrySet().stream().flatMap(entry -> {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            return java.util.Collections.nCopies(quantity, product).stream();
        }).sorted(Comparator.comparing(Product::getPrice)).toList();

        int totalQuantity = cart.getItems().values().stream().mapToInt(Integer::intValue).sum();

        int pennyCount = totalQuantity / 3;
        float discountedPrice = 0f;

        for (int i = 0; i < productsSorted.size(); i++) {
            if (i < pennyCount) {
                discountedPrice += 1.0f;
            } else {
                discountedPrice += productsSorted.get(i).getPrice();
            }
        }

        return discountedPrice;
    }

    private float applyTwoForOneDiscount() {
        float discountedPrice = 0f;
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            float price = product.getPrice();
            int pairs = quantity / 2;
            int remaining = quantity % 2;

            discountedPrice += pairs * price * 1.5f;
            discountedPrice += remaining * price;
        }
        return discountedPrice;
    }

    public boolean isEmpty() {
        return cart.isEmpty();
    }

    public void clearCart() {
        cart.clear();
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    public DiscountType getDiscountType(){
        return this.discountType;
    }

    public List<String> getDiscountTypeListNumbered() {
        DiscountType[] types = DiscountType.values();
        List<String> result = new java.util.ArrayList<>();
        for (int i = 0; i < types.length; i++) {
            result.add(i + ". " + types[i].name());
        }
        return result;
    }

    public boolean setDiscountTypeByIndex(int index) {
        DiscountType[] types = DiscountType.values();
        if (index < 0 || index >= types.length) return false;
        this.discountType = types[index];
        return true;
    }
}
