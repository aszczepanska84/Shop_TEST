Feature: Zadanie2

  Scenario: Pelen proces zakupowy

    Given uzytkownik jest juz zalogowany
    When dodaje do koszyka Hummingbird Printed Sweater, rozmiar M, 5 sztuk i przechodzi do kasy
    And potwierdza address, odbior PrestaShop in store, platnosc Pay by Check
    And klika: order with an obligation to pay
    Then screenshot z potwierdzeniem i kwota
    And zamowienie ma status awaiting check payment


