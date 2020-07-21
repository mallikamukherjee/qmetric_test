package kata.supermarket.discount.scheme;

import kata.supermarket.common.Configuration;
import kata.supermarket.data.model.item.Item;
import kata.supermarket.data.model.product.Product;
import kata.supermarket.data.model.product.ProductPricedPerKilo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class BuyOneKiloForHalfPriceScheme extends AbstractDiscountScheme {

    public BuyOneKiloForHalfPriceScheme() {
        super();
    }

    public boolean isApplicable(String name) {
        return Configuration.isApplicableForBuyOneKiloForHalfPriceScheme(name);
    }

    @Override
    public BigDecimal reducePriceBy(Product product, List<Item> items) {
        BigDecimal bd = ((ProductPricedPerKilo) product).getWeightItem().getPrice()
                .divide(BigDecimal.valueOf(2));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd;
    }
}
