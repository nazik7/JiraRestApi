Feature: Creating a new Sprint

  Scenario Outline: Creating <Scenario>
    When user sends "POST" request to Jira Api with parameters "<name>","<startDate>","<endDate>","<originBoardId>","<goal>"
    Then status code is 201
    And user verifies response scheme:
      | name          | <name>          |
      | startDate     | <startDate>     |
      | endDate       | <endDate>       |
      | originBoardId | <originBoardId> |
      | goal          | <goal>          |
    Examples:
      | Scenario | name  | startDate                     | endDate                       | originBoardId | goal              |
      | Sprint1  | TEC-3 | 2020-04-11T15:22:00.000+10:00 | 2020-04-25T15:22:00.000+10:00 | 3             | Sprintgoal        |
      | Sprint2  | TEC-4 | 2020-04-26T15:22:00.000+10:00 | 2020-05-10T15:22:00.000+10:00 | 3             | learn Jira Api    |
      | Sprint3  | TEC-5 | 2020-05-11T15:22:00.000+10:00 | 2020-05-25T15:22:00.000+10:00 | 3             | Practice Jira Api |
