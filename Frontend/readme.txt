1.Install node,angular cli in your local PC

2. Get angular project 
ng new YOUR_APP_NAME
cd YOUR_APP_NAME

3. Change projects as you need and check locally


4. Create a docker file with the following content 

FROM node:slim as build
# install dependencies first, in a different location for easier app bind mounting for local development
# due to default /home permissions we have to create the dir with root and change perms
RUN mkdir /home/app && chown node:node /home/app
WORKDIR /home/app
RUN npm install -g @angular/cli
# the official node image provides an unprivileged user as a security best practice
# but we have to manually enable it. We put it here so npm installs dependencies as the same
# user who runs the app. 
# https://github.com/nodejs/docker-node/blob/master/docs/BestPractices.md#non-root-user
USER node
COPY package.json package-lock.json* ./
RUN npm install --no-optional && npm cache clean --force
ENV PATH /home/app/node_modules/.bin:$PATH
# copy in our source code last, as it changes the most
COPY --chown=node:node . .
CMD ng serve --host 0.0.0.0

5. Create a docker ignore file to avoid copying local node_modules into docker.

6. To check whether everything is working or not
Just run docker-compose up
and goto http://localhost:4242
Change something in the source file from the local (PC) and again goto http://localhost:4242
you will see the change in the browser which is shown from the docker container.


7. We can simply build by  
docker build -t your_image_name .
or we can tag the image while building as it is necessary to tag the image when u want to push it in the dockerhub
so we will use 
docker build -t your_dockerhub_username/your_image_name :version_name .
example: docker build -t imonbayazid/angular-docker-way3:v1 .

8. check docker images in the docker 
docker images

9. Run the docker image
docker run --name your_container_name-p 4200:4200 -d your_dockerhub_username/your_image_name :version_name

10. check the running container and it's port 
docker ps -a

11. check the localhost:port


12.Now push the docker image that we just built to the dockerhub.
 So, first login to your dockerhub account.
docker login -u your_dockerhub_username 
then just push the image 
docker push your_dockerhub_username/your_image_name :version_name

13. now to check wether your image works or not,pull the image from the dockerhub and run it.
First remove your the image that you run previously also remove the container.
docker rm -f YOUR_DOCKER_CONTAINER_ID
docker rmi -f YOUR_DOCKER_IMAGE_ID 

Now pull the image from the dockerhub
docker pull your_dockerhub_username/your_image_name :version_name

Check the image 
docker images

Lastly, run the image like before.
docker run --name your_container_name-p 4200:4200 -d your_dockerhub_username/your_image_name :version_name

check the running container and it's port 
docker ps -a

14.  check the localhost:4200

-------------------------------------------------
Shortcut Instruction

1. git clone this frontend folder
2. run docker-compose up
   It will run the frontend only 
   (to check goto localhost:4200)
   It will create a docker image named 'frontend_asmd_frontend_service' 
   which u need to push to the docker_hub to use the image with backend.