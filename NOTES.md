# Notes

Have added below some notes, assumptions and design decisions that I have taken. This will help the readers to understand the classes and the flow better.

### Classes

#### Package: kata.supermarket.data.model.item
- Interface 'Item' - has one method which returns the actual price of an item.

- Class 'ItemByUnit' - provides concrete implementation of the above interface. 
  This item class represents an item of a Product which is priced per unit.

- Class 'ItemByWeight' - provides concrete implementation of the above interface. 
  This item class represents an item of a Product which is priced per unit of weight eg. kilos.

#### Package: kata.supermarket.data.model.product
- Class 'Product' - this base class represents an inventory product. For now, have added three member variables:
    name - name of the product
    price, and
    priced per - Enum with values Unit and Kilo. If need be, more values can be added later.
  This class provides the accessor/getter methods for all three variables.
  This class can be amended later to include more fields to match an inventory product.
  
- Class 'ProductPricedPerUnit' - implementing sub-class of Product. This class represents an inventory product, which is priced by unit.
  An item of this class will always be of type 'ItemByUnit'.
  
- Class 'ProductPricedPerKilo' - implementing sub-class of Product. This class represents an inventory product, which is priced by weight in Kilos.
  An item of this class will always be of type 'ItemByWeight'.

#### Package: kata.supermarket.data.model
- Class 'Basket' - this is the main class which handles the price calculation of all items in the basket.
  It has two Maps - one stores the purchased products(priced per unit) alongwith the list of items bought of each.
  The second map stores the purchased products(priced per weight eg. kilos) alongwith the list of items bought of each.

  All items are added to the basket and then the total is calculated.
  This class has an inner class, which manages the total calculation. It first sums up the original price of all items and then subtracts the discounts to derive the final price. 

#### Package: kata.supermarket.common
- Class 'Configuration' - provides the product names applicable for each discount scheme.
  This class can be refactored to read the values from some resource config file.
  
#### Package: kata.supermarket.discount.scheme  
- Class 'AbstractDiscountScheme' - abstract base discount scheme class, which every concrete discount scheme class will extend.
  This class has two methods:
  - isApplicable(...) - this method checks if the scheme is applicable for the product
  - reducePriceBy(...) - this method has the logic to calculate the price to be deducted because of the discount
  
This package also has four dedicated classes, one per scheme mentioned in the task.
Strategy pattern has been used.

- Class 'DiscountChecker' - this is the main class which creates the list of discount schema applicable
  on products priced by unit as well as the ones priced by weight, and then applies the discount to get
  the price to be deducted from the total(before discount).    

If and when more schema need to be added, the following needs to be implemented:
 - applicable product names need to be configured for each new scheme,
 - a dedicated scheme class needs to be added (that must extend the abstract class),
 - the checker class has to add the new schema to the relevant list.  