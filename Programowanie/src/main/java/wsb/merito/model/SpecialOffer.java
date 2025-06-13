package wsb.merito.model;

public class SpecialOffer {

    private String name;
    private Float discount;
    private Category category;
    private Product product;
    private int quantity;
    private Boolean isActive;


    public SpecialOffer(String name, Float discount, Category category, Product product, int quantity, boolean active)
    {
        this.name = name;
        this.discount = discount;
        this.category = category;
        this.product = product;
        this.quantity = quantity;
        this.isActive = active;
        if (isActive) {
            validateActive();
        }
    }

    public boolean appliesTo(Product p)
    {
        if (!isActive) return false;
        if (product !=null)
        {
            return product.equals(p);
        }
        if (category!=null)
        {
            return category.equals(p.getCategory());
        }
        return false;
    }

    public void Activate() {
        validateActive();
    }
    public void Deactivate() {this.isActive = false;}

    public void validateActive(){
        try {
            validate();
            this.isActive = true;
        } catch (IllegalArgumentException e) {
            this.isActive = false;
            System.err.println("Validation failed for promotion '" + this.name + "': " + e.getMessage());
        }
    }

    public boolean checkActive(){ return isActive;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getDiscountRate() {
        return discount;
    }

    public void setDiscountRate(Float discount) {
        this.discount = discount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product getProduct() {return product;}

    public void setProduct(Product product) {this.product = product;}

    public Integer getQuantity() {return quantity;}

    public void setQuantity(int quantity) {
        this.quantity = quantity;}


    public void validate()
    {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Promotion name must not be empty.");
        if (discount == null || discount < 0 || discount > 1) throw new IllegalArgumentException("Discount must be between 0 and 1.");
        if (category == null && product == null) throw new IllegalArgumentException("Either a product or a category must be set.");
        if (quantity > product.getStock()) throw new IllegalArgumentException("Not enough stock available for product: " + product.getName());

    }



}