package kata.supermarket.discount.scheme;

import kata.supermarket.common.Configuration;
import kata.supermarket.data.model.item.Item;
import kata.supermarket.data.model.product.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class BuyTwoItemsForOnePoundScheme extends AbstractDiscountScheme {

    public BuyTwoItemsForOnePoundScheme() {
        super();
    }

    public boolean isApplicable(String name) {
        return Configuration.isApplicableForBuyTwoItemsForOnePoundScheme(name);
    }

    @Override
    public BigDecimal reducePriceBy(Product product, List<Item> items) {
        int totalNumberOfItems = items.size();
        int quotient = totalNumberOfItems / 2;
        // calculate price
        BigDecimal bd = product.getPrice().multiply(BigDecimal.valueOf(items.size()));
        bd = bd.subtract(BigDecimal.valueOf(quotient)).setScale(2, RoundingMode.HALF_UP);
        return bd;
    }
}
