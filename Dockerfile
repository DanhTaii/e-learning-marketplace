FROM maven:3.8.1-openjdk-17-slim AS build
WORKDIR /e-learning
COPY . .
RUN mvn clean package -DskipTests

FROM tomcat:10.1.48-jdk17
RUN rm -rf /usr/local/tomcat/webapps/*
COPY --from=build /e-learning/target/*.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080

CMD ["catalina.sh", "run"]