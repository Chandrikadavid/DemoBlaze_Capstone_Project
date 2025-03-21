# **DemoBlaze Capstone Project**

## **Project Overview**
This project automates the **Demoblaze** website using **Selenium WebDriver, TestNG, and Cucumber** with the **Page Object Model (POM)** and **Page Factory** design pattern. It includes data-driven testing with a **data.properties** file and generates **Extent Reports with screenshots** for test execution.

## **Technologies Used**
- **Java**
- **Selenium WebDriver**
- **TestNG**
- **Cucumber (BDD Framework)**
- **Maven**
- **Page Object Model (POM) with Page Factory**
- **Extent Reports for Test Execution Reports**
- **Cucumber Reports for BDD Execution Results**
- **Data Properties File for Data Handling**

## **Project Features**
### **Signup Page**
- Implemented using **Cucumber BDD**.
- Fetches data from **DataTable** in feature file.
- Generates **Cucumber Reports** for test execution.

### **Cart Page**
- Automated using **TestNG and Page Object Model (POM)**.

### **Contact Us, About Us, Category, and Login Pages**
- Implemented using **Page Factory (PF)**.
- Retrieves test data from the **data.properties** file.

### **Common Functionalities**
- **Base Class**: Manages browser initialization, teardown, and common utilities.
- **Extent Reports**: Generates detailed execution reports with screenshots.
- **Cucumber Reports**: Provides BDD execution results in an HTML format.
- **Test Execution**: Runs the project using **TestNG.xml** and **Maven test** command.

