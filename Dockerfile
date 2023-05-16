FROM openjdk:17-oracle
EXPOSE 8081
ADD target/Homework_Conditional_App-0.0.1-SNAPSHOT.jar myapp.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]