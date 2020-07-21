package kata.supermarket.discount.scheme;

import kata.supermarket.data.model.item.Item;
import kata.supermarket.data.model.product.Product;

import java.math.BigDecimal;
import java.util.List;

public abstract class AbstractDiscountScheme {

    /**
     * Default constructor.
     */
    public AbstractDiscountScheme() {
    }

    /**
     * Default implementation.
     * Needs to be overridden in implementing sub-classes.
     *
     * @param name - corresponds to name of the product(as per inventory catalogue)
     * @return <code>true</code> if the product is chosen for this discount scheme;
     * <code>false</code> otherwise.
     */
    public boolean isApplicable(String name) {
        return false;
    }

    /**
     * To be implemented by sub-classes.
     * This method has the logic to calculate the price that needs to be deducted
     * from the running total of the basket, on application of a discount scheme on the
     * items.
     *
     * @param product - product on which the discount has been applied.
     * @param items - items of the above product, the shopping basket has.
     * @return the price to be deducted from the running total.
     */
    public abstract BigDecimal reducePriceBy(Product product, List<Item> items);
}
