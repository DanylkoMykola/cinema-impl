# This cinema implementation with embedded h2 database

To run this project you need to do ```git clone``` and then you can open it in your IDE and run. H2 database will be created automatically and available on ```http://localhost:8080/h2-console```

## Swagger:
```http://localhost:8080/swagger-ui/```

### Endpoints
Movie:
```
GET http://localhost:8080/movie/{id}
GET http://localhost:8080/movie/param
POST http://localhost:8080/movie
PUT http://localhost:8080/movie
DELETE http://localhost:8080/movie/{id}
```
Oorder:
```
GET http://localhost:8080/order/{id}
GET http://localhost:8080/order/param
POST http://localhost:8080/order
PUT http://localhost:8080/order
DELETE http://localhost:8080/order/{id}
```
