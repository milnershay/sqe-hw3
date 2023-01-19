Feature: A set of scenarios for testing the quiz module

  Scenario: 1 student answering quiz d when correct is a
    Given student is on homepage
    When student logs in and navigates to quiz
    And student enters quiz
    And student answers d
    And student finishes quiz
    Then grade should be 0

  Scenario: 2 teacher changes quiz answer from a to d
    Given teacher is on homepage
    When teacher logs in and goes to quiz
    And teacher edits questions
    And teacher changes answer from a to d
    Then correct answer should be d

  Scenario: 3 student answering quiz d when correct is d
    Given student is on homepage
    When student logs in and navigates to quiz
    And student enters quiz
    And student answers d
    And student finishes quiz
    Then grade should be 10

  Scenario: 4 teacher changes quiz answer from d to a
    Given teacher is on homepage
    When teacher logs in and goes to quiz
    And teacher edits questions
    And teacher changes answer from d to a
    Then correct answer should be a


  Scenario: 5 student answering quiz a when correct is a
    Given student is on homepage
    When student logs in and navigates to quiz
    And student enters quiz
    And student answers a
    And student finishes quiz
    Then grade should be 10


  Scenario: 6 student answering a after teacher changes to d
    Given student is on homepage
    And teacher is on homepage
    When student logs in and navigates to quiz
    And student enters quiz
    And student answers a
    And teacher logs in and goes to quiz
    And teacher edits questions
    And teacher changes answer from a to d
    And student finishes quiz
    # if student started quiz then correct answer is last correct answer
    Then grade should be 10
    And correct answer should be d

  Scenario: 7 student answering d before teacher changes to a
    Given student is on homepage
    And teacher is on homepage
    When student logs in and navigates to quiz
    And student enters quiz
    And student answers d
    And teacher logs in and goes to quiz
    And teacher edits questions
    And student finishes quiz
    And teacher changes answer from d to a
    Then grade should be 10
    And correct answer should be a