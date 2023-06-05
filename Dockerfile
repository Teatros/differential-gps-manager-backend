FROM openjdk:8-jdk-alpine
ADD differential-gps-manager-backend.jar /opt

ENTRYPOINT ["java","-jar","/opt/differential-gps-manager-backend.jar"]
