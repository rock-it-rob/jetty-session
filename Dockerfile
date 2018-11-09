FROM openjdk:8-alpine

ARG app_version

COPY ./target/jetty-app-${app_version}.jar /
RUN ln -s /jetty-app-${app_version}.jar /jetty-app.jar

CMD [ "java", "-jar", "/jetty-app.jar" ]