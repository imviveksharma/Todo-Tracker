FROM openjdk
WORKDIR usr/lib
ENV MONGO_DATABASE=adminDb
ENV MONGO_URL=mongodb://localhost:27017/adminDb

ADD  ./target/AdminService-0.0.1-SNAPSHOT.jar /usr/lib/AdminService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","AdminService-0.0.1-SNAPSHOT.jar"]