


Instructions:
1. Create a spring project
2. Add all your required changes and files
3. Build the spring project
>mvn clean package && java -jar target/app.jar
Package will compile your code and also package it. For example, if your pom says the project is a jar, it will create a jar for you when you package it and put it somewhere in the target directory (by default).

4. Create a docker file with 
FROM openjdk:8
VOLUME /temp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT  java -jar app.jar

Basically it will take jar file(which we got by building the project in step 3)
and copy to the container finally then run the jar file at 8080 port.



5.Create a docker compose with 

version: '3'
services:
  #************Mongo DB***************
  database_mongo:
    container_name: mongo_container
    image: mongo:latest
    environment:
      - MONGO_INITDB_ROOT_DATABASE=asmd_mongo_db
    volumes:
      - ./mongodb_data_container:/data/db
      #means you want to set data on container persist on your local folder named mongo-volume . /data/db/ is a folder that already created inside the mongo container.
    ports:
      - 27017:27017
    networks:
      - asmd_net
  #************Backend***************
  backend_spring:
    container_name: spring_container
    build:
      context: .
      dockerfile: Dockerfile
    ports:
      - 8080:8080
    depends_on:
      - database_mongo
    networks:
      - asmd_net
      
networks:
  asmd_net:
    driver: bridge

It will run all required docker and container.
Goto localhost:8080 and check the backend


Additionally you can push the docker images to your docker hub.

6. If everything works well then remove container and image
>docker rm -f CONTAINER_ID
>docker rmi -f IMAGE_ID

7. Now build the docker file with proper tagging to push it in the docker_hub. 
>docker build -t YOUR_DOCKERHUB_USERNAME/YOUR_DOCKER_IMG_NAME:IMG_VERSION .

8. Login to user docker account
>docker login -u your_dockerhub_username 

9. Push the newly built docker image to your dockerhub account
>docker push YOUR_DOCKERHUB_USERNAME/YOUR_DOCKER_IMG_NAME:IMG_VERSION
