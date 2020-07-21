package kata.supermarket.data.model.item;

import kata.supermarket.data.model.product.ProductPricedPerUnit;

import java.math.BigDecimal;

public class ItemByUnit implements Item {

    private final ProductPricedPerUnit product;

    public ItemByUnit(final ProductPricedPerUnit product) {
        this.product = product;
    }

    public BigDecimal getPrice() {
        return product.getPrice();
    }
}
