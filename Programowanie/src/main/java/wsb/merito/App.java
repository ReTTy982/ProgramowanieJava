package wsb.merito;


import wsb.merito.model.*;
import wsb.merito.repository.ProductRepositoryMock;
import wsb.merito.service.CartService;
import wsb.merito.service.CatalogService;
import wsb.merito.service.ProductService;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class App {

    public static void main(String[] args) {
        ProductRepositoryMock productRepositoryMock = new ProductRepositoryMock();

        CatalogService catalogService = CatalogService.getInstance(new Catalog());
        CartService cartService = CartService.getInstance(new Cart());
        ProductService productService = ProductService.getInstance();
        productRepositoryMock.findAll().forEach(catalogService::addProduct);
        Scanner scanner = new Scanner(System.in);
        boolean running = true;


        String menuMessage = """
                -------------------------
                1. Pokaż katalog
                2. Katalog - nazwy i ceny alfabetycznie
                3. Katalog - produkty danej kategorii
                4. Koszyk - zawartość
                5. Koszyk - Dodaj produkty
                6. Koszyk - Usuń produkty
                7. Zastosuj promocję
                8. Usuń wszystkie przedmioty z koszyka
                0. Wyjdź z programu
                -------------------------
                Wybierz opcję:
                """;
        while(running){
            System.out.println(menuMessage);
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    catalogService.getProductListWithIdAndPrice().forEach(System.out::println);
                    break;
                case "2":
                    catalogService.getAllProductSummariesSortedByName().forEach(System.out::println);
                    break;
                case "3":
                    System.out.println("Dostępne kategorie:");
                    catalogService.getCategoryListNumbered().forEach(System.out::println);
                    System.out.print("Wybierz numer kategorii: ");
                    int index = Integer.parseInt(scanner.nextLine());
                    List<String> result = catalogService.getAvailableProductsByCategoryIndex(index);
                    result.forEach(System.out::println);
                    break;
                case "4":
                    cartService.getProductSummaries().forEach(System.out::println);
                    float finalPrice = cartService.getFinalPrice();
                    System.out.println("\u001B[33mCena końcowa: " + String.format("%.2f zł", finalPrice) + "\u001B[0m");
                    break;
                case "5":
                    catalogService.getProductListWithIdAndPrice().forEach(System.out::println);
                    System.out.print("Wklej ID produktu do dodania: ");
                    String inputId = scanner.nextLine();
                    try {
                        UUID id = UUID.fromString(inputId);
                        Product p = catalogService.findProductById(id);
                        if (p == null) {
                            System.out.println("Nie znaleziono produktu.");
                        } else {
                            System.out.print("Ile sztuk chcesz dodać? ");
                            int qty = Integer.parseInt(scanner.nextLine());
                            cartService.addProduct(p, qty);
                            System.out.println("Dodano do koszyka.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Niepoprawny format ID.");
                        break;
                    }
                    break;
                case "6":
                    cartService.getProductSummariesWithId().forEach(System.out::println);
                    System.out.print("Wklej ID produktu do usunięcia: ");
                    String inputDelete = scanner.nextLine();

                    try {
                        UUID id = UUID.fromString(inputDelete);
                        Product p = catalogService.findProductById(id);
                        if (p == null) {
                            System.out.println("Nie znaleziono produktu.");
                        } else {
                            System.out.print("Ile sztuk chcesz usunąć? ");
                            int qty = Integer.parseInt(scanner.nextLine());
                            cartService.removeProduct(p, qty);
                            System.out.println("Usunięto z koszyka.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Niepoprawny format ID.");
                        break;
                    }
                    break;
                case "7": {
                    System.out.println("Dostępne promocje:");
                    cartService.getDiscountTypeListNumbered().forEach(System.out::println);
                    System.out.println("-1. Brak promocji");

                    System.out.print("Wybierz numer promocji: ");
                    try {
                        int discountIndes = Integer.parseInt(scanner.nextLine());
                        if (cartService.setDiscountTypeByIndex(discountIndes)) {
                            DiscountType active = cartService.getDiscountType();
                            System.out.println("Promocja ustawiona na: " + (active != null ? active : "brak"));
                        } else {
                            System.out.println("Niepoprawny numer promocji.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Błąd: podaj liczbę.");
                        break;
                    }
                    break;
                }
                case "8":
                    cartService.clearCart();
                    break;
                case "0":
                    running = false;
                    break;
                default: System.out.println("Nieznana opcja");
            }
        }
    }
}