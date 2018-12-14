@echo off
call mvn clean install
call cd web
call mvn spring-boot:run