FROM openjdk
WORKDIR usr/src
EXPOSE 8761
ADD ./target/EurekaServer-0.0.1-SNAPSHOT.jar /usr/src/EurekaServer-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","EurekaServer-0.0.1-SNAPSHOT.jar"]