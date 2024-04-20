FROM openjdk:21
COPY ./build/libs/etl-0.0.1-SNAPSHOT.jar etl.jar
ENTRYPOINT ["java","-Xmx62G","-Xms62G","-Djdk.attach.allowAttachSelf=true","-Djol.magicFieldOffset=true","-jar","/etl.jar"]
