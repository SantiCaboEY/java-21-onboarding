FROM docker.io/openjdk:21-oracle
EXPOSE 8173
ARG PROFILE
ARG JAR=target/ms-personas-0.0.1.jar
ADD ${JAR} app.jar
RUN java -jar -Dspring.profiles.active="$PROFILE" app.jar