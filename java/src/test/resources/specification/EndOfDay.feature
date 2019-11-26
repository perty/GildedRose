Feature: At the end of each day our system lowers both values for every item

  Scenario: There is an item on the shelf

    Given an item "Barcacus" on the shelf with quality 10 and sell in 5 days
    When a day has passed
    Then the quality will be 9
    And sell in is 4 days