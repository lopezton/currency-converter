# Currency Converter
This repository holds the code base for the Currency Converter project. View the project live at http://currency-converter.tonelope.com.

## Project Technologies
This project is written in Java/Spring with AngularJS on the front end. The project includes use of the following technologies:  

** Backend **
 * Maven 3
 * Java 7
 * Tomcat 7
 * Spring MVC
 * Hibernate Validation
 * Ehcache

** Frontend **
 * HTML/JS/CSS
 * jQuery
 * Bootstrap
 * AngularJS

** 3rd Party APIs**  
 * [OpenExchangeRates](http://openexchangerates.org) - For use in retrieving currency information and live exchange rate data.

## Running the Project Locally
This project is built using Maven. You may build the project in many different ways, but for the scope of this setup we will execute a manual build.  Open a terminal and cd into the project directory and execute a "mvn clean install".  

Make sure you have Tomcat 7 installed. When the build has completed, extract the "currency-converter-www.war" from the /target directory and drop it into a Tomcat 7 "webapps" directory as "ROOT.war" and start the server.  At this point you should be able to hit http://localhost:8080/ and view the application.  

## More About this Project
This project was written by Tony Lopez as an exploratory project to explore the use of AngularJS within a Java Spring application. The project was also introduced to add a completed project to the author's portfolio.
