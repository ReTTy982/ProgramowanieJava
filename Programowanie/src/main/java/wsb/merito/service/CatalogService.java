package wsb.merito.service;

import wsb.merito.model.Catalog;
import wsb.merito.model.Category;
import wsb.merito.model.Product;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CatalogService {
    private static CatalogService INSTANCE;
    private final Catalog catalog;

    private CatalogService(Catalog catalog) {
        this.catalog = catalog;
    }

    public static CatalogService getInstance(Catalog catalog) {
        if (INSTANCE == null) {
            INSTANCE = new CatalogService(catalog);
        }
        return INSTANCE;
    }

    public void addProduct(Product product) {
        catalog.addProduct(product);
    }

    public void removeProduct(Product product) {
        catalog.removeProduct(product);
    }

    public List<String> getAllProductSummariesSortedByName() {
        return catalog.getProducts().stream()
                .sorted(Comparator.comparing(Product::getName, String.CASE_INSENSITIVE_ORDER))
                .map(p -> p.getName() + " - " + p.getPrice() + " PLN")
                .collect(Collectors.toList());
    }

    private List<String> getAvailableProductSummariesByCategorySortedByPrice(Category category) {
        if (category == null) return List.of();

        return catalog.getProducts().stream()
                .filter(p -> p.getCategory() == category)
                .filter(Product::getAvailable)
                .sorted(Comparator.comparing(Product::getPrice))
                .map(p -> p.getName() + " - " + p.getPrice() + " PLN")
                .collect(Collectors.toList());
    }

    public List<String> getAvailableProductsByCategoryIndex(int index) {
        Category[] categories = Category.values();
        if (index < 0 || index >= categories.length) {
            return List.of("Niepoprawny numer kategorii.");
        }
        Category selected = categories[index];
        return getAvailableProductSummariesByCategorySortedByPrice(selected);
    }

    public List<String> getCategoryListNumbered() {
        Category[] categories = Category.values();
        List<String> list = new java.util.ArrayList<>();
        for (int i = 0; i < categories.length; i++) {
            list.add(i + ". " + categories[i].name());
        }
        return list;
    }

    public Product findProductById(UUID id) {
        return catalog.getProducts().stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

    public List<Product> getCatalog() {
        return List.copyOf(catalog.getProducts());
    }

    public List<String> getProductListWithIdAndPrice() {
        return catalog.getProducts().stream()
                .sorted(Comparator.comparing(Product::getName, String.CASE_INSENSITIVE_ORDER))
                .map(p -> p.getId() + " - " + p.getName() + " - " + String.format("%.2f zł", p.getPrice()))
                .collect(Collectors.toList());
    }
}
