# Spring Boot and JSF with Maven Multi Module Architecture

This project is combination of **Spring Boot** and **JSF** with **maven multi module** architecture, In which I'm using **Spring Schedular** (in one module) to run scheduled task to consume **Weather rest api** with **JAXB** and save data into PostgreSQL database. In frontend module I'm fetching that data from **PostgreSQL database** to display and some other functionality. For Backend I'm using Spring-Data-JPA and Spring-JPA-Repositories.

I'm using postgreSQL (Schema will be created by it self) but url,username, password and port you have set in web\src\main\resources application.properties file

Web Module is main Module

After that:-

Please execute run.bat (Windows)

Or simply execute bellow commands:

From inside project mvn clean install

Then move to web folder mvn spring-boot:run

Now open browser and hit this url: 
http://localhost:8080/index.xhtml

Thanks, 
Azhar Mobeen
AzharMobeen@gmail.com
