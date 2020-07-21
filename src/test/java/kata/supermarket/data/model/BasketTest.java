package kata.supermarket.data.model;

import kata.supermarket.data.model.Basket;
import kata.supermarket.data.model.item.Item;
import kata.supermarket.data.model.product.ProductPricedPerKilo;
import kata.supermarket.data.model.product.ProductPricedPerUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasketTest {

    @DisplayName("Basket provides its total value when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    void BasketProvidesTotalValue(String description, String expectedTotal, Basket basket) {
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    static Stream<Arguments> BasketProvidesTotalValue() {
        return Stream.of(
                noItems(),
                aSingleItemPricedPerUnit(),
                multipleItemsPricedPerUnit(),
                aSingleItemPricedByWeight(),
                multipleItemsPricedByWeight(),
                aSingleItemPricedByWeightAndWithDiscount(), //test one kilo with 1/2 price scheme
                twoSingleItemsPricedPerUnitAndWithDiscount(), //test buy one get one free scheme
                threeSingleItemsPricedPerUnitAndWithDiscount() //test buy one get one free scheme
        );
    }

    private static Arguments aSingleItemPricedByWeight() {
        Basket basket = new Basket();
        basket.addWeighedProductItem(aKiloOfAmericanSweets(), new BigDecimal(.25));
        return Arguments.of("a single weighed item", "1.25", basket);
    }

    private static Arguments aSingleItemPricedByWeightAndWithDiscount() {
        Basket basket = new Basket();
        basket.addWeighedProductItem(aKiloOfCarrots(), new BigDecimal(.5));
        return Arguments.of("a single weighed item with discount", "1.50", basket);
    }

    private static Arguments multipleItemsPricedByWeight() {
        Basket basket = new Basket();
        basket.addWeighedProductItem(aKiloOfAmericanSweets(), new BigDecimal(.25));
        basket.addWeighedProductItem(aKiloOfPickAndMix(), new BigDecimal(.2));
        return Arguments.of("multiple weighed items", "1.85", basket);
    }

    private static Arguments multipleItemsPricedPerUnit() {
        Basket basket = new Basket();
        basket.addUnitProductItems(createAPackOfDigestives(), 1);
        basket.addUnitProductItems(createAPintOfMilkProduct(), 1);
        return Arguments.of("multiple items priced per unit", "2.04", basket);
    }

    private static Arguments aSingleItemPricedPerUnit() {
        Basket basket = new Basket();
        basket.addUnitProductItems(createAPintOfMilkProduct(), 1);
        return Arguments.of("a single item priced per unit", "0.49", basket);
    }

    private static Arguments twoSingleItemsPricedPerUnitAndWithDiscount() {
        Basket basket = new Basket();
        basket.addUnitProductItems(createAPintOfMilkProduct(), 2);
        return Arguments.of("a single item priced per unit", "0.49", basket);
    }

    private static Arguments threeSingleItemsPricedPerUnitAndWithDiscount() {
        Basket basket = new Basket();
        basket.addUnitProductItems(createAPintOfMilkProduct(), 3);
        return Arguments.of("a single item priced per unit", "0.98", basket);
    }

    private static Arguments noItems() {
        Basket basket = new Basket();
        return Arguments.of("no items", "0.00", basket);
    }

    private static ProductPricedPerUnit createAPintOfMilkProduct() {
        return new ProductPricedPerUnit("Milk", new BigDecimal("0.49"));
    }

    private static Item aPintOfMilk() {
        return createAPintOfMilkProduct().oneOf();
    }

    private static ProductPricedPerUnit createAPackOfDigestives() {
        return new ProductPricedPerUnit("Digestives", new BigDecimal("1.55"));
    }

    private static Item aPackOfDigestives() {
        return createAPackOfDigestives().oneOf();
    }

    private static ProductPricedPerKilo aKiloOfAmericanSweets() {
        return new ProductPricedPerKilo("American Sweets", new BigDecimal("4.99"));
    }

    private static Item twoFiftyGramsOfAmericanSweets() {
        return aKiloOfAmericanSweets().weighing(new BigDecimal(".25"));
    }

    private static ProductPricedPerKilo aKiloOfPickAndMix() {
        return new ProductPricedPerKilo("Pick and Mix", new BigDecimal("2.99"));
    }

    private static Item twoHundredGramsOfPickAndMix() {
        return aKiloOfPickAndMix().weighing(new BigDecimal(".2"));
    }

    private static ProductPricedPerKilo aKiloOfCarrots() {
        return new ProductPricedPerKilo("Carrots", new BigDecimal("6.00"));
    }

    private static Item fiveHundredGramsOfCarrots() {
        return aKiloOfCarrots().weighing(new BigDecimal(".5"));
    }
}
