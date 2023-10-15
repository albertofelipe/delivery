## API REST of a delivery

### About the project

- I first thought of this project as a delivery application developed to fix the concepts related to a REST API, from diagram analysis to the application of business and programming rules. But after gaining more knowledge, I decided to apply these to this project.

- The application consists of implementing a complete domain model of a Delivery.

### Conceptual model

![database-delivery](https://github.com/albertofelipe/delivery/assets/96255866/951a291e-fd80-4046-af6c-166e097f8b5b)

## Technologies used

- Java
- Spring Boot
- JPA / Hibernate
- MySQL
- Docker - Docker Compose
- Maven
- FlyWay
- Postman
- Mockito and Asserj

## Some patterns used

- DTO
- Migration
- API REST
- Exception Handler
- Unit Tests and Integration Tests
- Use of containers
- Dependency Injection
- Layered application (Repository, Service and Controller)

## How to execute

### Locally
- Clone git repository
- Access the project package
- Build the project:
```
./mvnw clean package
```
- Execute:
```
java -jar delivery/target/delivery-0.0.1-SNAPSHOT.jar
```

The API can be accessed at localhost:8080.
### With Docker

- Clone git repository
- Access the project package
- Build the project:
```
./mvnw clean package
```
- Up docker-compose file:
```
docker-compose up
```
The API can be accessed at localhost:8080.

### API Endpoints
Just a few endpoints for the application to be tested.

To make the HTTP requests below, the PostMan tool was used.

#### CLIENTS
- POST /clients
```
http://localhost:8080/clients
```

```
{
    "name": "Person1",
    "email": "person1@gmail.com",
    "phone": "9999999"
}
```
- GET /clients/{clientId} or /clients

```
http://localhost:8080/clients/1
```

- DELETE /clients/{clientId}


- UPDATE /clients/{clientId}

```
http://localhost:8080/clients/1
```
```
{
    "name": "Person2",
    "email": "person2@gmail.com",
    "phone": "9999998"
}
```

#### Deliveries

- POST /deliveries
```
http://localhost:8080/deliveries
```

```
{
    "client":{
        "id":1,
        "name":"Person1"
        },
    "receiver":{
        "name":"Person3",
        "street":"Dom Pedro",
        "number":"1234",
        "neighborhood":"Cupuaçu",
        "complement":"School"
        },
        "tax":1
}
```
- GET /deliveries/{deliveryId} or /deliveries

```
http://localhost:8080/deliveries/1
```
- FINALIZE PUT /deliveries/{deliveryId}/finalization

```
http://localhost:8080/deliveries/1/finalization
```


### Author

Alberto Felipe Monteiro Sena

[![Linkedin](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/alberto-sena-4351a4227/)
[![Gmail](https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white "Gmail")](mailto:felipe0032sena@gmail.com)
