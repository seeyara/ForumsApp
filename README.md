Application is written in java using Spring boot framework.
	Application does the following:

1. Reads data from a json file and stores in database. 
	To run the migration script run project with command line argument "migrate"
2. Exposes apis to fetch data from database. 
	Endpoints are : /users/{id}, /posts/{id}, articles{id}

EntryPoint for jar is org.assignment.main.ForumServer.java
Application is divided into controller, service and data layer. 
Service layer is responsible for fetching data from Redis and if not present fetch from database and write into Redis. Then again fetch from Redis and return response. 

Database structure is defined in create_tables.sql. Make sure to run on SQl server before running the script. 
	
Notes: 
To run java application, make sure you have jdk and jre installed. 
1. cd ForumService
2. mvn clean 
3. mvn install 
4. cd /target
5. java -jar ForumService.jar {migrate}(if migration script is to be run)

Endpoints are mentioned in src/main/resources/application.properties

