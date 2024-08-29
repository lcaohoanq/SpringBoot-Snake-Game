![image](https://github.com/user-attachments/assets/46cde35d-9aa7-4abb-ab31-3a06c6bb21b5)

- Before start the project, ensure run the below command to establish the database connection
```bash
docker-compose up
```

- To start the project, run the below command

```bash
mvn clean install
mvn spring-boot:run
```

- `data.sql` is auto generated by enter the command below
```bash
# Verify mysql installation path 

set path=C:\Program Files\MySQL\MySQL Server 8.0\bin

# mysqldump -h localhost -P <PORT> -u <DB_USER> -p mysql_starter > <FILE_NAME>.sql

mysqldump -h localhost -P 3308 -u root -p mysql_starter > data.sql

# Enter password: ************
```

- I have the extra profiles in the `application-xampp.properties` file. To run the project with the specific profile, run the below command
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=xampp
```
