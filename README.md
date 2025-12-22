# 🧪 SwagLabs Automation Project

An automated end-to-end testing framework for [SwagLabs](https://www.saucedemo.com) built using **Selenium**, **TestNG**, and the **Page Object Model (POM)** design pattern.  
This project demonstrates scalable and maintainable test automation practices including structured page modeling, logging, reporting, and data-driven testing.

---


## 📁 Project Structure

```text
SwagLabsAutomationProject/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── DriverFactory/
│   │   │   │   └── DriverFactory.java
│   │   │   ├── pages/
│   │   │   │   ├── P01_LoginPage.java
│   │   │   │   ├── P02_LandingPage.java
│   │   │   │   ├── P03_CartPage.java
│   │   │   │   ├── P04_CheckoutPage.java
│   │   │   │   ├── P05_OverviewPage.java
│   │   │   │   └── P06_FinishingOrderPage.java
│   │   │   └── utilities/
│   │   │       ├── DataUtils.java
│   │   │       ├── LogsUtils.java
│   │   │       └── Utility.java
│   │   └── resources/
│   │       ├── log4j2.properties
│   │       └── allure.properties
│
│   ├── test/
│   │   ├── java/
│   │   │   ├── TC01_LoginTest.java
│   │   │   ├── TC02_LandingTest.java
│   │   │   ├── TC03_CartTest.java
│   │   │   ├── TC04_CheckoutTest.java
│   │   │   ├── TC05_OverviewTest.java
│   │   │   └── TC06_FinishingOrderTest.java
│   │   └── resources/
│   │       ├── Logs/
│   │       └── Test-Data/
│   │           ├── ValidLogin.json
│   │           ├── CheckoutForm.json
│   │           └── environment.properties
│
├── TestRunner/
│   ├── LoginSuite.xml
│   ├── LandingSuite.xml
│   ├── CartSuite.xml
│   ├── CheckoutSuite.xml
│   ├── OverviewSuite.xml
│   ├── FinishingOrderSuite.xml
│   └── RegressionSuite.xml
│
├── test-output/
│   ├── allure-results/
│   └── screenshots/
│
└── pom.xml
```

---

## ⚙️ Technologies Used

- **Programming Language:** Java  
- **Automation Framework:** Selenium WebDriver  
- **Testing Framework:** TestNG  
- **Design Pattern:** Page Object Model (POM)  
- **Reporting:** Allure Report  
- **Logging:** Log4j2  
- **Data Handling:** JSON & Properties files  

---

## 🧱 Framework Components

### 🧩 Page Object Model (POM)
Each web page of SwagLabs is represented by a dedicated class that contains:
- Web element locators  
- Page actions (methods)  
- Validation methods  

**Pages Implemented:**
1. `P01_LoginPage`  
2. `P02_LandingPage`  
3. `P03_CartPage`  
4. `P04_CheckoutPage`  
5. `P05_OverviewPage`  
6. `P06_FinishingOrderPage`

---

### 🧰 Utilities
- **DataUtils:** Reads data from `.json` and `.properties` files  
- **LogsUtils:** Handles application logging via Log4j2  
- **Utility:** Common reusable functions (e.g., screenshots, file handling, waiting methods)

---

### 🧭 DriverFactory
Handles WebDriver initialization and browser management (Chrome, Firefox, Edge) using a **thread-safe setup**.

---

### 🧪 Test Cases
Each test case validates one module of the SwagLabs application:
- `TC01_LoginTest`  
- `TC02_LandingTest` *(uses cookies for login)*  
- `TC03_CartTest`  
- `TC04_CheckoutTest`  
- `TC05_OverviewTest`  
- `TC06_FinishingOrderTest`

---

### 🧾 Test Suites (XML)
Located in the **TestRunner** directory.  
Each suite file runs a specific set of tests, and `RegressionSuite.xml` executes the full end-to-end flow.

---

### 📊 Test Data
Located in `src/test/resources/Test-Data/`:
- `ValidLogin.json` → Valid login credentials  
- `CheckoutForm.json` → Checkout form details  
- `environment.properties` → Environment configurations (URL, browser type, etc.)

---

## 🔍 Key Features

✅ Cookie-based login for faster execution  
✅ Modular and reusable Page Object Model  
✅ Centralized WebDriver management  
✅ Data-driven test execution  
✅ Log4j2 integrated logging  
✅ Allure reporting with screenshots on failure  
✅ Regression suite for full flow validation  

---

## 🚀 How to Run the Project

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/AndrewHany22/SwagLabsAutomationProject.git
```
2️⃣Navigate to the Project Folder
```bash
cd SwagLabsAutomationProject
```
3️⃣ Run TestNG Suites
Example — to run the full regression suite:
```bash
mvn clean test -DsuiteXmlFile=TestRunner/RegressionSuite.xml
```
4️⃣ Generate Allure Report
```bash
allure serve test-output/allure-results
```

## 🧾 Reports & Logs

| Type          | Location                         |
|----------------|----------------------------------|
| **Allure Reports** | `test-output/allure-results/`     |
| **Logs**           | `src/test/resources/Logs/`        |
| **Screenshots**    | `test-output/screenshots/`        |

---

## 👨‍💻 Author

**Andrew Hany Wadie**  
🎓 *Mechatronics Engineer* | 💻 *Manual & Automation Tester*  
📍 *Egypt*  
🔗 [**LinkedIn Profile**](https://www.linkedin.com/in/andrew-hany-30570428b/)
