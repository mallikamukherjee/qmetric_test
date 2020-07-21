package kata.supermarket.discount.scheme;

import kata.supermarket.data.model.Basket;
import kata.supermarket.data.model.item.Item;
import kata.supermarket.data.model.product.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This checker class is designed to execute applicable discount schemes on all product items that are in the basket.
 * Post execution of all applicable discount schemes, the net price will be obtained which then needs to be deducted
 * from the running total to get the final price.
 *
 * This class uses Singleton pattern.
 */
public class DiscountChecker {

    private static DiscountChecker _instance = null;

    /**
     * Private Constructor.
     */
    private DiscountChecker() {
        //do nothing
    }

    /**
     *
     * @return singleton instance of DiscountChecker
     */
    public synchronized static DiscountChecker getInstance() {
        if (_instance == null) {
            _instance = new DiscountChecker();
        }
        return _instance;
    }

    /**
     * This method adds the individual scheme(applicable for unit products) to the list.
     * @return the schema list.
     */
    private List<AbstractDiscountScheme> getUnitProductDiscountSchemeList() {
        List<AbstractDiscountScheme> schema = new ArrayList<>();
        schema.add(new BuyOneGetOneFreeScheme());
        schema.add(new BuyTwoItemsForOnePoundScheme());
        schema.add(new BuyThreeInPriceOfTwoScheme());
        return schema;
    }

    /**
     * This method adds the individual scheme(applicable for weighed products) to the list.
     * @return the schema list.
     */
    private List<AbstractDiscountScheme> getWeighedProductDiscountSchemeList() {
        List<AbstractDiscountScheme> schema = new ArrayList<>();
        schema.add(new BuyOneKiloForHalfPriceScheme());
        return schema;
    }

    /**
     * This method applies the right discount schema on unit products, then on weighed products
     * and returns the price amount that needs to be deducted from the total(before discount).
     *
     * @param basket
     * @return the price amount that needs to be deducted from the total(before discount).
     */
    public BigDecimal applyDiscounts(Basket basket) {
        return applyDiscountsOnUnitProductItems(basket.getUnitItemsMap())
                .add(applyDiscountsOnWeighedProductItems(basket.getKiloItemsMap()))
                .setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * This method applies the right discount schema on unit products
     * and returns the price amount that needs to be deducted from the total(before discount).
     *
     * @param unitItems - items from unit products map
     * @return
     */
    private BigDecimal applyDiscountsOnUnitProductItems(Map<Product, List<Item>> unitItems) {
        return applyDiscountsOnSomeProductItems(unitItems, getUnitProductDiscountSchemeList());
    }

    /**
     * This method applies the right discount schema on weighed products
     * and returns the price amount that needs to be deducted from the total(before discount).
     *
     * @param kiloItems - items from kilo products map
     * @return
     */
    private BigDecimal applyDiscountsOnWeighedProductItems(Map<Product, List<Item>> kiloItems) {
        return applyDiscountsOnSomeProductItems(kiloItems, getWeighedProductDiscountSchemeList());
    }

    /**
     *
     * @param items
     * @param schema
     * @return
     */
    private BigDecimal applyDiscountsOnSomeProductItems(Map<Product, List<Item>> items,
                                                  List<AbstractDiscountScheme> schema) {
        BigDecimal bd = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        for(Product product: items.keySet()) {
            for (AbstractDiscountScheme scheme : schema) {
                if (scheme.isApplicable(product.getName())) {
                    bd = bd.add(scheme.reducePriceBy(product, items.get(product)));
                }
            }
        }
        return bd;
    }
}
