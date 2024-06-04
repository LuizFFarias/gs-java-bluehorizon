FROM openjdk:17-jdk-alpine
RUN mkdir /BlueHorizon-GS
WORKDIR /BlueHorizon-GS
COPY target/*.jar /BlueHorizon-GS/BlueHorizon.jar
CMD ["java","-jar","/BlueHorizon-GS/BlueHorizon.jar"]
