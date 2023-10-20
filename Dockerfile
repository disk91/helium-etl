FROM openjdk:21
COPY ./build/libs/etl-0.0.1-SNAPSHOT.jar etl.jar
ENTRYPOINT ["java","-Xmx60G","-Xms60G","-jar","/etl.jar"]
