package wsb.merito.service;

import wsb.merito.model.Catalog;
import wsb.merito.model.Category;
import wsb.merito.model.Product;

import java.util.Comparator;
import java.util.List;
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

    public List<String> getAvailableProductSummariesByCategorySortedByPrice(Category category) {
        if (category == null) return List.of();

        return catalog.getProducts().stream()
                .filter(p -> p.getCategory() == category)
                .filter(Product::getAvailable)
                .sorted(Comparator.comparing(Product::getPrice))
                .map(p -> p.getName() + " - " + p.getPrice() + " PLN")
                .collect(Collectors.toList());
    }
}
