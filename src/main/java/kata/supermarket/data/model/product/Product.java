package kata.supermarket.data.model.product;

import java.math.BigDecimal;

public abstract class Product {

    private final String name;
    private final BigDecimal price;
    private final PricedPer type;

    public enum PricedPer {
         Unit, Kilo
    }

    /**
     * Constructor.
     *
     * @param name
     * @param price
     * @param type
     */
    public Product(final String name, final BigDecimal price, final PricedPer type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public PricedPer getPricePerType() {
        return type;
    }
}
