This automation project is trying to cover two different parts:
- Java + RestAssured for testing API
- Selenium grid + java for UI part

The second part is thought to run in Windows environment with Chrome.

### How to run:

In order to avoid dependency with chromedriver, I attached last binary file to resources folder, and it's automatically
set at the beginning.
It's possible to run them in command console with **_"maven clean test"_** or with JUnit selecting TestSuite.class

### **_Project overview_.**
I tried to follow a structure in several layers to create a small framework that allow us to have behavior coded and 
independents from every test
Also we have Page object for pages used in automation. 

In RestAssured test case I tried to follow a similar way, defining in a layer request, one up layer more we have behavior as well 
and finally tests

### **_Test plan_**
https://docs.google.com/spreadsheets/d/1-naulKyRIrtOxx54ciQaFSWl8p2lECGRSRrcAMn1PkQ/edit?usp=sharing


### **_UI Test cases automated_.**

1. ###### Internal call ######
   - Login with user (rbcarballo@gmail.com / 4c5682af)
   - Login with user (qa+test@aircall.io / f77398b7)
   - With qa+test  
   - Do a small check for navigation bottom menu as a sanity
   - Search in people tab for a teammate using part of email (rbcarballo)
   - Assure every single results with our user
   - If user is found create a call though call icon in call content 
   - Check call is create correctly
   - Check menu inside call
   - Hang up call
   

2. ###### Standard call ######
    - Login with userReceiver (rbcarballo@gmail.com / 4c5682af)
    - Login with userSender (qa+test@aircall.io/ f77398b7)
    - For receiver user get phone number from keyPad screen
    - From sender user go to Keypad screen
    - Change flag country according phone number
    - Fill with phone number
    - Press call button 
   - Check menu inside call, taking in account that in standard call Notes, Tag and Assign are shown and 
     Start recording call is enabled 

### **_API Test cases automated_.**

json files used for next test are attached to resources folder 
1. ###### API Get Part  ######
1. ###### Contacts API Create contact  ######
1. ###### Contacts API Create contact Blank values  ######
1. ###### Contacts API Create contact no mandatory fields  ######
1. ###### Contacts API Update contact  ######

### **_EXTRAS_.**

An auto system for generating a extendReport with every run, it's a html file with results of test cases and 
detailed steps 


   