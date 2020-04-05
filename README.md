The Template Service

Running Locally
To build and run with maven do the following:

```
mvn clean install
mvn spring-boot:run -pl app
```

It will automatically pick up newly compiled code 
when running outside of a jar. 
Restart is needed when changing dependencies.

After running you can access the service at
[http://localhost:8080/api/services](http://localhost:8080/api/version)

You can also view the API documentation, using Swagger
[http://localhost:8080/api/api-docs?url=/api/swagger.json](http://localhost:8080/api/api-docs?url=/api/swagger.json)
