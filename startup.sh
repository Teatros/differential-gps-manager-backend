#!/bin/bash

git pull origin release;
mvn clean install;
cp -r ./target/differential-gps-manager-backend.jar .;
# 获取docker ps列表的第一行的id
docker_id=$(docker ps | grep differential-gps-manager-backend | awk 'NR==1{print $1}')
if [ ! -n "$docker_id" ]; then
 echo "Docker ID IS NULL"
else
 echo "Docker ID: $docker_id";
 docker stop $docker_id;
 docker rm $docker_id;
fi
docker image rm differential-gps-manager-backend:v1;
docker build -t differential-gps-manager-backend:v1 .;
docker-compose -f ./docker-compose.yml up -d;
