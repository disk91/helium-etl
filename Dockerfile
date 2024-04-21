FROM openjdk:21
COPY ./build/libs/etl-0.0.1-SNAPSHOT.jar etl.jar
ENTRYPOINT ["java","-Xmx64G","-Xms64G","-jar","/etl.jar"]
