FROM eclipse-temurin:21-jre
COPY ./build/libs/etl-0.0.1-SNAPSHOT.jar etl.jar
ENTRYPOINT ["java","-Xmx116G","-Xms116G","-jar","/etl.jar"]
