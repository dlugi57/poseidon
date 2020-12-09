# spring-boot
## Technical:

1. Framework: Spring Boot v2.0.4
2. Java 8
3. Thymeleaf
4. Bootstrap v.4.3.1


## Setup with Intellij IDE
1. Create project from Initializr: File > New > project > Spring Initializr
2. Add lib repository into pom.xml
3. Add folders
    - Source root: src/main/java
    - View: src/main/resources
    - Static: src/main/resource/static
4. Create database with name "poseidon" as configuration in application.properties
5. Run sql script to create table doc/data.sql

## Implement a Feature
1. Create mapping domain class and place in package com.nnk.springboot.domain
2. Create repository class and place in package com.web.poseidon.repositories
3. Create controller class and place in package com.web.poseidon.controllers
4. Create view files and place in src/main/resource/templates

## Write Unit Test
1. Create unit test and place in package com.nnk.springboot in folder test > java

## Security
1. Create user service to load user from  database and place in package com.nnk.springboot.services
2. Add configuration class and place in package com.nnk.springboot.config


## Prerequisites
### What things you need to install the software

1. Framework: Spring Boot v2.3.1 RELEASE
2. Java 1.8
3. Maven 3.6.2
4. Thymeleaf
5. Mysql 8.0 : need to create a MySQL database "poseidon" on localhost.


## Installing

1. Run the sql command data.sql to create tables (available in *"/doc"*)

2. Run the sql command data.sql to populate data (available in *"/src/main/resources"*)

3. User and password to access to the DB 
     
    -    Use application.properties to change :
        
            - **spring.datasource.username=poseidon**
            - **spring.datasource.password=poseidon**

## Running App
To start the application, execute :

java -jar **poseiden-0.0.1-SNAPSHOT.jar**

### Build Production

For Spring : Use application.properties (update jdbc connection values)

spring.jpa.hibernate.ddl-auto=``update`` Set value to none if the dll is already loaded 

## Profiles and configuration 

There are two profiles already created to access to the application 

  - **ADMIN** with user name : ``test``, and password: ``test``  
  - **USER** with user name : ``user``, and password: ``user``

## Testing

For test there is data source in data.sql (available in *"/test/resources"*)

mvn clean install mvn clean verify (generate tests and test report) mvn site (generate reportings)