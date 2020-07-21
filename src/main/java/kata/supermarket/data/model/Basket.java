package kata.supermarket.data.model;

import kata.supermarket.data.model.item.Item;
import kata.supermarket.data.model.product.Product;
import kata.supermarket.data.model.product.ProductPricedPerKilo;
import kata.supermarket.data.model.product.ProductPricedPerUnit;
import kata.supermarket.discount.scheme.DiscountChecker;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket {

    Map<Product, List<Item>> unitItemsMap = new HashMap<Product, List<Item>>();
    Map<Product, List<Item>> kiloItemsMap = new HashMap<Product, List<Item>>();

    /**
     * Default Constructor.
     */
    public Basket() {
    }

    /**
     * This method adds a unit product to the map. If the map does not contain the unit product,
     * then a new entry is created with product as the key and an ItemByUnit instance is added to the value items list.
     * Else when an entry already exists, the value items list is amended to include the new ItemByUnit instance.
     *
     * @param product
     * @param quantity
     */
    public void addUnitProductItems(ProductPricedPerUnit product, int quantity) {
        if (unitItemsMap.containsKey(product)) {
            List<Item> items = unitItemsMap.get(product);
            addItemToList(product, quantity, items);
            unitItemsMap.replace(product, items);
        } else {
            List<Item> items = new ArrayList<>();
            addItemToList(product, quantity, items);
            unitItemsMap.put(product, items);
        }
    }

    /**
     *
     * @param product
     * @param quantity
     * @param items
     */
    private void addItemToList(ProductPricedPerUnit product, int quantity, List<Item> items) {
        for(int i=0; i<quantity; i++) {
            items.add(product.oneOf());
        }
    }

    /**
     * This method adds an weighed product to the map. If the map does not contain the weighed product,
     * then a new entry is created with product as the key and an ItemByWeight instance is added to the value items list.
     * Else when an entry already exists, the value items list is amended to include the new ItemByWeight instance.
     *
     * @param product
     * @param weight
     */
    public void addWeighedProductItem(ProductPricedPerKilo product, BigDecimal weight) {
        if (kiloItemsMap.containsKey(product)) {
            List<Item> items = kiloItemsMap.get(product);
            items.add(product.weighing(weight));
            kiloItemsMap.replace(product, items);
        } else {
            List<Item> items = new ArrayList<>();
            items.add(product.weighing(weight));
            kiloItemsMap.put(product, items);
        }
    }

    public Map<Product, List<Item>> getUnitItemsMap() {
        return unitItemsMap;
    }

    public Map<Product, List<Item>> getKiloItemsMap() {
        return kiloItemsMap;
    }

    final Basket getThisBasket() {
        return this;
    }

    /**
     * @return the total price of the items in the basket, post application of discounts.
     */
    public BigDecimal total() {
        return new Basket.TotalCalculator().calculateFinalPriceAfterDiscounts();
    }

    /**
     * Inner class to calculate the total price of all items in basket.
     */
    private class TotalCalculator {

        /**
         * Default Constructor.
         */
        TotalCalculator() {
        }

        /**
         * This method calculates the total price of all items in the basket, prior to application of discounts if any.
         * The calculation is done in two steps: first for all items priced by unit followed by items priced by weight.
         *
         * @return the total price of all items in the basket, before application of discounts.
         */
        private BigDecimal getTotalPriceBeforeDiscounts() {
            BigDecimal total = getTotalOfItemsInAMap(unitItemsMap);
            return total.add(getTotalOfItemsInAMap(kiloItemsMap));
        }

        /**
         * This method goes through the list of items (stores as values() in the map) and
         * calculates the total price.
         *
         * @param map
         * @return
         */
        private BigDecimal getTotalOfItemsInAMap(Map<Product, List<Item>> map) {
            BigDecimal total = BigDecimal.ZERO;
            total = total.setScale(2, RoundingMode.HALF_UP);
            for (List<Item> items : map.values()) {
                total = total.add(items.stream().map(Item::getPrice)
                        .reduce(BigDecimal::add)
                        .orElse(BigDecimal.ZERO)
                        .setScale(2, RoundingMode.HALF_UP));
            }
            return total;
        }

        /**
         * This method calculates the total price of all items in the basket, post application of discounts if any.
         *
         * @return the total price of all items in the basket, after application of discounts.
         */
        private BigDecimal calculateFinalPriceAfterDiscounts() {
            return getTotalPriceBeforeDiscounts().subtract(DiscountChecker.getInstance().applyDiscounts(getThisBasket()));
        }
    }
}
