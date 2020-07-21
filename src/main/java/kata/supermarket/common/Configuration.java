package kata.supermarket.common;

import java.util.Arrays;
import java.util.List;

/**
 * This class holds the configuration data. We can later move these details to some resource file
 * and access the file to get the applicable discount schema per product.
 *
 * This class currently lists names of all products eligible per discount scheme.
 */
public class Configuration {

    private static final List<String> BUY_ONE_GET_ONE_FREE_PROD_NAMES = Arrays.asList("Milk", "Digestive Biscuits");
    private static final List<String> BUY_ONE_KILO_FOR_HALF_PRICE_PROD_NAMES =
            Arrays.asList("Carrots");
    private static final List<String> BUY_THREE_IN_PRICE_OF_TWO_PROD_NAMES = Arrays.asList("Bread");
    private static final List<String> BUY_TWO_ITEMS_FOR_ONE_POUND_PROD_NAMES = Arrays.asList("French Beans");

    public static  boolean isApplicableForBuyOneGetOneFreeScheme(String name) {
        return BUY_ONE_GET_ONE_FREE_PROD_NAMES.contains(name);
    }

    public static  boolean isApplicableForBuyOneKiloForHalfPriceScheme(String name) {
        return BUY_ONE_KILO_FOR_HALF_PRICE_PROD_NAMES.contains(name);
    }

    public static  boolean isApplicableForBuyThreeInPriceOfTwoScheme(String name) {
        return BUY_THREE_IN_PRICE_OF_TWO_PROD_NAMES.contains(name);
    }

    public static  boolean isApplicableForBuyTwoItemsForOnePoundScheme(String name) {
        return BUY_TWO_ITEMS_FOR_ONE_POUND_PROD_NAMES.contains(name);
    }
}
