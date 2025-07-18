Selenium Automation Framework
=============================  

This is a modular Selenium Automation Framework built with Java, Cucumber, and JUnit.  
It supports reusable utilities, try-catch with logging, and CSV-driven data input.

---------------------------------------  
Tools
---------------------------------------  
- Java 11+
- Selenium WebDriver
- JUnit
- Cucumber (BDD)
- JavaFaker
- WebDriverManager
- Chrome DevTools (for ad blocking)

---------------------------------------  
Key Features
---------------------------------------  
- Modular Utility Classes (Element, Alert, Frame, Shadow, Window, Validation)
- Logging with try-catch and PrintWriter (per test session)
- CSV Data Input (supporting dynamic test data)
- Auto log generation inside /output/
- Parallel test execution ready (ThreadLocal WebDriver)
- Chrome DevTools Ad-blocker integrated

---------------------------------------  
Project Structure Example
---------------------------------------  

src/  
└── test/  
├── java/  
│   ├── hooks/  
│   ├── runners/  
│   ├── setting/  
│   ├── test/      (Step Definitions)  
│   └── utils/     (Utility Classes)  
└── resources/  
├── data/  
│   ├── input/   (CSV Files)  
│   └── output/  (Generated Logs)  
└── feature/     (Cucumber Features)

---------------------------------------  
How to Run
---------------------------------------  

- Open the testRunner.java
- Right click > Run
- Or use Maven CLI:  
  mvn test

---------------------------------------  
CSV Input Example
---------------------------------------  

first_name,last_name,email,country_code,phone_number,...  
John,Doe,john@example.com,+62,8123456789,...

In code:  
CSVReader reader = new CSVReader(new FileReader("src/test/resources/data/input/sample_data.csv"));

---------------------------------------  
Log Output Example
---------------------------------------  

[INFO] Filled first name: John  
[ASSERT PASSED] Expected: "Success" | Actual: "Success"

Generated files:
- output/log_YYYYMMDD_HHMMSS.txt
- output/csv/log_YYYYMMDD_HHMMSS.txt  