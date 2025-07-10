# Selenium Test Automation Project - Merchant1 Login

## 📄 Project Summary

This is a **Selenium-based automation framework** designed to test the login page of the **Merchant1** website.

The project automates four main test cases covering:
1. **Page Load Performance measurement**
2. **Invalid login validation**
3. **Forgot Password flow validation**
4. **Instagram redirect check** (conditionally)

This framework is built using **Java**, **TestNG**, **Selenium WebDriver**, **Apache POI** (for Excel reporting), and **Extent Reports** (for HTML reporting).  
It follows the **Page Object Model (POM)** design pattern for better code reusability and maintainability.

---

## 🌐 Application Under Test (AUT)

**URL:** [https://merchant1.uatdev.in/auth/login](https://merchant1.uatdev.in/auth/login)

---

## 📁 Project Structure

Merchant1Automation/
├── src/
│ ├── main/
│ │ └── java/
│ │ ├── base/ -> Base classes like BaseTest.java (WebDriver + ExtentReports setup)
│ │ ├── pages/ -> Page Object classes (LoginPage.java, ForgotPasswordPage.java)
│ │ └── utils/ -> Utility classes (ExcelUtils.java, ConfigReader.java, WaitUtils.java)
│ └── test/
│ └── java/
│ └── tests/ -> TestNG test classes (LoginTests.java)
├── reports/ -> Generated Extent HTML Reports and Screenshots
├── test-data/
│ └── TestData.xlsx -> Test Data and Excel Result storage
├── pom.xml
└── README.md


---

## ✅ Automated Test Cases

| Test Case ID | Test Case Name                  | Description                                                              |
|-------------|-----------------------------------|--------------------------------------------------------------------------|
| **TC_001**  | Page Load Performance            | Measure time taken for login elements to appear; fail if > 30 seconds.    |
| **TC_002**  | Invalid Login Validation         | Enter invalid credentials; check for toaster message.                    |
| **TC_003**  | Forgot Password Flow             | Validate Forgot Password link and error handling for invalid user.       |
| **TC_004**  | Instagram Redirect (Conditional) | Validate Instagram redirection if Forgot Password is not visible.        |

---

## 🛠 Tools & Technologies Used

| Tool/Library          | Version (Latest as of July 2025) |
|-----------------------|-----------------------------------|
| Java                  | 17                                |
| Selenium WebDriver     | 4.21.0                            |
| TestNG                | 7.9.0                             |
| Maven                 | 3.9.6                             |
| ExtentReports         | 5.1.1                             |
| Apache POI (Excel)     | 5.2.5                             |
| Chrome Browser        | Latest Stable Version             |

---

## 🖥 Pre-requisites

- ✅ Java JDK 17 installed
- ✅ Maven installed (`mvn -v` to verify)
- ✅ Chrome browser installed
- ✅ IntelliJ IDEA or Eclipse IDE installed

---

## 🚀 Setup Instructions

- 
1. **Clone the Project:**

   ```bash
   git clone https://github.com/your-repo/Merchant1Automation.git

## 🚀 Run project

- mvn clean install