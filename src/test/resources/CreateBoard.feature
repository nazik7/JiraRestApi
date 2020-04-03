Feature: Create a new board
  Scenario: Create a new board in Jira
    When user sends "POST" request to create a board:
      |name|Online Board|
      |type|scrum       |
      |filterId|10000   |

    Then status code is 201
    And user verifies response scheme:
    |name|Online Board|
    |type|scrum       |


