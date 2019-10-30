# Microservice

To run the program, go to the main folder (where the pom.xml is located) with a terminal window and run:
`mvnw install` to download the dependencies.

 - In case you want to use the command `java -jar`:
  `java -jar target\microservice-0.0.1-SNAPSHOT.jar` to run it directly 
  
  
 - In case you want to use maven: `mvnw spring-boot:run`


You can use [Postman](https://www.getpostman.com/) in order to test the application. There is a Postman collection in the repository.
The program runs on port 8080.


I've assumed several things during development:
- That the creation of bank accounts is done by introducing a transaction to a non-existent account
- That the endpoints to requests that I have considered of type GET, even when they have a payload in JSON format, introduce the data by parameters through the query.
- I don't know why in getStatus the channel is optional and what happens if the request comes without that data or if it is erroneous, so I have made it return an INVALID status. 

I would have liked to do ATDD, but since I haven't done unit tests since university, I haven't had enough time these days to go over them again and add them to the Microservice.