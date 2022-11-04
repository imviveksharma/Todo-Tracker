FROM openjdk
WORKDIR usr/lib
ENV MONGO_DATABASE=usertaskDb
ENV MONGO_URL=mongodb://localhost:27017/usertaskDb
ADD  ./target/ToDoService-0.0.1-SNAPSHOT.jar /usr/lib/ToDoService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","ToDoService-0.0.1-SNAPSHOT.jar"]