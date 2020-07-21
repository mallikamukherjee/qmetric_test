package kata.supermarket.data.model.item;

import kata.supermarket.data.model.product.ProductPricedPerKilo;

import java.math.BigDecimal;

public class ItemByWeight implements Item {

    private final ProductPricedPerKilo product;
    private final BigDecimal weightInKilos;

    public ItemByWeight(final ProductPricedPerKilo product, final BigDecimal weightInKilos) {
        this.product = product;
        this.weightInKilos = weightInKilos;
    }

    public BigDecimal getPrice() {
        return product.getPrice().multiply(weightInKilos).setScale(2, BigDecimal.ROUND_HALF_UP);
    }
}
