# qa-api-testing
This repository contains automated API tests for Pokemon API using Java with Rest Assured, Cucumber, and Allure for reporting.

# Installation Guide for QA API Testing

This guide will help you set up the QA API Testing project on your local machine.

---

## **1. Prerequisites**
Before you start, make sure you have the following installed:

- **Java 11 or later** ([Download Java](https://adoptopenjdk.net/))
- **Maven** ([Download Maven](https://maven.apache.org/download.cgi))
- **Git** ([Download Git](https://git-scm.com/downloads))

### **Check Installed Versions**
Run the following commands to verify that everything is correctly installed:

```sh
java -version
mvn -version
git --version
allure --version
```

If any of these return an error, install or configure them before proceeding.

---

## **2. Clone the Repository**
Navigate to the directory where you want to store the project and run:

```sh
git clone https://github.com/sbabet-qualiphilos/qa-api-testing.git
cd qa-api-testing
```

---

## **3. Configure Dependencies**
Run the following command to download and install all project dependencies:

```sh
mvn clean install
```

This will ensure all required dependencies are installed in your local environment.

---

## **4. Run the Tests**
Execute the following command to run all tests:

```sh
mvn clean test
```

If everything is configured correctly, the test execution should start.

---

## **5. Generate Allure Report**
After running the tests, generate the Allure report by executing:

```sh
mvn allure:serve
```

Alternatively, you can generate and open it manually:

```sh
allure generate target/allure-results --clean -o target/allure-report
allure open target/allure-report
```

This will open the test execution report in a web browser.

---

## **6. Additional Commands**
- **Run tests with specific tags:**
  ```sh
  mvn clean test -Dcucumber.filter.tags="@/pokemon"
  ```
- **Enable debug logs:**
  ```sh
  mvn clean test -Dallure.logging.level=DEBUG
  ```

---

Congratulations! 🎉 You have successfully set up the QA API Testing project. 🚀
