package wsb.merito;


import wsb.merito.model.Cart;
import wsb.merito.model.Catalog;
import wsb.merito.model.Category;
import wsb.merito.repository.ProductRepositoryMock;
import wsb.merito.service.CartService;
import wsb.merito.service.CatalogService;
import wsb.merito.service.ProductService;

public class App {

    public static void main(String[] args) {
        ProductRepositoryMock productRepositoryMock = new ProductRepositoryMock();

        CatalogService catalogService = CatalogService.getInstance(new Catalog());
        CartService cartService = CartService.getInstance(new Cart());
        ProductService productService = ProductService.getInstance();
        productRepositoryMock.findAll().forEach(catalogService::addProduct);
        System.out.println(catalogService.getAllProductSummariesSortedByName());
        System.out.println(catalogService.getAvailableProductSummariesByCategorySortedByPrice(Category.ELECTRONICS));



    }
}