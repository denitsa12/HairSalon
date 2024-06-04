Feature: Searching for different beauty procedures in a salon

  Scenario: Open the homepage with all the available beauty procedures
    Then Visualises a list with categories
    And Visualises a list with the 6 available procedures

  Scenario: Open the home page with beauty procedures and choose a category
    When The user chooses a category "Подстрижка"
    Then Visualises a list with the 3 available procedures

  Scenario Outline: Open the home page with beauty procedures and search by the name of a procedure
    When The user searches for a "<Процедура>" in the search box
    Then Visualises a list with the available procedure "<Процедура>" and <Цена>
    Examples:
      | Процедура     | Цена |
      | Кичури        | 15   |
      | Къса коса     | 39   |
      | Официален кок | 50   |

  Scenario: Open the home page with beauty procedures and search by the price of a procedure
    When The user enters a price of the procedure in the search box
    Then Visualises a list with the 3 available procedures