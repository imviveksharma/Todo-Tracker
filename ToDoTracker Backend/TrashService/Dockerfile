FROM openjdk
WORKDIR usr/lib
ENV MONGO_DATABASE=trashDb
ENV MONGO_URL=mongodb://localhost:27017/trashDb

ADD  ./target/TrashService-0.0.1-SNAPSHOT.jar /usr/lib/TrashService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","TrashService-0.0.1-SNAPSHOT.jar"]