Feature: Once the sell by date has passed, Quality degrades twice as fast.

  Scenario Outline: The quality of some fruit is falling twice as fast
    Given there is an item "<Item>" with quality "<Quality>" and due sell in "<Sell in>"
    And "<Days passed>" days has passed
    Then the quality will be "<New Quality>"

    Examples:
      | Item   | Quality | Sell in | Days passed | New Quality |
      | Apple  | 16      | 1       | 5           | 7           |
      | Banana | 15      | 10      | 5           | 10          |