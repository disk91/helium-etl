FROM openjdk:21
COPY ./build/libs/etl-0.0.1-SNAPSHOT.jar etl.jar
ENTRYPOINT ["java","-Xmx116G","-Xms116G","-jar","/etl.jar"]
