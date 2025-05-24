package project.msu.gr4f;

public class Product {

    // fields //
    public String name;
	public Float price;
	public String category;
    public Integer stock;
    public Boolean isAvailable;
    public String description;

    // constructors //
    public Product(String name, Float price, String category, Integer stock, Boolean isAvailable, String description) {
        this.name = name;
        this.price = price;
        this.category = category;
		this.stock = stock;
		this.isAvailable = isAvailable;
        this.description = description;
    }
	
    // setters //
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPrice(Float price) {
		this.price = price;
	}
		
    public void setCategory(String category) {
        this.category = category;
    }
	
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	
	public void setIsAvailable(Boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

    // getters //
	
	public String getName() {
		return name;
	}
    public Float getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getStock() {
        return stock;
    }
	
	public Boolean getIsAvaiable() {
		return isAvailable;
	}
	
	public String getDescription() {
		return description;
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