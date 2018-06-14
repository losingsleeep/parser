# HTTP access log file parser


## Stack
- Java 8
- Spring Boot , Hibernate , MySQL
- Maven

## Database
No need to run the schema manually. You should only introduce an existing database to the application in `application.yml` file, and the tables are created automatically on application startup.
But if you want to create the schema manually, you can use `schema.sql` under `db` folder.

### Database Tables
#### batch
Every time you run the application to parse and import data, a batch is created with a unique ID and all other entities are related to this batch.
#### request
Every successfully parsed line of access log file is inserted into this table as a record which is connected to its batch by `batch_id` column.
#### blocked
Every blocked IP address data is inserted into this table as a record which is connected to its batch by `batch_id` column.


## Config file
`application.yml` under `output` folder contains configuration and default values. You can change the parts you need and run the application.
### Database address
You can set the database connectivity via `spring.datasource` section:
```
spring:
  jpa:
    generate-ddl: true
  datasource:
    url: jdbc:mysql://localhost:3306/world?useSSL=false
    username: root
    password: root
```

### Other properties
In addition to database connectivity and threadpool settings, there are other easy to understand properties you may like to have a look.

## Execute
You can run the app by executing this command from project's root:
```sh
cd output
java -jar parser.jar --accesslog=..\access.log --startDate=2017-01-01.13:00:00 --duration=hourly --threshold=100
```

Or you can compile and run the source code directly by:
```
cd source
mvn spring-boot:run -Daccesslog=..\access.log -DstartDate=2017-01-01.13:00:00 -Dduration=hourly -Dthreshold=100
```
