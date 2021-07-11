# Course Availability Checker 
###### *Virginia Tech Classes only
Programmed using Java and uses the tool [Selenium](https://www.selenium.dev/downloads/).  Requires ChromeDriver to be installed (make sure it is the appropriate version for your version of chrome) which is found [here](https://chromedriver.chromium.org/downloads).

### There are three classes in this project, and you will have to configure the values of some variables to customize it.  

### ProjectRunner.java
* Replace the value of the string variable named *email* on line 10 with the email you would like to receive reminder notifications to.
* Then, input your class information as a String array in the format shown in the code (**line 20 onwards**).  The names should be taken from text displayed [in the VT time table of classes]( https://apps.es.vt.edu/ssb/HZSKVTSC.P_DispRequest). e.g. Discrete Math should be represented as `String[] class2 = { "MATH - Mathematics", "2534", "Fall 2021", "" }; ` (assuming you're not searching by CRN) then added to the ArrayList in the class.  
* There is an option to search just for one specific section, by CRN.  To do this, leave the values of the String array blank except for third and fourth entries.  e.g. `String[] class3 = { "", "", "Fall 2021", "86623" };`  In this example, this will search for the specific hybrid MATH1014 class, Precalculus, taught by J.Thompson at classroom EMPOR 100.
* Repeat this process for as many classes as you want to monitor.  Be careful, too many classes at once may lag your computer.
### ClassChecker.java
* Change the value on **line 56** to reflect the file location of your chromedriver.  Currently it reflects mine, it can be used as an example.  
### EmailSender.java
* Selenium cannot login to gmail accounts due to security issues, so YahooMail is used to set up the reminder functionality.  
* Replace the value of the field `username` with the username of the YahooMail account you want to use to remind yourself.  
* Replace the value of the field `password` with the password of the YahooMail account you are using.  
* Change the file location to your chromedriver on **line 33**.
## You're all set!  Get your courses and ace that semester!
