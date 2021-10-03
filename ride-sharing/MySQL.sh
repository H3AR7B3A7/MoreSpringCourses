#!/bin/bash
echo --- Building NEW MySQL container ---
docker pull mysql:latest
docker run --name MySQL -e MYSQL_ROOT_PASSWORD=pass -p 3306:3306 -d mysql:latest
docker pull phpmyadmin:latest
docker run --name phpMyAdmin -d -e PMA_HOST=MySQL --link MySQL -p 8081:80 phpmyadmin:latest
echo --- Done! ---
exec "$SHELL"