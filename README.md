#Tecko app

##Intro

Aplikaciu som sa snazil urobil co najjednoduchsie.

Obsahuje 2 services:
- DataProviderService
- BiznisAnalystService

V idealnom pripade by som to oddelil do 2 samostatnych aplikacii ktore spolu komunikuju ale z casovych dovodov som to uz neoddeloval.



##Docker
To run application in docker command bellow open browser and url localhost:1234

```
docker build --build-arg JAR_FILE=build/libs/*.jar -t tecko/api .
docker run -p 8080:1234 tecko/api
```

##Swagger
Run application and open
```
http://localhost:8080/v2/api-docs
```
