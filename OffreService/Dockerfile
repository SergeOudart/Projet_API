FROM eclipse-temurin:17-jdk-jammy
VOLUME /tmp
ADD target/OffreService-0.0.1-SNAPSHOT.jar OffreService-0.0.1-SNAPSHOT.jar
RUN bash -c 'touch /OffreService-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar", "OffreService-0.0.1-SNAPSHOT.jar"]