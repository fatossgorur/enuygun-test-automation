# enuygun-test-automation

This project contains automated test scenarios for the Flight Ticket product at www.enuygun.com.

## Prerequisite

- **Java 11 installed**
- **Maven installed**
- **IntelliJ IDEA installed**
- **Allure installed**

## Dependency List:

- **selenium-java**
- **testng**
- **webdrivermanager**
- **allure-testng**


## Running the tests

- Clone the project:

```sh
git clone https://github.com/fatossgorur/enuygun-test-automation.git
```
- To run all tests, use the command:
```$ mvn clean test``` 
- This will create the allure-results folder with all the test reports. These files will be used to generate the Allure Report.
- To create an Allure Report, use the command:
    ```$ allure serve```








