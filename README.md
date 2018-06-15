---Lunch Receipt REST API Project: Instructions---
---Implemented by Igor Maltsev, Jun 15 2018---

The Project is based on a Spring BOOT framework
Two types of access implemented:
for the current date (today): **http://localhost:8080/lunch** 
for any selected date (example): **http://localhost:8080/lunch?day=2018-06-07** 

Technologies:
    - Java version: 1.8.0_162-b12
    - Maven version: Apache Maven 3.5.3
    - IntelliJ Idea

Tech Requirements:
JDK and Maven of the above specified versions (or similar).

The Application gets compiled and started with the Maven commands.

Commands:
To compile the Application in \lunch, run: '**mvn clean install**' (compiled in project)
To start the Application (with compilation), run the Spring BOOT server in ..\lunch in the Console:
'**mvn spring-boot:run**' or **..\lunch\target>java -jar lunch-0.0.1-SNAPSHOT.jar**.

Then go to **http://localhost:8080/lunch** for simple check of requests.
Or use the below GET requests to test:
http://localhost:8080/lunch
http://localhost:8080/lunch?day=2018-06-07

Developer Notes:
- As specified above, the way the Project is implemented, the receipts for the current date
are taken by default - with the '/lunch' endpoint. The other option - 
to use a customised date - is also implemented (as exemplified above '/lunch?day=2018-06-07' )
- with the endpoint based on optional 'day' parameter.

- The Domain Object Model is implemented for the two directions:
to deserialize data from the *.json files  and
to serialize data to be sent via the REST API.
Please note: for this Demo version, the .json structure in the .json files is equivalent 
to the REST API. 

- *.json files are located in the /resources folder and are accessible from the application 
via the classpath. Other possible alternatives for the location could be implemented, such as:
any folder on a computer or uploading via the URL. For this Demo version, the implemented 
variant is deemed the most appropriate considering the fact the files are not expected 
to be changed. 

- LunchDAO repository interface is implemented to read and deserialize the data from *.json 
files. Additionally, the implementation could include an option of taking data from 
other sources, e.g DataBase or other REST API. However, such a request is not part of the task
description, therefore, has not been included in this Demo Project. 

- The Docker part of the task is configured. Debugging and testing have not been done, due to
the tech limitations of Windows 10 Home version on my Notebook. This would not be an 
issue on any OS suitable for the Docker (such as Linux or Win Pro versions, or latest Macs). 

- The implemented tests for Repository, Service and Controller are found in the 'test' 
folder. The tests are sufficient to check the basic functionality of the application.

- As an extra feature, the application returns errors in a case of an incorrect format 
with "day" parameter in the endpoint. Alternatively, in a case of an internal server errors, 
the application returns the empty collection. Technically, it is possible to return an error 
as a REST response, however, it is not part of the task requirements.

- Application is implemented using the synchronous request-response approach, as it allows 
for a more optimal implementation. The asynchronous approach using WebFlux is also quite 
possible, but is deemed excessive for the purpose of this Demo project.
 

