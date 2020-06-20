# angular-spring-mongo-docker
This project will show how to dockerize and run a full stack project(angular,spring,mongoDB).

##Instruction:

To run both backend and frontend by building the docker files.
>docker-compose.yml

To run both backend and frontend by fetching the docker images which are already built and uploaded to the dockerhub.
>docker-compose -f docker-compose-online.yml up 


-------------------------------------------------------------------------
##Details:
In this tutorial, we will show how to run a full stack project with angular,spring-boot,mongodb and docker.
First, we will create a simple angular project which has a button. If we click the button it will send request to the backend(spring-boot project) to add a new item and fetch all the items. After getting data it will show the fetched data in a list on the frontend.
We will create a docker file and dockerize the frontend and push the dockerized frontend image to our dockerhub account.

Next, we will create a simple spring-boot project which will have 2 APIs (getItems and addItem) to add a new item and to get items.All the data will be saved in a mongodb database.
 


There are two practical scenarios to run a
1. Run spring-boot application locally ; run mongodb locally
2. Run spring-boot application locally ; run mongodb inside the docker
3. Run spring-boot application inside the docker ; run mongodb inside the docker


[1]
If we run both locally then we dont need to worry about port and host of mongodb.
Just install and run mongo locally and run the spring app. Dont forget to add mongodb in the pom.xml.
Spring will connect to the mongodb via the default port of 27017 and also if you want to change the port then you can config this in the application.properties file  or using the command line during running the spring-boot application.


[2] 
If we want to run mongodb in the docker and want to run the spring-boot application locally then we need to run the mongo image exposing the container via a port to the localhost.
In this case the mongo container can talk/communication to the localhost from the container.
For example:
> docker run -p 27027:27017 --name mongodb_container mongo

It will fetch the mongo image and run a container at 27017 port in the docker.
Then it will expose the container in the localhost via 27027 port.
So, to connect to the mongoDB in such case we have to define the specific mongo port(which we exposed from the container to localhost) in the application.properties file like
spring.data.mongo.port=27027
Now, if we run the spring-boot application then it can connect to the mongoDB.

[3]
If we want to run the mongodb in the docker and also want to run the spring-boot app inside the docker then first run the docker image of mongo like 

> docker run -p 27027:27017 --name mongodb_container mongo

Then use the mongo container name as the mongo host 
For example, in the application.properties use
spring.data.mongo.host=mongodb_container; spring.data.mongo.port=27017
Also they should be in the same network to communicate to each other. We can do this during running the docker image or we can use docker compose file.
It is easy and good practice to used a docker-compose file in this case.

-----------------------------------------------------------------------------
