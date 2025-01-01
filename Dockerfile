FROM openjdk:17
COPY target/invoice-manager-0.0.1-SNAPSHOT.jar invoice-manager-app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/invoice-manager-app.jar"]