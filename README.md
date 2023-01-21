# API based on IFOOD 
<div align="center">
 <img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original-wordmark.svg" width="128" />
</div>


## Features

### Database tables
- [x] Kitchens
- [x] Restaurants
- [x] Products
- [x] Orders

## Requirements

- [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Spring](https://spring.io/)
- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

## Running the application

### Docker

```bash
docker-compose up
```

### Spring boot application

```bash
$ mvn spring-boot:run or ./mvnw spring-boot:run
$ mvn clean package -DskipTests or ./mvnw clean package -DskipTests 
$ java -jar JAR_FILE_NAME.jar
```



## Environment variables

- Application-dev.yml
```yml
spring:
  config:
    import: optional:file:.env[.properties]
  datasource:
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
    url: jdbc:mysql://${DB_HOST}:${DB_PORT}/${DB_NAME}?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    database: postgresql
    database-platform: org.hibernate.dialect.MySQL5InnoDBDialect
    show-sql: false
    hibernate:
      ddl-auto: none
server:
  error:
    include-message: always
  port: 8080
file:
  upload-dir: ${UPLOAD_DIR}



 ```


## Postman API testing


[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/1b1b1b1b1b1b1b1b1b1b)

**THE REQUEST MUST BE SENT IN JSON FORMAT**
- [x] [Get postman](https://www.postman.com/)
- [x] [Postman Collection](postman/Food-delivery.postman_collection.json)



## Swagger API documentation

**LOCALHOST**

[Swagger API documentation](http://localhost:8080/swagger-ui.html)
