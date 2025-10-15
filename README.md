# **Automation Testing Framework – AutomationExercise.com**

## **Abstract**

This project is developed as part of the *Software Testing Diploma* at **Edges for Training**.
It represents a comprehensive **Test Automation Framework** that automates multiple real-world web application scenarios on [AutomationExercise.com](https://automationexercise.com/).
The project demonstrates advanced capabilities in **functional testing**, **test design**, and **test execution** using **Selenium WebDriver**, **TestNG**, and **Java**, integrated within a **Maven** structure.

The main goal of the project is to design a maintainable, modular, and reusable automation framework that reflects professional software testing practices.

---

## **Objectives**

* Implement a structured automation framework following best practices and design patterns.
* Validate major user journeys on the AutomationExercise platform through automated functional tests.
* Apply the **Page Object Model (POM)** for scalability and maintainability.
* Use **TestNG** for test orchestration, parameterization, and reporting.
* Apply assertions and verification steps for UI and functional validation.
* Document and report test results using **Allure Reports**.

---

## **Scope of the Project**

The project covers 26 automated test cases across various modules of AutomationExercise.com, including:

* User registration and login
* Product search and cart functionality
* Checkout process and payment validation
* Contact form submission
* Subscription flow
* Account deletion and logout validation

Each test follows a logical structure that reflects professional real-world testing workflows.

---

## **Tools and Technologies**

| Category                                 | Tool / Technology       |
| ---------------------------------------- | ----------------------- |
| Programming Language                     | Java                    |
| Automation Framework                     | Selenium WebDriver      |
| Testing Framework                        | TestNG                  |
| Build Tool                               | Maven                   |
| Design Pattern                           | Page Object Model (POM) |
| Reporting                                | Allure Report           |
| Test Management                          | TestNG XML Suite        |
| Version Control                          | Git & GitHub            |
| API Testing (Complementary Work)         | Postman                 |
| Database Validation (Complementary Work) | SQL Queries             |

---

## **Framework Design**

The project follows a modular and layered structure:

* **`src/main/java`** – contains utility classes, drivers, and base configurations.
* **`src/test/java`** – includes test cases organized by feature modules.
* **`pages/`** – implements Page Object Model for each functional page.
* **`testng.xml`** – defines test suite and test groups for execution control.
* **`pom.xml`** – manages project dependencies and build automation.

This architecture allows reusability, readability, and easier maintenance.

---

## **Test Design and Execution**

* Each test case is based on a specific user story or business scenario.
* Data-driven approaches are applied using parameters and reusable locators.
* Tests include both positive and negative scenarios to ensure robustness.
* Execution is controlled via TestNG annotations (`@BeforeClass`, `@Test`, `@AfterClass`).
* Reports are generated automatically after each test suite run.

---

## **Test Reporting**

The framework integrates **Allure Reports** for visual analytics of test results.
Reports include:

* Execution summary
* Step-by-step logs
* Screenshots for failed test cases
* Environment and framework metadata

This provides complete visibility into the execution and outcome of all test scenarios.

---

## **Results and Evaluation**

During the course evaluation, the project was graded as **Excellent** in requirements achievement, code quality, and design.
Instructor’s feedback included the following comment:

> “All extra requirements are fulfilled. Code logic and design are excellent.
> Point of improvement is to add more comments to the code.
> Overall: Excellent project. Thanks again for the effort, Master of Automation.”

---

## **Setup and Installation**

To clone and run the project locally:

```bash
# Clone this repository
git clone https://github.com/[your-username]/Automation-Testing-Framework-AutomationExercise.git

# Navigate to project directory
cd Automation-Testing-Framework-AutomationExercise

# Run tests using Maven
mvn clean test
```

To view the Allure report:

```bash
allure serve target/allure-results
```

---

## **Project Demonstration**

The project automates **26 test scenarios** covering critical user workflows.
Each scenario is designed to simulate real-world web interactions and validate end-to-end behavior.

You can explore the project source code and reports at:
**GitHub Repository:** [https://github.com/farida_waheed/Automation-Testing-Framework-AutomationExercise](https://github.com/farida_waheed/Automation-Testing-Framework-AutomationExercise)

---

## **Acknowledgment**

This project was completed under the guidance and evaluation of the **Edges for Training** team as part of the *Software Testing Diploma*.
Their structured curriculum and mentorship played a key role in developing this automation framework.

---

## **Author**

**Farida Waheed Abdelbary**
Software Testing Diploma Graduate – Edges for Training
