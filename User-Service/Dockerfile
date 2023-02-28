FROM eclipse-temurin:17-jdk-jammy
VOLUME /tmp
ADD target/User-Service-0.0.1-SNAPSHOT.jar User-Service-0.0.1-SNAPSHOT.jar
RUN bash -c 'touch /User-Service-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar", "User-Service-0.0.1-SNAPSHOT.jar"]