MyStore Automated Tests - https://mystore-testlab.coderslab.pl/index.php?
Selenium WebDriver + Cucumber

   ** Test 1 (Zadanie1)**
    a. Register a new user manually
    b. Prepare a script which: 
      - logs in the user
      - clicks "Addressess" and "Create new address"
      - fills form "New address" with data from Gherkin array ((alias, address, city, zip/postal code, country, phone)
      - chcecks if data in added address is correct
      - removes new address by clicking "delete"
      - checks if new address was removed

**      Test 2 (Zadanie2)**
      a. Prepare a script which:
      - logs in the user from Test 1
      - selects Hummingbird Printed Sweater, size M, 5 pcs and adds to cart
      - goes to checkout
      - confirms shipping address
      - chooses "pick up in store" and "pay by check" options
      - clicks "order with an obligation to pay"
      - makes a screenshot with the order and order ammount
      - checks in order history if the order has status "Awaiting check payment" and the ammount the same as two steps back
