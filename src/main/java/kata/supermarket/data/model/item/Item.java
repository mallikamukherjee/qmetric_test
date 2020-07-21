package kata.supermarket.data.model.item;

import java.math.BigDecimal;

public interface Item {

    /**
     * @return the original price of an item. The price can correspond to one unit or to an unit of weight.
     */
    BigDecimal getPrice();
}
