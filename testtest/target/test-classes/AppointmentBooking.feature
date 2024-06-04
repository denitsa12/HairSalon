Feature: The users should have the ability to book an appointment for a beauty procedure

  Background:
    Given A logged in user

  Scenario: Successful appointment booking
    When A user selects a beauty procedure
    And Enters valid details such as name, contact number, and preferred time slot
    Then The system should successfully book the appointment

  Scenario: Handle invalid user input
    Given A logged in user attempting to book an appointment
    When The user enters invalid details such as missing name or incorrect contact number format
    Then The system should display an error message indicating the required fields or format issues

  Scenario: Time slot availability check
    Given A logged in user trying to book an appointment
    When The selected time slot is already booked
    Then The system should inform the user that the chosen time slot is unavailable and offer alternative slots

  Scenario: Cancellation of appointment
    Given A logged in user with an existing appointment
    When The user decides to cancel the appointment
    Then The system should successfully cancel the appointment and notify both the user and the salon staff accordingly

  Scenario: Booking multiple procedures in a single appointment
    When The user selects both procedures during the booking process
    And Provides valid details such as name, contact number, and preferred time slot
    Then The system should allow the booking of multiple procedures within a single appointment