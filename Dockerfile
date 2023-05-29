FROM openjdk:17
MAINTAINER arthurvmlucena
COPY /build/libs/costmate-0.0.1-SNAPSHOT.jar costmate-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "costmate-0.0.1-SNAPSHOT.jar"]
