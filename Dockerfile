FROM openjdk:17
ADD /*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
