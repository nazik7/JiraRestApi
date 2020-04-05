Feature: Create a new board
  Scenario: Create a new board in Jira
    When user sends "POST" request to create a board:
      |name|Online Board|
      |type|scrum       |
      |filterId|10000   |

    Then verify status code is 201
    And user verifies the response scheme:
    |name|Online Board|
    |type|scrum       |

  Scenario: Delete all the created boards
    When user sends "DELETE" request to delete a board
    Then verify status code is 204
