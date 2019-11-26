Feature: "Aged Brie" actually increases in Quality the older it gets

  Scenario Outline: Aged Brie waiting on the shelf

    Given an Aged Brie on the shelf with <Quality> and sell in 1 day
    When <Days passed> days has passed
    Then the quality will be <New Quality>

    Examples:
      | Quality | Days passed | New Quality |
      | 10      | 5           | 19          |
