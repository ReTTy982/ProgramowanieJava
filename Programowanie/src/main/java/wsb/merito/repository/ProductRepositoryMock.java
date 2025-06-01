package wsb.merito.repository;

import wsb.merito.model.Category;
import wsb.merito.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryMock {

    private final List<Product> products = new ArrayList<>();

    public ProductRepositoryMock() {
        loadInitialProducts();
    }

    private void loadInitialProducts() {
        products.add(new Product("Mleko", 4.50f, Category.GROCERY, 20));
        products.add(new Product("Chleb", 3.00f, Category.GROCERY, 0));
        products.add(new Product("Masło", 6.20f, Category.GROCERY, 15));
        products.add(new Product("Woda mineralna", 2.50f, Category.GROCERY, 40));
        products.add(new Product("Ser żółty", 8.90f, Category.GROCERY, 10));
        products.add(new Product("Telefon", 999.99f, Category.ELECTRONICS, 10));
        products.add(new Product("Laptop", 2499.00f, Category.ELECTRONICS, 5));
        products.add(new Product("Smartwatch", 599.99f, Category.ELECTRONICS, 12));
        products.add(new Product("Ładowarka", 49.99f, Category.ELECTRONICS, 0));
        products.add(new Product("Krem", 15.00f, Category.BEAUTY, 25));
        products.add(new Product("Szampon", 12.50f, Category.BEAUTY, 30));
        products.add(new Product("Perfumy", 120.00f, Category.BEAUTY, 4));
        products.add(new Product("Balsam", 18.00f, Category.BEAUTY, 0));
        products.add(new Product("Zabawka", 45.00f, Category.TOYS, 8));
        products.add(new Product("Puzzle 1000", 29.99f, Category.TOYS, 14));
        products.add(new Product("Klocki", 89.99f, Category.TOYS, 6));
        products.add(new Product("Koszulka", 39.99f, Category.CLOTHING, 20));
        products.add(new Product("Spodnie jeansowe", 129.90f, Category.CLOTHING, 7));
        products.add(new Product("Kurtka zimowa", 249.99f, Category.CLOTHING, 0));
        products.add(new Product("Książka: Java dla początkujących", 59.00f, Category.BOOKS, 16));
        products.add(new Product("Poradnik ogrodniczy", 35.00f, Category.BOOKS, 11));
        products.add(new Product("Kalendarz 2025", 19.99f, Category.BOOKS, 0));
    }

    public List<Product> findAll() {
        return new ArrayList<>(products);
    }
}
