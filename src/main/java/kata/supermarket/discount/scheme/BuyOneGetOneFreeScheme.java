package kata.supermarket.discount.scheme;

import kata.supermarket.common.Configuration;
import kata.supermarket.data.model.item.Item;
import kata.supermarket.data.model.product.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class BuyOneGetOneFreeScheme extends AbstractDiscountScheme {

    public BuyOneGetOneFreeScheme() {
        super();
    }

    public boolean isApplicable(String name) {
        return Configuration.isApplicableForBuyOneGetOneFreeScheme(name);
    }

    @Override
    public BigDecimal reducePriceBy(Product product, List<Item> items) {
        int totalNumberOfItems = items.size();
        int quotient = totalNumberOfItems / 2;
        // calculate price
        BigDecimal bd = product.getPrice().multiply(BigDecimal.valueOf(quotient));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd;
    }
}
