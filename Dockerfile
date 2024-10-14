FROM openjdk:17-jdk

ADD build/libs/microservicocadastrarclient-0.0.1-SNAPSHOT.jar /microservicocadastrarclient.jar

ENV SPRING_PROFILES_ACTIVE=default

ENTRYPOINT ["java", "-jar", "/microservicocadastrarclient.jar"]