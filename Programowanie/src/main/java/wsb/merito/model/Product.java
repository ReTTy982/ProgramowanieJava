package wsb.merito.model;

public class Product {

    private String name;
    private Float price;
    private Category category;
    private Integer stock;
    private Boolean isAvailable;


    public Product(String name, Float price, Category category, Integer stock) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.stock = stock;
        updateAvailable();
        validate();
    }

    private void updateAvailable(){
        this.isAvailable = stock != null && stock > 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
        updateAvailable();
    }

    public Boolean getAvailable() {
        return isAvailable;
    }


    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", stock=" + stock +
                ", isAvailable=" + isAvailable +
                '}';
    }

    public void validate() {
        if (name == null || name.isBlank()) throw new IllegalArgumentException();
        if (price == null || price < 0) throw new IllegalArgumentException();
        if (category == null) throw new IllegalArgumentException();
        if (stock == null || stock < 0) throw new IllegalArgumentException();
    }

}




/*prywatne metody do zdefiniowania:
zmiana dostępności w zależności od stock'a - jeśli 0 -> IsAvailable = False, jeśli większe od 0 -> IsAvailable=True
pobranie danych o kategorii ze słownika
modyfikacja stock'a
dodanie nowego produktu
usunięcie produktu

dependency: definicja słownika kategorii na poziomie bazy danych - pozyskanie kategorii*/
/*
    // other private methods //


    private int getProductionYear() {
        if (this.productionDate == null) {
            return 0;
        }
        return this.productionDate.getYear();
    }

    private String getColorName() {
        if (this.color == null) {
            return "unknown color";
        }
        return this.color.toString().toLowerCase();
    }

    // and public one //
    public String getCarSummary() {
        int productionYear = this.getProductionYear();
        String colorName = this.getColorName();

        return String.format("%d %s in %s", productionYear, this.model, colorName);
    }
}
*/