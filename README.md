# Spring Boot Cassandra 

 
 - Build docker in root directory
   ```
   docker build -f Dockerfile -t spring-cassandra-crud-example .
   ```
 - Show docker builded images
   ```
   docker images
   ```
 - Run docker image with exposed port 
   ```
   docker run -p 8084:8084 spring-cassandra-crud-example
   ```


