package kata.supermarket.data.model.product;

import kata.supermarket.data.model.item.Item;
import kata.supermarket.data.model.item.ItemByUnit;

import java.math.BigDecimal;

public class ProductPricedPerUnit extends Product {

    public ProductPricedPerUnit(String name, BigDecimal pricePerUnit) {
        super(name, pricePerUnit, PricedPer.Unit);
    }

    public Item oneOf() {
        return new ItemByUnit(this);
    }
}
