FROM docker.io/openjdk:21-oracle
EXPOSE 8173/tcp
EXPOSE 8173/udp
ARG PROFILE=dev
ARG JAR=target/ms-cuentas-0.0.1.jar
ADD ${JAR} app.jar
#RUN java -jar -Dspring.profiles.active="$PROFILE" app.jar
CMD ["java","-jar","-Dspring.profiles.active=$PROFILE","app.jar"]