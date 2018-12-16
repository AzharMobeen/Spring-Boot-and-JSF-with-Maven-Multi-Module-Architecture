Hi,

This project is combination of Spring Boot and JSF with maven multi module architecture

I'm using postgreSQL (Schema will be created by it self) 
but url,username, password and port you have set in web\src\main\resources application.properties file

Web Module is main Module


After that:- 

Please execute run.bat (Windows)

Or simply execute bellow commands:
1) From inside project (1ot-assignment/)
	mvn clean install
2) Then move to web folder (1ot-assignment/web/)
	mvn spring-boot:run

3) Now open browser and hit this url: 
http://localhost:8080/index.xhtml


Thanks,
Azhar Mobeen
AzharMobeen@gmail.com