FROM openjdk:12-jdk-alpine
COPY ./build/libs/etl-0.0.1-SNAPSHOT.jar etl.jar
ENTRYPOINT ["java","-Xmx31G","-Xms31G","-jar","/etl.jar"]
