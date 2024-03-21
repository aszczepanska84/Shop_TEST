Feature: Zadanie1

  Scenario Outline: Dodanie nowego adresu uzytkownika

    Given uzytkownik jest zalogowany
    When uzytkownik dodaje adres do swojego konta <alias> <address> <city> <zipCode> <phone>
    Then adres zostaje dodany <alias> <address> <city> <zipCode> <phone>
    And adres zostaje usuniety <alias> <address> <city> <zipCode> <phone>
     #country nie da sie zmienic
    Examples:
    |alias         |address        |city    |zipCode|phone|
    |LoginDoZadania1|ul.Testerska1|Honolulu|063331 |987654321|
