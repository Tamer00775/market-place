# Market Place Test Task for Halyk Fin Service
# About: 
A small test application from Halyk Fin Service. It was necessary to implement an application for an online store, which should have sorting, pagination, and filtering of products. There are also other APIs, such as category, and authorization.

## Techologies: 
- Java 11
- Spring Boot 2.58
- Spring Data Jpa, Validation, Spring Test, Converter
- Postgresql
- Lombok
- Docker, docker-compose
- JWT Authorization
- Swagger

## Commands:
`gradle build -x test`
`docker-compose up -d --build`

## Application URL's: 
Application run on 8080 ports; 
- /open-api/login - login to system.
- /open-api/register - registration to system.
- /swagger-ui.html - documentation of application.

## Default Test UserCredentials:  
login: admin 

password: Aa123456
