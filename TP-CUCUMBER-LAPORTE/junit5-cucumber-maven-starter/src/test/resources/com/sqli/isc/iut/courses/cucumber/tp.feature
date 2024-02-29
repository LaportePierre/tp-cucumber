@dashboard
Feature: Go in a bar
  Test the behaviour of a bar and peoples consuming drinks

  @first-story
  Scenario: They get refused
    Given Mr Pignon and Mr Leblanc arrive to the bar named le Juste (A cocktail bar)
    And there is only 10 seats in the bar
    And there is only 9 peoples in the bar
    When they try to enter
    Then they get refused at the entrance

  @second-story
  Scenario: They get accepted, order drinks, and Mr LeBlanc pays
    Given Mr Pignon and Mr Leblanc arrive to the bar named le Juste (A cocktail bar)
    And there is only 10 seats in the bar
    And there are 8 people in the bar
    When they try to enter
    Then they get accepted
      And the guy behind them can't enter because the bar is full

    And they each order a cocktail of the month at 10 euros
    And Mr Leblanc pays for both drinks
    When they have ended their drink
    Then the bill is checked, and Mr Leblanc pays
      And Mr Pignon is happy because he only consumed one drink (he has liver problems)

  @third-story
  Scenario: They get accepted in the bar, order drinks, and pay
    Given Mr Pignon and Mr Leblanc arrive to the bar named le Juste (A cocktail bar)
    And there is only 10 seats in the bar
    And there are 3 people in the bar
    When they try to enter
    Then they get accepted

    And they each order a cocktail of the month at 10 euros
    When they have ended their drink
    Then the bill is checked, and they both paid

    And Mr Leblanc orders 2 other cocktail of the month for him and Mr Pignon at 10 euros
    When they have ended their drink
    Then the bill is checked, and Mr Leblanc pays for both drinks
      And Mr Pignon is not happy because he consumed more than one drink (he has liver problems)



