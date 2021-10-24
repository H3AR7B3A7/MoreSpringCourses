#!/bin/bash
echo --- Setting up mongoDB and mongo-express docker containers ---
docker pull mongo:latest
docker run --name mongo -e MONGO_INITDB_ROOT_USERNAME=root -e MONGO_INITDB_ROOT_PASSWORD=pass -p 27017:27017 -d mongo:latest
docker pull mongo-express:latest
docker run --name mongo-express --link mongo:mongo -e ME_CONFIG_MONGODB_URL=mongodb://root:pass@mongo:27017/ -e ME_CONFIG_MONGODB_ADMINUSERNAME=root -e ME_CONFIG_MONGODB_ADMINPASSWORD=pass -p 8081:8081 -d mongo-express:latest
echo --- Done! ---
exec "$SHELL"