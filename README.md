# Gmail Automation Tests with Cucumber
1. **Use of "Scenario Outline" keyword and "Examples:" section**:
   - **Condition Met**: This project uses the `Scenario Outline` keyword along with the `Examples:` section, allowing the same scenario to be executed with different sets of data. 

2. **Applying the "Background" keyword**:
   - **Condition Met**: The `Background` keyword is applied for setting common preconditions shared across scenarios, such as opening the Gmail login page.

3. **Use of filtering/regular expressions in Gherkin step definitions**:
   - **Condition Met**: Filtering and regular expressions are used in Gherkin step definitions to handle dynamic input, such as email.

## Tools Used

- **Cucumber**: A tool for running automated tests written in Gherkin syntax.
- **Gherkin**: A language used to write test scenarios in a human-readable format.
- **Selenium WebDriver**: A browser automation tool used to simulate user interactions.
- **TestNG**: A testing framework used for running and reporting tests.
- **Maven**: A build automation tool used to manage dependencies and build the project.

## Configuration File Template for Gmail Automation Testing
```properties
email=
password=
recipient=
subject=
body=
```

## Example Feature File

```gherkin
Feature: Gmail functionality

  Background:
    Given User opens Gmail login page

  @qa1
  Scenario Outline: Login, create a draft, send email, and logout for QA1
    Given the user has the following credentials:
      | email             | password   |
      | <email>           | <password> |
    And the email message details are:
      | recipient         | subject               | body                         |
      | <recipient>       | <subject>             | <body>                       |
    When User logs in
    Then User should see account email "<email>"
    When User creates draft email
    Then Draft email should be present
    And Draft email content should match
    When User sends the draft
    Then Draft email should not be present
    And Sent email should be present
    Then User logs out

    Examples:
      | email              | password | recipient          | subject                | body                        |
      | test1@gmail.com    | smth     | rec1@gmail.com     | Second Test Draft Mail | Second Test Draft Mail Body |

  @qa2
  Scenario Outline: Login, create a draft, send email, and logout for QA2
    Given the user has the following credentials:
      | email             | password   |
      | <email>           | <password> |
    And the email message details are:
      | recipient         | subject               | body                         |
      | <recipient>       | <subject>             | <body>                       |
    When User logs in
    Then User should see account email "<email>"
    When User creates draft email
    Then Draft email should be present
    And Draft email content should match
    When User sends the draft
    Then Draft email should not be present
    And Sent email should be present
    Then User logs out

    Examples:
      | email             | password | recipient         | subject               | body                        |
      | test2@gmail.com   | smth     | rec2@gmail.com    | Test Draft Mail       | Test Draft Mail Body        |
```

## Run Tests
### 1. Running Specific Test Class
To run a specific test class (such as `CucumberTestRunner`), you can use the following Maven command:
```bash
mvn clean -Dtest=CucumberTestRunner test
```

### 2. Running Tests with a Specific Browser 
If you want to run the tests in a specific browser (such as Firefox), you can pass the browser as a system property like this:
```bash
mvn clean -Dbrowser=firefox -Dtest=CucumberTestRunner test
```
