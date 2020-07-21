package kata.supermarket.data.model.product;

import kata.supermarket.data.model.item.Item;
import kata.supermarket.data.model.item.ItemByWeight;

import java.math.BigDecimal;

public class ProductPricedPerKilo extends Product {

    private ItemByWeight weightItem;

    public ProductPricedPerKilo(final String name, final BigDecimal pricePerKilo) {
        super(name, pricePerKilo, PricedPer.Kilo);
    }

    public ItemByWeight getWeightItem() {
        return weightItem;
    }

    public Item weighing(final BigDecimal kilos) {
        weightItem = new ItemByWeight(this, kilos);
        return weightItem;
    }
}
